package churski.projektindywidualny3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Radek on 2017-11-24.
 */

public class TcpClient {

    Socket socket = null;
    PrintWriter bufferOut = null;
    BufferedReader bufferIn = null;

    public TcpClient(String server_ip, int server_port) {
        try {
            InetAddress serverAddr = InetAddress.getByName(server_ip);
            new Initializer().execute(serverAddr, server_port);
        } catch (Exception e) {
            System.out.println("[APP]: " + e.toString());
        }
    }

    public void send(String msg) {
        new Communicator().execute(msg);
    }

    private class Initializer extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            System.out.println("[APP]: Init");
            try {
                socket = new Socket((InetAddress) objects[0], (int) objects[1]);
                bufferOut = new PrintWriter(socket.getOutputStream());
                bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return null;
        }
    }

    // [message]
    private class Communicator extends AsyncTask {

        @Override
        protected String doInBackground(Object[] objects) {
            try {
                bufferOut.print((String) objects[0]);
                bufferOut.flush();
                System.out.println("[APP]: " + bufferIn.read());
                return null;
            } catch (Exception e) {
                System.out.println("[APP]: Exception while sending message");
                System.out.println("[APP]: " + e.toString());
                return null;
            }
        }
    }
}