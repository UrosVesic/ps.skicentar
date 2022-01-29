/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.SkiPas;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author UrosVesic
 */
public class ZapamtiSkiPasSo extends OpstaSo {

    public ZapamtiSkiPasSo(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        b.pamtiSlozeniSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof SkiPas)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        SkiPas skiPas = (SkiPas) odo;
        Validator.startValidation().validateNotNull(skiPas.getDatumIzdavanja(), "Null datum izdavanja")
                .validateNotNull(skiPas.getImePrezimeKupca(), "Null ime i prezime kupca")
                .validateNotNull(skiPas.getUkupnaCena(), "Null ukupna cena")
                .validateGreaterThanZero(skiPas.getSifraSkiPasa(), "Sifra ski pasa manja od 1")
                .throwIfInvalide();
    }

}
