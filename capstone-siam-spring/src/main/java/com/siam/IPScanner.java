package com.siam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IPScanner {
	
	public static void main(String[] args) throws IOException {
		String subnet = "192.168.88.";
		Runtime rt = Runtime.getRuntime();
    	Process process = rt.exec("nmap -p 515,62078 " + subnet + "146");
    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	String ipAddr = null;
		String macAddr = null;
		String company = null;
		String type = null;
    	String line;
    	
    	while((line = br.readLine()) != null) {
//    		System.out.println(line);
    		StringTokenizer st = new StringTokenizer(line);
    		while(st.hasMoreTokens()) {
    			String token = st.nextToken();
    			if(token.equals("for")) {
    				ipAddr = st.nextToken().trim();
    				System.out.println(ipAddr);
    			}
    			if(token.equals("515/tcp")) {
    				if(st.nextToken().equals("open"))
    					type = st.nextToken().trim();
    					System.out.println(type);
    			}
    			if(token.equals("62078/tcp")) {
    				if(st.nextToken().equals("open"))
    					type = st.nextToken().trim();
    					System.out.println(type);
    			}
    			if(token.equals("Address:")) {
    				macAddr = st.nextToken().trim();
    				company = st.nextToken();
    				while(company.charAt(company.length() - 1) != ')')
    					company += " " + st.nextToken();
    				company = company.substring(1, company.length()-1);
    				System.out.println(macAddr);
    				System.out.println(company);
    			}
    			if(ipAddr != null && macAddr != null && company != null) {
	    			ipAddr = null;
	    			macAddr = null;
	    			company = null;
	    			type = null;
    			}
    		}
    	}
	}
}
