/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UrosVesic
 */
public class ServerskaNit extends Thread{
    
    ServerSocket serverSocket;
    List<KlijentskaNit> klijentskeNiti;

    public ServerskaNit() throws IOException {
        klijentskeNiti = new ArrayList<>();
        serverSocket = new ServerSocket(9000);
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao");
                KlijentskaNit k = new KlijentskaNit(socket);
                k.start();
                klijentskeNiti.add(k);
            } catch (IOException ex) {
                //Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        zaustaviKlijentskeNiti();
    }

    private void zaustaviKlijentskeNiti() {
        for (KlijentskaNit klijentskaNit : klijentskeNiti) {
            klijentskaNit.zaustavi();
        }
    }
    
    
    public void zaustavi() throws IOException{
        serverSocket.close();
    }
    
}
