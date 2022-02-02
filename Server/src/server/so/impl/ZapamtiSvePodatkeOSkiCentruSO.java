/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.Staza;
import domen.Zicara;
import java.util.List;
import server.broker.BrokerBP;
import server.so.OpstaSo;

/**
 *
 * @author UrosVesic
 */
public class ZapamtiSvePodatkeOSkiCentruSO extends OpstaSo{
    
    List<Staza> staze;
    List<Zicara> zicare;

    public ZapamtiSvePodatkeOSkiCentruSO(List<Staza> staze, List<Zicara> zicare, BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
        this.staze = staze;
        this.zicare = zicare;
    }
    
    

    @Override
    protected void izvrsiOperaciju() throws Exception {
        b.promeniSlog(odo);
        for (Staza staza : staze) {
            b.promeniSlog(staza);
        }
        for (Zicara zicara : zicare) {
            b.promeniSlog(zicara);
        }
    }

    @Override
    protected void proveriPreduslove() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
