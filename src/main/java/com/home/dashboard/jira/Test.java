package com.home.dashboard.jira;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test {

	private static final String COMMA = ";";
	private static FileOutputStream fs = null;
	private static PrintStream p = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss"); 
	private static SimpleDateFormat out = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

	public static void createOutput() {

		try {
			String fname = "./activity";
			fs = new FileOutputStream(new File(fname));
			p = new PrintStream(fs);

			System.out.println(fname);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		createOutput();
		
		for (int i = 1006 ; i< 2030;i++){
			try {
				System.out.println("Try to get: HZZFAC-"+i);
				getActivityToFile("HZZFAC-"+i);
			} catch (Exception e) {
				System.out.println("Error: HZZFAC-"+i);
			}
		}
	}
	
	public static void getActivityToFile(String id) throws Exception{
		
		SAXReader reader = new SAXReader();
		String file = JiraApiUtil.getActivity(id);
		Document document = DocumentHelper.parseText(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements("entry");
		List<String> updatedList = new ArrayList<String>();
		for (Element child : childElements) {
			
			String title = child.elementText("title");
			int index = title.indexOf("changed the status");
			if (index > 0){
				String status = title.substring(index, index + 60);
				if (status.indexOf(" on") > 0){
					status = status.substring(0, status.indexOf(" on"));
				}
				StringBuilder builder = new StringBuilder();
				
				Date date = getTime(child.elementText("updated")); 
				
				builder.append(id).append(COMMA);
				builder.append(status).append(COMMA);
				builder.append(out.format(date)).append(COMMA);
				builder.append(child.element("author").elementText("name")).append(COMMA);							
				updatedList.add(builder.toString());
				
			}
		}
		if (updatedList.size() > 1){
			for (int i= 0;i<updatedList.size();i++){
				if (updatedList.get(i).indexOf("Ready for QA Customer") == -1){
					p.print(updatedList.get(i));
					break;
				}
			}
			p.println(updatedList.get(updatedList.size()-1));
		}
		
	}
	
	public static Date getTime(String dateString) throws ParseException{
		Date date = sdf.parse(dateString); 
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.HOUR_OF_DAY, 8);
		return ca.getTime();
	}

}
