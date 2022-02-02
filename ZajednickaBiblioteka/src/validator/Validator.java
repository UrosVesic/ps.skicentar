/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.SkiPas;
import domen.StavkaSkiPasa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Korisnik
 */
public class Validator {

    private final List<String> validationErros;

    private Validator() {
        validationErros = new ArrayList();
    }

    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateValueIsNumber(String value, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                new BigDecimal(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (NumberFormatException nfe) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateValueIsDate(String value, String pattern, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.parse(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (ParseException ex) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateListIsNotEmpty(List list, String errorMessage) throws ValidationException {
        if (list == null || list.isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

    public Validator validirajFormatRadnogVremena(String radnoVreme, String poruka) {
        int i = 0;
        if (radnoVreme == null) {
            return this;
        }
        String[] sati = radnoVreme.split("-");
        if (sati.length != 2) {
            this.validationErros.add(poruka);
            return this;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            sdf.parse(sati[0]);
            sdf.parse(sati[1]);
        } catch (ParseException ex) {
            i++;
            //this.validationErros.add(poruka);
        }
        sdf = new SimpleDateFormat("HH");
        try {
            sdf.parse(sati[0]);
            sdf.parse(sati[1]);
        } catch (ParseException ex) {
            i++;
            //this.validationErros.add(poruka);
        }
        if (i == 2) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validirajFormatMejla(String email, String poruka) {
        if (!email.contains("@")) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validateGreaterThanZero(long id, String poruka) {
        if (id <= 0) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validirajDaLiPostojeStavkeZaPeriod(StavkaSkiPasa stavka, SkiPas skiPas, String poruka) {
        for (StavkaSkiPasa stavkaPostojeca : skiPas.getStavkeSkiPasa()) {
            if (stavka.getZavrsetakVazenja() != null && stavka.getZavrsetakVazenja().after(stavkaPostojeca.getPocetakVazenja()) && stavka.getZavrsetakVazenja().before(stavkaPostojeca.getZavrsetakVazenja())) {
                this.validationErros.add(poruka);
                return this;
            }
            if (stavka.getPocetakVazenja() != null && stavka.getPocetakVazenja().after(stavkaPostojeca.getPocetakVazenja()) && stavka.getPocetakVazenja().before(stavkaPostojeca.getZavrsetakVazenja())) {
                this.validationErros.add(poruka);
                return this;
            }
            if (stavka.getPocetakVazenja() != null && stavka.getPocetakVazenja().compareTo(stavkaPostojeca.getPocetakVazenja()) == 0) {
                this.validationErros.add(poruka);
                return this;
            }
            if (stavka.getZavrsetakVazenja() != null && stavka.getZavrsetakVazenja().compareTo(stavkaPostojeca.getZavrsetakVazenja()) == 0) {
                this.validationErros.add(poruka);
                return this;
            }
            if (stavka.getPocetakVazenja().compareTo(stavkaPostojeca.getZavrsetakVazenja()) == 0) {
                this.validationErros.add(poruka);
                return this;
            }
        }
        return this;
    }

    public Validator validirajDaLiJeDatumStavkePosleIzdavanja(StavkaSkiPasa stavka, SkiPas skiPas, String poruka) {
        if (stavka.getPocetakVazenja().before(skiPas.getDatumIzdavanja())) {
            this.validationErros.add(poruka);
            return this;
        }
        return this;
    }

}
