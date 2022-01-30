/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi.dodatak;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.Staza;
import domen.Zicara;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleSkiCentri;
import klijent.forme.modeli.ModelTabeleStaza;
import klijent.forme.modeli.ModelTabeleZicara;
import klijent.forme.skiCentar.dodatak.IzmeniSkiCentarForma2;
import klijent.komunikacija.Komunikacija;
import klijent.kontrolerKi.OpstiKontrolerKI;
import komunikacija.Odgovor;
import komunikacija.Operacije;
import komunikacija.Zahtev;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKiIzmeniSkiCentar2 extends OpstiKontrolerKI {

    public KontrolerKiIzmeniSkiCentar2(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SkiCentar skiCentar = (SkiCentar) odo;
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        skiCentar.setSifraSkiCentra(Long.parseLong(f.getTxtSifraSkiCentra().getText()));
        skiCentar.setNazivSkiCentra(f.getTxtNazivSkiCentra().getText());
        skiCentar.setNazivPlanine(f.getTxtNazivPlanine().getText());
        skiCentar.setRadnoVreme(f.getTxtRadnoVreme().getText());
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        SkiCentar sc = (SkiCentar) odo;
        f.getTxtSifraSkiCentra().setText(sc.getSifraSkiCentra() + "");
        f.getTxtNazivSkiCentra().setText(sc.getNazivSkiCentra());
        f.getTxtNazivPlanine().setText(sc.getNazivPlanine());
        f.getTxtRadnoVreme().setText(sc.getRadnoVreme());
        /*if (odo instanceof Staza) {
            ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Staza staza = (Staza) opstiDomenskiObjekat;
                model.dodaj(staza);
            }
        }
        if (odo instanceof Zicara) {

        }*/
    }

    @Override
    public void isprazniGrafickiObjekat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pripremiTabele() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;

        String[] tezine = {"Laka", "Srednja", "Teska"};
        JComboBox comboBox = new JComboBox(tezine);

        f.getTblStaze().setModel(new ModelTabeleStaza());
        TableColumn tcs = f.getTblStaze().getColumnModel().getColumn(2);
        tcs.setCellEditor(new DefaultCellEditor(comboBox));
        //TODO: pripremi tabelu zicara
        String[] ufji = {"Da", "Ne"};
        JComboBox comboBox1 = new JComboBox(ufji);

        f.getTblZicare().setModel(new ModelTabeleZicara());
        TableColumn tcz = f.getTblZicare().getColumnModel().getColumn(4);
        tcz.setCellEditor(new DefaultCellEditor(comboBox1));
    }

