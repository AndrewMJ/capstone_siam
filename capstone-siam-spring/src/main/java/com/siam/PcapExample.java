package com.siam;

import org.icmp4j.IcmpPingUtil;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;

public class PcapExample {
	public static void sendPing(String host) throws InterruptedException {
		final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();

		request.setHost (host);
		// delegate
		final IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);

		// log
//		final String formattedResponse = IcmpPingUtil.formatResponse (response);
		System.out.println("successFlag: " + response.getSuccessFlag());
		System.out.println("address: " + response.getHost());
		System.out.println("message: " + response.getErrorMessage());
		System.out.println("size: " + response.getSize());
		System.out.println("rtt: " + response.getRtt());
		System.out.println("ttl: " + response.getTtl());
		// rest
		Thread.sleep (1000);
		
	}
	
	public static void main (final String[ ] args) throws Exception {
		// request
		String subnet = "192.168.88.";
		for(int i = 1; i <= 255; i++) {
			String host = subnet + i;
			System.out.println(host + " ====================================");
			sendPing(host);
			System.out.println("=============================================");
		}
		// repeat a few times
		
	}
}
