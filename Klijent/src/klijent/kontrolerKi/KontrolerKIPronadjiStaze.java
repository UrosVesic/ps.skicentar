/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.Staza;
import java.util.ArrayList;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleStaza;
import klijent.forme.staza.PronadjiStazeForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPronadjiStaze extends OpstiKontrolerKI {

    public KontrolerKIPronadjiStaze(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Staza staza = (Staza) odo;
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        napuniDomenskiObjekat(staza, psf);
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        //Staza staza = (Staza) odo;
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        napuniGrafickiObjekatIzDOmenskog(/*staza,*/psf);
    }

    @Override
    public void isprazniGrafickiObjekat() {
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        model.setStaze(new ArrayList<>());
    }

    private void napuniDomenskiObjekat(Staza staza, PronadjiStazeForma psf) {
        //if (!"".equals(psf.getTxtTezina().getText())) {
        staza.setTipStaze(psf.getTxtTezina().getText()/*Long.parseLong(psf.getTxtTezina().getText())*/);
        //}
    }

    private void napuniGrafickiObjekatIzDOmenskog(/*Staza staza,*/PronadjiStazeForma psf) {
        /*psf.getTxtTezina().setText(staza.getBrojStaze() + "");*/
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Staza staza = (Staza) opstiDomenskiObjekat;
            model.dodaj(staza);
        }

    }

    public void pripremiTabelu() {
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        ModelTabeleStaza model = new ModelTabeleStaza();
        psf.getTblStaze().setModel(model);
        psf.getTblStaze().setRowSelectionAllowed(true);

    }

    public void azurirajTabelu(Staza staza) {
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        model.azurirajStazu(staza);
    }

    public Staza vratiIzabranuStazu() throws Exception {
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        if (psf.getTblStaze().getSelectedRow() == -1) {
            throw new Exception("Niste izabrali red za promenu");
        }
        Staza staza = model.getStaze().get(psf.getTblStaze().getSelectedRow());
        return staza;
    }

    @Override
    public void validirajPretragu() throws ValidationException {
        PronadjiStazeForma psf = (PronadjiStazeForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(psf.getTxtTezina().getText(), "Tezina staze je obavezna").throwIfInvalide();
    }

}
