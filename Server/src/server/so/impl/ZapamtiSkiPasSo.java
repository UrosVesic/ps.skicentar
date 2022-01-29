/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.OpstiDomenskiObjekat;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.util.List;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author UrosVesic
 */
public class ZapamtiSkiPasSo extends OpstaSo {

    public ZapamtiSkiPasSo(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        //b.pamtiSlozeniSlog(odo);
        SkiPas skiPas = (SkiPas) odo;
        List<OpstiDomenskiObjekat> stavkeIzBaze = b.pronadjiSlogove(skiPas.getStavkeSkiPasa().get(0));
        b.promeniSlog(odo);

        for (StavkaSkiPasa stavka : skiPas.getStavkeSkiPasa()) {
            if (b.daLiPostojiSlog(stavka)) {
                b.promeniSlog(stavka);
            } else {
                b.ubaciSlog(stavka);
            }
        }

        for (OpstiDomenskiObjekat stavkaIzBaze : stavkeIzBaze) {
            if (!skiPas.getStavkeSkiPasa().contains(stavkaIzBaze)) {
                b.obrisiSlog(stavkaIzBaze);
            }
        }
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof SkiPas)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }
        SkiPas skiPas = (SkiPas) odo;
        Validator.startValidation().validateNotNull(skiPas.getDatumIzdavanja(), "Null datum izdavanja")
                .validateNotNull(skiPas.getImePrezimeKupca(), "Null ime i prezime kupca")
                .validateNotNull(skiPas.getUkupnaCena(), "Null ukupna cena")
                .validateGreaterThanZero(skiPas.getSifraSkiPasa(), "Sifra ski pasa manja od 1")
                .validateListIsNotEmpty(skiPas.getStavkeSkiPasa(), "Ski pas nema stavke")
                .throwIfInvalide();
    }

}
