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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void pamtiSlozeniSlog(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        String upit;
        promeniSlog(odo);
        konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
        Statement statement = konekcija.createStatement();
        for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
            OpstiDomenskiObjekat vezo;
            for (int j = 0; j < odo.vratiBrojSlogovaVezanogObjekta(i); j++) {
                vezo = odo.vratiSlogVezanogObjekta(i, j);
                try {
                    if (daLiPostojiSlog(vezo)) {
                        promeniSlog(vezo);
                    } else {
                        upit = "INSERT INTO " + vezo.vratiImeKlase() + " VALUES (" + vezo.vratiVrednostiAtributa() + ")";
                        statement.executeUpdate(upit);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw ex;
                }

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
            ex.printStackTrace();
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
                    pronadjiSlog(vezo);
                    odo.postaviVrednostVezanogObjekta(vezo, i);
                }
            } else {
                throw new Exception("Slog nije pronadjen\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean daLiPostojiSlog(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        try {
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void pronadjiSlozenSlog(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String upit = "SELECT * FROM " + odo.vratiImeKlase()
                + " WHERE " + odo.vratiUslovZaNadjiSlog();
        ResultSet rs = st.executeQuery(upit);
        if (rs.next()) {
            odo.napuni(rs);
            for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
                OpstiDomenskiObjekat vezo = odo.vratiVezaniObjekat(i);
                upit = "SELECT COUNT(*) as brojStavki FROM " + vezo.vratiImeKlase() + " WHERE " + vezo.vratiUslovZaNadjiSlogove();
                rs = st.executeQuery(upit);
                rs.next();
                int brojStavki = rs.getInt("brojStavki");
                odo.kreirajVezaniObjekat(brojStavki, i);
                upit = "SELECT * FROM " + vezo.vratiImeKlase() + " WHERE " + vezo.vratiUslovZaNadjiSlogove();
                rs = st.executeQuery(upit);
                int brojSloga = 0;
                while (rs.next()) {
                    odo.napuni(rs, brojSloga, i);
                    brojSloga++;
                }
                for (int j = 0; j < brojSloga; j++) {
                    vezo = odo.vratiSlogVezanogObjekta(i, j);
                    pronadjiSlog(vezo);
                }

            }
        }
        else{
            //ne postoji slog u bazi
        }
    }

    public void vratiMaxID(OpstiDomenskiObjekat odo) throws SQLException {
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
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
