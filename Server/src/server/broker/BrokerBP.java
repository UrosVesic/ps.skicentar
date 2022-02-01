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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UrosVesic
 */
public class BrokerBP {

    private Connection konekcija;

    public void uspostaviKonekciju() throws Exception {
        DbFabrikaKonekcije.getInstanca().getKonekcija();
    }

    public void raskiniKonekciju() throws Exception {
        DbFabrikaKonekcije.getInstanca().getKonekcija().close();
    }

    public void potvrdiTransakciju() throws Exception {
        DbFabrikaKonekcije.getInstanca().getKonekcija().commit();
    }

    public void ponistiTransakciju() throws Exception {
        DbFabrikaKonekcije.getInstanca().getKonekcija().rollback();
    }

    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
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
        return lista;
    }

    public void kreirajSlog(OpstiDomenskiObjekat odo) throws Exception {
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

    public void promeniSlog(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        try {
            String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + odo.postaviVrednostiAtributa() + " WHERE " + odo.vratiUslovZaPromeniSlog();
            konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
            Statement statement = konekcija.createStatement();
            if (statement.executeUpdate(upit) == 0) {
                throw new Exception();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void obrisiSlog(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "DELETE FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
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
        } else {
            throw new Exception();
        }
    }

    public List<OpstiDomenskiObjekat> pronadjiSlozeneSlogove(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        konekcija = DbFabrikaKonekcije.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st1 = konekcija.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st2 = konekcija.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String upit = "SELECT * FROM " + odo.vratiImeKlase()
                + " WHERE " + odo.vratiUslovZaNadjiSlogove();
        ResultSet rs = st.executeQuery(upit);
        while (rs.next()) {
            OpstiDomenskiObjekat odo1 = odo.kreirajInstancu();
            odo1.napuni(rs);
            for (int i = 0; i < odo1.vratiBrojVezanihObjekata(); i++) {
                OpstiDomenskiObjekat vezo = odo1.vratiVezaniObjekat(i);
                upit = "SELECT COUNT(*) as brojStavki FROM " + vezo.vratiImeKlase() + " WHERE " + vezo.vratiUslovZaNadjiSlogove();
                ResultSet rs1 = st1.executeQuery(upit);
                rs1.next();
                int brojStavki = rs1.getInt("brojStavki");
                odo1.kreirajVezaniObjekat(brojStavki, i);
                //rs1.close();
                upit = "SELECT * FROM " + vezo.vratiImeKlase() + " WHERE " + vezo.vratiUslovZaNadjiSlogove();
                ResultSet rs2 = st2.executeQuery(upit);
                int brojSloga = 0;
                while (rs2.next()) {
                    odo1.napuni(rs2, brojSloga, i);
                    brojSloga++;
                }
                //rs2.close();
                for (int j = 0; j < brojSloga; j++) {
                    vezo = odo1.vratiSlogVezanogObjekta(i, j);
                    pronadjiSlog(vezo);
                }

            }

            lista.add(odo1);
        }
        if (lista.isEmpty()) {
            throw new Exception();
        }
        return lista;
    }

    public void vratiMaxID(OpstiDomenskiObjekat odo) throws Exception {
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
            Logger.getLogger(BrokerBP.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public List<OpstiDomenskiObjekat> pronadjiSlogove(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        int j = 0;
        try {
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlogove();
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
            /*if (j == 0) {
                throw new Exception(odo.vratiImeKlase() + " nije pronadjen/a");
            }*/

        } catch (SQLException ex) {
            throw ex;
        }
        return lista;
    }

    public void ubaciSlog(OpstiDomenskiObjekat odo) throws Exception {
        String upit;
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

}
