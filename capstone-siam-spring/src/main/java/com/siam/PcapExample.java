package com.siam;

import org.icmp4j.IcmpPingUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;

public class PcapExample extends Thread {
	
	public void run() {
		try {
			sendPing(getName());
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void sendPing(String host) throws InterruptedException, IOException {
		final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();

		request.setHost (host);
		// delegate
		final IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);
		// log
		final String formattedResponse = IcmpPingUtil.formatResponse (response);
//		File file = new File("log.txt");
//		System.out.println(file.getAbsolutePath());
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
		System.out.println("successFlag: " + response.getSuccessFlag());
		System.out.println("address: " + host);
		System.out.println("message: " + response.getErrorMessage());
		System.out.println("size: " + response.getSize());
		System.out.println("rtt: " + response.getRtt());
		System.out.println("ttl: " + response.getTtl());
		// rest
//		writer.append("IP address: " + host);
//		writer.append("/message: " + response.getErrorMessage());
//		writer.append("/Date: " + new Date());
//		writer.newLine();
//		writer.close();
		
		Thread.sleep (1000);
	}
	
}
