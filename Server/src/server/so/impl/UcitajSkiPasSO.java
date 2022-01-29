/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.util.ArrayList;
import java.util.List;
import server.broker.BrokerBP;
import server.so.OpstaSo;

/**
 *
 * @author draskovesic
 */
public class UcitajSkiPasSO extends OpstaSo {

    public UcitajSkiPasSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        //b.pronadjiSlozenSlog(odo);
        b.pronadjiSlog(odo);
        SkiPas skiPas = (SkiPas) odo;
        StavkaSkiPasa stavka = new StavkaSkiPasa();
        stavka.setSkiPas(skiPas);
        try {
            List<OpstiDomenskiObjekat> listaStavkiOdo = b.pronadjiSlogove(stavka);
            List<StavkaSkiPasa> stavkeSkiPasa = new ArrayList<>();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavkiOdo) {
                stavkeSkiPasa.add((StavkaSkiPasa) opstiDomenskiObjekat);
            }
            skiPas.setStavkeSkiPasa(stavkeSkiPasa);
        } catch (Exception exception) {
        }

    }

    @Override
    public void proveriPreduslove() throws Exception {
    }

}
