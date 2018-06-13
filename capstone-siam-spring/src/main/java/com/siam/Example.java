package com.siam;

public class Example {
	
	  public static void main (final String[ ] args) throws Exception {
	        // request
	        String subnet = "192.168.88.";
	        for(int i = 1; i <= 255; i++) {
	        	PcapExample pcapExample = new PcapExample();
	            String host = subnet + i;
	            pcapExample.setName(host);
	            pcapExample.start();
	        }
	        // repeat a few times
	        
	    }

}
