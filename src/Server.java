import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//not sure where to place
public class Server {
	private String message;
	private ServerSocket listener;
	private Socket server_socket;
	private boolean closed;
	private boolean stopped;
	
	public Server() throws IOException {
		ServerSocket listener = new ServerSocket(5353); //getting an error because port number is taken on this computer
		Socket server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
													// the server listens on, and another thread where is reads/writes
		closed = false;
		stopped = false;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public boolean getClosed() {
		return closed;
	}
	
	public boolean getStopped() {
		return stopped;
	}
	
	public void close() throws IOException {
		  try {
			if (!closed)
			{
				closed = true;
			    server_socket.close();
			    listener.close();
			}
		  }
		  catch(Exception e) {
			  //catch exceptions
		  }
	}
	
	public static void main(String args[]) throws IOException {
		try {
			Server s = new Server();
			Scanner socket_scanner = new Scanner(s.server_socket.getInputStream());
			while (!s.getStopped()) {
				s.setMessage(socket_scanner.nextLine());
				
				PrintStream p_stream = new PrintStream(s.server_socket.getOutputStream());
				p_stream.println(s.getMessage());
		
			}
			socket_scanner.close();
			s.close();
		}
		catch (Exception e){
			// catch exceptions
		}
	}
}
