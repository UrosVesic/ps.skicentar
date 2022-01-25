/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.kontrolerKi;

import domen.OpstiDomenskiObjekat;
import domen.SkiKarta;
import java.math.BigDecimal;
import java.util.ArrayList;
import klijent.forme.OpstaEkranskaForma;
import klijent.forme.modeli.ModelTabeleSkiKarte;
import klijent.forme.skiKarta.PretraziSkiKarteForma;
import klijent.validator.ValidationException;
import klijent.validator.Validator;

/**
 *
 * @author draskovesic
 */
public class KontrolerKIPretraziSkiKarte extends OpstiKontrolerKI {

    public KontrolerKIPretraziSkiKarte(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUDomenskiObjekat() {
        SkiKarta skiKarta = (SkiKarta) odo;
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        //if (!"".equals(pskf.getTxtGornjaCena().getText())) {
        skiKarta.setCenaSkiKarte(new BigDecimal(pskf.getTxtGornjaCena().getText()));
        //}
    }

    @Override
    public void KonvertujObjekatUGrafickeKomponente() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        ModelTabeleSkiKarte model = (ModelTabeleSkiKarte) pskf.getTblSkiKarte().getModel();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            SkiKarta skiKarta = (SkiKarta) opstiDomenskiObjekat;
            model.add(skiKarta);
        }

    }

    @Override
    public void isprazniGrafickiObjekat() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        ModelTabeleSkiKarte model = (ModelTabeleSkiKarte) pskf.getTblSkiKarte().getModel();
        model.setSkiKarte(new ArrayList<>());
    }

    public void pripremiTabelu() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        ModelTabeleSkiKarte model = new ModelTabeleSkiKarte();
        pskf.getTblSkiKarte().setModel(model);

    }

    @Override
    public void validirajPretragu() throws ValidationException {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        Validator.startValidation().validateNotNullOrEmpty(pskf.getTxtGornjaCena().getText(), "Cena je obavezna")
                .validateValueIsNumber(pskf.getTxtGornjaCena().getText(), "Cena mora biti broj").throwIfInvalide();
    }

}
