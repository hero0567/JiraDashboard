package com.home.dashboard.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Epics {

	private Set<Epic> epics = new HashSet<Epic>();
	private Set<Sprint> depOutSps = new TreeSet<Sprint>();
	private Set<Sprint> depInSps= new TreeSet<Sprint>();

	public void add(Epic epic){
		epics.add(epic);
	}
	
	public Sprint getOutSprint(String name){
		for (Sprint sp : depOutSps){
			if (sp.getName().equals(name)){
				return sp;
			}
		}
		return null;
	}
	
	public Sprint getInSprint(String name){
		for (Sprint sp : depInSps){
			if (sp.getName().equals(name)){
				return sp;
			}
		}
		return null;
	}
	
	public void addOutSprint(Sprint sp){
		depOutSps.add(sp);
	}
	
	public void addInSprint(Sprint sp){
		depInSps.add(sp);
	}
		
	public Epic getEpic(String name){
		for (Epic ep : epics){
			if (ep.getName().equals(name)){
				return ep;
			}
		}
		return null;
	}

	public List<Epic> getEpics() {
		List<Epic> list = new ArrayList<Epic>();
		for (Epic epic : epics){
			list.add(epic);
			list.add(epic);
		}
		return list;
	}

	public Set<Sprint> getDepOutSps() {
		
		if (depOutSps.size() == 5){
			return depOutSps;
		}
		String name = null;
		boolean sp1 = false, sp2 = false, sp3 = false, sp4 = false, sp5 = false;
		for (Sprint sprint : depOutSps){
			if (name == null) name = sprint.getName();
			if (sprint.getName().indexOf(".1") > 0){
				sp1 = true;
			}
			if (sprint.getName().indexOf(".2") > 0){
				sp2 = true;			
			}
			if (sprint.getName().indexOf(".3") > 0){
				sp3 = true;
			}
			if (sprint.getName().indexOf(".4") > 0){
				sp4 = true;
			}
			if (sprint.getName().indexOf(".5") > 0){
				sp5 = true;
			}
		}
		
		if (name != null){
			if (!sp1){
				depOutSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.1")));
			}
			if (!sp2){
				depOutSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.2")));
			}
			if (!sp3){
				depOutSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.3")));
			}
			if (!sp4){
				depOutSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.4")));
			}
			if (!sp5){
				depOutSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.5")));
			}
		}
		
		return depOutSps;
	}
	
	public Set<Sprint> getDepInSps() {
		
		if (depInSps.size() == 5){
			return depInSps;
		}
		String name = null;
		boolean sp1 = false, sp2 = false, sp3 = false, sp4 = false, sp5 = false;
		for (Sprint sprint : depInSps){
			if (name == null) name = sprint.getName();
			if (sprint.getName().indexOf(".1") > 0){
				sp1 = true;
			}
			if (sprint.getName().indexOf(".2") > 0){
				sp2 = true;			
			}
			if (sprint.getName().indexOf(".3") > 0){
				sp3 = true;
			}
			if (sprint.getName().indexOf(".4") > 0){
				sp4 = true;
			}
			if (sprint.getName().indexOf(".5") > 0){
				sp5 = true;
			}
		}
		
		if (name != null){
			if (!sp1){
				depInSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.1")));
			}
			if (!sp2){
				depInSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.2")));
			}
			if (!sp3){
				depInSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.3")));
			}
			if (!sp4){
				depInSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.4")));
			}
			if (!sp5){
				depInSps.add(new Sprint(name.replaceAll("5.\\d{1}", "5.5")));
			}
		}
		
		return depInSps;
	}

	public void setDepInSps(Set<Sprint> depSps) {
		this.depInSps = depSps;
	}
	
	public void setDepOutSps(Set<Sprint> depSps) {
		this.depOutSps = depSps;
	}

	public void setEpics(Set<Epic> epics) {
		this.epics = epics;
	}

	@Override
	public String toString() {
		return "Epics [epics=" + epics + "]";
	}
}
