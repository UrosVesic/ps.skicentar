/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleSkiKarte;
import klijent.forme.skiKarta.PretraziSkiKarteForma;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPretraziSkiKarte extends OpstiKontrolerKI {

    public KontrolerKIPretraziSkiKarte(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SkiKarta skiKarta = (SkiKarta) odo;
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        if (!"".equals(pskf.getTxtSifraSkiKarte().getText())) {
            skiKarta.setSifraSkiKarte(Long.parseLong(pskf.getTxtSifraSkiKarte().getText()));
        }
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        SkiKarta skiKarta = (SkiKarta) odo;
        pskf.getTxtCenaSkiKArte().setText(skiKarta.getCenaSkiKarte() + "");
        pskf.getCmbVrstaSkiKarte().setSelectedItem(skiKarta.getVrstaSkiKarte());
        pskf.getCmbSkiCentar().setSelectedItem(skiKarta.getSkiCentar());
    }

    @Override
    public void isprazniGrafickiObjekat() {
    }

    public void pripremiKomboBox() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        SOUcitajListuSkiCentara();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            SkiCentar skiCentar = (SkiCentar) opstiDomenskiObjekat;
            pskf.getCmbSkiCentar().addItem(skiCentar);
        }
    }

}
