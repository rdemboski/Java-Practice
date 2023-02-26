package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException;

//class that attempts to send messages from local client to the server
public class SocketClient 
{
 public static void main(String[] args) throws
 UnknownHostException, IOException, ClassNotFoundException, InterruptedException
 {
  //get the localhost IP address 
  InetAddress host = InetAddress.getLocalHost();
  
  //initialize other variables
  Socket socket = null;
  ObjectOutputStream oos = null;
  ObjectInputStream ois = null; 

  //send 5 messages as a test
  for (int index = 0; index < 5; index++)
  {
    //establish socket connection to server 
	socket = new Socket(host.getHostName(), 9000); //(Socket.socket())

    //write to socket using ObjectOutputStream
    oos = new ObjectOutputStream(socket.getOutputStream());
    
    System.out.println("Sending request to Socket Server...");

    //if index is the last message
    if (index == 4)
	{
       //write the exit
	   oos.writeObject("end");
	}

    //otherwise its not the last
    else 
	{
       //write the message
	   oos.writeObject("message " + index);
	}

    //read the server response message
    ois = new ObjectInputStream(socket.getInputStream());
    
    String message = (String) ois.readObject();
    
    //display message
    System.out.println("Message: " + message); 
    
    //close resources 
    ois.close(); 
    oos.close();
    
    //wait before continuing to next index
    Thread.sleep(200);
  }
 }
}