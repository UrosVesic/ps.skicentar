/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;
import server.broker.BrokerBazePodataka;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstaSo1 {
    public BrokerBazePodataka b;

    public OpstaSo1(BrokerBazePodataka b) {
        this.b = b;
    }
    
    

    public void opsteIzvrsenjeSo(List<OpstiDomenskiObjekat> lista,OpstiDomenskiObjekat odo) throws Exception {
        
        b.connect();
        try {
           izvrsenjeSo(lista,odo);
            b.commit();
            
        } catch (SQLException ex) {
            b.rollback();
            throw ex;
        } finally {
            b.disconnect();
        }

    }

    public abstract void izvrsenjeSo(List<OpstiDomenskiObjekat> lista,OpstiDomenskiObjekat odo) throws Exception;
}
