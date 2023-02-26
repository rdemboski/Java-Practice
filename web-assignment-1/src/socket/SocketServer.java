package socket;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//class that starts up a socket server
public class SocketServer 
{
    //static ServerSocket variable 
	private static ServerSocket server;
	
	//socket server port on which it will listen
	private static int port = 9000;

    public static void main(String args[]) throws
    IOException, ClassNotFoundException
    { 
     //create the socket server object
     //keeps listening until there is an 'end' message from client
     server = new ServerSocket(port); //(Socket.bind())

     //display waiting message
     System.out.println("Waiting for a client request...\n");
	 
     //create socket and wait for client connection (Socket.listen())
     Socket socket = server.accept(); //(Socket.accept())

     //process the http get request
     handleRequest(socket);
     
     //display server shut down
     System.out.println("\nServer shut down.\n"); 
     
     //close the ServerSocket object 
     server.close();
    }
    
    //method that takes a socket and input stream, and produces a message as output
    public static void handleRequest(Socket socket) throws IOException 
    {
        //Instantiates a new PrintWriter passing in the sockets output stream
        PrintWriter wtr = new PrintWriter(socket.getOutputStream());

        //Prints the request string to the output stream
        wtr.println("GET / HTTP/1.1");
        wtr.println("");
        wtr.flush();

        //Creates a BufferedReader that contains the server response
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String outStr;

        //Prints each line of the response 
        while((outStr = bufRead.readLine()) != null)
        {
            System.out.println(outStr);
        }

        //Closes out buffer and writer
        bufRead.close();
        wtr.close();
    }
}
