/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.Zicara;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.zicara.KreirajZicaruForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIKreirajZicaru extends OpstiKontrolerKI {

    public KontrolerKIKreirajZicaru(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Zicara zicara = (Zicara) odo;
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        if (!"".equals(kzf.getTxtSifraZicare().getText())) {
            zicara.setSifraZicare(Long.parseLong(kzf.getTxtSifraZicare().getText()));
        }
        zicara.setNazivZicare(kzf.getTxtNazivZicare().getText());
        if (!"".equals(kzf.getTxtKapacitet().getText())) {
            zicara.setKapacitet(Integer.parseInt(kzf.getTxtKapacitet().getText()));
        }
        zicara.setRadnoVreme(kzf.getTxtRadnoVreme().getText());
        zicara.setSkiCentar((SkiCentar) kzf.getCmbSkiCentri().getSelectedItem());
        if (kzf.getCmbUfunkciji().getSelectedItem().equals("DA")) {
            zicara.setUFunkciji(true);
        } else {
            zicara.setUFunkciji(false);
        }

    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        Zicara zicara = (Zicara) odo;
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        kzf.getTxtSifraZicare().setText(zicara.getSifraZicare() + "");
        kzf.getTxtKapacitet().setText(zicara.getKapacitet() + "");
        kzf.getTxtNazivZicare().setText(zicara.getNazivZicare());
        kzf.getTxtRadnoVreme().setText(zicara.getRadnoVreme());
        if (zicara.getSkiCentar() != null) {
            kzf.getCmbSkiCentri().setSelectedItem(zicara.getSkiCentar());
        }
        if (zicara.isUFunkciji()) {
            kzf.getCmbUfunkciji().setSelectedIndex(0);
        } else {
            kzf.getCmbUfunkciji().setSelectedIndex(1);
        }
    }

    @Override
    public void isprazniGrafickiObjekat() {
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        kzf.getTxtSifraZicare().setText("");
        kzf.getTxtKapacitet().setText("");
        kzf.getTxtNazivZicare().setText("");
        kzf.getTxtRadnoVreme().setText("");
        kzf.getCmbSkiCentri().setSelectedIndex(0);
    }

    public void pripremiKomboBoks() {
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        List<OpstiDomenskiObjekat> skiCentri;
        try {
            SOUcitajListuSkiCentara();
            skiCentri = lista;
            for (OpstiDomenskiObjekat skiCentar : skiCentri) {
                kzf.getCmbSkiCentri().addItem((SkiCentar) skiCentar);
            }
        } catch (Exception ex) {
            Logger.getLogger(KreirajZicaruForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void omoguciPamcenje() {
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        kzf.getBtnZapamti().setEnabled(true);
    }

    @Override
    public void onemoguciPamcenje() {
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        kzf.getBtnZapamti().setEnabled(false);
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        KreirajZicaruForma kzf = (KreirajZicaruForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(kzf.getTxtNazivZicare().getText(), "Naziv zicare je obavezan")
                .validateNotNullOrEmpty(kzf.getTxtRadnoVreme().getText(), "Radno vreme je obavezno")
                .validateNotNullOrEmpty(kzf.getTxtKapacitet().getText(), "Kapcitet je obavezan")
                .validirajFormatRadnogVremena(kzf.getTxtRadnoVreme().getText(), "Format radnog vremena mora biti HH-HH")
                .validateValueIsNumber(kzf.getTxtKapacitet().getText(), "Kapacitet mora biti broj").throwIfInvalide();
        Validator.startValidation().validateGreaterThanZero(Long.parseLong(kzf.getTxtKapacitet().getText()), "Kapacitet mora biti veci od 0");

    }

}
