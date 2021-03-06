/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.niti;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacije;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import server.kontroler.Kontroler;

/**
 *
 * @author UrosVesic
 */
public class KlijentskaNit extends Thread {

    Socket socket;
    ServerskaNit serverskaNit;
    Korisnik trenutKorisnik;

    public KlijentskaNit(Socket socket, ServerskaNit serverskaNit) {
        this.socket = socket;
        this.serverskaNit = serverskaNit;
    }

    public Korisnik getTrenutKorisnik() {
        return trenutKorisnik;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Zahtev zahtev = (Zahtev) new Primalac(socket).primi();
                obradiZahtev(zahtev);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        serverskaNit.obavestiOOdjavljivanju(this);
    }

    void zaustavi() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obradiZahtev(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        switch (zahtev.getOperacija()) {
            case Operacije.KREIRAJ_SKI_CENTAR:
                odgovor = kreirajSkiCentar(zahtev);
                break;
            case Operacije.ZAPAMTI_SKI_CENTAR:
                odgovor = zapamtiSkiCentar(zahtev);
                break;
            case Operacije.PRETRAZI_STAZE:
                odgovor = pronadjiStazu(zahtev);
                break;
            case Operacije.UCITAJ_LISTU_SKI_CENTARA:
                odgovor = ucitajListuSkiCentara(zahtev);
                break;
            case Operacije.ZAPAMTI_STAZU:
                odgovor = zapamtiStazu(zahtev);
                break;
            case Operacije.KREIRAJ_STAZU:
                odgovor = kreirajStazu(zahtev);
                break;
            case Operacije.KREIRAJ_ZICARU:
                odgovor = kreirajZicaru(zahtev);
                break;
            case Operacije.ZAPAMTI_ZICARU:
                odgovor = zapamtiZicaru(zahtev);
                break;
            case Operacije.KREIRAJ_SKI_KARTU:
                odgovor = kreirajSkiKartu(zahtev);
                break;
            case Operacije.ZAPAMTI_SKI_KARTU:
                odgovor = zapamtiSkiKartu(zahtev);
                break;
            case Operacije.PRETRAZI_SKI_KARTE:
                odgovor = pretraziSkiKarte(zahtev);
                break;
            case Operacije.KREIRAJ_SKI_PAS:
                odgovor = kreirajSkiPas(zahtev);
                break;
            case Operacije.UCITAJ_LISTU_SKI_KARATA:
                odgovor = ucitajListuSkiKarata(zahtev);
                break;
            case Operacije.ZAPAMTI_SKI_PAS:
                odgovor = zapamtiSkiPas(zahtev);
                break;
            case Operacije.PRETRAZI_SKI_CENTAR:
                odgovor = pretraziSkiCentar(zahtev);
                break;
            case Operacije.PRETRAZI_SKI_PAS:
                odgovor = pretraziSkiPas(zahtev);
                break;
            case Operacije.UCITAJ_SKI_PAS:
                odgovor = ucitajSkiPas(zahtev);
                break;
            case Operacije.UCITAJ_STAZU:
                odgovor = ucitajStazu(zahtev);
                break;
            case Operacije.PRIJAVI_SE:
                odgovor = prijaviSe(zahtev);
                break;
            case Operacije.REGISTRUJ_SE:
                odgovor = registrujSe(zahtev);
                break;
            case Operacije.PRETRAZI_ZICARE:
                odgovor = pretraziZicare(zahtev);
                break;
            case Operacije.OBRISI_STAZU:
                odgovor = obrisiStazu(zahtev);
                break;
            case Operacije.OBRISI_ZICARU:
                odgovor = obrisiZicaru(zahtev);
                break;
            case Operacije.ZAPAMTI_SVE_PODATKE_O_SKICENTRU:
                odgovor = zapamtiSvePodatkeOSkiCentru(zahtev);
                break;
            default:
                break;
        }

        new Posiljalac(socket).posalji(odgovor);
    }

