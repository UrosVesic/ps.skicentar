/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import server.broker.BrokerBP;
import server.so.OpstaSo;

/**
 *
 * @author UrosVesic
 */
public class PretraziZicareSO extends OpstaSo {

    public PretraziZicareSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        lista = b.pronadjiSlogove(odo);
    }

    @Override
    protected void proveriPreduslove() throws Exception {
    }

}
