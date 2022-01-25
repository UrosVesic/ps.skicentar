/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.niti;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
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

    public KlijentskaNit(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Zahtev zahtev = (Zahtev) new Primalac(socket).primi();
                obradiZahtev(zahtev);
            } catch (Exception ex) {
                //ex.printStackTrace();
                //System.out.println(ex.getMessage());
            }
        }
    }

    void zaustavi() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obradiZahtev(Zahtev zahtev) throws Exception {
        Odgovor odgovor = null;
        switch (zahtev.getOperacija()) {
            case Operacije.KREIRAJ_SKI_CENTAR:
                odgovor = kreirajSkiCentar(zahtev);
                break;
            case Operacije.ZAPAMTI_SKI_CENTAR:
                odgovor = zapamtiSkiCentar(zahtev);
                break;
            case Operacije.PRETRAZI_STAZU:
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
            odgovor.setRezultat(skiCentar);
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
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_STAZU);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor ucitajListuSkiCentara(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista = (List<OpstiDomenskiObjekat>) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().ucitajListuSkiCentara(lista);
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
            odgovor.setRezultat(staza);
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
            odgovor.setRezultat(zicara);
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
            odgovor.setRezultat(skiKarta);
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
        List<OpstiDomenskiObjekat> skiKarteOdo = new ArrayList<>();
        List<SkiKarta> skiKarte = new ArrayList<>();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().ucitajListuSkiKarata(skiKarteOdo);
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : skiKarteOdo) {
                skiKarte.add((SkiKarta) opstiDomenskiObjekat);
            }
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_LISTU_SKI_KARATA);
            odgovor.setRezultat(skiKarte);
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
            odgovor.setRezultat(skiPas);
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
        SkiPas skiPas = (SkiPas) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().pronadjiSkiPas(skiPas);
            odgovor.setIzvrsenaOperacija(Operacije.PRETRAZI_SKI_PAS);
            odgovor.setRezultat(skiPas);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            ex.printStackTrace();
            odgovor.setException(ex);
        }
        return odgovor;
    }

}
