/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.SkiKarta;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleStavkeSkiPasa;
import klijent.forme.skiPas.KreirajSkiPasForma;
import klijent.kontroler.Kontroler;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIKreirajSkiPas extends OpstiKontrolerKI {

    public KontrolerKIKreirajSkiPas(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
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
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        kspf.getTxtSifraSkiPasa().setText(skiPas.getSifraSkiPasa() + "");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        kspf.getTxtDatumIzdavanja().setText(sdf.format(skiPas.getDatumIzdavanja()));
        kspf.getTxtImePrezimeKupca().setText(skiPas.getImePrezimeKupca());
        kspf.getTxtUkupnaCena().setText(skiPas.getUkupnaCena() + "");
        ((ModelTabeleStavkeSkiPasa)kspf.getTblStavkeSkiPasa().getModel()).setSkiPas(skiPas);
    }

    @Override
    public void isprazniGrafickiObjekat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void konvertujRedoveTabeleUNizStavki() {
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        skiPas.setStavkeSkiPasa(model.getSkiPas().getStavkeSkiPasa());

    }

    public void dodajStavkuUTabelu() {
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) kspf.getTblStavkeSkiPasa().getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date pocetakVazenja = sdf.parse(kspf.getTxtPocetakVazenja().getText());
            Date zavrsetakVazenja = sdf.parse(kspf.getTxtZavrsetakVazenja().getText());
            StavkaSkiPasa stavka = new StavkaSkiPasa(skiPas, 0, new BigDecimal(kspf.getTxtVrednostStavke().getText()), pocetakVazenja, zavrsetakVazenja, (SkiKarta) kspf.getCmbSkiKarte().getSelectedItem());
            model.dodaj(stavka);
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

    public void pripremiTabelu() {
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
        ModelTabeleStavkeSkiPasa model = new ModelTabeleStavkeSkiPasa(new SkiPas());
        kspf.getTblStavkeSkiPasa().setModel(model);
    }

    public void pripremiKomboBoks() {
        KreirajSkiPasForma kspf = (KreirajSkiPasForma) oef;
        SkiPas skiPas = (SkiPas) odo;
        try {
            List<SkiKarta> skiKarte = Kontroler.getInstanca().ucitajListuSkiKarata();
            for (SkiKarta skiKarta : skiKarte) {
                kspf.getCmbSkiKarte().addItem(skiKarta);
            }
        } catch (Exception ex) {
            Logger.getLogger(KreirajSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}