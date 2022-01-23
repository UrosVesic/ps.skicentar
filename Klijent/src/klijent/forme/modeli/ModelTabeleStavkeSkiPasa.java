/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.modeli;

import domen.SkiKarta;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiPas.IzmeniSkiPasForma;
import klijent.kontrolerKi.KontrolerKIKreirajSkiPas;
import klijent.kontrolerKi.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleStavkeSkiPasa extends AbstractTableModel {

    private SkiPas skiPas;
    String[] kolone = {"Redni broj", "Vrednost stavke", "Pocetak vazenja", "Zavrsetak vazenja", "Ski karta"};
    OpstiKontrolerKI ok;

    public ModelTabeleStavkeSkiPasa(SkiPas skiPas, OpstiKontrolerKI ok) {
        this.skiPas = skiPas;
        this.ok = ok;
    }

    public SkiPas getSkiPas() {
        return skiPas;
    }

    public void setSkiPas(SkiPas skiPas) {
        this.skiPas = skiPas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (skiPas == null) {
            return 0;
        }
        return skiPas.getStavkeSkiPasa().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaSkiPasa stavka = skiPas.getStavkeSkiPasa().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRedniBroj();
            case 1:
                return stavka.getVrednostStavke();
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(stavka.getPocetakVazenja());

            case 3:
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(stavka.getZavrsetakVazenja());
            case 4:
                return stavka.getSkiKarta();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(StavkaSkiPasa stavka) {
        if (skiPas.getStavkeSkiPasa().contains(stavka)) {
            return;
        }
        stavka.setRedniBroj(skiPas.getStavkeSkiPasa().size() + 1);
        skiPas.getStavkeSkiPasa().add(stavka);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StavkaSkiPasa stavkaSkiPasa = skiPas.getStavkeSkiPasa().get(rowIndex);
        switch (columnIndex) {
            case 2:
                try {
                    stavkaSkiPasa.setPocetakVazenja(sdf.parse((String) aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(ModelTabeleStavkeSkiPasa.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case 3:
                try {
                    stavkaSkiPasa.setZavrsetakVazenja(sdf.parse((String) aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(ModelTabeleStavkeSkiPasa.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                stavkaSkiPasa.setSkiKarta((SkiKarta) aValue);
                stavkaSkiPasa.setVrednostStavke(((SkiKarta) aValue).getCenaSkiKarte());
                fireTableDataChanged();
                ok.promeniCenu();
                break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 2;
    }

    public void obrisi(int selectedRow) {
        skiPas.getStavkeSkiPasa().remove(selectedRow);
        fireTableDataChanged();
    }

}
