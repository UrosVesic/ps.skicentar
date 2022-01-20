/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author UrosVesic
 */
public class Odgovor implements Serializable{
    
    private boolean uspesno;
    private Object rezultat;
    private Exception exception;
    private int izvrsenaOperacija;

    public Odgovor() {
    }

    public Odgovor(boolean uspesno, Object rezultat, Exception exception, int izvrsenaOperacija) {
        this.uspesno = uspesno;
        this.rezultat = rezultat;
        this.exception = exception;
        this.izvrsenaOperacija = izvrsenaOperacija;
    }

    public int getIzvrsenaOperacija() {
        return izvrsenaOperacija;
    }

    public void setIzvrsenaOperacija(int izvrsenaOperacija) {
        this.izvrsenaOperacija = izvrsenaOperacija;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    
    
}
