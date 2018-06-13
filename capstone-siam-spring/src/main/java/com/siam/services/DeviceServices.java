package com.siam.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.siam.dao.DeviceDao;
import com.siam.model.Device;
import com.siam.model.Message;

@Service
public class DeviceServices {
	
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private MessageService messageService;
	private final Logger LOGGER = LoggerFactory.getLogger(DeviceServices.class);
	
	@PostConstruct
	public void init() throws IOException {
//		deviceDao.deleteAllDevices();
		deviceDao.updateConnected();
		scanSubnet();
		
	}
	
	public Device getDeviceByIp(String host) {
		return deviceDao.getDeviceByIp(host);
	}
	
	@Scheduled(cron="0 0/5 * * * ?")
	public void scanSubnet() throws IOException {
		String subnet = "192.168.88.";
		LOGGER.info("Scanning all ip addresses in " + subnet);
		Runtime rt = Runtime.getRuntime();
    	Process process = rt.exec("nmap -p 515,62078 " + subnet + "*");
    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	String ipAddr = null;
		String macAddr = null;
		String company = null;
		String type = null;
    	String line;
    	
    	while((line = br.readLine()) != null) {
    		StringTokenizer st = new StringTokenizer(line);
    		while(st.hasMoreTokens()) {
    			String token = st.nextToken();
    			if(token.equals("for")) {
    				ipAddr = st.nextToken().trim();
    			}
    			if(token.equals("515/tcp")) {
    				if(st.nextToken().equals("open"))
    					type = st.nextToken().trim();
    			}
    			if(token.equals("62078/tcp")) {
    				if(st.nextToken().equals("open"))
    					type = "iphone";
    			}
    			if(token.equals("Address:")) {
    				macAddr = st.nextToken().trim();
    				company = st.nextToken();
    				while(company.charAt(company.length() - 1) != ')')
    					company += " " + st.nextToken();
    				company = company.substring(1, company.length()-1);
    			}
    			if(ipAddr != null && macAddr != null && company != null) {
    				if(deviceDao.countDeviceByMac(macAddr) == 0) {
    					if(type == null) type = "PC";
    					deviceDao.insertDevice(macAddr, ipAddr, company, type, 1);
    					LOGGER.info(macAddr + " " + ipAddr + " " + company + " is inserted in DB");
    				} else {
    					// Check if there are same ip addresses!!
    					LOGGER.info("Machine with " + macAddr + " is already in DB");
    					LOGGER.info("Checking IP address of " + macAddr);
    					deviceDao.updateConnectedDevice(macAddr);
    					if(!deviceDao.getDeviceByMac(macAddr).getIpaddr().equals(ipAddr)) {
    						deviceDao.updateDevice(macAddr, ipAddr);
    						LOGGER.info(macAddr + "'s IP address has changed to " + ipAddr);
    					}
    				}
	    			ipAddr = null;
	    			macAddr = null;
	    			company = null;
	    			type = null;
    			}
    		}
    	}
    	messageService.results();
    	trigger();
	}
	
	public void trigger() throws IOException {
		String url = "http://35.237.107.174:8080/history";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
	}

}
