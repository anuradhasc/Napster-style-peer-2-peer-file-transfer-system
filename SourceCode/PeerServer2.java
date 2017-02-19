import java.net.*; 
import java.io.*; 


public class PeerServer2 extends Thread
{ 
 protected Socket clientSocket;

 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    
    try { 
         serverSocket = new ServerSocket(5052); 
         System.out.println ("Connection Socket Created");
         try { 
              while (true)
                 {
                  System.out.println ("Waiting for Connection: Port number 5052: peer");
                  new PeerServer2 (serverSocket.accept()); 
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
         System.err.println("Could not listen on port: 5052."); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: 5052."); 
              System.exit(1); 
             } 
        }
   }

 private PeerServer2 (Socket clientSoc)
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
	        	 System.out.println(strLine);
	        	  out.println (strLine);
	        }
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
