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
public class Zicara implements OpstiDomenskiObjekat, Serializable {

    private long SifraZicare;
    private String nazivZicare;
    private String radnoVreme;
    private int kapacitet;
    private boolean UFunkciji;
    private SkiCentar skiCentar;

    public Zicara() {
        nazivZicare="n/a";
        radnoVreme="00-00";
    }

    public Zicara(long SifraZicare, String NazivZicare, String RadnoVreme, int Kapacitet, boolean UFunkciji, SkiCentar skiCentar) {
        this.SifraZicare = SifraZicare;
        this.nazivZicare = NazivZicare;
        this.radnoVreme = RadnoVreme;
        this.kapacitet = Kapacitet;
        this.UFunkciji = UFunkciji;
        this.skiCentar = skiCentar;
    }

    public long getSifraZicare() {
        return SifraZicare;
    }

    public void setSifraZicare(long SifraZicare) {
        this.SifraZicare = SifraZicare;
    }

    public String getNazivZicare() {
        return nazivZicare;
    }

    public void setNazivZicare(String nazivZicare) {
        this.nazivZicare = nazivZicare;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public boolean isUFunkciji() {
        return UFunkciji;
    }

    public void setUFunkciji(boolean UFunkciji) {
        this.UFunkciji = UFunkciji;
    }

    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return SifraZicare + ", " + (nazivZicare == null ? null : "'" + nazivZicare + "'") + ", "
                + (radnoVreme == null ? null : "'" + radnoVreme + "'") + ", " + kapacitet + ", " + UFunkciji + ", " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraZicare = " + SifraZicare + ", " + "nazivZicare = " + (nazivZicare == null ? null : "'" + nazivZicare + "'") + ", "
                + "radnoVreme = " + (radnoVreme == null ? null : "'" + radnoVreme + "'") + ", " + "kapacitet = " + kapacitet + ", " + "uFunkciji = " + UFunkciji + ", "
                + "sifraSkiCentra = " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String vratiImeKlase() {
        return "Zicara";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "SifraZicare = " + SifraZicare;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        SifraZicare = rs.getLong("sifraZicare");
        nazivZicare = rs.getString("nazivZicare");
        radnoVreme = rs.getString("radnoVreme");
        kapacitet = rs.getInt("kapacitet");
        UFunkciji = rs.getBoolean("uFunkciji");
        skiCentar.setSifraSkiCentra(rs.getLong("sifraSkiCentra"));

    }

    @Override
    public String vratiNazivPK() {
        return "SifraZicare";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraZicare(rs.getLong("sifraZicare") + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setSifraZicare(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Zicara();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return skiCentar;//new SkiCentar();
        }
        return null;
    }

    @Override
    public void postaviVrednostPK(Object pk) {
        this.setSifraZicare((long) pk);
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            this.setSkiCentar((SkiCentar) vezo);
        }
    }

    @Override
    public Object vratiVrednostSK(int i) {
        if (i == 0) {
            return skiCentar.getSifraSkiCentra();
        }
        return null;
    }

    

}
