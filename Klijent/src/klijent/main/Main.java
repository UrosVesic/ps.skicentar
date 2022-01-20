/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.main;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import klijent.forme.GlavnaForma;
import klijent.komunikacija.Komunikacija;

/**
 *
 * @author UrosVesic
 */
public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            Komunikacija.getInstanca().setSocket(socket);
            new GlavnaForma().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
