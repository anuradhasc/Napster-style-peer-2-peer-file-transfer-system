import java.net.*; 
import java.io.*; 


public class Peer3Server extends Thread
{ 
 protected Socket clientSocket;



 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    
    try { 
         serverSocket = new ServerSocket(5053); 
         System.out.println ("Connection Socket Created: Port number 5053: peer3");
         try { 
              while (true)
                 {
                  System.out.println ("Waiting for Connection: port number 5053");
                  new Peer3Server (serverSocket.accept()); 
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
         System.err.println("Could not listen on port: 5053."); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: 5053."); 
              System.exit(1); 
             } 
        }
   }

 private Peer3Server (Socket clientSoc)
   {
    clientSocket = clientSoc;
    
    start();
   }
 public void run()
 {
	 
  System.out.println ("New Communication Thread Started");

  try
  { 
   PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
   BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream())); 
   String inputLine;
   while ((inputLine = in.readLine()) != null)
   {
	 Boolean Flag = false;
        String[] tokens = inputLine.split(" ");
        String strLine;
        int cnt = 0;
        
        if(tokens[0].equals("Obtain"))
        {
        	System.out.print(tokens[1]);
        	FileInputStream fstream = new FileInputStream(tokens[1]);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
     LineNumberReader reader  = new LineNumberReader(new FileReader(tokens[1]));
     while((reader.readLine()) != null)  
     {
	cnt++;
     }
     System.out.print(cnt);
     out.println(cnt);
     reader.close();
		Flag = true;
	        out.println(Flag);
		
	        while ((strLine = br.readLine()) != null)   {
	        //	 System.out.println(strLine);
	        	  out.println (strLine);
	        }
		System.out.println("File transfered from Peer3");
	        br.close();
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
