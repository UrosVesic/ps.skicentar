/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.Korisnik;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.PrijaviSeForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPrijaviSe extends OpstiKontrolerKI {

    public KontrolerKIPrijaviSe(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Korisnik korisnik = (Korisnik) odo;
        PrijaviSeForma pf = (PrijaviSeForma) oef;
        korisnik.setEmail(pf.getTxtEmail().getText());
        korisnik.setSifra(String.valueOf(pf.getTxtSifra().getPassword()));
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
    public void validirajPrijavu() throws ValidationException {
        PrijaviSeForma f = (PrijaviSeForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(f.getTxtEmail().getText(), "Email je obavezan")
                .validateNotNullOrEmpty(String.valueOf(f.getTxtSifra().getPassword()), "Sifra je obavezna")
                .validirajFormatMejla(f.getTxtEmail().getText(), "Emali mora da sadrzi '@'").throwIfInvalide();
    }

}
