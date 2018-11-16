/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HieuabUDP;

import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;

/**
 *
 * @author admin
 */
public class Server { 
    public static void main(String []args) throws IOException 
    { 
        // tao ket noi 
        DatagramSocket serverSocket = new DatagramSocket(1234); 
        //thong bao server da san sang ket noi 
        System.out.println("Server is now already"); 
        //tao mang byte de chua du lieu gui len tu client 
        byte inFromClient1[]; 
        inFromClient1 = new byte[256]; 
        byte inFromClient2[]; 
        inFromClient2 = new byte[256]; 
        // lay kich thuoc mang 
        int leng1 = inFromClient1.length; 
        int leng2 = inFromClient2.length; 
        // tao goi de nhan du lieu gui len tu client 
        DatagramPacket fromClient1 = new DatagramPacket(inFromClient1, leng1); 
        DatagramPacket fromClient2 = new DatagramPacket(inFromClient2, leng2); 
        // nhan goi ve server 
        serverSocket.receive(fromClient1); 
        serverSocket.receive(fromClient2); 
        // tao bien data kieu string de lay du lieu trong goi ra 
        String data1,data2; 
        int a = 0,b = 0,hieu; 

        // lay du lieu vao bien data 
        data1 = (new String(fromClient1.getData(),0,inFromClient1.length)).trim(); 
        String [] dl1 = data1.split("_");
        if (dl1[1].equals("1")) 
            a = Integer.parseInt( dl1[0]);
        else
            b = Integer.parseInt( dl1[0]);
        
        data2 = (new String(fromClient2.getData(),0,inFromClient2.length)).trim();
        String [] dl2 = data2.split("_");
        
        if (dl2[1].equals("2")) 
            b = Integer.parseInt(dl2[0]);
        else
            a = Integer.parseInt(dl2[0]);
        // chuyen du lieu tu kieu String -> integer 
        // xu ly yeu cau 
        hieu = a - b; 
        System.out.println(hieu);
        //chuyen du lieu tu kieu int -> String va truyen vao bien data 
        data1 = String.valueOf(hieu); 
        // dong goi ket qua 
        byte outToClient[]; 
        outToClient = data1.getBytes(); 
        //lay kich thuoc mang 
        leng1 = outToClient.length; 
        //lay dia chi cua may khach, no nam luon trong goi ma da gui len server 
        InetAddress address = fromClient1.getAddress(); 
        // lay so port 
        int port = fromClient1.getPort(); 
        // tao goi de gui ve client 
        DatagramPacket toClient = new DatagramPacket(outToClient, leng1, address, port); 
        //gui goi ve client 
        serverSocket.send(toClient); 
        //dong socket 
        serverSocket.close(); 
    } 

} 
