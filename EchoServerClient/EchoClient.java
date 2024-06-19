package echoclient;
import java.net.*;
import java.io.*;
/**
 * Run this java file in the Command Line
 * @author Brandon Logan
 */
public class EchoClient {

    /**
     * @param args the command line arguments
     *        args[0] host server IP address
     *        args[1] is an integer port number
     */
    public static void main(String[] args) throws IOException {
        //Checks to see that there is a supplied server host and port number
        if (args.length != 2) {
            System.err.println(
                "Supply a host and port number");
            System.exit(1);
        }
        //Set first arg to host and second arg to port
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        //Use try catch for error handling
        try (
            //Create socket for user interaction to server
            Socket echo = new Socket(host, port);
            //Create PrintWriter and BufferedReader to handle client data
            PrintWriter out =
                new PrintWriter(echo.getOutputStream(), true);    
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echo.getInputStream()));
            //Create another BufferedReader for user input
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            //Create String for user input
            String userInput;
            //Run while loop to keep prompting client to talk to server
            //TIP: type "" to disconnect client from server
            while (!(userInput = stdIn.readLine()).equals("")) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine() + "\n");
            }
            //Close client
            echo.close();
        } 
        //Catch for unknown host
        catch (UnknownHostException e) {
            System.err.println("Unkwown Host " + host);
            System.exit(1);
        } 
        //Catch for no I/O connection
        catch (IOException e) {
            System.err.println("No I/O from server " +
                host);
            System.exit(1);
        } 
    }
    
}
