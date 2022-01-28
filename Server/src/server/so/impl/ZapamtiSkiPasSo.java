/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo;

/**
 *
 * @author UrosVesic
 */
public class ZapamtiSkiPasSo extends OpstaSo{

    public ZapamtiSkiPasSo(BrokerBazePodataka b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    

    @Override
    public void izvrsenjeSo() throws Exception {
        b.pamtiSlozeniSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
