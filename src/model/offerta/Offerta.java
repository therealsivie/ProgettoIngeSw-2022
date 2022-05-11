package model.offerta;

import model.gerarchia.Categoria;

import java.util.List;

public class Offerta {
    private String titolo;
    private Categoria categoria;
    private List<CampoCompilato> listaCampiCompilati;
    private String autore;
    private List<StatoOfferta> stati;
    private StatoOfferta statoCorrente;

    public Offerta(String titolo, Categoria categoria, String autore) {
        this.titolo = titolo;
        this.categoria = categoria;
        this.autore = autore;
    }

    public void setStati(List<StatoOfferta> stati) {
        this.stati = stati;
    }

    public void setStatoCorrente(StatoOfferta statoCorrente) {
        this.statoCorrente = statoCorrente;
    }

    public void addCampiCompilati(List<CampoCompilato> listaCampiCompilati){
        this.listaCampiCompilati = listaCampiCompilati;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }
}
