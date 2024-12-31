/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bitmap.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author n3v3rm1nd
 */
public class Scanner {
    
    private List openPortList;
    private String ip;
    
    public Scanner() {
    }

    public Scanner(List openPortList, String ip) {
        this.openPortList = openPortList;
        this.ip = ip;
    }

    public List getOpenPortList() {
        return openPortList;
    }

    public void setOpenPortList(List openPortList) {
        this.openPortList = openPortList;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public void portScan(int threads, int timeout, int startRange, int finishRange) {
         ConcurrentLinkedQueue openPorts = new ConcurrentLinkedQueue<>();
         ExecutorService executorService = Executors.newFixedThreadPool(threads);
         AtomicInteger port = new AtomicInteger(startRange);
         while (port.get() < finishRange) {
             final int currentPort = port.getAndIncrement();
             executorService.submit(() -> {
                 try {
                     Socket socket = new Socket();
                     socket.connect(new InetSocketAddress(ip, currentPort), timeout);
                     socket.close();
                     openPorts.add(currentPort);
                     System.out.println(ip + " ,port open: " + currentPort);
                 }
                 catch (IOException e) {
                 }
 
             });
         }
         executorService.shutdown();
         try {
             executorService.awaitTermination(10, TimeUnit.MINUTES);
         }
         catch (InterruptedException e) {
             e.printStackTrace();
         }
 
         openPortList = new ArrayList<>();
         System.out.println("openPortsQueue: " + openPorts.size());
         while (!openPorts.isEmpty()) {
             openPortList.add(openPorts.poll());
         }
     }
}
