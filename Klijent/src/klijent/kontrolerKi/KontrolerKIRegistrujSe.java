/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.Korisnik;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.RegistrujSeForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIRegistrujSe extends OpstiKontrolerKI {

    public KontrolerKIRegistrujSe(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        RegistrujSeForma rf = (RegistrujSeForma) oef;
        Korisnik korisnik = (Korisnik) odo;
        korisnik.setIme(rf.getTxtIme().getText());
        korisnik.setPrezime(rf.getTxtPrezime().getText());
        korisnik.setEmail(rf.getTxtEmail().getText());
        korisnik.setSifra(String.valueOf(rf.getTxtSifra().getPassword()));
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isprazniGrafickiObjekat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validirajRegistraciju() throws ValidationException {
        RegistrujSeForma f = (RegistrujSeForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(f.getTxtIme().getText(), "Ime je obavezno")
                .validateNotNullOrEmpty(f.getTxtPrezime().getText(), "Prezime je obavezno")
                .validateNotNullOrEmpty(f.getTxtEmail().getText(), "Email je obavezan")
                .validateNotNullOrEmpty(String.valueOf(f.getTxtSifra().getPassword()), "Sifra je obavezna")
                .validirajFormatMejla(f.getTxtEmail().getText(), "Email mora da sadrzi '@'").throwIfInvalide();
    }

}
