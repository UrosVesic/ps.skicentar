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
import java.util.List;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiKarta.KreirajSkiKartuForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIKreirajSkiKartu extends OpstiKontrolerKI {

    public KontrolerKIKreirajSkiKartu(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SkiKarta skiKarta = (SkiKarta) odo;
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        if (!"".equals(kskf.getTxtSifraSkiKarte().getText())) {
            skiKarta.setSifraSkiKarte(Integer.parseInt(kskf.getTxtSifraSkiKarte().getText()));
        }
        if (!"".equals(kskf.getTxtCenaSkiKarte().getText())) {
            skiKarta.setCenaSkiKarte(new BigDecimal(kskf.getTxtCenaSkiKarte().getText()));
        }
        skiKarta.setSkiCentar((SkiCentar) kskf.getCmbSkiCentar().getSelectedItem());
        skiKarta.setVrstaSkiKarte((String) kskf.getCmbVrstaSkiKarte().getSelectedItem());

    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        SkiKarta skiKarta = (SkiKarta) odo;
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        kskf.getTxtSifraSkiKarte().setText(String.valueOf(skiKarta.getSifraSkiKarte()));
        if (skiKarta.getCenaSkiKarte() != null) {
            kskf.getTxtCenaSkiKarte().setText(String.valueOf(skiKarta.getCenaSkiKarte()));
        }
        kskf.getCmbSkiCentar().setSelectedItem(skiKarta.getSkiCentar());
        kskf.getCmbVrstaSkiKarte().setSelectedItem(skiKarta.getVrstaSkiKarte());

    }

    @Override
    public void isprazniGrafickiObjekat() {
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        kskf.getTxtSifraSkiKarte().setText("0");
        kskf.getTxtCenaSkiKarte().setText("");
        kskf.getCmbSkiCentar().setSelectedIndex(0);
        kskf.getCmbVrstaSkiKarte().setSelectedIndex(0);
    }

    public void pripremiKombobox() {
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        List<OpstiDomenskiObjekat> skiCentri;
        kskf.getCmbSkiCentar().removeAllItems();
        try {
            SOUcitajListuSkiCentara();
            skiCentri = lista;
            for (OpstiDomenskiObjekat skiCentar : skiCentri) {
                kskf.getCmbSkiCentar().addItem((SkiCentar) skiCentar);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da vrati listu ski centara");
        }
    }

    @Override
    public void omoguciPamcenje() {
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        kskf.getBtnZapamti().setEnabled(true);
    }

    @Override
    public void onemoguciPamcenje() {
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        kskf.getBtnZapamti().setEnabled(false);
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        KreirajSkiKartuForma kskf = (KreirajSkiKartuForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(kskf.getTxtCenaSkiKarte().getText(), "Cena ski karte je obavezna")
                .validateValueIsNumber(kskf.getTxtCenaSkiKarte().getText(), "Cena mora biti broj")
                .throwIfInvalide();
    }

}
