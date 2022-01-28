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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import server.broker.BrokerBazePodataka;
import server.forme.KonfiguracijaBazeForma;
import server.forme.KonfiguracijaServeraForma;
import server.forme.ServerForm;
import server.konstante.ServerskeKonstante;
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
import server.so.impl.UcitajSkiPasSO;
import server.so.impl.UcitajStazuSO;
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

    public void pokreniServer(ServerForm serverskaForma) throws IOException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.SERVER_CONFIG_PATH));
        int port = -1;
        if (!"".equals(properties.getProperty("port"))) {
            port = Integer.parseInt(properties.getProperty("port"));
        } else {
            throw new Exception();
        }
        ServerSocket serverSocket = new ServerSocket(port);
        serverskaNit = new ServerskaNit(serverSocket);
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

    public List<OpstiDomenskiObjekat> ucitajListuSkiCentara() throws SQLException, Exception {
        OpstaSo so = new UcitajListuSkiCentaraSO(b, new SkiCentar());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void zapamtiStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new ZapamtiStazuSO(b, staza);
        so.opsteIzvrsenjeSo();

    }

    public List<OpstiDomenskiObjekat> pronadjiStaze(Staza staza) throws SQLException, Exception {
        OpstaSo so = new PretraziStazuSO(b, staza);
        so.opsteIzvrsenjeSo();
        return so.getLista();
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
        OpstaSo so = new PretraziSkiKarteSo(b, skiKarta);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void kreirajSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new KreirajSkiPasSO(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> ucitajListuSkiKarata() throws Exception {
        OpstaSo so = new UcitajListuSkiKarataSO(b, new SkiKarta());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void zapamtiSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new ZapamtiSkiPasSo(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> pronadjiSkiPasove(SkiPas skiPas) throws Exception {
        OpstaSo so = new PretraziSkiPasSo(b, skiPas);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void konfigurisiBazu(String url, String username, String password) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        properties.store(new FileOutputStream(ServerskeKonstante.DB_CONFIG_PATH), "");
    }

    public void procitajKonfiguracijuBaze(KonfiguracijaBazeForma kbf) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.DB_CONFIG_PATH));
        kbf.getTxtURL().setText(properties.getProperty("url"));
        kbf.getTxtUsername().setText(properties.getProperty("username"));
        kbf.getTxtPassword().setText(properties.getProperty("password"));
    }

    public void konfigurisiServer(String port) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("port", port);
        properties.store(new FileOutputStream(ServerskeKonstante.SERVER_CONFIG_PATH), port);

    }

    public void procitajKonfiguracijuServera(KonfiguracijaServeraForma ksf) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.SERVER_CONFIG_PATH));
        ksf.getTxtPort().setText(properties.getProperty("port"));
    }

    public void ucitajSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new UcitajSkiPasSO(b, skiPas);
        so.opsteIzvrsenjeSo();
    }

    public void ucitajStazu(Staza staza) throws Exception {
        OpstaSo so = new UcitajStazuSO(b, staza);
        so.opsteIzvrsenjeSo();
    }

}
