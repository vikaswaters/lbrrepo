package com.lbr.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;

public class WebServiceCall {
private String urlString;
	
	    public static String callWebServiceGET(String urlString ) {
	        //flickrURL = "http://api.flickr.com/services/rest/?method=flickr.test.echo&name=value&api_key=[yourflickrkey]";
	    	String resp = null;
	        try {
	            HttpClient client = new HttpClient();
	            GetMethod method = new GetMethod(urlString);
	            
	            // Send GET request
	            int statusCode = client.executeMethod(method);
	            
	            if (statusCode != HttpStatus.SC_OK) {
	            	System.err.println("Method failed: " + method.getStatusLine());
	            }
	            InputStream rstream = null;
	            
	            // Get the response body
	            rstream = method.getResponseBodyAsStream();
	            
	            // Process the response from Yahoo! Web Services
	            BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                resp = line;
	            }
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resp;
	    }

	    public static String callWebServicePOST(String urlString , Properties params) {
	        //flickrURL = "http://api.flickr.com/services/rest/?method=flickr.test.echo&name=value&api_key=[yourflickrkey]";
	        try {
	            HttpClient client = new HttpClient();

	            PostMethod method = new PostMethod(urlString);

	            // Add POST parameters
/*	            int numparams = params.size();
	            for (int i = 0; i < numparams; i++) {
	            	params.get
				}
				for (Iterator iterator = params.iterator(); iterator.hasNext();) {
					String type = (String) iterator.next();
					method.addParameter();
					
				}*/
	            method.addParameter("appid","YahooDemo");

	            method.addParameter("query","umbrella");

	            method.addParameter("results","10");


	            // Send POST request

	            int statusCode = client.executeMethod(method);

	            InputStream rstream = null;

	            
	            // Get the response body

	            rstream = method.getResponseBodyAsStream();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public static void callWebServiceWithProxy(String urlString ) {
	        //flickrURL = "http://api.flickr.com/services/rest/?method=flickr.test.echo&name=value&api_key=[yourflickrkey]";
	        try {
	            SocketAddress addr = new InetSocketAddress("[proxy]", 9090);
	            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
	            URL u = new URL(urlString);
	            HttpURLConnection uc = (HttpURLConnection) u.openConnection(proxy);
	            uc.setRequestProperty("Accept", "*/*");
	            uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
	            uc.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
	            uc.setRequestProperty("Keep-Alive", "300");
	            uc.setRequestProperty("ucection", "keep-alive");
	            String proxyUser = "[netUserId]";
	            String proxyPassword = "[netPassword]";
	            uc.setRequestProperty("Proxy-Authorization", "NTLM " + new sun.misc.BASE64Encoder().encode((proxyUser + ":" + proxyPassword).getBytes()));
	 
	            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            Document doc = docBuilder.parse(uc.getInputStream());
	            System.out.println(doc.getDocumentElement().getTagName());
	            System.out.println();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }	    
}
