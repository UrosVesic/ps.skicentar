/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo1;

/**
 *
 * @author UrosVesic
 */
public class PretraziSkiKarteSo extends OpstaSo1{
    
    

    public PretraziSkiKarteSo(BrokerBazePodataka b) {
        super(b);
    }

    @Override
    public void izvrsenjeSo(List<OpstiDomenskiObjekat> lista, OpstiDomenskiObjekat odo) throws Exception {
        b.pronadjiSlogove(lista, odo);
    }
    
}
