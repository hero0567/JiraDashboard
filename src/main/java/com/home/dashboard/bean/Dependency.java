package com.home.dashboard.bean;

public class Dependency
{

  private String name;
  private String component;
  private String status;
  private String type;
  private String summary;
  private UserStory us;

  public Dependency(String name, String status, String type)
  {
    super();
    this.name = name;
    this.status = status;
    this.type = type;
  }

  public Dependency(String name, String status, String type, String summary)
  {
    super();
    this.name = name;
    this.status = status;
    this.type = type;
    this.summary = summary;
  }

  public Dependency(String name, String status, String type, String summary, String component)
  {
    super();
    this.name = name;
    this.status = status;
    this.type = type;
    this.summary = summary;
    this.component = component;
  }

  public String getSummary()
  {
    return summary;
  }

  public void setSummary(String summary)
  {
    this.summary = summary;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getComponent()
  {
    return component;
  }

  public void setComponent(String component)
  {
    this.component = component;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public UserStory getUs()
  {
    return us;
  }

  public void setUs(UserStory us)
  {
    this.us = us;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Dependency other = (Dependency) obj;
    if (name == null)
    {
      if (other.name != null)
        return false;
    }
    else if (!name.equals(other.name))
      return false;
    if (status == null)
    {
      if (other.status != null)
        return false;
    }
    else if (!status.equals(other.status))
      return false;
    if (type == null)
    {
      if (other.type != null)
        return false;
    }
    else if (!type.equals(other.type))
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "Dependency [name=" + name + ", status=" + status + ", type="
        + type + "]";
  }

}
