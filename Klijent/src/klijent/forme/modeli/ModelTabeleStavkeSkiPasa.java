/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.modeli;

import domen.SkiKarta;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import klijent.kontrolerKi.OpstiKontrolerKI;
import validator.ValidationException;

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

    public void dodaj(StavkaSkiPasa stavka) throws Exception {
        validator.Validator.startValidation().validirajDaLiPostojeStavkeZaPeriod(stavka, skiPas, "Vec postoje karte za izabrani period")
                .validirajDaLiJeDatumStavkePosleIzdavanja(stavka, skiPas, "Stavka mora biti nakon datuma izdavanja")
                .throwIfInvalide();
        if (skiPas.getStavkeSkiPasa().size() > 0) {
            stavka.setRedniBroj(skiPas.getStavkeSkiPasa().get(skiPas.getStavkeSkiPasa().size() - 1).getRedniBroj() + 1);
        } else {
            stavka.setRedniBroj(1);
        }

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
                    StavkaSkiPasa stavka1 = new StavkaSkiPasa();
                    stavka1.setPocetakVazenja(sdf.parse((String) aValue));
                    validator.Validator.startValidation().validirajDaLiPostojeStavkeZaPeriod(stavka1, skiPas, "Vec postoje karte za izabrani period").throwIfInvalide();
                    stavkaSkiPasa.setPocetakVazenja(sdf.parse((String) aValue));
                    stavkaSkiPasa.generisiDatumZavrsetka();
                } catch (ParseException ex) {
                    ok.prikaziPorukuOGresci("Datum mora biri u formatu dd.MM.gggg");
                } catch (ValidationException ex) {
                    ok.prikaziPorukuOGresci(ex.getMessage());
                }

                break;
            case 3:
                try {
                    StavkaSkiPasa stavka1 = new StavkaSkiPasa();
                    stavka1.setZavrsetakVazenja(sdf.parse((String) aValue));
                    validator.Validator.startValidation().validirajDaLiPostojeStavkeZaPeriod(stavka1, skiPas, "Vec postoje karte za izabrani period").throwIfInvalide();
                    stavkaSkiPasa.setZavrsetakVazenja(sdf.parse((String) aValue));
                } catch (ParseException ex) {
                    ok.prikaziPorukuOGresci("Datum mora biri u formatu dd.MM.gggg");
                } catch (ValidationException ex) {
                    ok.prikaziPorukuOGresci(ex.getMessage());
                }
                break;
            case 4:
                stavkaSkiPasa.setSkiKarta((SkiKarta) aValue);
                stavkaSkiPasa.setVrednostStavke(((SkiKarta) aValue).getCenaSkiKarte());
                stavkaSkiPasa.generisiDatumZavrsetka();
                fireTableDataChanged();
                ok.promeniCenu();
                break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 4;
    }

    public void obrisi(int selectedRow) {
        skiPas.getStavkeSkiPasa().remove(selectedRow);
        fireTableDataChanged();
    }

}
