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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.broker.BrokerBazePodataka;
import server.so.OpstaSo;
import server.so.OpstaSo1;
import server.so.impl.KreirajSo;
import server.so.impl.PretraziSkiKarteSo;
import server.so.impl.PretraziSkiPasSo;
import server.so.impl.ZapamtiSo;
import server.so.impl.PretraziSo;
import server.so.impl.UcitajListuSo;
import server.so.impl.ZapamtiSkiPasSo;

/**
 *
 * @author UrosVesic
 */
public class Kontroler {

    private static Kontroler instanca;
    private final BrokerBazePodataka b;

    private Kontroler() {
        b = new BrokerBazePodataka();

    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void pronadjiSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new PretraziSo(b);
        so.opsteIzvrsenjeSo(skiCentar);
    }

    public void zapamtiSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new ZapamtiSo(b);
        try {

            so.opsteIzvrsenjeSo(skiCentar);
        } catch (SQLException ex) {
            throw ex;

        }
    }

    public void ucitajListuSkiCentara(List<OpstiDomenskiObjekat> skiCentri) throws SQLException, Exception {
        OpstaSo1 so = new UcitajListuSo(b);
        so.opsteIzvrsenjeSo(skiCentri, new SkiCentar());
    }

    public void zapamtiStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new ZapamtiSo(b);
        so.opsteIzvrsenjeSo(staza);

    }

    public void pronadjiStaze(Staza staza) throws SQLException, Exception {
        OpstaSo so = new PretraziSo(b);
        so.opsteIzvrsenjeSo(staza);
    }

    public void kreirajStazu(Staza staza) throws SQLException, Exception {
        OpstaSo so = new KreirajSo(b);
        so.opsteIzvrsenjeSo(staza);
    }

    public void kreirajSkiCentar(SkiCentar skiCentar) throws SQLException, Exception {
        OpstaSo so = new KreirajSo(b);
        so.opsteIzvrsenjeSo(skiCentar);
    }

    public void kreirajZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new KreirajSo(b);
        so.opsteIzvrsenjeSo(zicara);
    }

    public void zapamtiZicaru(Zicara zicara) throws Exception {
        OpstaSo so = new ZapamtiSo(b);
        so.opsteIzvrsenjeSo(zicara);
    }

    public void kreirajSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new KreirajSo(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void zapamtiSkiKartu(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new ZapamtiSo(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void pretraziSkiKarte(SkiKarta skiKarta) throws Exception {
        OpstaSo so = new PretraziSkiKarteSo(b);
        so.opsteIzvrsenjeSo(skiKarta);
    }

    public void kreirajSkiPas(SkiPas skiPas) throws Exception {
        OpstaSo so = new KreirajSo(b);
        so.opsteIzvrsenjeSo(skiPas);
    }

    public void ucitajListuSkiKarata(List<OpstiDomenskiObjekat> skiKarte) throws Exception {
        OpstaSo1 so = new UcitajListuSo(b);
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
