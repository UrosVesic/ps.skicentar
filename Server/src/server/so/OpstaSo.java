/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
 */
package server.so;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import server.broker.BrokerBazePodataka;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstaSo {

    /*public void template(OpstiDomenskiObjekat odo, BrokerBazePodataka r) throws SQLException {
        r.connect();
        try {
            izvrsenjeSo(r, odo);
            r.commit();
        } catch (SQLException ex) {
            r.rollback();
            throw ex;
        } finally {
            r.disconnect();
        }

    }*/
    
    public BrokerBazePodataka b;

    public OpstaSo(BrokerBazePodataka b) {
        this.b = b;
    }
    
    

    public void opsteIzvrsenjeSo(OpstiDomenskiObjekat odo) throws Exception {
        
        b.connect();
        try {
            //proveriPreduslove(odo);
           izvrsenjeSo(odo);
            b.commit();
            
        } catch (SQLException ex) {
            b.rollback();
            throw ex;
        } finally {
            b.disconnect();
        }

    }

    public abstract void izvrsenjeSo(OpstiDomenskiObjekat odo) throws Exception;

    public abstract void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception;
}