    private Odgovor kreirajSkiCentar(Zahtev zahtev) {
        SkiCentar skiCentar = (SkiCentar) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajSkiCentar(skiCentar);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_CENTAR);
            odgovor.setRezultat(skiCentar);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_CENTAR);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor zapamtiSkiCentar(Zahtev zahtev) {
        SkiCentar skiCentar = (SkiCentar) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().zapamtiSkiCentar(skiCentar);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_SKI_CENTAR);
            //odgovor.setRezultat(skiCentar);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_SKI_CENTAR);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor pronadjiStazu(Zahtev zahtev) {
        Staza staza = (Staza) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().pronadjiStaze(staza);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_STAZE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor ucitajListuSkiCentara(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().ucitajListuSkiCentara();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_LISTU_SKI_CENTARA);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor zapamtiStazu(Zahtev zahtev) {
        Staza staza = (Staza) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().zapamtiStazu(staza);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_STAZU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor kreirajStazu(Zahtev zahtev) {
        Staza staza = (Staza) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajStazu(staza);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_STAZU);
            odgovor.setRezultat(staza);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_STAZU);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor kreirajZicaru(Zahtev zahtev) {
        Zicara zicara = (Zicara) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajZicaru(zicara);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_ZICARU);
            odgovor.setRezultat(zicara);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_ZICARU);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;

    }

    private Odgovor zapamtiZicaru(Zahtev zahtev) {
        Zicara zicara = (Zicara) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().zapamtiZicaru(zicara);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_ZICARU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor kreirajSkiKartu(Zahtev zahtev) {
        SkiKarta skiKarta = (SkiKarta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajSkiKartu(skiKarta);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_KARTU);
            odgovor.setRezultat(skiKarta);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_KARTU);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor zapamtiSkiKartu(Zahtev zahtev) {
        SkiKarta skiKarta = (SkiKarta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().zapamtiSkiKartu(skiKarta);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_SKI_KARTU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziSkiKarte(Zahtev zahtev) {
        SkiKarta skiKarta = (SkiKarta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().pretraziSkiKarte(skiKarta);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_SKI_KARTE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor kreirajSkiPas(Zahtev zahtev) {
        SkiPas skiPas = (SkiPas) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajSkiPas(skiPas);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_PAS);
            odgovor.setRezultat(skiPas);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_SKI_PAS);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor ucitajListuSkiKarata(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().ucitajListuSkiKarata();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_LISTU_SKI_KARATA);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor zapamtiSkiPas(Zahtev zahtev) {
        SkiPas skiPas = (SkiPas) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().zapamtiSkiPas(skiPas);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_SKI_PAS);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziSkiCentar(Zahtev zahtev) {
        SkiCentar skiCentar = (SkiCentar) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().pronadjiSkiCentar(skiCentar);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_SKI_CENTAR);
            odgovor.setRezultat(skiCentar);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziSkiPas(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        SkiPas skiPas = (SkiPas) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            lista = Kontroler.getInstanca().pronadjiSkiPasove(skiPas);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_SKI_PAS);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor ucitajSkiPas(Zahtev zahtev) {
        SkiPas skiPas = (SkiPas) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().ucitajSkiPas(skiPas);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SKI_PAS);
            odgovor.setRezultat(skiPas);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor ucitajStazu(Zahtev zahtev) {
        Staza staza = (Staza) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().ucitajStazu(staza);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SKI_PAS);
            odgovor.setRezultat(staza);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor prijaviSe(Zahtev zahtev) {
        Korisnik korisnik = (Korisnik) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().prijaviSe(korisnik);
            odgovor.setIzvrsenaOperacija(Operacije.PRIJAVI_SE);
            odgovor.setRezultat(korisnik);
            trenutKorisnik = korisnik;
            Kontroler.getInstanca().dodajKorisnikaUTabelu(trenutKorisnik);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor registrujSe(Zahtev zahtev) {
        Korisnik korisnik = (Korisnik) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().registrujSe(korisnik);
            odgovor.setIzvrsenaOperacija(Operacije.REGISTRUJ_SE);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziZicare(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Zicara zicara = (Zicara) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            lista = Kontroler.getInstanca().pronadjiZicare(zicara);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_ZICARE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor obrisiStazu(Zahtev zahtev) {
        Staza staza = (Staza) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().obrisiStazu(staza);
            odgovor.setIzvrsenaOperacija(Operacije.OBRISI_STAZU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor obrisiZicaru(Zahtev zahtev) {
        Zicara zicara = (Zicara) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().obrisiZicaru(zicara);
            odgovor.setIzvrsenaOperacija(Operacije.OBRISI_ZICARU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor zapamtiSvePodatkeOSkiCentru(Zahtev zahtev) {
        Object[] parametar = (Object[]) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().zapamtiSvePodatkeOSkiCentru((SkiCentar) parametar[0], (List<Staza>) parametar[1], (List<Zicara>) parametar[2]);
            odgovor.setIzvrsenaOperacija(Operacije.ZAPAMTI_SVE_PODATKE_O_SKICENTRU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

}
