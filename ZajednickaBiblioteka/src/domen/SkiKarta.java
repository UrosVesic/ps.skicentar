/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author UrosVesic
 */
public class SkiKarta implements OpstiDomenskiObjekat, Serializable {

    private long sifraSkiKarte;
    private String vrstaSkiKarte;
    private BigDecimal cenaSkiKarte;
    private SkiCentar skiCentar;

    public SkiKarta() {
        cenaSkiKarte = new BigDecimal(0);
    }

    public SkiKarta(long sifraSkiKarte, String vrstaSkiKarte, BigDecimal cenaSkiKarte, SkiCentar skiCentar) {
        this.sifraSkiKarte = sifraSkiKarte;
        this.vrstaSkiKarte = vrstaSkiKarte;
        this.cenaSkiKarte = cenaSkiKarte;
        this.skiCentar = skiCentar;
    }

    public long getSifraSkiKarte() {
        return sifraSkiKarte;
    }

    public void setSifraSkiKarte(long sifraSkiKarte) {
        this.sifraSkiKarte = sifraSkiKarte;
    }

    public String getVrstaSkiKarte() {
        return vrstaSkiKarte;
    }

    public void setVrstaSkiKarte(String vrstaSkiKarte) {
        this.vrstaSkiKarte = vrstaSkiKarte;
    }

    public BigDecimal getCenaSkiKarte() {
        return cenaSkiKarte;
    }

    public void setCenaSkiKarte(BigDecimal cenaSkiKarte) {
        this.cenaSkiKarte = cenaSkiKarte;
    }

    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return sifraSkiKarte + ", " + (vrstaSkiKarte == null ? null : "'" + vrstaSkiKarte + "'") + ", " + cenaSkiKarte + ", " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraSkiKarte = " + sifraSkiKarte + ", " + "vrstaSkiKarte = " + (vrstaSkiKarte == null ? null : "'" + vrstaSkiKarte + "'") + ", "
                + "cenaSkiKarte = " + cenaSkiKarte + ", " + "sifraSkiCentra  = " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String vratiImeKlase() {
        return "SkiKarta";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiKarte=  " + sifraSkiKarte;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "cenaSkiKarte <= " + cenaSkiKarte;
    }

    @Override
    public void postaviVrednostPK(Object pk) {
        sifraSkiKarte = (long) pk;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraSkiKarte = rs.getLong("sifraSkiKarte");
        vrstaSkiKarte = rs.getString("vrstaSkiKarte");
        cenaSkiKarte = rs.getBigDecimal("cenaSkiKarte");
        skiCentar = new SkiCentar();
        skiCentar.setSifraSkiCentra(rs.getLong("sifraSkiCentra"));
    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiKarte";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraSkiKarte(rs.getLong("sifraSkiKarte") + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setSifraSkiKarte(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiKarta();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return skiCentar;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            this.skiCentar = (SkiCentar) vezo;
        }
    }

    @Override
    public Object vratiVrednostSK(int i) {
        if (i == 0) {
            return skiCentar.getSifraSkiCentra();
        }
        return null;
    }

    @Override
    public String vratiUslovZaPromeniSlog() {
        return "sifraSkiKarte= " + sifraSkiKarte;
    }

    @Override
    public String toString() {
        return skiCentar + " " + vrstaSkiKarte;
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
        final SkiKarta other = (SkiKarta) obj;
        if (this.sifraSkiKarte != other.sifraSkiKarte) {
            return false;
        }
        return true;
    }

}
