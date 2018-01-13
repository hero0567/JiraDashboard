package com.home.dashboard.jira;


import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class JiraApiUtil {


	public static String getJiraData(String jql) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic bGV2eS54aWE6TG15MTIzNDU2");
		headers.add("Accept","application/json;charset=utf-8");
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    
	    //String url = "https://jira.community.veritas.com/rest/api/2/search?jql=assignee=Shuai.Ma and workratio >= \"1\" AND workratio <= \"20\"";
	    String url = "https://jira.community.veritas.com/rest/api/2/search?jql=" + jql;
	    System.out.println(url);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

	    String result = response.getBody();

	    return result;
	}
	
	public static String getActivity(String id){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic Y24ubWFueXVuLmxpdTpsbXk0NTYzNjcz");
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    
	    //String url = "https://jira.community.veritas.com/rest/api/2/search?jql=assignee=Shuai.Ma and workratio >= \"1\" AND workratio <= \"20\"";
	    String url = "https://nedsenseloft.atlassian.net/plugins/servlet/streams?streams=issue-key+IS+" +id;
	    System.out.println(url);
	    
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(URLDecoder.decode(url), HttpMethod.GET, request, String.class);

	    String result = response.getBody();

	    return result;
	}
	
//	private static RestTemplate createRestTemplate(String username, String password) {
//
//	    UsernamePasswordCredentials cred = new UsernamePasswordCredentials(username, password);
//	    BasicCredentialsProvider cp = new BasicCredentialsProvider();
//	    cp.setCredentials(AuthScope.ANY, cred);
//	    DefaultHttpClient client = new DefaultHttpClient();
//	    client.setCredentialsProvider(cp);
//	    ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
//
//	    RestTemplate restTemplate = new RestTemplate(factory);
//	    // set the media types properly
//	    return restTemplate;
//	}
	
//	public static void main(String[] args){
//		String plainCreds = "cn.manyun.liu:lmy4563673";
//		byte[] plainCredsBytes = plainCreds.getBytes();
//		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
//		String base64Creds = new String(base64CredsBytes);
//		System.out.println(base64Creds);
//	}
	
	public static void main(String[] args){
		String s = JiraApiUtil.getActivity("HZZFAC-1111");
		System.out.println(s);
	}
}