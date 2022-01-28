/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
 */
package server.so;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.broker.BrokerBazePodataka;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstaSo {

    protected BrokerBazePodataka b;
    protected OpstiDomenskiObjekat odo;
    protected List<OpstiDomenskiObjekat> lista;

    public OpstaSo(BrokerBazePodataka b, OpstiDomenskiObjekat odo) {
        this.b = b;
        this.odo = odo;
        lista = new ArrayList<>();
    }

   

    public void opsteIzvrsenjeSo() throws Exception {

        b.connect();
        try {
            //proveriPreduslove(odo);
            izvrsenjeSo();
            b.commit();

        } catch (Exception ex) {
            b.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            b.disconnect();
        }

    }

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    protected abstract void izvrsenjeSo() throws Exception;

    protected abstract void proveriPreduslove() throws Exception;
}
