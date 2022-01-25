/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.SkiCentar;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiCentar.PromeniSkiCentarForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPromeniSkiCentar extends OpstiKontrolerKI {

    public KontrolerKIPromeniSkiCentar(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SkiCentar skiCentar = (SkiCentar) odo;
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        if (!"".equals(pscf.getTxtSifraSkiCentra().getText())) {
            skiCentar.setSifraSkiCentra(Integer.parseInt(pscf.getTxtSifraSkiCentra().getText()));
        }
        skiCentar.setNazivPlanine(pscf.getTxtNazivPlanine().getText());
        skiCentar.setNazivSkiCentra(pscf.getTxtNazivSkiCentra().getText());
        skiCentar.setRadnoVreme(pscf.getTxtRadnoVreme().getText());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        SkiCentar skiCentar = (SkiCentar) odo;
        pscf.getTxtSifraSkiCentra().setText(skiCentar.getSifraSkiCentra() + "");
        pscf.getTxtNazivPlanine().setText(skiCentar.getNazivPlanine());
        pscf.getTxtNazivSkiCentra().setText(skiCentar.getNazivSkiCentra());
        pscf.getTxtRadnoVreme().setText(skiCentar.getRadnoVreme());
    }

    @Override
    public void isprazniGrafickiObjekat() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        pscf.getTxtSifraSkiCentra().setText("");
        pscf.getTxtNazivPlanine().setText("");
        pscf.getTxtNazivSkiCentra().setText("");
        pscf.getTxtRadnoVreme().setText("");
    }

    @Override
    public void omoguciPamcenje() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        pscf.getTxtNazivPlanine().setEditable(true);
        pscf.getTxtNazivSkiCentra().setEditable(true);
        pscf.getTxtRadnoVreme().setEditable(true);
        pscf.getTxtSifraSkiCentra().setEditable(false);
        pscf.getBtnZapamti().setEnabled(true);
    }

    @Override
    public void onemoguciPamcenje() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        pscf.getTxtNazivPlanine().setEditable(false);
        pscf.getTxtNazivSkiCentra().setEditable(false);
        pscf.getTxtRadnoVreme().setEditable(false);
        pscf.getTxtSifraSkiCentra().setEditable(true);
        pscf.getBtnZapamti().setEnabled(false);
    }

    @Override
    public void validirajPretragu() throws ValidationException {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(pscf.getTxtSifraSkiCentra().getText(), "Morate uneti sifru ski centra")
                .validateValueIsNumber(pscf.getTxtSifraSkiCentra().getText(), "Sifra ski centra mora biti broj")
                .throwIfInvalide();

    }

}
