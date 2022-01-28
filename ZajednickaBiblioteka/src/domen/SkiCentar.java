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
 * @author UrosVesic
 */
public class SkiCentar implements OpstiDomenskiObjekat, Serializable {

    private long sifraSkiCentra;
    private String nazivSkiCentra;
    private String nazivPlanine;
    private String radnoVreme;

    public SkiCentar() {
        
    }

    public SkiCentar(long sifraSkiCentra) {
        this.sifraSkiCentra = sifraSkiCentra;
    }

    public SkiCentar(long sifraSkiCentra, String nazivSkiCentra, String nazivPlanine, String radnoVreme) {
        this.sifraSkiCentra = sifraSkiCentra;
        this.nazivSkiCentra = nazivSkiCentra;
        this.nazivPlanine = nazivPlanine;
        this.radnoVreme = radnoVreme;
    }

    public long getSifraSkiCentra() {
        return sifraSkiCentra;
    }

    public void setSifraSkiCentra(long sifraSkiCentra) {
        this.sifraSkiCentra = sifraSkiCentra;
    }

    public String getNazivSkiCentra() {
        return nazivSkiCentra;
    }

    public void setNazivSkiCentra(String nazivSkiCentra) {
        this.nazivSkiCentra = nazivSkiCentra;
    }

    public String getNazivPlanine() {
        return nazivPlanine;
    }

    public void setNazivPlanine(String nazivPlanine) {
        this.nazivPlanine = nazivPlanine;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return sifraSkiCentra + ", " + (nazivSkiCentra == null ? null : "'" + nazivSkiCentra + "'") + ", "
                + (nazivPlanine == null ? null : "'" + nazivPlanine + "'") + ", " + (radnoVreme == null ? null : "'" + radnoVreme + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraSkiCentra = " + sifraSkiCentra + ", " + "nazivSkiCentra = " + (nazivSkiCentra == null ? null : "'" + nazivSkiCentra + "'") + ", " + "nazivPlanine = " + (nazivPlanine == null ? null : "'" + nazivPlanine + "'") + ", " + "radnoVreme = " + (radnoVreme == null ? null : "'" + radnoVreme + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "SkiCentar";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiCentra = " + sifraSkiCentra;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        SkiCentar sc = (SkiCentar) this;
        sc.setSifraSkiCentra(rs.getLong("sifraSkiCentra"));
        sc.setNazivSkiCentra(rs.getString("nazivSkiCentra"));
        sc.setNazivPlanine(rs.getString("nazivPlanine"));
        sc.setRadnoVreme(rs.getString("radnoVreme"));
        //return new SkiCentar(rs.getLong("sifraSkiCentra"), rs.getString("nazivSkiCentra"), rs.getString("nazivPlanine"), rs.getString("radnoVreme"));
    }

    @Override
    public String toString() {
        return nazivSkiCentra;//super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiCentra";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraSkiCentra(rs.getLong(vratiNazivPK()) + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setSifraSkiCentra(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiCentar();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SkiCentar other = (SkiCentar) obj;
        if (this.sifraSkiCentra != other.sifraSkiCentra) {
            return false;
        }
        return true;
    }

}
