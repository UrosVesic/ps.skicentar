/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.SkiCentar;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiCentar.PromeniSkiCentarForma;

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
        pscf.getTxtSifraSkiCentra().setText("0");
        pscf.getTxtNazivPlanine().setText("");
        pscf.getTxtNazivSkiCentra().setText("");
        pscf.getTxtRadnoVreme().setText("00-00");
    }
    
}
