import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	String message;
	Socket client_socket;
	Scanner client_scanner;
	
	public Client() throws IOException {
		client_socket = new Socket("127.0.0.1", 4444);
		client_scanner = new Scanner(client_socket.getInputStream());
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String new_message) {
		message = new_message;
	}
	
	public void close() {
		try {
			client_socket.close();
			client_scanner.close();
		}
		catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
		}
		catch (Exception e) {
			System.exit(1);
		}
	}

		public static void main(String args[]) throws UnknownHostException, ConnectException, IOException{
			try {
				Client c = new Client();
				Scanner scanner = new Scanner(System.in);
				System.out.println("Pass to server: ");
				c.setMessage(scanner.nextLine());
				
				PrintStream p_stream = new PrintStream(c.client_socket.getOutputStream());
				p_stream.println(c.getMessage());
				
				c.setMessage(c.client_scanner.nextLine());
				System.out.println(c.getMessage());
				
				scanner.close();
				c.close();
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
