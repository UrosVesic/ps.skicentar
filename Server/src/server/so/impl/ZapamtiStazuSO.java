/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.Staza;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class ZapamtiStazuSO extends OpstaSo {

    public ZapamtiStazuSO(BrokerBP b, OpstiDomenskiObjekat odo) {
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
        if (!(odo instanceof Staza)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        Staza staza = (Staza) odo;
        Validator.startValidation().validateNotNull(staza.getNazivStaze(), "Null naziv staze")
                .validateNotNull(staza.getSkiCentar(), "Null ski centar")
                .validateNotNull(staza.getTipStaze(), "Null tip staze")
                .validateGreaterThanZero(staza.getBrojStaze(), "Broj staze manji od 1")
                .throwIfInvalide();
    }

}
