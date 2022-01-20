/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo1;

/**
 *
 * @author UrosVesic
 */
public class UcitajListuSo extends OpstaSo1{
    

    public UcitajListuSo(BrokerBazePodataka b) {
        super(b);
    }
    
  
    /*public void opsteIzvrsenjeSo(List<OpstiDomenskiObjekat> lista,OpstiDomenskiObjekat odo) throws SQLException {
        
        b.connect();
        try {
           izvrsenjeSo(lista, odo);
            b.commit();
            
        } catch (SQLException ex) {
            b.rollback();
            throw ex;
        } finally {
            b.disconnect();
        }

    }*/

    @Override
    public void izvrsenjeSo(List<OpstiDomenskiObjekat> lista, OpstiDomenskiObjekat odo) throws SQLException {
        b.vratiSve(lista, odo);
    }
}
