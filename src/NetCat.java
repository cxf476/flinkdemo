

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetCat {
	public static void main(String[] args) throws Exception {
				int port = 2003;
				connect("10.194.232.101", port);
		}

	private static void connect(String host, int port) throws Exception {
		final Socket socket = new Socket(host, port);
		System.err.println("Connecting to " + host + " port " + port);
		transferStreams(socket);
	}

	private static void transferStreams(Socket socket) throws IOException,
			InterruptedException {
		InputStream input1 = System.in;
		OutputStream output1 = socket.getOutputStream();
		
		PrintWriter writer = new PrintWriter(output1);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input1));
		String line;
		while ((line = reader.readLine()) != null) {
			writer.println(line);
			writer.flush();
			System.out.println(line);
		}
	}
}