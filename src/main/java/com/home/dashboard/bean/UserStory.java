package com.home.dashboard.bean;

import java.util.ArrayList;
import java.util.List;

public class UserStory
{

  private String name;
  private String status;
  private String summary;
  private String component;
  private List<Dependency> dependencies = new ArrayList<Dependency>();

  public UserStory(String name, String status)
  {
    super();
    this.name = name;
    this.status = status;
  }

  public UserStory(String name, String status, String summary)
  {
    this.name = name;
    this.status = status;
    this.summary = summary;
  }

  public UserStory(String name, String status, String summary, String component)
  {
    this.name = name;
    this.status = status;
    this.summary = summary;
    this.component = component;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getComponent()
  {
    return component;
  }

  public void setComponent(String component)
  {
    this.component = component;
  }

  public List<Dependency> getDependencies()
  {
    return dependencies;
  }

  public void setDependencies(List<Dependency> dependencies)
  {
    this.dependencies = dependencies;
  }

  public String getSummary()
  {
    return summary;
  }

  public void setSummary(String summary)
  {
    this.summary = summary;
  }

  @Override
  public String toString()
  {
    return "UserStory [name=" + name + ", status=" + status + "]";
  }
}
