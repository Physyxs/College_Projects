package echoserver;
import java.net.*;
import java.io.*;
/**
 * Run this java file in the Command Line
 * @author Brandon Logan
 */
public class EchoServer {

    /**
     * @param args the command line arguments 
     *        args[0] is an integer port number
     */
    public static void main(String[] args) throws IOException {
        //Checks to see that there is a supplied port number
        if (args.length != 1) {
            System.err.println("Supply a server port number");
            System.exit(1);
        }
        //Create a port number integer from args[0]
        int port = Integer.parseInt(args[0]);
        //Use try catch for error handling
        try (
            //Create a server socket and establish connection with a client
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();     
            //Create PrintWriter and BufferedReader to handle client data
            PrintWriter out =
                new PrintWriter(client.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        ) {
            //Create String for echoing back to client
            String inputLine;
            //Counter that counts the number of echoes on server
            int count = 0;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Number of Echoes: " + ++count);
            }
            server.close();
        } 
        //Catch for when connection is disrupted due to client termination
        catch (IOException e) {
            System.out.println("Connection to "+ port + " was disrupted "
                    + "or terminated");
            System.out.println(e.getMessage());
        }
    }
    
}
