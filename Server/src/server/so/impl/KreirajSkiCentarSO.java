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
public class KreirajSkiCentarSO extends OpstaSo {

    public KreirajSkiCentarSO(BrokerBP b, OpstiDomenskiObjekat odo) {
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
        if (!(odo instanceof SkiCentar)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        SkiCentar skiCentar = (SkiCentar) odo;
        Validator.startValidation().validateNotNull(skiCentar.getNazivPlanine(), "Null naziv planine")
                .validateNotNull(skiCentar.getNazivSkiCentra(), "Null naziv ski centra")
                .validateNotNull(skiCentar.getRadnoVreme(), "Null radno vreme")
                .throwIfInvalide();

    }

}
