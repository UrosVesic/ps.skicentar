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
import server.so.OpstaSo1;

/**
 *
 * @author UrosVesic
 */
public class PretraziSkiKarteSo extends OpstaSo {

    public PretraziSkiKarteSo(BrokerBazePodataka b) {
        super(b);
    }

    @Override
    public void izvrsenjeSo(OpstiDomenskiObjekat odo) throws Exception {
        b.pronadjiSlog(odo);
    }

    @Override
    public void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
