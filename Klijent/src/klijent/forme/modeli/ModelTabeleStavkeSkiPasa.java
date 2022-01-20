/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.modeli;

import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleStavkeSkiPasa extends AbstractTableModel {

    private final SkiPas skiPas;
    String[] kolone = {"Redni broj", "Vrednost stavke", "Pocetak vazenja", "Zavrsetak vazenja", "Ski karta"};

    public ModelTabeleStavkeSkiPasa(SkiPas skiPas) {
        this.skiPas = skiPas;
    }

    public SkiPas getSkiPas() {
        return skiPas;
    }
    
    

    @Override
    public int getRowCount() {
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
        if(skiPas.getStavkeSkiPasa().contains(stavka)){
            return;
        }
        stavka.setRedniBroj(skiPas.getStavkeSkiPasa().size() + 1);
        skiPas.getStavkeSkiPasa().add(stavka);
        fireTableDataChanged();
    }
}
