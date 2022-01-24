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
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.forme.ServerForm;
import server.niti.ServerskaNit;
import server.so.OpstaSo;
import server.so.OpstaSo1;
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
        OpstaSo so = new PretraziSkiCentarSO(b);
        so.opsteIzvrsenjeSo(skiCentar);
    }

    public void zapamtiSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new ZapamtiSkiCentarSO(b);
        try {

            so.opsteIzvrsenjeSo(skiCentar);
        } catch (SQLException ex) {
            throw ex;

        }
    }

    public void ucitajListuSkiCentara(List<OpstiDomenskiObjekat> skiCentri) throws SQLException, Exception {
        OpstaSo1 so = new UcitajListuSkiCentaraSO(b);
        so.opsteIzvrsenjeSo(skiCentri, new SkiCentar());
    }

    public void zapamtiStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new ZapamtiStazuSO(b);
        so.opsteIzvrsenjeSo(staza);

    }

    public void pronadjiStaze(Staza staza) throws SQLException, Exception {
        OpstaSo so = new PretraziStazuSO(b);
        so.opsteIzvrsenjeSo(staza);
    }

    public void kreirajStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new KreirajStazuSO(b);
        so.opsteIzvrsenjeSo(staza);
    }

    public void kreirajSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new KreirajSkiCentarSO(b);
        so.opsteIzvrsenjeSo(skiCentar);
    }

    public void kreirajZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new KreirajZicaruSO(b);
        so.opsteIzvrsenjeSo(zicara);
    }

    public void zapamtiZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new ZapamtiZicaruSO(b);
        so.opsteIzvrsenjeSo(zicara);
    }

    public void kreirajSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new KreirajSkiKartuSO(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void zapamtiSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new ZapamtiSkiKartuSO(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void pretraziSkiKarte(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new PretraziSkiKarteSo(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void kreirajSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new KreirajSkiPasSO(b);
        so.opsteIzvrsenjeSo(skiPas);
    }

    public void ucitajListuSkiKarata(List<OpstiDomenskiObjekat> skiKarte) throws Exception {
        OpstaSo1 so = new UcitajListuSkiKarataSO(b);
        so.opsteIzvrsenjeSo(skiKarte, new SkiKarta());
    }

    public void zapamtiSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new ZapamtiSkiPasSo(b);
        so.opsteIzvrsenjeSo(skiPas);
    }

    public void pronadjiSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new PretraziSkiPasSo(b);
        so.opsteIzvrsenjeSo(skiPas);
    }

}
