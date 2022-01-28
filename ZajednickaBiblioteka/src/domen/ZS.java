/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author draskovesic
 */
public class ZS implements Serializable, OpstiDomenskiObjekat {

    Staza staza;
    Zicara zicara;

    public ZS() {
    }

    public ZS(Staza staza, Zicara zicara) {
        this.staza = staza;
        this.zicara = zicara;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return staza.getBrojStaze() + ", " + zicara.getSifraZicare();
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "brojStaze = " + staza.getBrojStaze() + ", sifraZicare = " + zicara.getSifraZicare();
    }

    @Override
    public String vratiImeKlase() {
        return "ZS";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "brojStaze = " + staza.getBrojStaze() + "AND sifraZicare = " + zicara.getSifraZicare();
    }


    @Override
    public void napuni(ResultSet rs) throws SQLException {
        staza.setBrojStaze(rs.getLong("brojStaze"));
        zicara.setSifraZicare(rs.getLong("sifraZicare"));
    }

    @Override
    public String vratiNazivPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviPocetniBroj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new ZS();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 2;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return staza;
        }
        if (i == 1) {
            return zicara;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            staza = (Staza) vezo;
        }
        if (i == 1) {
            zicara = (Zicara) vezo;
        }
    }


}
