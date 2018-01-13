package com.home.dashboard.jira;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class JiraUtil {

	private static String SERVER_NAME = "https://nedsenseloft.atlassian.net";
	public static String USERNAME = "cn.manyun.liu";
	public static String PASSWORD = "lmy4563673";
	private static JerseyJiraRestClientFactory f;
	private static JiraRestClient jc;

	private final static JiraUtil instance = new JiraUtil();

	private JiraUtil() {
		try {
			f = new JerseyJiraRestClientFactory();
			jc = f.createWithBasicHttpAuthentication(new URI(SERVER_NAME),
					USERNAME, PASSWORD);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static JiraUtil getInstance() {
		return instance;
	}


	public SearchResult searchJql(String jql) {
		SearchResult r = jc.getSearchClient().searchJql(jql, null);
		return r;
	}

	public Iterable<BasicProject> getAllProjects() {
		NullProgressMonitor pm = new NullProgressMonitor();

		final Iterable<BasicProject> allProjects = jc.getProjectClient()
				.getAllProjects(pm);
		return allProjects;
	}
	
	public static void main(String[] args){
		JiraUtil instance =  JiraUtil.getInstance();
		Iterable<BasicProject> project = instance.getAllProjects();
		System.out.println(project);
	}
}