/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.Staza;
import domen.Zicara;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KreirajZicaruSO extends OpstaSo {

    public KreirajZicaruSO(BrokerBP b, OpstiDomenskiObjekat odo) {
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
        if (!(odo instanceof Zicara)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        Zicara zicara = (Zicara) odo;
        Validator.startValidation().validateNotNull(zicara.getNazivZicare(), "Null naziv zicare")
                .validateNotNull(zicara.getSkiCentar(), "Null ski centar")
                .validateNotNull(zicara.getRadnoVreme(), "Null radno vreme")
                .throwIfInvalide();
    }

}
