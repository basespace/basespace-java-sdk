package com.illumina.basespace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 
 * @author bking
 *
 */
class HttpServer implements Runnable
{
    private static Logger logger = Logger.getLogger(HttpServer.class.getPackage().getName());
    private List<AuthCodeListener>authListeners =  Collections.synchronizedList(new ArrayList<AuthCodeListener>());
    
    private boolean running = true;
    private int port = 7777;
    
    HttpServer(int listenerPort)
    {
        this.port = listenerPort;
    }
    
    public void run()
    {
        Socket socket = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            printServerSocketInfo(serverSocket);
            String authCode = null;
            while (running)
            {
                try
                {
                    socket = serverSocket.accept();
                    // Someone is calling this server
                    printSocketInfo(socket);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String m = reader.readLine();
                    if (m != null)
                    {
                        authCode = getAuthCode(m);

                        String responseText = authCode != null?
                                "Authorization has been sent to the SDK. You can close the browser."
                                :"There was an error receiving the auth code";
                        
                        // We have a real data connection
                        writer.write("HTTP/1.0 200 OK");
                        writer.newLine();
                        writer.write("Content-Type: text/html");
                        writer.newLine();
                        writer.newLine();
                        writer.write("<html><body><pre>");
                        writer.newLine();
                        writer.write(responseText);
                        writer.newLine();
                  
                        while ((m = reader.readLine()) != null)
                        {
                           if (m.length() == 0) break; // End of a GET call
                           writer.newLine();
                        }
                        writer.write("</pre></body></html>");
                        writer.newLine();
                        writer.flush();
                    }
                    if (authCode != null)
                    {
                        running = false;
                    }
                }
                catch(Throwable t)
                {
                    t.printStackTrace();
                    
                }
                finally
                {
                    if (writer !=  null)
                    {
                        try{writer.close();}catch(Throwable t){}
                    }
                    if (reader !=  null)
                    {
                        try{reader.close();}catch(Throwable t){}
                    }
                    if (socket !=  null)
                    {
                        try{socket.close();}catch(Throwable t){}
                    } 
                }
            }//While server running
            if (authCode != null)
            {
                fireAuthCodeEvent(new AuthCodeEvent(this,authCode));
            }
            logger.info("Exiting server thread");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            removeAllAuthCodeListeners();
        }
    }
    
    private static String getAuthCode(String params)
    {
        final String START = "GET /?";
        final String END = " HTTP/1.1";
        if ( params.indexOf(START) == -1 ||  params.indexOf(END) == -1)return null;
        params = params.substring(START.length(),params.length()-END.length());
        Map<String, String>map =  getQueryMap(params);
        return map.get("code");
    }
   
    private  static Map<String, String> getQueryMap(String query)   
    {   
        String[] params = query.split("&");   
        Map<String, String> map = new HashMap<String, String>();   
        for (String param : params)   
        {   
            String name = param.split("=")[0];   
            String value = param.split("=")[1];   
            map.put(name, value);   
        }   
        return map;   
    } 
    
    boolean isRunning()
    {
        return running;
    }
    void setRunning(boolean running)
    {
        this.running = running;
    }

    void addAuthCodeListener(AuthCodeListener listener)
    {
        if (!authListeners.contains(listener))authListeners.add(listener);
    }
    void removeAuthCodeListener(AuthCodeListener listener)
    {
        if (authListeners.contains(listener))authListeners.remove(listener);
    }
    void removeAllAuthCodeListeners()
    {
        while(authListeners.size() > 0)
        {
            authListeners.remove(0);
        }
    }
    protected void fireAuthCodeEvent(AuthCodeEvent evt)
    {
        for(AuthCodeListener listener:authListeners)
        {
            listener.authCodeReceived(evt);
        }
    }
    

    private static void printSocketInfo(Socket s)
    {
        logger.info("Server socket class: " + s.getClass());
        logger.info("\tRemote address = " + s.getInetAddress().toString());
        logger.info("\tRemote port = " + s.getPort());
        logger.info("\tLocal socket address = " + s.getLocalSocketAddress().toString());
        logger.info("\tLocal address = " + s.getLocalAddress().toString());
        logger.info("\tLocal port = " + s.getLocalPort());
    }

    private static void printServerSocketInfo(ServerSocket s)
    {
        logger.info("Server socket class: " + s.getClass());
        logger.info("\tSocket address = " + s.getInetAddress().toString());
        logger.info("\tSocket port = " + s.getLocalPort());
    }
}
