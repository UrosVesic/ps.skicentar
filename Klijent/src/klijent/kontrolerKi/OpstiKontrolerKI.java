/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.forme.OpstaEkranskaForma;
import klijent.komunikacija.Komunikacija;
import validator.ValidationException;
import komunikacija.Odgovor;
import komunikacija.Operacije;
import komunikacija.Zahtev;

/**
 *
 * @author draskovesic
 */
public abstract class OpstiKontrolerKI {

    protected OpstiDomenskiObjekat odo;
    protected OpstaEkranskaForma oef;
    protected List<OpstiDomenskiObjekat> lista = new ArrayList<>();

    //***************************************************************************************************
    public void SOUcitajZicareZaSkiCentar() {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_ZICARE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                KonvertujListuUGrafickeKomponente();
                
                //JOptionPane.showMessageDialog(oef, "Sistem je ucitao zicare");
            }
            else {
                throw new Exception();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita zicare", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajStazeZaSkiCentar() {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                KonvertujListuUGrafickeKomponente();
                //JOptionPane.showMessageDialog(oef, "Sistem je ucitao staze");
            }
            /*else {
                throw new Exception();
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita staze", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajSkiCentar() throws Exception {
        //odo = oef.kreirajObjekat();
        //KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao ski centar");
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita ski centar", "Greska", JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }
    }

    //******************************************************************************************************
    public boolean SORegistrujSe() {
        odo = oef.kreirajObjekat();
        try {
            validirajRegistraciju();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom registracije:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.REGISTRUJ_SE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Uspesna registracija");
                oef.dispose();
                return true;
            } else {
                JOptionPane.showMessageDialog(oef, "Neuspesna registracija", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesna registracija", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean SOPrijaviSe() {
        odo = oef.kreirajObjekat();
        try {
            validirajPrijavu();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom prijave:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRIJAVI_SE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Korisnik) odgovor.getRezultat();
                Komunikacija.getInstanca().setTrenutniKorisnik((Korisnik) odo);
                oef.dispose();
                return true;
            } else {
                JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void SOUcitajListuSkiCentara() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_CENTARA, lista);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                if(lista.isEmpty()) throw new Exception();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajListuSkiKarata() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_KARATA, lista);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                if(lista.isEmpty()) throw new Exception();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajSkiPas() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiPas) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao ski pas");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajStazu() {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Staza) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao stazu ");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOKreirajStazu() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOZapamtiStazu() {
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja staze:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
                onemoguciPamcenje();
                isprazniGrafickiObjekat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOPretraziStaze() {
        isprazniGrafickiObjekat();
        try {
            validirajPretragu();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja staze:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                lista = (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
                if(lista.isEmpty()) throw new Exception();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao staze po zadatom kriterijumu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje staze po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje staze po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOKreirajZicaru() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOZapamtiZicaru() {
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja zicare:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
                onemoguciPamcenje();
                isprazniGrafickiObjekat();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOKreirajSkiKartu() {

        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski kartu", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski kartu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOZapamtiSkiKartu() {
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja ski karte:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_KARTU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski kartu");
                isprazniGrafickiObjekat();
                onemoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski kartu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski kartu", "Greska", JOptionPane.ERROR_MESSAGE);
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
                if(lista.isEmpty()) throw new Exception();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski kartu po zadatom kriterijumu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski kartu po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski kartu po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOKreirajSkiCentar() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski centar", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski centar", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOZapamtiSkiCentar() {
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja ski centra:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
                isprazniGrafickiObjekat();
                onemoguciPamcenje();

            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar", "Greska", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski centar po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski centar po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOKreirajSkiPas() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
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
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOZapamtiSkiPas() {
        try {
            validirajPamcenje();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(oef, "Greska prilikom pamcenja ski pasa:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski pas");
                isprazniGrafickiObjekat();
                onemoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas", "Greska", JOptionPane.ERROR_MESSAGE);
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
                if(lista.isEmpty()) throw new Exception();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski pas po zadatom kriterijumu");
                omoguciPamcenje();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski pas po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da nadje ski pas po zadataom kriterijumu", "Greska", JOptionPane.ERROR_MESSAGE);
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
        throw new UnsupportedOperationException();
    }

    public void validirajPretragu() throws ValidationException {
    }

    public void validirajRegistraciju() throws ValidationException {
    }

    public void validirajPrijavu() throws ValidationException {
    }

    public void KonvertujListuUGrafickeKomponente() {
    }

    public void prikaziPorukuOGresci(String message) {
        JOptionPane.showMessageDialog(oef, message, "Greska", JOptionPane.ERROR_MESSAGE);
    }
}
