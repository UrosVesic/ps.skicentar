/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class ZapamtiSkiCentarSO extends OpstaSo {

    public ZapamtiSkiCentarSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        b.promeniSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof SkiCentar)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        SkiCentar skiCentar = (SkiCentar) odo;
        Validator.startValidation().validateNotNullOrEmpty(skiCentar.getNazivPlanine(), "Null ili prazan naziv planine")
                .validateNotNullOrEmpty(skiCentar.getNazivSkiCentra(), "Null ili prazan naziv ski centra")
                .validateNotNullOrEmpty(skiCentar.getRadnoVreme(), "Null ili prazno radno vreme")
                .validateGreaterThanZero(skiCentar.getSifraSkiCentra(), "Sifra ski centra manja od 0")
                .throwIfInvalide();
    }

}
