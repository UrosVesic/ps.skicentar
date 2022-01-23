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
import klijent.forme.skiPas.KreirajSkiPasForma;
import klijent.komunikacija.Komunikacija;
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
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara");
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
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira stazu");
        }
    }

    public void SOZapamtiStazu() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Staza) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu");
        }
    }

    public void SOPretraziStaze() {
        isprazniGrafickiObjekat();
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Staza) odgovor.getRezultat();
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
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_ZICARU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Zicara) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao zicaru");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira zicaru");
        }
    }

    public void SOZapamtiZicaru() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (Zicara) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
                isprazniGrafickiObjekat();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru");
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
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira ski kartu");
        }
    }

    public void SOZapamtiSkiKartu() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_KARTU, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiKarta) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski kartu");
                isprazniGrafickiObjekat();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski kartu");
        }
    }

    public void SOPretraziKarte() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_KARTE, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiKarta) odgovor.getRezultat();
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
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao ski centar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da kreira centar");
        }
    }

    public void SOZapamtiSkiCentar() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
                isprazniGrafickiObjekat();
                if (oef instanceof PromeniSkiCentarForma) {
                    onemoguciPromenu();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar");
        }
    }

    public void SOPretraziSkiCentar() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_CENTAR, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiCentar) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski centar po zadatom kriterijumu");
                omoguciPromenu();
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
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiPas) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je kreirao ski pas");
                omoguciPamcenjeSkiPasa();
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
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski pas");
                isprazniGrafickiObjekat();
                onemoguciPamcenjeSkiPasa();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas");
        }
    }

    public void SOPretraziSkiPas() {
        odo = oef.kreirajObjekat();
        KonvertujGrafickiObjekatUDomenskiObjekat();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_PAS, odo);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                odo = (SkiPas) odgovor.getRezultat();
                KonvertujObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski pas po zadatom kriterijumu");
                omoguciPamcenjeSkiPasa();
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

    private void omoguciPromenu() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        pscf.getTxtNazivPlanine().setEditable(true);
        pscf.getTxtNazivSkiCentra().setEditable(true);
        pscf.getTxtRadnoVreme().setEditable(true);
        //pscf.getTxtSifraSkiCentra().setEditable(false);
        pscf.getBtnPromeni().setEnabled(true);
    }

    private void onemoguciPromenu() {
        PromeniSkiCentarForma pscf = (PromeniSkiCentarForma) oef;
        pscf.getTxtNazivPlanine().setEditable(false);
        pscf.getTxtNazivSkiCentra().setEditable(false);
        pscf.getTxtRadnoVreme().setEditable(false);
        pscf.getTxtSifraSkiCentra().setEditable(true);
        pscf.getBtnPromeni().setEnabled(false);
    }

    public void omoguciPamcenjeSkiPasa() {
    }

    public void promeniCenu() {
    }

    public void onemoguciPamcenjeSkiPasa() {
    }

}
