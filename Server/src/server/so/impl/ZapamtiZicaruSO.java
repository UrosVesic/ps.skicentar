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
 * @author draskovesic
 */
public class ZapamtiZicaruSO extends OpstaSo {

    public ZapamtiZicaruSO(BrokerBazePodataka b) {
        super(b);
    }

    @Override
    public void izvrsenjeSo(OpstiDomenskiObjekat odo) throws Exception {
        b.promeniSlog(odo);
    }

    @Override
    public void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
