/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiPas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleSkiPas;
import klijent.forme.skiPas.PronadjiSkiPasoveForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPronadjiSkiPasove extends OpstiKontrolerKI {

    public KontrolerKIPronadjiSkiPasove(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        skiPas.setImePrezimeKupca(pspf.getTxtImePrezimeKupca().getText());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            SkiPas skiPas = (SkiPas) opstiDomenskiObjekat;
            model.dodaj(skiPas);
        }
    }

    @Override
    public void isprazniGrafickiObjekat() {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        model.setSkiPasevi(new ArrayList<>());

    }

    public SkiPas vratiSelektovanSP() throws Exception {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        if (pspf.getTblSkiPasevi().getSelectedRow() == -1) {
            throw new Exception();
        }
        return model.getSkiPasevi().get(pspf.getTblSkiPasevi().getSelectedRow());
    }

    public void pripremiTabelu() {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        pspf.getTblSkiPasevi().setModel(new ModelTabeleSkiPas());
    }

    public void azurirajTabelu(SkiPas skiPas) {
        PronadjiSkiPasoveForma pspf = (PronadjiSkiPasoveForma) oef;
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        model.azurirajSkiPas(skiPas);
    }

    

}
