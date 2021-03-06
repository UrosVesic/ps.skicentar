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
import server.broker.BrokerBP;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstaSo {

    protected BrokerBP b;
    protected OpstiDomenskiObjekat odo;
    protected List<OpstiDomenskiObjekat> lista;

    public OpstaSo(BrokerBP b, OpstiDomenskiObjekat odo) {
        this.b = b;
        this.odo = odo;
        lista = new ArrayList<>();
    }

   

    public void opsteIzvrsenjeSo() throws Exception {

        b.uspostaviKonekciju();
        try {
            //proveriPreduslove();
            izvrsiOperaciju();
            b.potvrdiTransakciju();

        } catch (Exception ex) {
            b.ponistiTransakciju();
            ex.printStackTrace();
            throw ex;
        } finally {
            b.raskiniKonekciju();
        }

    }

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    protected abstract void izvrsiOperaciju() throws Exception;

    protected abstract void proveriPreduslove() throws Exception;
}
