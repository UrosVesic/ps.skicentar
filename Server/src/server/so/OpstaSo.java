/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
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
public abstract class OpstaSo {

    public BrokerBazePodataka b;
    protected OpstiDomenskiObjekat odo;
    protected List<OpstiDomenskiObjekat> lista;

    public OpstaSo(BrokerBazePodataka b, OpstiDomenskiObjekat odo) {
        this.b = b;
        this.odo = odo;
    }

    public OpstaSo(BrokerBazePodataka b, OpstiDomenskiObjekat odo, List<OpstiDomenskiObjekat> lista) {
        this.b = b;
        this.odo = odo;
        this.lista = lista;
    }

    public void opsteIzvrsenjeSo() throws Exception {

        b.connect();
        try {
            //proveriPreduslove(odo);
            izvrsenjeSo();
            b.commit();

        } catch (SQLException ex) {
            b.rollback();
            throw ex;
        } finally {
            b.disconnect();
        }

    }

    public abstract void izvrsenjeSo() throws Exception;

    public abstract void proveriPreduslove(OpstiDomenskiObjekat odo) throws Exception;
}
