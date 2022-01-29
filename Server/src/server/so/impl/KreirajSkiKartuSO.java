/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KreirajSkiKartuSO extends OpstaSo {

    public KreirajSkiKartuSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        b.kreirajSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof SkiKarta)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        SkiKarta skiKarta = (SkiKarta) odo;
        Validator.startValidation().validateNotNull(skiKarta.getCenaSkiKarte(), "Null cena ski karte")
                .validateNotNull(skiKarta.getSkiCentar(), "Null ski centar")
                .validateNotNull(skiKarta.getVrstaSkiKarte(), "Null vrsta ski karte")
                .throwIfInvalide();
    }

}