//    public void pripremiPodatkeOSkiCentru(SkiCentar skiCentar) {
//        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
//        f.getTxtSifraSkiCentra().setText(skiCentar.getSifraSkiCentra());
//        f.gettxt
//    }
    @Override
    public void KonvertujListuUGrafickeKomponente() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        if (odo instanceof Staza) {
            ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Staza staza = (Staza) opstiDomenskiObjekat;
                model.dodaj(staza);
            }
        }
        if (odo instanceof Zicara) {
            ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Zicara zicara = (Zicara) opstiDomenskiObjekat;
                model.dodaj(zicara);
            }
        }
    }

    @Override
    public void SOKreirajStazu() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Staza staza = new Staza();
        staza.setSkiCentar((SkiCentar) odo);
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_STAZU, staza);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                staza = (Staza) odgovor.getRezultat();
                KonvertujObjekatURedTabele(staza);
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao stazu");
                onemoguciKreiranjeStaze();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void SOZapamtiStazu() {

        try {
            Staza staza = KonvertujRedTabeleStazaUGrafickiObjekat();
            odo = staza;
            try {
                validirajPamcenjeStaze();
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja staze:\n " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, staza);
            Odgovor odgovor;
            try {
                odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
                if (odgovor.isUspesno()) {
                    JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
                    omoguciKreiranjeStaze();
                } else {
                    JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Izaberite red u tabeli");
        }
    }

    private void KonvertujObjekatURedTabele(OpstiDomenskiObjekat odo) {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        if (odo instanceof Staza) {
            Staza staza = (Staza) odo;
            ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
            model.dodaj(staza);
        }
        if (odo instanceof Zicara) {
            Zicara zicara = (Zicara) odo;
            ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
            model.dodaj(zicara);
        }
    }

    private Staza KonvertujRedTabeleStazaUGrafickiObjekat() throws Exception {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
        if (f.getTblStaze().getSelectedRow() != -1) {
            Staza staza = model.getStaze().get(f.getTblStaze().getSelectedRow());
            return staza;
        } else {
            throw new Exception();
        }
    }

    private Zicara KonvertujRedTabeleZicaraUGrafickiObjekat() throws Exception {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
        if (f.getTblZicare().getSelectedRow() != -1) {
            Zicara zicara = model.getZicare().get(f.getTblZicare().getSelectedRow());
            return zicara;
        } else {
            throw new Exception();
        }
    }

    public void SOObrisiStazu() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        try {
            Staza staza = KonvertujRedTabeleStazaUGrafickiObjekat();
            Zahtev zahtev = new Zahtev(Operacije.OBRISI_STAZU, staza);
            Odgovor odgovor;
            try {
                odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
                if (odgovor.isUspesno()) {
                    JOptionPane.showMessageDialog(oef, "Sistem je obrisao stazu");
                    ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
                    model.obrisi(f.getTblStaze().getSelectedRow());
                    proveriStatusPoslednjeStaze();
                } else {
                    JOptionPane.showMessageDialog(oef, "Sistem ne moze da obrise stazu", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da obrise stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Izaberite red u tabeli");
        }
    }

    @Override
    public void SOKreirajZicaru() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zicara zicara = new Zicara();
        zicara.setSkiCentar((SkiCentar) odo);
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_ZICARU, zicara);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                zicara = (Zicara) odgovor.getRezultat();
                KonvertujObjekatURedTabele(zicara);
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao zicaru");
                onemoguciKreiranjeZicare();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void SOZapamtiZicaru() {
        try {
            Zicara zicara = KonvertujRedTabeleZicaraUGrafickiObjekat();
            odo = zicara;
            try {
                validirajPamcenjeZicare();
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja zicare: \n" + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, zicara);
            Odgovor odgovor;
            try {
                odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
                if (odgovor.isUspesno()) {
                    JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
                    omoguciKreiranjeZicare();
                } else {
                    JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Izaberite red u tabeli");
        }
    }

    public void SOObrisiZicaru() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        try {
            Zicara zicara = KonvertujRedTabeleZicaraUGrafickiObjekat();
            Zahtev zahtev = new Zahtev(Operacije.OBRISI_ZICARU, zicara);
            Odgovor odgovor;
            try {
                odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
                if (odgovor.isUspesno()) {
                    JOptionPane.showMessageDialog(oef, "Sistem je obrisao zicaru");
                    ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
                    model.obrisi(f.getTblZicare().getSelectedRow());
                    proveriStatusPoslednjeZicare();
                } else {
                    JOptionPane.showMessageDialog(oef, "Sistem ne moze da obrise zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da obrise zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Izaberite red u tabeli");
        }
    }

    public void SOZapamtiSvePodatkeOSkiCentru() throws Exception {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        SkiCentar skiCentar = (SkiCentar) odo;
        List<Staza> staze = ((ModelTabeleStaza) f.getTblStaze().getModel()).getStaze();
        List<Zicara> zicare = ((ModelTabeleZicara) f.getTblZicare().getModel()).getZicare();
        Object[] parametar = {skiCentar, staze, zicare};
        try {
            validirajPamcenje();
            validirajPamcenjeListeStaza(staze);
            validirajPamcenjeListeZicara(zicare);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom cuvanja svih podataka: \n" + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SVE_PODATKE_O_SKICENTRU, parametar);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio sve podatke o ski centru");
                ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti sve podatke o ski centru", "Greska", JOptionPane.ERROR_MESSAGE);
                throw new Exception();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze zapamti sve podatke o ski centru", "Greska", JOptionPane.ERROR_MESSAGE);
            throw ex;
        }
    }

    public void validirajPamcenjeStaze() throws ValidationException {
        Staza staza = (Staza) odo;
        Validator.startValidation().validateNotNullOrEmpty(staza.getNazivStaze(), "Naziv staze je obavezan")
                .validateNotNullOrEmpty(staza.getTipStaze(), "Tip staze je obavezan").throwIfInvalide();
    }

    public void validirajPamcenjeZicare() throws ValidationException {
        Zicara zicara = (Zicara) odo;
        Validator.startValidation().validateNotNullOrEmpty(zicara.getNazivZicare(), "Naziv zicare je obavezan")
                .validateNotNullOrEmpty(zicara.getRadnoVreme(), "Radno vreme je obavezno")
                .validirajFormatRadnogVremena(zicara.getRadnoVreme(), "Radno vreme mora biti u formatu HH(:mm)-HH(:mm)")
                .validateGreaterThanZero(zicara.getKapacitet(), "Kapacitet mora biti veći od 0")
                .throwIfInvalide();
    }

    private void validirajPamcenjeListeStaza(List<Staza> staze) throws ValidationException {
        int i = 1;
        Validator validator = Validator.startValidation();
        for (Staza staza : staze) {
            validator.validateNotNullOrEmpty(staza.getNazivStaze(), "Naziv staze je obavezan - id: " + staza.getBrojStaze())
                    .validateNotNullOrEmpty(staza.getTipStaze(), "Tip staze je obavezan - id: " + staza.getBrojStaze());
            i++;
        }
        validator.throwIfInvalide();
    }

    private void validirajPamcenjeListeZicara(List<Zicara> zicare) throws ValidationException {
        int i = 1;
        Validator validator = Validator.startValidation();
        for (Zicara zicara : zicare) {
            Validator.startValidation().validateNotNullOrEmpty(zicara.getNazivZicare(), "Naziv zicare je obavezan - id: " + zicara.getSifraZicare())
                    .validateNotNullOrEmpty(zicara.getRadnoVreme(), "Radno vreme je obavezno- id: " + zicara.getSifraZicare())
                    .validateGreaterThanZero(zicara.getKapacitet(), "Kapacitet mora biti veći od 0- id: " + zicara.getSifraZicare());
            i++;
        }

        validator.throwIfInvalide();
    }

    @Override
    public void validirajPamcenje() throws ValidationException {
        SkiCentar skiCentar = (SkiCentar) odo;
        Validator.startValidation().validateNotNullOrEmpty(skiCentar.getNazivPlanine(), "Naziv planine je obavezan")
                .validateNotNullOrEmpty(skiCentar.getNazivSkiCentra(), "Naziv ski centra je obavezan")
                .validateNotNullOrEmpty(skiCentar.getRadnoVreme(), "Radno vreme je obavezno")
                .validirajFormatRadnogVremena(skiCentar.getRadnoVreme(), "Radno vreme mora biti u formatu HH(:mm)-HH(:mm)").throwIfInvalide();
    }

    public void proveriStatusPoslednjeZicare() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        ModelTabeleZicara model = (ModelTabeleZicara) f.getTblZicare().getModel();
        Zicara zicara;
        try {
            zicara = model.vratiPoslednju();
            odo = zicara;
            try {
                validirajPamcenjeZicare();
                f.getBtnKreirajZicaru().setEnabled(true);
            } catch (ValidationException ex) {
                f.getBtnKreirajZicaru().setEnabled(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Prazna tabela zicara");
        }

    }

    public void proveriStatusPoslednjeStaze() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        ModelTabeleStaza model = (ModelTabeleStaza) f.getTblStaze().getModel();
        Staza staza;
        try {
            staza = model.vratiPoslednju();
            odo = staza;
            try {
                validirajPamcenjeStaze();
                f.getBtnKreirajStazu().setEnabled(true);
            } catch (ValidationException ex) {
                f.getBtnKreirajStazu().setEnabled(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Prazna tabela");
        }

    }

    private void onemoguciKreiranjeStaze() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        f.getBtnKreirajStazu().setEnabled(false);
    }

    private void omoguciKreiranjeStaze() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        f.getBtnKreirajStazu().setEnabled(true);
    }

    private void onemoguciKreiranjeZicare() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        f.getBtnKreirajZicaru().setEnabled(false);
    }

    private void omoguciKreiranjeZicare() {
        IzmeniSkiCentarForma2 f = (IzmeniSkiCentarForma2) oef;
        f.getBtnKreirajZicaru().setEnabled(true);
    }

}
