/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.Staza;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.staza.KreirajStazuForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIKreirajStazu extends OpstiKontrolerKI {

    public KontrolerKIKreirajStazu(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Staza staza = (Staza) odo;
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        if (!"".equals(ksf.getTxtBrojStaze().getText())) {
            staza.setBrojStaze(Long.parseLong(ksf.getTxtBrojStaze().getText()));
        }
        staza.setNazivStaze(ksf.getTxtNazivStaze().getText());
        staza.setSkiCentar((SkiCentar) ksf.getCmbSkiCentar().getSelectedItem());
        staza.setTipStaze((String) ksf.getCmbTipStaze().getSelectedItem());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        Staza staza = (Staza) odo;
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        ksf.getTxtBrojStaze().setText(staza.getBrojStaze() + "");
        ksf.getTxtNazivStaze().setText(staza.getNazivStaze());
        if (staza.getSkiCentar() != null) {
            ksf.getCmbSkiCentar().setSelectedItem(staza.getSkiCentar());
        }
        if (staza.getTipStaze() != null) {
            ksf.getCmbTipStaze().setSelectedItem(staza.getTipStaze());
        }
    }

    @Override
    public void isprazniGrafickiObjekat() {
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        ksf.getTxtBrojStaze().setText("");
        ksf.getTxtNazivStaze().setText("");
        ksf.getCmbSkiCentar().setSelectedIndex(0);
        ksf.getCmbTipStaze().setSelectedIndex(0);
    }

    public void pripremiKomboBox() {
        List<OpstiDomenskiObjekat> skiCentri;
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        ksf.getCmbSkiCentar().removeAllItems();
        try {
            SOUcitajListuSkiCentara();
            skiCentri = lista;
            for (OpstiDomenskiObjekat skiCentar : skiCentri) {
                ksf.getCmbSkiCentar().addItem((SkiCentar) skiCentar);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ksf, "Sistem ne moze da vrati listu ski centara");
        }
    }

    @Override
    public void omoguciPamcenje() {
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        ksf.getBtnZapamti().setEnabled(true);

    }

    @Override
    public void onemoguciPamcenje() {
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        ksf.getBtnZapamti().setEnabled(false);
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        KreirajStazuForma ksf = (KreirajStazuForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(ksf.getTxtBrojStaze().getText(), "Morate prvo kreirati stazu")
                .validateNotNullOrEmpty(ksf.getTxtNazivStaze().getText(), "Naziv staze je obavezan").throwIfInvalide();
    }

}
