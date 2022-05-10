package model.offerta;

import model.gerarchia.CampoNativo;
import model.gerarchia.Categoria;

import java.util.List;

public class Articolo {
    private String titolo;
    private Categoria categoria;
    private List<CampoNativo> listaCampiCompilati;
    private String autore/*username*/;

    private List<StatoOfferta> stati;
    private StatoOfferta statoCorrente;

    public Articolo(String titolo, Categoria categoria, List<CampoNativo> listaCampiCompilati, String autore) {
        this.titolo = titolo;
        this.categoria = categoria;
        this.listaCampiCompilati = listaCampiCompilati;
        this.autore = autore;
    }

    public void setStati(List<StatoOfferta> stati) {
        this.stati = stati;
    }

    public void setStatoCorrente(StatoOfferta statoCorrente) {
        this.statoCorrente = statoCorrente;
    }
}
