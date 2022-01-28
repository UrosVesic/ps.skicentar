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
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.staza.PromeniStazuForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPromeniStazu extends OpstiKontrolerKI {

    public KontrolerKIPromeniStazu(OpstaEkranskaForma oef, Staza staza) {
        this.oef = oef;
        odo = staza;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        Staza staza = (Staza) odo;
        PromeniStazuForma psf = (PromeniStazuForma) oef;
        if (!"".equals(psf.getTxtBrojStaze().getText())) {
            staza.setBrojStaze(Long.parseLong(psf.getTxtBrojStaze().getText()));
        }
        staza.setNazivStaze(psf.getTxtNazivStaze().getText());
        staza.setSkiCentar((SkiCentar) psf.getCmbSkiCentar().getSelectedItem());
        staza.setTipStaze((String) psf.getCmbTipStaze().getSelectedItem());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        PromeniStazuForma psf = (PromeniStazuForma) oef;
        Staza staza = (Staza) odo;
        psf.getTxtBrojStaze().setText(staza.getBrojStaze() + "");
        psf.getTxtNazivStaze().setText(staza.getNazivStaze());
        if (staza.getSkiCentar() != null) {
            psf.getCmbSkiCentar().setSelectedItem(staza.getSkiCentar());
        }
        if (staza.getTipStaze() != null) {
            psf.getCmbTipStaze().setSelectedItem(staza.getTipStaze());
        }
    }

    @Override
    public void isprazniGrafickiObjekat() {
        PromeniStazuForma psf = (PromeniStazuForma) oef;
        psf.getTxtBrojStaze().setText("");
        psf.getTxtNazivStaze().setText("");
        psf.getCmbSkiCentar().setSelectedIndex(0);
        psf.getCmbTipStaze().setSelectedIndex(0);
    }

    public void pripremiKomboBox() {
        List<OpstiDomenskiObjekat> skiCentri;
        Staza staza = (Staza) odo;
        PromeniStazuForma psf = (PromeniStazuForma) oef;
        psf.getCmbSkiCentar().removeAllItems();
        try {
            SOUcitajListuSkiCentara();
            skiCentri = lista;
            for (OpstiDomenskiObjekat skiCentar : skiCentri) {
                psf.getCmbSkiCentar().addItem((SkiCentar) skiCentar);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(psf, "Sistem ne moze da vrati listu ski centara");
        }
        psf.getCmbSkiCentar().setSelectedItem(staza.getSkiCentar());

    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        PromeniStazuForma f = (PromeniStazuForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(f.getTxtNazivStaze().getText(), "Naziv staze je obavezan").throwIfInvalide();
    }

}
