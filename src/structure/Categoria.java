package structure;

import java.util.ArrayList;

public class Categoria {
    private String nome;
    private String descrizione;
    private ArrayList<CampoNativo> campi;

    public Categoria(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }
    /*
    public Categoria(String nome, String descrizione, ArrayList<Categoria> categorieFiglie) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categorieFiglie = categorieFiglie;
    }

    public void setCategorieFiglie(ArrayList<Categoria> categorieFiglie) {
        this.categorieFiglie = categorieFiglie;
    }

     */
}
