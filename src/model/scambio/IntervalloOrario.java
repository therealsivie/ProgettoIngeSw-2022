package model.scambio;

import java.time.LocalTime;

public class IntervalloOrario {
    private LocalTime oraInizio;
    private LocalTime oraFine;

    public IntervalloOrario(LocalTime oraInizio, LocalTime oraFine) {
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }
}
