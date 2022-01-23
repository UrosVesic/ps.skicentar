/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.komunikacija;

import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author UrosVesic
 */
public class Komunikacija {

    static Komunikacija instanca;
    Socket socket;

    private Komunikacija() {

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public Odgovor kreirajSkiCentar(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor zapamtiSkiCentar(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();

    }

    public Odgovor pronadjiStazu(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor ucitajListuSkiCentara(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor zapamtiStazu(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor kreirajStazu(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor kreirajZicaru(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor zapamtiZicaru(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor kreirajSkiKartu(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor zapamtiSkiKartu(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor pretraziSkiKarte(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor kreirajSkiPas(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor ucitajListuSkiKarata(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor zapamtiSkiPas(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

    public Odgovor pozivSo(Zahtev zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return (Odgovor) new Primalac(socket).primi();
    }

}
