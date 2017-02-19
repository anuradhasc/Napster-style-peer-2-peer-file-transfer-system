import java.io.*;
import java.net.*;

public class Peer3Client {
    public static void main(String[] args) throws IOException {

        String serverHostname = new String ("mandar-VirtualBox");

        if (args.length > 0)
           serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
                serverHostname + " on port 5053: peer3.");

        Socket mainSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            mainSocket = new Socket(serverHostname, 5053);
            out = new PrintWriter(mainSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        mainSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println ("Type Message (\"Bye.\" to quit)");
        while ((userInput = stdIn.readLine()) != null) 
       {
         out.println(userInput);
         String[] tokenc = userInput.split(" ");
         String FileContent;
	 
	 int Lines = Integer.parseInt(in.readLine());
     //    System.out.print(Lines);
         String Check = in.readLine();
    //     System.out.println(Check);
         if(Check.equals("true"))
	 {
			if(tokenc[0].equals("Obtain"))
			{
			
			File file = new File(tokenc[1]);
			
		  FileWriter fw = new FileWriter(file.getAbsoluteFile());
	          BufferedWriter bw = new BufferedWriter(fw);
			 for (int i=0; i<Lines; i++)  
                                {
			         FileContent = in.readLine();
	//  System.out.println("Downloading lines "+i+ FileContent);
			    	 bw.write(FileContent);
				 bw.newLine();
				}
				
                        System.out.println("Done");
			bw.close();
			}
                        }
	   System.out.println("Do you still want to continue?(y/n)");
			 String ans = stdIn.readLine();
                          if(ans.equals("y"))
			   {
				MainClient cl = new MainClient();
				String[] arr = {"mandar-VirtualBox"};
			        cl.main(arr);
			   }
                          if(ans.equals("n"))
			  {
			    break;
			  }
			 if (userInput.equals("Bye."))
	                break;
			 }

		out.close();
		in.close();
		stdIn.close();
		mainSocket.close();
	    }
	}
