/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.broker;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author UrosVesic
 */
public class BrokerBazePodataka {

    private Connection konekcija;

    public void connect() throws SQLException {
        DbFabrikaKonekcije.getInstanca().getKonekcija();
    }

    public void disconnect() throws SQLException {
        DbFabrikaKonekcije.getInstanca().getKonekcija().close();
    }

    public void commit() throws SQLException {
        DbFabrikaKonekcije.getInstanca().getKonekcija().commit();
    }

    public void rollback() throws SQLException {
        DbFabrikaKonekcije.getInstanca().getKonekcija().rollback();
    }

    public void vratiSve(List<OpstiDomenskiObjekat> lista, OpstiDomenskiObjekat odo) throws SQLException, Exception {

        try {
            String upit = "SELECT * FROM " + odo.vratiImeKlase();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat odo1 = odo.kreirajInstancu();
                odo1.napuni(rs);
                for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
                    pronadjiSlog(odo1.vratiVezaniObjekat(i));
                }

                lista.add(odo1);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void kreirajSlog(OpstiDomenskiObjekat odo) throws SQLException {
        String upit;
        vratiMaxID(odo);
        try {
            upit = "INSERT INTO " + odo.vratiImeKlase()
                    + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            statement.executeUpdate(upit);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void pamtiSlozeniSlog(OpstiDomenskiObjekat odo) throws SQLException {
        String upit;
        promeniSlog(odo);
        konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
        Statement statement = konekcija.createStatement();

        for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
            OpstiDomenskiObjekat vezo;
            //int z = 
            for (int j = 0; j < odo.vratiBrojSlogovaVezanogObjekta(i); j++) {
                vezo = odo.vratiSlogVezanogObjekta(i, j);
                upit = " INSERT INTO " + vezo.vratiImeKlase() + " VALUES (" + vezo.vratiVrednostiAtributa() + ")";
                statement.executeUpdate(upit);

            }
        }
        statement.close();
    }

    public void promeniSlog(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + odo.postaviVrednostiAtributa() + " WHERE " + odo.vratiUslovZaPromeniSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            statement.executeUpdate(upit);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void obrisiSlog(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String upit = "DELETE FROM" + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            statement.executeUpdate(upit);
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public void pronadjiSlog(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        try {
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            if (rs.next()) {
                odo.napuni(rs);
                for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
                    OpstiDomenskiObjekat vezo = odo.vratiVezaniObjekat(i);
                    //postavi pk za vezo
                    //vezo.postaviVrednostPK(odo.vratiVrednostSK(i));
                    pronadjiSlog(vezo);
                    odo.postaviVrednostVezanogObjekta(vezo, i);
                }
            } else {
                throw new Exception("Staza nije pronadjena");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void vratiMaxID(OpstiDomenskiObjekat odo) throws SQLException {
        String upit;
        upit = "SELECT Max(" + odo.vratiNazivPK() + ") AS " + odo.vratiNazivPK() + " FROM " + odo.vratiImeKlase();
        Connection konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        if (rs.next() == false) {
            odo.postaviPocetniBroj();
        } else {
            odo.povecajBroj(rs);
        }
    }

    public void pronadjiSlogove(List<OpstiDomenskiObjekat> lista, OpstiDomenskiObjekat odo) throws Exception {
        int j = 0;
        try {
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat odo1 = odo.kreirajInstancu();
                odo1.napuni(rs);
                lista.add(odo1);
                for (int i = 0; i < odo1.vratiBrojVezanihObjekata(); i++) {
                    OpstiDomenskiObjekat vezo = odo1.vratiVezaniObjekat(i);
                    //postavi pk za vezo
                    //vezo.postaviVrednostPK(odo1.vratiVrednostSK(i));
                    pronadjiSlog(vezo);
                    odo1.postaviVrednostVezanogObjekta(vezo, i);
                }
                j++;
            }
            if (j == 0) {
                throw new Exception(odo.vratiImeKlase() + " nije pronadjen/a");
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }
}
