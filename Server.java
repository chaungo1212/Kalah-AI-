import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//not sure where to place
public class Server {
	public static void main(String args[]) throws IOException{
	String message;
	ServerSocket listener = new ServerSocket(5353); //getting an error because port number is taken on this computer
	Socket server_socket = listener.accept(); //causes blocking so you want to make some kind of threading that
											 // the server listens on, and another thread where is reads/writes
	Scanner socket_scanner = new Scanner(server_socket.getInputStream());
	while (true) {
		message = socket_scanner.nextLine();
		
		PrintStream p_stream = new PrintStream(server_socket.getOutputStream());
		p_stream.println(message);

	}
	server_socket.close();
	listener.close();
	}
}
