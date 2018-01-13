package com.home.dashboard.controller;

import com.atlassian.jira.rest.client.domain.BasicProject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.home.dashboard.bean.Dependency;
import com.home.dashboard.bean.Epic;
import com.home.dashboard.bean.Epics;
import com.home.dashboard.bean.Sprint;
import com.home.dashboard.bean.UserStory;
import com.home.dashboard.jira.JiraApiUtil;
import com.home.dashboard.jira.JiraUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/2")
public class JiraController
{

  private Logger logger = LoggerFactory.getLogger(getClass());

  @RequestMapping("/search")
  public Epics search(@RequestParam(value = "jql", defaultValue = "") String jql,
      @RequestParam(value = "maxResults", defaultValue = "50") int maxResults) throws JsonProcessingException, IOException
  {
    logger.info("jira jql:" + jql);
    String json = JiraApiUtil
        .getJiraData(jql + "&maxResults=" + maxResults);
    return convertToEpics(json);
  }

  @RequestMapping("/project")
  public Iterable<BasicProject> project()
  {
    return JiraUtil.getInstance().getAllProjects();
  }

  public Epics convertToEpics(String json) throws JsonProcessingException, IOException
  {

    Epics epics = new Epics();

    logger.info("JSON from jira:" + json);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(json);
    Iterator<JsonNode> issues = rootNode.path("issues").elements();

    while (issues.hasNext())
    {
      String usName = "";
      String epName = "";
      String spName = "";
      String status = "";
      String type = "";
      String team = "";
      List<Dependency> dependencies = null;
      JsonNode l = issues.next();
      usName = l.path("key").asText();
      JsonNode fields = l.path("fields");
      String summary = fields.path("summary").asText();

      JsonNode customfield_10001 = fields.path("customfield_10001");
      if (!(customfield_10001 instanceof NullNode))
      {
        epName = customfield_10001.asText();
      }

      // com.atlassian.greenhopper.service.sprint.Sprint@1ae2e797[rapidViewId=9209,state=CLOSED,name=DOTA sprint
      // 5.1,startDate=2016-01-21T01:00:23.339Z,endDate=2016-02-03T10:00:00.000Z,completeDate=2016-02-05T02:03:44.908Z,sequence=15069,id=15069]
      JsonNode issuetype = fields.path("issuetype");
      if (!(issuetype instanceof NullNode))
      {
        type = issuetype.path("name").asText();
        if (type == null || !type.equalsIgnoreCase("Story"))
        {
          continue;
        }
      }
      else
      {
        continue;
      }

      JsonNode customfield_10000 = fields.path("customfield_10000");
      if (!(customfield_10000 instanceof NullNode))
      {
        spName = customfield_10000.elements().next().asText();
      }

      JsonNode components = fields.path("components");
      if (!(components instanceof NullNode))
      {
        team = components.elements().next().path("name").asText();
      }

      String[] spNames = spName.split(",");
      for (String sp : spNames)
      {
        if (sp != null && sp.indexOf("name=") != -1)
        {
          spName = sp.replaceAll("name=", "");
          break;
        }
      }
      if (spName.length() == 0 || spName.indexOf("5.") == -1)
      {
        continue;
      }

      JsonNode statusFields = fields.path("status");
      if (!(statusFields instanceof NullNode))
      {
        status = statusFields.path("name").asText();
      }

      Epic epic = epics.getEpic(epName);
      if (epic == null)
      {
        epic = new Epic(epName);
        String epicsummary = "Others";
        if (epName != "")
        {
          epicsummary = getEpicSummaryByKey(epName);
        }
        epic.setSummary(epicsummary);
        epics.add(epic);
      }

      Sprint sp = epic.getSprint(spName);
      if (sp == null)
      {
        sp = new Sprint(spName);
        epic.addSprint(sp);
      }

      UserStory us = new UserStory(usName, status, summary, team);
      us.setDependencies(dependencies);
      sp.addUserStory(us);

      JsonNode issuelinks = fields.path("issuelinks");
      if (!(issuelinks instanceof NullNode))
      {
        dependencies = new ArrayList<Dependency>();
        Iterator<JsonNode> links = issuelinks.elements();
        while (links.hasNext())
        {
          String outName = "", inName = "";
          String outStatus = "", inStatus = "";
          String outSummary = "", inSummary = "";
          String outComponent = "", inComponent = "";
          String linktype = "";
          JsonNode link = links.next();
          linktype = link.path("type").path("name").asText();
          outStatus = link.path("outwardIssue").path("fields").path("status").path("name").asText();
          outName = link.path("outwardIssue").path("key").asText();
          outSummary = link.path("outwardIssue").path("fields").path("summary").asText();
          if (outName != "")
          {
            outComponent = getStoryComponentByKey(outName);
          }

          inStatus = link.path("inwardIssue").path("fields").path("status").path("name").asText();
          inName = link.path("inwardIssue").path("key").asText();
          inSummary = link.path("inwardIssue").path("fields").path("summary").asText();
          if (inName != "")
          {
            inComponent = getStoryComponentByKey(inName);
          }

          if (outName.length() > 0 && linktype.equalsIgnoreCase("Dependency"))
          {
            sp = epics.getOutSprint(spName);
            if (sp == null)
            {
              sp = new Sprint(spName);
              epics.addOutSprint(sp);
            }
            Dependency dependency = new Dependency(outName, outStatus, linktype, outSummary, outComponent);
            dependency.setUs(us);
            sp.addDependency(dependency);
          }
          if (inStatus.length() > 0 && linktype.equalsIgnoreCase("Dependency"))
          {
            sp = epics.getInSprint(spName);
            if (sp == null)
            {
              sp = new Sprint(spName);
              epics.addInSprint(sp);
            }
            Dependency dependency = new Dependency(inName, inStatus, linktype, inSummary, inComponent);
            dependency.setUs(us);
            sp.addDependency(dependency);
          }
        }
      }
    }
    addEpicAttribute(epics);
    logger.info("EPICS:" + epics);
    return epics;
  }

