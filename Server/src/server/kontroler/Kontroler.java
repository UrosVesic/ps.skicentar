/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
 */
package server.kontroler;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiKarta;
import domen.SkiPas;
import domen.Staza;
import domen.Zicara;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.forme.ServerForm;
import server.niti.ServerskaNit;
import server.so.OpstaSo;
import server.so.impl.KreirajSkiCentarSO;
import server.so.impl.KreirajSkiKartuSO;
import server.so.impl.KreirajSkiPasSO;
import server.so.impl.KreirajStazuSO;
import server.so.impl.KreirajZicaruSO;
import server.so.impl.PretraziSkiCentarSO;
import server.so.impl.PretraziSkiKarteSo;
import server.so.impl.PretraziSkiPasSo;
import server.so.impl.PretraziStazuSO;
import server.so.impl.UcitajListuSkiCentaraSO;
import server.so.impl.UcitajListuSkiKarataSO;
import server.so.impl.ZapamtiSkiCentarSO;
import server.so.impl.ZapamtiSkiKartuSO;
import server.so.impl.ZapamtiSkiPasSo;
import server.so.impl.ZapamtiStazuSO;
import server.so.impl.ZapamtiZicaruSO;

/**
 *
 * @author UrosVesic
 */
public class Kontroler {

    private static Kontroler instanca;
    private final BrokerBazePodataka b;
    ServerskaNit serverskaNit;

    private Kontroler() {
        b = new BrokerBazePodataka();

    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void pokreniServer(ServerForm serverskaForma) throws IOException {
        serverskaNit = new ServerskaNit();
        serverskaNit.start();
        serverskaForma.getBtnPokreni().setEnabled(false);
        serverskaForma.getBtnZaustavi().setEnabled(true);
        serverskaForma.getLblStatusServera().setText("Server je pokrenut");
        serverskaForma.getLblStatusServera().setForeground(Color.GREEN);
    }

    public void zaustaviServer(ServerForm serverskaForma) throws IOException {
        serverskaNit.zaustavi();
        serverskaForma.getBtnPokreni().setEnabled(true);
        serverskaForma.getBtnZaustavi().setEnabled(false);
        serverskaForma.getLblStatusServera().setText("Server je zaustavljen");
        serverskaForma.getLblStatusServera().setForeground(Color.RED);
    }

    public void pronadjiSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new PretraziSkiCentarSO(b, skiCentar);
        so.opsteIzvrsenjeSo();
    }

    public void zapamtiSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new ZapamtiSkiCentarSO(b, skiCentar);
        so.opsteIzvrsenjeSo();
    }

    public void ucitajListuSkiCentara(List<OpstiDomenskiObjekat> skiCentri) throws SQLException, Exception {
        OpstaSo so = new UcitajListuSkiCentaraSO(b, new SkiCentar(), skiCentri);
        so.opsteIzvrsenjeSo();
    }

    public void zapamtiStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new ZapamtiStazuSO(b, staza);
        so.opsteIzvrsenjeSo();

    }

    public List<OpstiDomenskiObjekat> pronadjiStaze(Staza staza) throws SQLException, Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        OpstaSo so = new PretraziStazuSO(b, staza, lista);
        so.opsteIzvrsenjeSo();
        return lista;
    }

    public void kreirajStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new KreirajStazuSO(b, staza);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new KreirajSkiCentarSO(b, skiCentar);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new KreirajZicaruSO(b, zicara);
        so.opsteIzvrsenjeSo();
    }

    public void zapamtiZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new ZapamtiZicaruSO(b, zicara);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new KreirajSkiKartuSO(b, skiKarta);
        so.opsteIzvrsenjeSo();
    }

    public void zapamtiSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new ZapamtiSkiKartuSO(b, skiKarta);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> pretraziSkiKarte(SkiKarta skiKarta) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        OpstaSo so = new PretraziSkiKarteSo(b, skiKarta, lista);
        so.opsteIzvrsenjeSo();
        return lista;
    }

    public void kreirajSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new KreirajSkiPasSO(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

    public void ucitajListuSkiKarata(List<OpstiDomenskiObjekat> skiKarte) throws Exception {
        OpstaSo so = new UcitajListuSkiKarataSO(b, new SkiKarta(), skiKarte);
        so.opsteIzvrsenjeSo();
    }

    public void zapamtiSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new ZapamtiSkiPasSo(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

    public void pronadjiSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new PretraziSkiPasSo(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

}
