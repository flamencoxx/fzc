package com.fzc.fzccommon.Algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Flamenco.xxx
 * @date 2021/9/22 9:50
 */
public class BioTesting {
    public static void main(String[] args) throws IOException {


        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("服务器已启动");


        while(true){
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            executor.execute(() -> handler(socket));
        }

    }
    public static void handler(Socket socket)  {
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();

            while(true){
                int i = inputStream.read(bytes);
                if (i != -1){
                    System.out.println(new String(bytes,0,i));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
