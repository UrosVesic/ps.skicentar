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
import klijent.forme.staza.PronadjiStazuForma;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPronadjiStazu extends OpstiKontrolerKI {

    public KontrolerKIPronadjiStazu(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }
    
    

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Staza staza = (Staza) odo;
        PronadjiStazuForma psf = (PronadjiStazuForma) oef;
        napuniDomenskiObjekat(staza, psf);
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        Staza staza = (Staza) odo;
        PronadjiStazuForma psf = (PronadjiStazuForma) oef;
        napuniGrafickiObjekatIzDOmenskog(staza, psf);
    }

    @Override
    public void isprazniGrafickiObjekat() {
        PronadjiStazuForma psf = (PronadjiStazuForma) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        model.setStaze(new ArrayList<>());
    }

    private void napuniDomenskiObjekat(Staza staza, PronadjiStazuForma psf) {
        if (!"".equals(psf.getTxtBrojStaze().getText())) {
            staza.setBrojStaze(Long.parseLong(psf.getTxtBrojStaze().getText()));
        }
    }

    private void napuniGrafickiObjekatIzDOmenskog(Staza staza, PronadjiStazuForma psf) {
        psf.getTxtBrojStaze().setText(staza.getBrojStaze()+"");
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        model.dodaj(staza);
    }

    public void pripremiTabelu() {
        PronadjiStazuForma psf = (PronadjiStazuForma) oef;
        ModelTabeleStaza model = new ModelTabeleStaza();
        psf.getTblStaze().setModel(model);
        psf.getTblStaze().setRowSelectionAllowed(true);

    }

}
