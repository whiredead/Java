package com.example.controle.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class ChatWithServer extends Thread {
    private int clientNbre;
    private List<Communication> Clients = new ArrayList<>();
    private Map<Integer,String> Users = new HashMap<>();
    public static void main(String[] args) {
        new ChatWithServer().start();
    }
    @Override
    public void run(){
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("le sereveur est demarer ....");

            while(true){
                ++clientNbre;
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                Users.put(clientNbre,br.readLine());
                Communication NewCommunication = new Communication(s,clientNbre);
                Clients.add(NewCommunication);
                broadcastUserList();
                NewCommunication.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class Communication extends Thread{

        private Socket s;
        private int clientnbr ;
        Communication(Socket s,int clientNumber){
            this.s = s;
            this.clientnbr=clientNumber;
        }
        void Send(String UserMsg,int from,int destination ) throws IOException{
            for (Communication client : Clients) {
                if (client.clientnbr != from ) {
                    if(destination!=-1) {
                        if (client.clientnbr == destination) {
                            PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                            pw.println("chatprivee:" + "from = " + from + " message = " + UserMsg);
                        }
                    }
                    else{
                        PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                        pw.println("chat:" + "from = " + from + " message = " + UserMsg);
                    }
                }
            }
        }
        @Override
        public void run(){
            try {

                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os,true);
                String Ip= s.getRemoteSocketAddress().toString();
                System.out.println("user num = "+clientnbr+ " et ip = " +Ip);

                while (true){
                    String UserMsg = br.readLine();
                    if(UserMsg.contains("=>")){
                        String[] usermsg = UserMsg.split("=>");
                        if(usermsg.length==2) {
                            String msg = usermsg[1];
                            int dest =Integer.parseInt( usermsg[0]);
                            Send(msg, clientnbr, dest);
                        }
                    }
                    else {
                        Send(UserMsg,clientnbr,-1);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void broadcastUserList() throws IOException {
        StringBuilder userList = new StringBuilder();
        userList.append("users:");
        for (Map.Entry<Integer, String> entry : Users.entrySet()) {
            userList.append(entry.getKey()).append(" ").append(entry.getValue()).append("/");
        }

        for (Communication client : Clients) {
            PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
            pw.println(userList.toString());
        }
    }
}

