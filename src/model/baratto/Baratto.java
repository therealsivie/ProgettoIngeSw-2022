package model.baratto;

import model.offerta.Offerta;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Baratto {
    private Offerta offertaA;
    private Offerta offertaB;

    private LocalDateTime dataOraBaratto;

    private String utenteA;
    private String utenteB;

    private Appuntamento appuntamento;

    public Baratto(Offerta offertaA, Offerta offertaB, LocalDateTime dataOraBaratto) {
        this.offertaA = offertaA;
        this.offertaB = offertaB;
        this.dataOraBaratto = dataOraBaratto;
        this.utenteA = offertaA.getAutore();
        this.utenteB = offertaB.getAutore();
    }

    public Offerta getOffertaA() {
        return offertaA;
    }

    public Offerta getOffertaB() {
        return offertaB;
    }

    public LocalDateTime getDataOraBaratto() {
        return dataOraBaratto.truncatedTo(ChronoUnit.SECONDS);
    }

}
