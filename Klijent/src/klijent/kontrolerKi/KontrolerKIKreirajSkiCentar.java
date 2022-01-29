/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.SkiCentar;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiCentar.KreirajSkiCentarForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIKreirajSkiCentar extends OpstiKontrolerKI {

    public KontrolerKIKreirajSkiCentar(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        SkiCentar skiCentar = (SkiCentar) odo;
        if (!"".equals(kscf.getTxtSifraSkiCentra().getText())) {
        skiCentar.setSifraSkiCentra(Long.parseLong(kscf.getTxtSifraSkiCentra().getText()));
        }
        skiCentar.setNazivPlanine(kscf.getTxtNazivPlanine().getText());
        /*String radnoVreme = kscf.getTxtRadnoVreme().getText();
        proveriFormatRadnogVremena(radnoVreme);*/
        skiCentar.setRadnoVreme(kscf.getTxtRadnoVreme().getText());
        skiCentar.setNazivSkiCentra(kscf.getTxtNazivSkiCentra().getText());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        SkiCentar skiCentar = (SkiCentar) odo;
        kscf.getTxtSifraSkiCentra().setText(skiCentar.getSifraSkiCentra() + "");
        kscf.getTxtNazivPlanine().setText(skiCentar.getNazivPlanine());
        kscf.getTxtNazivSkiCentra().setText(skiCentar.getNazivSkiCentra());
        kscf.getTxtRadnoVreme().setText(skiCentar.getRadnoVreme());
    }

    @Override
    public void isprazniGrafickiObjekat() {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        kscf.getTxtSifraSkiCentra().setText("");
        kscf.getTxtNazivPlanine().setText("");
        kscf.getTxtNazivSkiCentra().setText("");
        kscf.getTxtRadnoVreme().setText("");
    }

//    private void proveriFormatRadnogVremena(String radnoVreme) {
//        
//
//    }
    @Override
    public void omoguciPamcenje() {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        kscf.getBtnZapamti().setEnabled(true);
    }

    @Override
    public void onemoguciPamcenje() {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        kscf.getBtnZapamti().setEnabled(false);
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        KreirajSkiCentarForma kscf = (KreirajSkiCentarForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(kscf.getTxtSifraSkiCentra().getText(), "Morate prvo kreirati ski centar")
                .validateNotNullOrEmpty(kscf.getTxtNazivPlanine().getText(), "Naziv planine je obavezan")
                .validateNotNullOrEmpty(kscf.getTxtNazivSkiCentra().getText(), "Naziv ski centra je obavezan")
                .validateNotNullOrEmpty(kscf.getTxtRadnoVreme().getText(), "Radno vreme je obavezno")
                .validirajFormatRadnogVremena(kscf.getTxtRadnoVreme().getText(), "Format radnog vremena mora biti HH(:mm)-HH(:mm) ").throwIfInvalide();
    }

}
