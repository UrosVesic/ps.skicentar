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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author UrosVesic
 */
public class SkiPas implements OpstiDomenskiObjekat, Serializable {

    private long sifraSkiPasa;
    private BigDecimal ukupnaCena;
    private String imePrezimeKupca;
    private Date datumIzdavanja;
    private List<StavkaSkiPasa> stavkeSkiPasa;

    public SkiPas() {
        imePrezimeKupca = "";
        ukupnaCena = new BigDecimal(0);
        
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        datumIzdavanja = java.sql.Date.valueOf(sm.format(dDatum));
        
        stavkeSkiPasa = new ArrayList<>();
    }

    public SkiPas(long sifraSkiPasa, BigDecimal ukupnaCena, String imePrezimeKupca, Date datumIzdavanja, List<StavkaSkiPasa> stavkeSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
        this.ukupnaCena = ukupnaCena;
        this.imePrezimeKupca = imePrezimeKupca;
        this.datumIzdavanja = datumIzdavanja;
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    public List<StavkaSkiPasa> getStavkeSkiPasa() {
        return stavkeSkiPasa;
    }

    public void setStavkeSkiPasa(List<StavkaSkiPasa> stavkeSkiPasa) {
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    public long getSifraSkiPasa() {
        return sifraSkiPasa;
    }

    public void setSifraSkiPasa(long sifraSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public String getImePrezimeKupca() {
        return imePrezimeKupca;
    }

    public void setImePrezimeKupca(String imePrezimeKupca) {
        this.imePrezimeKupca = imePrezimeKupca;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = datumIzdavanja;
        datumIzdavanja = java.sql.Date.valueOf(sm.format(dDatum));
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public String vratiVrednostiAtributa() {

        return sifraSkiPasa + ", " + ukupnaCena + ", " + (imePrezimeKupca == null ? null : "'" + imePrezimeKupca + "'") + ", " + "'"+datumIzdavanja+"'";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraSkiPasa = " + sifraSkiPasa + ", " + "ukupnaCena = " + ukupnaCena + ", " + "imePrezimeKupca = " + (imePrezimeKupca == null ? null : "'" + imePrezimeKupca + "'") + ", " + "datumIzdavanja = '" +datumIzdavanja+"'";
    }

    @Override
    public String vratiImeKlase() {
        return "SkiPas";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiPasa = " + sifraSkiPasa;
    }

    @Override
    public void postaviVrednostPK(Object pk) {
        sifraSkiPasa = (long) pk;
    }

    
    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraSkiPasa = rs.getLong("sifraSkiPasa");
        ukupnaCena = rs.getBigDecimal("ukupnaCena");
        imePrezimeKupca = rs.getString("imePrezimeKupca");
        datumIzdavanja = new Date(rs.getDate("datumIzdavanja").getTime());

    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiPasa";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        sifraSkiPasa = rs.getLong("sifraSkiPasa") + 1;
    }

    @Override
    public void postaviPocetniBroj() {
        sifraSkiPasa = 1;
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiPas();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i < stavkeSkiPasa.size()) {
            return new StavkaSkiPasa();
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i < stavkeSkiPasa.size()) {
            stavkeSkiPasa.set(i, (StavkaSkiPasa) vezo);
        }
    }

    @Override
    public Object vratiVrednostSK(int i) {
        return stavkeSkiPasa.get(i).getRedniBroj();
    }

    @Override
    public int vratiBrojSlogovaVezanogObjekta(int i) {
        if (i == 0) {
            return stavkeSkiPasa.size();
        }
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiSlogVezanogObjekta(int i, int j) {
        if (i == 0) {
            return stavkeSkiPasa.get(j);
        }
        return null;
    }

}
