import java.net.*; 
import java.io.*; 
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MainServer extends Thread
{ 
  protected Socket clientSocket;
  static Map<String, List<String>> Registry;


  public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    Registry = new ConcurrentHashMap<String, List<String>>();
    try { 
         serverSocket = new ServerSocket(10008); 
         System.out.println ("Connection Socket Created");
         try { 
              while (true)
                 {
                  System.out.println ("Waiting for Connection");
                  new MainServer (serverSocket.accept()); 
                 }
             } 
         catch (IOException e) 
             { 
              System.err.println("Accept failed."); 
              System.exit(1); 
             } 
        } 
    catch (IOException e) 
        { 
         System.err.println("Could not listen on port: 10008."); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: 10008."); 
              System.exit(1); 
             } 
        }
   }

 private MainServer (Socket clientSoc)
   {
    clientSocket = clientSoc;
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");
    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine;
 	 List<String> location;
 	 int count = 0;
             while ((inputLine = in.readLine()) != null) 
             {
 	         String[] tokens = inputLine.split(" ");

 	         if(tokens[0].equals("Add"))
                 {
				location = Registry.get(tokens[1]);
 	            if ( location == null)
 	            {
 	                location = new ArrayList<String>();
 	            }
				location.add(tokens[2]);
				Registry.put(tokens[1], location);
				count = count + 1;
				out.println(count);
 	         }
             if(tokens[0].equals("Search"))
 	         {
             location = Registry.get(tokens[1]);
             out.println(location);	
	    //System.out.print(location);					
 	         }
 	    if(tokens[0].equals("Obtain"))  
                {
		location = Registry.get(tokens[1]);
	      //System.out.println(location);
                out.println(location.get(0));
              //System.out.println(location.get(0));
                }    

              if (inputLine.equals("Bye.")) 
                  break; 
             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         System.exit(1); 
        } 
    }
} 

