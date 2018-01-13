package com.home.dashboard.bean;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Epic {
	
	private String name;
	private String summary;
	private int start;
	private int end;
	private String status;
	private int total;
	private int finished;
	private int index;
	private Set<Sprint> sprint = new TreeSet<Sprint>();
		
	public Epic(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Set<Sprint> getSprint() {
		return sprint;
	}
	public void setSprint(Set<Sprint> sprint) {
		this.sprint = sprint;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void addSprint(Sprint sp){
		sprint.add(sp);
	}
	public Sprint getSprint(String name){
		for (Sprint sp : sprint){
			if (sp.getName().equals(name)){
				return sp;
			}
		}
		return null;
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
		Epic other = (Epic) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Epic [name=" + name + ", sprint=" + sprint + "]";
	}
}
