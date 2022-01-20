/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontroler;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.util.List;
import klijent.komunikacija.Komunikacija;
import komunikacija.Odgovor;
import komunikacija.Operacije;
import komunikacija.Zahtev;

/**
 *
 * @author UrosVesic
 */
public class Kontroler {

    static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void kreirajSkiCentar(SkiCentar skiCentar) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_CENTAR, skiCentar);
        Odgovor odgovor = Komunikacija.getInstanca().kreirajSkiCentar(zahtev);
        if (odgovor.isUspesno()) {
            //return (SkiCentar) odgovor.getRezultat();
            SkiCentar skiCentar1 = (SkiCentar) odgovor.getRezultat();
            skiCentar.setSifraSkiCentra(skiCentar1.getSifraSkiCentra());
            /*skiCentar.setNazivPlanine(skiCentar1.getNazivPlanine());
            skiCentar.setNazivSkiCentra(skiCentar1.getNazivSkiCentra());
            skiCentar.setRadnoVreme(skiCentar1.getRadnoVreme());*/
        } else {
            throw odgovor.getException();
        }

    }

    public SkiCentar zapamtiSkiCentar(SkiCentar skiCentar) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, skiCentar);
        Odgovor odgovor = Komunikacija.getInstanca().zapamtiSkiCentar(zahtev);
        if (odgovor.isUspesno()) {
            return (SkiCentar) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public Staza pronadjiStaze(Staza staza) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZU, staza);
        Odgovor odgovor = Komunikacija.getInstanca().pronadjiStazu(zahtev);
        if (odgovor.isUspesno()) {
            /* Staza pronadjenaStaza = */
            return (Staza) odgovor.getRezultat();
            /*staza.setNazivStaze(pronadjenaStaza.getNazivStaze());
            staza.setSkiCentar(pronadjenaStaza.getSkiCentar());
            staza.setTipStaze(pronadjenaStaza.getTipStaze());*/
        } else {
            throw odgovor.getException();
        }
    }

    public List<OpstiDomenskiObjekat> ucitajListuSkiCentara(List<OpstiDomenskiObjekat> skiCentri) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_CENTARA, skiCentri);
        Odgovor odgovor = Komunikacija.getInstanca().ucitajListuSkiCentara(zahtev);

        if (odgovor.isUspesno()) {
            return (List<OpstiDomenskiObjekat>) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }

    }

    public Staza zapamtiStazu(Staza staza) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, staza);
        Odgovor odgovor = Komunikacija.getInstanca().zapamtiStazu(zahtev);

        if (odgovor.isUspesno()) {
            return (Staza) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public void kreirajStazu(Staza staza) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_STAZU, staza);
        Odgovor odgovor = Komunikacija.getInstanca().kreirajStazu(zahtev);
        if (odgovor.isUspesno()) {
            Staza staza1 = (Staza) odgovor.getRezultat();
            staza.setBrojStaze(staza1.getBrojStaze());
        } else {
            throw odgovor.getException();
        }
    }

    public void kreirajZicaru(Zicara zicara) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_ZICARU, zicara);
        Odgovor odgovor = Komunikacija.getInstanca().kreirajZicaru(zahtev);
        if (odgovor.isUspesno()) {
            Zicara zicara1 = (Zicara) odgovor.getRezultat();
            zicara.setSifraZicare(zicara1.getSifraZicare());
        } else {
            throw odgovor.getException();
        }
    }

    public void zapamtiZicaru(Zicara zicara) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, zicara);
        Odgovor odgovor = Komunikacija.getInstanca().zapamtiZicaru(zahtev);

        if (odgovor.isUspesno()) {
            //return (Zicara) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public void kreirajSkiKartu(SkiKarta skiKarta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_KARTU, skiKarta);
        Odgovor odgovor = Komunikacija.getInstanca().kreirajSkiKartu(zahtev);
        if (odgovor.isUspesno()) {
            SkiKarta skiKarta1 = (SkiKarta) odgovor.getRezultat();
            skiKarta.setSifraSkiKarte(skiKarta1.getSifraSkiKarte());
        } else {
            throw odgovor.getException();
        }
    }

    public void zapamtiSkiKartu(SkiKarta skiKarta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_KARTU, skiKarta);
        Odgovor odgovor = Komunikacija.getInstanca().zapamtiSkiKartu(zahtev);

        if (odgovor.isUspesno()) {
            //return (Zicara) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public List<SkiKarta> pretraziSkiKarte(SkiKarta skiKarta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_KARTE, skiKarta);
        Odgovor odgovor = Komunikacija.getInstanca().pretraziSkiKarte(zahtev);

        if (odgovor.isUspesno()) {
            List<SkiKarta> skiKarte = (List<SkiKarta>) odgovor.getRezultat();
            return skiKarte;
        } else {
            throw odgovor.getException();
        }
    }

    public void kreirajSkiPas(SkiPas skiPas) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_SKI_PAS, skiPas);
        Odgovor odgovor = Komunikacija.getInstanca().kreirajSkiPas(zahtev);
        if (odgovor.isUspesno()) {
            SkiPas skiPas1 = (SkiPas) odgovor.getRezultat();
            skiPas.setSifraSkiPasa(skiPas1.getSifraSkiPasa());
        } else {
            throw odgovor.getException();
        }
    }

    public List<SkiKarta> ucitajListuSkiKarata() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_KARATA, null);
        Odgovor odgovor = Komunikacija.getInstanca().ucitajListuSkiKarata(zahtev);

        if (odgovor.isUspesno()) {
            return (List<SkiKarta>) odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public void zapamtiSkiPas(SkiPas skiPas) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_PAS, skiPas);
        Odgovor odgovor = Komunikacija.getInstanca().zapamtiSkiPas(zahtev);

        if (!odgovor.isUspesno()) {
            throw odgovor.getException();
        }

    }
}
