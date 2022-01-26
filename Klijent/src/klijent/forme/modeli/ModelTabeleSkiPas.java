/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.modeli;

import domen.SkiPas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author draskovesic
 */
public class ModelTabeleSkiPas extends AbstractTableModel {

    List<SkiPas> skiPasevi;
    String[] kolone = {"Sifra ski pasa", "Ukupna cena", "Ime i prezime kupca", "Datum izdavanja"};

    public ModelTabeleSkiPas() {
        skiPasevi = new ArrayList<>();
    }

    public List<SkiPas> getSkiPasevi() {
        return skiPasevi;
    }

    public void setSkiPasevi(List<SkiPas> skiPasevi) {
        this.skiPasevi = skiPasevi;
    }

    @Override
    public int getRowCount() {
        return skiPasevi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column]; //super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SkiPas skiPas = skiPasevi.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return skiPas.getSifraSkiPasa();
            case 1:
                return skiPas.getUkupnaCena();
            case 2:
                return skiPas.getImePrezimeKupca();
            case 3:
                return sdf.format(skiPas.getDatumIzdavanja());
            default:
                return null;
        }
    }

    public void dodaj(SkiPas skiPas) {
        skiPasevi.add(skiPas);
        fireTableDataChanged();
    }

    public void azurirajSkiPas(SkiPas skiPas) {
        for (int i = 0; i < skiPasevi.size(); i++) {
            if (skiPasevi.get(i).equals(skiPas)) {
                if (skiPas.getImePrezimeKupca().equals(skiPasevi.get(i).getImePrezimeKupca())) {
                    skiPasevi.add(i, skiPas);
                    skiPasevi.remove(i + 1);
                } else {
                    skiPasevi.remove(i);
                }
                fireTableDataChanged();
            }
        }

    }

}
