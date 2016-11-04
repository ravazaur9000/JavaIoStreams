package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Client().startClient();

    }

    private String username;


    public void startClient() throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress(Server.HOST, Server.PORT);
        SocketChannel client = SocketChannel.open(hostAddress);
        Scanner scanner = new Scanner(System.in);


        while(true){
            System.out.println("Type Client username: ");
            this.username=scanner.nextLine();
            ByteBuffer buf = ByteBuffer.wrap(this.username.getBytes());
            client.write(buf);


            ByteBuffer buffin = ByteBuffer.allocate(1024);
            client.read(buffin);
            //System.out.println(new String("true").length());
            //System.out.println(new String(buffin.array()).length());
            String s="true";
            if(new String(buffin.array()).trim().equals(s)){
                System.out.println("Username OK!");
                break;
            }
            System.out.println("Username already in use!");
            //System.out.println(new String(buffin.array()).trim());

        }


       /* if(buf.equals("false")){
            System.out.println("(User already picked) Type Client username: ");
            this.username=scanner.nextLine();
        }*/

        System.out.println("Client "+ this.username+ " started.");
        while (true) {

            String message = scanner.nextLine();

            ByteBuffer buffere = ByteBuffer.allocate(1024);
            buffere = ByteBuffer.wrap(message.getBytes());
            client.write(buffere);
            buffere.flip();



        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}