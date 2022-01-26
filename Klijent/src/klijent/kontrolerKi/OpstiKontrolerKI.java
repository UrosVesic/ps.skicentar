/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.skiCentar.PromeniSkiCentarForma;
import klijent.komunikacija.Komunikacija;
import klijent.validator.ValidationException;
import klijent.validator.Validator;
import komunikacija.Odgovor;
import komunikacija.Operacije;
import komunikacija.Zahtev;

/**
 *
 * @author draskovesic
 */
public abstract class OpstiKontrolerKI {

    OpstiDomenskiObjekat odo;
    OpstaEkranskaForma oef;
    List<OpstiDomenskiObjekat> lista = new ArrayList<>();

    public void SOUcitajListuSkiCentara() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_CENTARA, lista);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara");
        }
    }

    public void SOUcitajListuSkiKarata() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_KARATA, lista);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara");
        }
    }

    public void SOKreirajStazu() {
        odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Staza) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao stazu");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu");
        }
    }

    public void SOZapamtiStazu() {
        odo = oef.kreirajObjekat();
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja staze:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Staza) odgovor.getRezultat();
                //KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
                onemoguciPamcenje();
                isprazniGrafickiObjekat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu");
        }
    }

    public void SOPretraziStaze() {
        isprazniGrafickiObjekat();
        odo = oef.kreirajObjekat();
        try {
            validirajPretragu();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja staze:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao stazu po zadatom kriterijumu");

            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje stazu po zadataom kriterijumu");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje stazu po zadataom kriterijumu");
        }
    }

    public void SOKreirajZicaru() {
        odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_ZICARU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Zicara) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao zicaru");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru");
        }
    }

    public void SOZapamtiZicaru() {
        odo = oef.kreirajObjekat();
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja zicare:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Zicara) odgovor.getRezultat();
                //KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
                onemoguciPamcenje();
                isprazniGrafickiObjekat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru");
        }
    }

    public void SOKreirajSkiKartu() {

        odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_KARTU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiKarta) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao ski kartu");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski kartu");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski kartu");
        }
    }

    public void SOZapamtiSkiKartu() {
        odo = oef.kreirajObjekat();
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja ski karte:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_KARTU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiKarta) odgovor.getRezultat();
                //KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski kartu");
                isprazniGrafickiObjekat();
                onemoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski kartu");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski kartu");
        }
    }

    public void SOPretraziKarte() {
        isprazniGrafickiObjekat();
        odo = oef.kreirajObjekat();
        try {
            validirajPretragu();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pretrage ski karte:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_KARTE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski kartu po zadatom kriterijumu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski kartu po zadataom kriterijumu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski kartu po zadataom kriterijumu");
        }
    }

    public void SOKreirajSkiCentar() {
        odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao ski centar");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira centar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira centar");
        }
    }

    public void SOZapamtiSkiCentar() {
        odo = oef.kreirajObjekat();
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja ski centra:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                //KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
                isprazniGrafickiObjekat();
                onemoguciPamcenje();

            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar");
        }
    }

    public void SOPretraziSkiCentar() {
        odo = oef.kreirajObjekat();
        try {
            validirajPretragu();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pretrage ski centra:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski centar po zadatom kriterijumu");
                omoguciPamcenje();

            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski centar po zadataom kriterijumu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski centar po zadataom kriterijumu");
        }
    }

    public void SOKreirajSkiPas() {
        odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiPas) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao ski pas");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira pas");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira pas");
        }
    }

    public void SOZapamitSkiPas() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiPas) odgovor.getRezultat();
                //KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski pas");
                isprazniGrafickiObjekat();
                //onemoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas");
        }
    }

    public void SOPretraziSkiPas() {
        isprazniGrafickiObjekat();
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski pas po zadatom kriterijumu");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski pas po zadataom kriterijumu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski pas po zadataom kriterijumu");
        }
    }

    public abstract void KonvertujGrafickiObjekatUDomenskiObjekat();

    public abstract void KonvertujObjekatUGrafickeKomponente();

    public abstract void isprazniGrafickiObjekat();

    public void omoguciPamcenje() {

    }

    public void onemoguciPamcenje() {

    }

    public void promeniCenu() {
    }

    public void validirajPamcenje() throws ValidationException {

    }

    public void validirajPretragu() throws ValidationException {

    }
}
