/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo;

/**
 *
 * @author draskovesic
 */
public class PretraziStazuSO extends OpstaSo {

    public PretraziStazuSO(BrokerBazePodataka b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsenjeSo() throws Exception {
        lista = b.pronadjiSlogove(odo);
    }

    @Override
    public void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
