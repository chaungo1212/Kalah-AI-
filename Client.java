import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

		public static void main(String args[]) throws UnknownHostException, IOException{
			String message;
			Scanner scanner = new Scanner(System.in);
			Socket client_socket = new Socket("127.0.0.1", 5353);
			Scanner client_scanner = new Scanner(client_socket.getInputStream());
			
			System.out.println("Pass to server: ");
			message = scanner.nextLine();
			
			PrintStream p_stream = new PrintStream(client_socket.getOutputStream());
			p_stream.println(message);
			
			message = client_scanner.nextLine();
			System.out.println(message);
			
		}
}
