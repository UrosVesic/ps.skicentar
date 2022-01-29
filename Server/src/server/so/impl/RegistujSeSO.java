/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class RegistujSeSO extends OpstaSo {

    public RegistujSeSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        b.ubaciSlog(odo);
    }

    @Override
    protected void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof Korisnik)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        Korisnik korisnik = (Korisnik) odo;
        Validator.startValidation().validateNotNull(korisnik.getEmail(), "Null email")
                .validateNotNull(korisnik.getSifra(), "Null sifra")
                .validateNotNull(korisnik.getIme(), "Null ime")
                .validateNotNull(korisnik.getPrezime(), "Null prezime")
                .throwIfInvalide();
    }

}
