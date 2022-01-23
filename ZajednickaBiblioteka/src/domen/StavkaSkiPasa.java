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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author UrosVesic
 */
public class StavkaSkiPasa implements OpstiDomenskiObjekat, Serializable {

    //VrednostStavke, PocetakVazenja, ZavrsetakVazenja, SifraSkiKarte
    private SkiPas skiPas;
    private long redniBroj;
    private BigDecimal vrednostStavke;
    private Date pocetakVazenja;
    private Date zavrsetakVazenja;
    private SkiKarta skiKarta;

    public StavkaSkiPasa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));
        zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));

    }

    public StavkaSkiPasa(SkiPas skiPas, long redniBroj, BigDecimal vrednostStavke, Date pocetakVazenja, Date zavrsetakVazenja, SkiKarta skiKarta) {
        this.skiPas = skiPas;
        this.redniBroj = redniBroj;
        this.vrednostStavke = vrednostStavke;
        this.pocetakVazenja = pocetakVazenja;
        this.zavrsetakVazenja = zavrsetakVazenja;
        this.skiKarta = skiKarta;
    }

    public SkiKarta getSkiKarta() {
        return skiKarta;
    }

    public void setSkiKarta(SkiKarta skiKarta) {
        this.skiKarta = skiKarta;
    }

    public SkiPas getSkiPas() {
        return skiPas;
    }

    public void setSkiPas(SkiPas skiPas) {
        this.skiPas = skiPas;
    }

    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public BigDecimal getVrednostStavke() {
        return vrednostStavke;
    }

    public void setVrednostStavke(BigDecimal vrednostStavke) {
        this.vrednostStavke = vrednostStavke;
    }

    public Date getPocetakVazenja() {
        return pocetakVazenja;
    }

    public void setPocetakVazenja(Date pocetakVazenja) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = pocetakVazenja;
        this.pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));
    }

    public Date getZavrsetakVazenja() {
        return zavrsetakVazenja;
    }

    public void setZavrsetakVazenja(Date zavrsetakVazenja) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = zavrsetakVazenja;
        this.zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));
    }

    @Override
    public String vratiVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatumZ = zavrsetakVazenja;
        Date dDatumP = pocetakVazenja;
        this.zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatumZ));
        this.pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatumP));
        System.out.println(pocetakVazenja);
        System.out.println(zavrsetakVazenja);
        return (skiPas == null ? null : skiPas.getSifraSkiPasa()) + ", " + redniBroj + ", " + vrednostStavke + ", '" + pocetakVazenja + "'"
                + ", '" + zavrsetakVazenja + "'" + ", " + (skiKarta == null ? null : skiKarta.getSifraSkiKarte());
    }

    @Override
    public String postaviVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatumZ = zavrsetakVazenja;
        Date dDatumP = pocetakVazenja;
        this.zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatumZ));
        this.pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatumP));
        System.out.println(pocetakVazenja);
        System.out.println(zavrsetakVazenja);
        return "sifraSkiPasa= " + (skiPas == null ? null : skiPas.getSifraSkiPasa()) + ", " + "redniBroj = " + redniBroj + ", " + "vrednostStavke = "
                + vrednostStavke + ", " + "pocetakVazenja = " + "'" + pocetakVazenja + "'" + ", " + "zavrsetakVazenja = "
                + "'" + zavrsetakVazenja + "'" + ", " + "sifraSkiKarte" + (skiKarta == null ? null : skiKarta.getSifraSkiKarte());
    }

    @Override
    public String vratiImeKlase() {
        return "stavkaSkiPasa";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiPasa = " + skiPas.getSifraSkiPasa() + " AND " + "redniBroj = " + redniBroj;
    }

    @Override
    public void postaviVrednostPK(Object pk) {
        redniBroj = (long) pk;
    }

    
    @Override
    public void napuni(ResultSet rs) throws SQLException {
        skiPas.setSifraSkiPasa(rs.getLong("sifraSkiPasa"));
        redniBroj = rs.getLong("redniBroj");
        vrednostStavke = rs.getBigDecimal("vrednostStavke");
        pocetakVazenja = new Date(rs.getDate("pocetakVazenja").getTime());
        zavrsetakVazenja = new Date(rs.getDate("zavrsetakVazenja").getTime());
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
        return new StavkaSkiPasa();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 2;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return new SkiPas();
        }
        if (i == 1) {
            return new SkiKarta();
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            skiPas = (SkiPas) vezo;
        }
        if (i == 1) {
            skiKarta = (SkiKarta) vezo;
        }
    }

    @Override
    public Object vratiVrednostSK(int i) {
        if (i == 0) {
            return skiPas.getSifraSkiPasa();
        }
        if (i == 1) {
            return skiKarta.getSifraSkiKarte();
        }
        return null;
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
        final StavkaSkiPasa other = (StavkaSkiPasa) obj;
        if (!Objects.equals(this.pocetakVazenja, other.pocetakVazenja)) {
            return false;
        }
        if (!Objects.equals(this.skiKarta, other.skiKarta)) {
            return false;
        }
        return true;
    }

}
