/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author UrosVesic
 */
public interface OpstiDomenskiObjekat {

    String vratiVrednostiAtributa();

    String postaviVrednostiAtributa();

    String vratiImeKlase();

    String vratiUslovZaNadjiSlog();

    default String vratiUslovZaPromeniSlog() {
        return vratiUslovZaNadjiSlog();
    }

    default int vratiBrojSlogovaVezanogObjekta(int i) {
        return 0;
    }

    //Object vratiVrednostSK();
    void postaviVrednostPK(Object pk);

    void napuni(ResultSet rs) throws SQLException;

    String vratiNazivPK();

    void povecajBroj(ResultSet rs) throws SQLException;

    void postaviPocetniBroj();

    public OpstiDomenskiObjekat kreirajInstancu();

    public int vratiBrojVezanihObjekata();

    public OpstiDomenskiObjekat vratiVezaniObjekat(int i);

    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i);

    public Object vratiVrednostSK(int i);

    default OpstiDomenskiObjekat vratiSlogVezanogObjekta(int i, int j){
        return null;
    }
}
