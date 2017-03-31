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
	private Scanner socket_scanner;
	private boolean closed;
	private boolean stopped;
	
	public Server() throws IOException {
		listener = new ServerSocket(4444); //getting an error because port number is taken on this computer
		server_socket = listener.accept(); 	//causes blocking so you want to make some kind of threading that
													// the server listens on, and another thread where is reads/writes
		socket_scanner = new Scanner(server_socket.getInputStream());
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
	
	public Socket getSocket() {
		return server_socket;
	}
	
	public ServerSocket getListener() {
		return listener;
	}
	
	public Scanner getSocketScan() {
		return socket_scanner;
	}
	
	public void close() throws IOException {
		  try {
			if (!closed)
			{
				closed = true;
			    server_socket.close();
			    listener.close();
			    socket_scanner.close();
			}
		  }
		  catch(Exception e) {
			  //catch exceptions
		  }
	}
	
	public static void main(String args[]) throws IOException {
		try {
			Server s = new Server();
			while (!s.getStopped()) {
				s.setMessage(s.socket_scanner.nextLine());
				
				PrintStream p_stream = new PrintStream(s.server_socket.getOutputStream());
				p_stream.println(s.getMessage());
		
			}
			s.close();
		}
		catch (IOException i) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.err.println("Connection refused: localhost.");
            System.exit(1);
		}
		catch (Exception e) {
			System.exit(1);
		}
	}
}
