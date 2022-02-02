/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import java.util.logging.Level;
import java.util.logging.Logger;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleSkiCentri;
import klijent.forme.skiCentar.SviSkiCentriForma;
import klijent.kontrolerKi.OpstiKontrolerKI;
import klijent.kontrolerKi.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKISviSkiCentri extends OpstiKontrolerKI {

    public KontrolerKISviSkiCentri(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SviSkiCentriForma sscf = (SviSkiCentriForma) oef;
        ModelTabeleSkiCentri model = (ModelTabeleSkiCentri) sscf.getTblSkiCentri().getModel();
        if (sscf.getTblSkiCentri().getSelectedRow() != -1) {
            SkiCentar skiCentar = model.getSkiCentri().get(sscf.getTblSkiCentri().getSelectedRow());
            odo = skiCentar;
        }
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {

    }

    @Override
    public void isprazniGrafickiObjekat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pripremiTabelu() {
        SviSkiCentriForma sscf = (SviSkiCentriForma) oef;
        sscf.getTblSkiCentri().setModel(new ModelTabeleSkiCentri());
    }

    public void napuniTabelu() {
        SviSkiCentriForma sscf = (SviSkiCentriForma) oef;
        ModelTabeleSkiCentri model = (ModelTabeleSkiCentri) sscf.getTblSkiCentri().getModel();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            SkiCentar skiCentar = (SkiCentar) opstiDomenskiObjekat;
            model.dodaj(skiCentar);
        }
    }

    public SkiCentar vratiSelektovanSC() throws Exception {
        SviSkiCentriForma sscf = (SviSkiCentriForma) oef;
        ModelTabeleSkiCentri model = (ModelTabeleSkiCentri) sscf.getTblSkiCentri().getModel();
        if (sscf.getTblSkiCentri().getSelectedRow() != -1) {
            SkiCentar skiCentar = model.getSkiCentri().get(sscf.getTblSkiCentri().getSelectedRow());
            return skiCentar;
        } else {
            throw new Exception();
        }

    }

    public void azurirajTabelu(SkiCentar skiCentar) {
        SviSkiCentriForma sscf = (SviSkiCentriForma) oef;
        ModelTabeleSkiCentri model = (ModelTabeleSkiCentri) sscf.getTblSkiCentri().getModel();
        model.azurirajSkiCentar(skiCentar);
    }

}
