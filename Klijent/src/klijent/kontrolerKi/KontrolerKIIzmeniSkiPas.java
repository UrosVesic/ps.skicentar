/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiKarta;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleStavkeSkiPasa;
import klijent.forme.skiPas.IzmeniSkiPasForma;
import klijent.forme.skiPas.KreirajSkiPasForma;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIIzmeniSkiPas extends OpstiKontrolerKI {

    private List<StavkaSkiPasa> dodateStavke;

    public KontrolerKIIzmeniSkiPas(OpstaEkranskaForma oef, SkiPas skiPas) {
        this.oef = oef;
        this.odo = skiPas;
        dodateStavke = new ArrayList<>();
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        if (!"".equals(kspf.getTxtSifraSkiPasa().getText())) {
            skiPas.setSifraSkiPasa(Long.parseLong(kspf.getTxtSifraSkiPasa().getText()));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            skiPas.setDatumIzdavanja(sdf.parse(kspf.getTxtDatumIzdavanja().getText()));
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Datum mora biti u formatu dd.MM.gggg");
        }
        skiPas.setImePrezimeKupca(kspf.getTxtImePrezimeKupca().getText());
        if (!"".equals(kspf.getTxtUkupnaCena().getText())) {
            skiPas.setUkupnaCena(new BigDecimal(kspf.getTxtUkupnaCena().getText()));
        }
        konvertujRedoveTabeleUNizStavki();
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        kspf.getTxtSifraSkiPasa().setText(skiPas.getSifraSkiPasa() + "");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        kspf.getTxtDatumIzdavanja().setText(sdf.format(skiPas.getDatumIzdavanja()));
        kspf.getTxtImePrezimeKupca().setText(skiPas.getImePrezimeKupca());
        kspf.getTxtUkupnaCena().setText(skiPas.getUkupnaCena() + "");
        ((ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel()).setSkiPas(skiPas);
    }

    @Override
    public void isprazniGrafickiObjekat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        kspf.getTxtSifraSkiPasa().setText("");
        kspf.getTxtDatumIzdavanja().setText(sdf.format(new Date()));
        kspf.getTxtImePrezimeKupca().setText("");
        kspf.getTxtUkupnaCena().setText("");
        ((ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel()).setSkiPas(new SkiPas());
    }

    private void konvertujRedoveTabeleUNizStavki() {
        IzmeniSkiPasForma ispf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        skiPas.setStavkeSkiPasa(model.getSkiPas().getStavkeSkiPasa());

    }

    public void dodajStavkuUTabelu() {
        IzmeniSkiPasForma ispf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date pocetakVazenja = sdf.parse(ispf.getTxtPocetakVazenja().getText());
            skiPas.setDatumIzdavanja(sdf.parse(ispf.getTxtDatumIzdavanja().getText()));
            StavkaSkiPasa stavka = new StavkaSkiPasa();
            stavka.setPocetakVazenja(pocetakVazenja);
            stavka.setSkiKarta((SkiKarta) ispf.getCmbSkiKarte().getSelectedItem());
            Date zavrsetakVazenja = stavka.generisiDatumZavrsetka();
            ispf.getTxtZavrsetakVazenja().setText(sdf.format(zavrsetakVazenja));
            if (ispf.getTxtVrednostStavke().getText() != "") {
                stavka = new StavkaSkiPasa(skiPas, 0, new BigDecimal(ispf.getTxtVrednostStavke().getText()), pocetakVazenja, zavrsetakVazenja, (SkiKarta) ispf.getCmbSkiKarte().getSelectedItem());
            } else {
                stavka = new StavkaSkiPasa(skiPas, 0, new BigDecimal(0), pocetakVazenja, zavrsetakVazenja, (SkiKarta) ispf.getCmbSkiKarte().getSelectedItem());
            }
            model.dodaj(stavka);
            ispf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(ispf, "Datum mora biti unesen u formatu dd.MM.gggg");
            Logger.getLogger(KreirajSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ispf, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String postaviCenu(List<StavkaSkiPasa> stavkeSkiPasa) {
        BigDecimal cena = new BigDecimal(0);
        for (StavkaSkiPasa stavkaSkiPasa : stavkeSkiPasa) {
            cena = cena.add(stavkaSkiPasa.getVrednostStavke());
        }
        return cena + "";
    }

    @Override
    public void promeniCenu() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        kspf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
    }

    public void pripremiTabelu() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        ModelTabeleStavkeSkiPasa model = new ModelTabeleStavkeSkiPasa(new SkiPas(), this);
        kspf.getTblStavkeSkiPasa().setModel(model);

        List<OpstiDomenskiObjekat> opstiDomenskiObjekti;
        List<SkiKarta> skiKarte = new ArrayList<>();
        try {
            SOUcitajListuSkiKarata();
            opstiDomenskiObjekti = lista;
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : opstiDomenskiObjekti) {
                skiKarte.add((SkiKarta) opstiDomenskiObjekat);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje liste ski karata", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SkiKarta[] arr = new SkiKarta[skiKarte.size()];
        arr = skiKarte.toArray(arr);
        JComboBox cmbSkiCentri = new JComboBox(arr);

        TableColumn tcSkiCentar = kspf.getTblStavkeSkiPasa().getColumnModel().getColumn(4);
        tcSkiCentar.setCellEditor(new DefaultCellEditor(cmbSkiCentri));
    }

    public void pripremiKomboBoks() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        try {
            SOUcitajListuSkiKarata();
            List<OpstiDomenskiObjekat> skiKarte = lista;
            for (OpstiDomenskiObjekat odoSK : skiKarte) {
                SkiKarta skiKarta = (SkiKarta) odoSK;
                kspf.getCmbSkiKarte().addItem(odoSK);
            }
        } catch (Exception ex) {
            Logger.getLogger(KreirajSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObrisiStavku() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        model.obrisi(kspf.getTblStavkeSkiPasa().getSelectedRow());
        kspf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
    }

    @Override
    public void omoguciPamcenje() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        kspf.getBtnZapamtiSkiPas().setEnabled(true);
    }

    @Override
    public void onemoguciPamcenje() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        kspf.getBtnZapamtiSkiPas().setEnabled(false);
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        IzmeniSkiPasForma ispf = (IzmeniSkiPasForma) oef;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        Validator.startValidation().validateNotNullOrEmpty(ispf.getTxtImePrezimeKupca().getText(), "Ime i prezime kupca je obavezno")
                .validateNotNullOrEmpty(ispf.getTxtDatumIzdavanja().getText(), "Datum je obavezan")
                .validateValueIsDate(ispf.getTxtDatumIzdavanja().getText(), "dd.MM.yyyy", "Datum mora biti u formatu dd.MM.gggg")
                .validateListIsNotEmpty(model.getSkiPas().getStavkeSkiPasa(), "Mora postojati bar jedna stavka")
                .throwIfInvalide();
    }

}
