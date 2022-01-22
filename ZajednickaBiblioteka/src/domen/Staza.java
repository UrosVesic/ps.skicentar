/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import com.sun.corba.se.pept.broker.Broker;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author UrosVesic
 */
public class Staza implements OpstiDomenskiObjekat, Serializable {

    private long brojStaze;
    private String nazivStaze;
    private String tipStaze;
    private SkiCentar skiCentar;

    public Staza() {

    }

    public Staza(long brojStaze, String nazivStaze, String tipStaze, SkiCentar skiCentar) {
        this.brojStaze = brojStaze;
        this.nazivStaze = nazivStaze;
        this.tipStaze = tipStaze;
        this.skiCentar = skiCentar;
    }

    public long getBrojStaze() {
        return brojStaze;
    }

    public void setBrojStaze(long brojStaze) {
        this.brojStaze = brojStaze;
    }

    public String getNazivStaze() {
        return nazivStaze;
    }

    public void setNazivStaze(String nazivStaze) {
        this.nazivStaze = nazivStaze;
    }

    public String getTipStaze() {
        return tipStaze;
    }

    public void setTipStaze(String tipStaze) {
        this.tipStaze = tipStaze;
    }

    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return brojStaze + ", " + (nazivStaze == null ? null : "'" + nazivStaze + "'") + ", "
                + (tipStaze == null ? null : "'" + tipStaze + "'") + ", " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "brojStaze = " + brojStaze + ", " + "nazivStaze = " + (nazivStaze == null ? null : "'" + nazivStaze + "'") + ", "
                + "tipStaze = " + (tipStaze == null ? null : "'" + tipStaze + "'") + ", " + "sifraSkiCentra = " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String vratiImeKlase() {
        return "Staza";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        //return "brojStaze = " + brojStaze;
        return "nazivStaze LIKE '"+nazivStaze+"'";
    }

    @Override
    public String vratiUslovZaPromeniSlog() {
        return "brojStaze = " + brojStaze;
    }
    
    

    @Override
    public void napuni(ResultSet rs) throws SQLException {

        Staza s = (Staza) this;
        s.setBrojStaze(rs.getLong("brojStaze"));
        s.setNazivStaze(rs.getString("nazivStaze"));
        s.setTipStaze(rs.getString("tipStaze"));
        SkiCentar sc = new SkiCentar();
        sc.setSifraSkiCentra(rs.getLong("skiCentar"));
        /*try {
            new BrokerBazePodataka().pronadjiSlog(sc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        //kontroler.Kontroler.getInstanca().pronadjiSkiCentar(skiCentar);
        s.setSkiCentar(sc);
        //return new Staza(rs.getLong("brojStaze"), rs.getString("nazivStaze"), rs.getString("tipStaze"), null);

    }

    @Override
    public String vratiNazivPK() {
        return "brojStaze";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setBrojStaze(rs.getLong("brojStaze") + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        setBrojStaze(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Staza();
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
        brojStaze = (long) pk;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if(i==0){
            this.setSkiCentar((SkiCentar) vezo);
        }
    }

    @Override
    public Object vratiVrednostSK(int i) {
        if(i==0){
            return skiCentar.getSifraSkiCentra();
        }
        return null;
    }

    

}
