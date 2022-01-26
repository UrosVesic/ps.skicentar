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

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        if (!"".equals(kspf.getTxtSifraSkiPasa().getText())) {
            skiPas.setSifraSkiPasa(Long.parseLong(kspf.getTxtSifraSkiPasa().getText()));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            if (!"".equals(kspf.getTxtDatumIzdavanja().getText())) {
                skiPas.setDatumIzdavanja(sdf.parse(kspf.getTxtDatumIzdavanja().getText()));
            }
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
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        skiPas.setStavkeSkiPasa(model.getSkiPas().getStavkeSkiPasa());

    }

    public void dodajStavkuUTabelu() {
        IzmeniSkiPasForma kspf = (IzmeniSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date pocetakVazenja = sdf.parse(kspf.getTxtPocetakVazenja().getText());
            Date zavrsetakVazenja = sdf.parse(kspf.getTxtZavrsetakVazenja().getText());
            StavkaSkiPasa stavka = new StavkaSkiPasa(skiPas, 0, new BigDecimal(kspf.getTxtVrednostStavke().getText()), pocetakVazenja, zavrsetakVazenja, (SkiKarta) kspf.getCmbSkiKarte().getSelectedItem());
            model.dodaj(stavka);
            dodateStavke.add(stavka);
            kspf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(kspf, "Datum mora biti unesen u formatu dd.MM.gggg");
            Logger.getLogger(KreirajSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje liste ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
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

}
