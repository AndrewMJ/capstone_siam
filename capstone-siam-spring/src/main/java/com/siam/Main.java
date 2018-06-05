package com.siam;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnknownHostException{
    	InetAddress addr = InetAddress.getLocalHost();
    	System.out.println(addr);
    	System.out.println(addr.getHostName());
    	System.out.println(addr.getHostAddress());
//        checkHosts("192.168.88");
    }

    public static void checkHosts(String subnet){
        int timeout=1000;
        for (int i=1;i<255;i++){
            String possibleIP = subnet + "." + i;
//            System.out.println(possibleIP);


            try {
                InetAddress inetAddress = InetAddress.getByName(possibleIP);
                if (inetAddress.isReachable(100)){
                    System.out.println(possibleIP + " is reachable");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
