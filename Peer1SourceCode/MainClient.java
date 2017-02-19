import java.io.*;
import java.net.*;

public class MainClient {
    public static void main(String[] args) throws IOException {

        String serverHostname = new String ("mandar-VirtualBox");

        if (args.length > 0)
           serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
                serverHostname + " on port 10008 Indexing Server.");

        Socket indexSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            indexSocket = new Socket(serverHostname, 10008);
            out = new PrintWriter(indexSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        indexSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }

	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	String userInput;
	String Location;

        System.out.println ("Type Message (\"Bye.\" to quit)");
        System.out.println ("to add into registry--> Add Filename Peername");
        System.out.println (" to search --> Search Filename");
        System.out.println (" to download file --> Obtain Filename");
        System.out.println ("Case-sensitive inputs: press ctrl+c to exit");
	while ((userInput = stdIn.readLine()) != null) 
           {
		  out.println(userInput);
			String[] tokenc = userInput.split(" ");
			if(tokenc[0].equals("Add"))
			{
			System.out.println("Added entry"+ in.readLine());
			 
                        }
	                if(tokenc[0].equals("Search"))
			{
	                
			Location=in.readLine();
	                System.out.print("File is at:" + Location);
                       
			}
			if(tokenc[0].equals("Obtain"))
			{
			
			Location=in.readLine();
	                if(Location.equalsIgnoreCase("Peer1"))
	                {
	                	
	                	PeerClient pcobj = new PeerClient();
	                	String[] arr = {"mandar-VirtualBox"};
	                        pcobj.main(arr);
                                
	                }
	                if(Location.equalsIgnoreCase("Peer2"))
	                {
	                	
	                	PeerClient2 pcobj2 = new PeerClient2();
	                	String[] arr = {"mandar-VirtualBox"};
	                        pcobj2.main(arr);

	                }
	                if(Location.equalsIgnoreCase("Peer3"))
	                {
	                	
	                	Peer3Client pcobj = new Peer3Client();
	                	String[] arr = {"mandar-VirtualBox"};
	                        pcobj.main(arr);
	                }
		
			}
                        if (userInput.equals("Bye."))
			{
                        break;
			}

	   }

	out.close();
	in.close();
	stdIn.close();
	indexSocket.close();
    }
}