  public void addEpicAttribute(Epics epics)
  {
    for (Epic epic : epics.getEpics())
    {
      int total = 0;
      int finished = 0;
      List<String> spArray = new ArrayList<String>();
      for (Sprint sprint : epic.getSprint())
      {
        for (UserStory us : sprint.getUserStories())
        {
          total++;
          if (us.getStatus().equalsIgnoreCase("Closed") || us.getStatus().equalsIgnoreCase("Done"))
          {
            finished++;
          }

        }
        String spName = sprint.getName();
        if (spName.length() > 0)
        {
          String spNumber = spName.substring(spName.length() - 1);
          spArray.add(spNumber);
        }

      }
      epic.setTotal(total);
      epic.setFinished(finished);
      if (total == finished)
      {
        epic.setStatus("EpClosed");
      }
      else if (finished != 0)
      {
        epic.setStatus("EpProgress");
      }
      else
      {
        epic.setStatus("EpOpen");
      }

      spArray.sort(null);
      epic.setStart(Integer.valueOf(spArray.get(0)));
      epic.setEnd(Integer.valueOf(spArray.get(spArray.size() - 1)));
    }

  }

  public static String getEpicSummaryByKey(String key) throws JsonProcessingException, IOException
  {
    String summary = "";
    // logger.info("jira jql:" + jql);
    String json = JiraApiUtil
        .getJiraData("key=" + key);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(json);
    Iterator<JsonNode> issues = rootNode.path("issues").elements();

    while (issues.hasNext())
    {
      JsonNode l = issues.next();
      JsonNode fields = l.path("fields");
      summary = fields.path("summary").asText();
    }

    return summary;
  }

  public static String getStoryComponentByKey(String key) throws JsonProcessingException, IOException
  {
    String component = "";
    // logger.info("jira jql:" + jql);
    String json = JiraApiUtil
        .getJiraData("key=" + key);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(json);
    Iterator<JsonNode> issues = rootNode.path("issues").elements();

    while (issues.hasNext())
    {
      JsonNode l = issues.next();
      JsonNode fields = l.path("fields");
      JsonNode components = fields.path("components");
      if (!(components instanceof NullNode))
      {
        component = components.elements().next().path("name").asText();
      }
    }
    return component;
  }

  // public static void main(String[] args) throws JsonProcessingException, IOException
  // {
  // JiraController c = new JiraController();
  // String json =
  // JiraApiUtil.getJiraData("project='Appliance Solutions' and component='DOTA AllStars' and fixVersion = PI5&maxResults=500");
  // c.convertToEpics(json);
  // // String component = "";
  // // component = getStoryComponentByKey("APPSOL-23505");
  // // System.out.print(component);
  // }
}
