/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo;


/**
 *
 * @author UrosVesic
 */
public class PretraziSo extends OpstaSo{
    

    public PretraziSo(BrokerBazePodataka b) {
        super(b);
    }

    

    /*@Override
    public void izvrsenjeSo(List<OpstiDomenskiObjekat> lista, OpstiDomenskiObjekat odo) throws Exception {
        b.pronadjiSlogove(lista,odo);
    }*/

    @Override
    public void izvrsenjeSo(OpstiDomenskiObjekat odo) throws Exception {
        b.pronadjiSlog(odo);
    }

    @Override
    public void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}