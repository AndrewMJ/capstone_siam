package com.siam;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
    	Runtime rt = Runtime.getRuntime();
    	Process process = rt.exec("nmap -sP 192.168.88.*");
    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	String line;
    	String ipAddr = null;
		String macAddr = null;
		String company = null;
		
    	while((line = br.readLine()) != null) {
    		StringTokenizer st = new StringTokenizer(line);
    		while(st.hasMoreTokens()) {
    			String token = st.nextToken();
    			if(token.equals("for")) {
    				ipAddr = st.nextToken().trim();
    			}
    			if(token.equals("Address:")) {
    				macAddr = st.nextToken().trim();
    				company = st.nextToken();
    				while(company.charAt(company.length() - 1) != ')')
    					company += " " + st.nextToken();
    			}
    			if(ipAddr != null && macAddr != null && company != null) {
    				System.out.println(ipAddr + " " + macAddr + " " + company);
	    			ipAddr = null;
	    			macAddr = null;
	    			company = null;
    			}
    		}
    	}
    	
//    	InetAddress addr = InetAddress.getLocalHost();
//    	System.out.println(addr);
//    	System.out.println(addr.getHostName());
//    	System.out.println(addr.getHostAddress());
//        checkHosts("192.168.88");
    }

//    public static void checkHosts(String subnet){
//        int timeout=1000;
//        for (int i=1;i<255;i++){
//            String possibleIP = subnet + "." + i;
////            System.out.println(possibleIP);
//
//
//            try {
//                InetAddress inetAddress = InetAddress.getByName(possibleIP);
//                if (inetAddress.isReachable(100)){
//                    System.out.println(possibleIP + " is reachable");
//                }
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
}
