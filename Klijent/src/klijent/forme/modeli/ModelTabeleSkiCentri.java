/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.modeli;

import domen.SkiCentar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleSkiCentri extends AbstractTableModel {

    List<SkiCentar> skiCentri;
    String[] kolone = {"Sifra ski centra", "Naziv ski centra", "Naziv planine", "Radno vreme"};

    public List<SkiCentar> getSkiCentri() {
        return skiCentri;
    }

    public ModelTabeleSkiCentri() {
        skiCentri = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return skiCentri.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SkiCentar sc = skiCentri.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sc.getSifraSkiCentra();
            case 1:
                return sc.getNazivSkiCentra();
            case 2:
                return sc.getNazivPlanine();
            case 3:
                return sc.getRadnoVreme();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(SkiCentar skiCentar) {
        skiCentri.add(skiCentar);
        fireTableDataChanged();
    }

    public void azurirajSkiCentar(SkiCentar skiCentar) {
        for (int i = 0; i < skiCentri.size(); i++) {
            if (skiCentar.equals(skiCentri.get(i))) {
                skiCentri.add(i, skiCentar);
                skiCentri.remove(i + 1);
                fireTableDataChanged();
            }
        }
    }

}
