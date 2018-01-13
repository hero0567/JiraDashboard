package com.home.dashboard.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sprint implements Comparable{

	private String name;
	private int start;
	private int end;
	private List<Dependency> dependencies = new ArrayList<Dependency>();
	private List<UserStory> userStories = new ArrayList<UserStory>();
	
	public Sprint() {
	}
	public Sprint(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<UserStory> getUserStories() {
		return userStories;
	}
	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}
	public void addUserStory(UserStory us){
		userStories.add(us);
	}
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
	public void addDependency(Dependency dep){
		dependencies.add(dep);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprint other = (Sprint) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sprint [name=" + name + ", userStories=" + userStories + "]";
	}
	@Override
	public int compareTo(Object o) {
		Sprint sp = (Sprint)o;
		
		int num = this.name.toLowerCase().compareTo(sp.getName().toLowerCase());
		return num;
	}
}
