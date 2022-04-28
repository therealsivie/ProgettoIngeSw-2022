package model.structure;

import java.util.ArrayList;

public class CategoriaDB {
    private String nome;
    private String descrizione;
    private ArrayList <CampoNativo> campi;
    private Categoria padre;


    public CategoriaDB(String nome, String descrizione, ArrayList<CampoNativo> campi,Categoria padre) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campi = campi;
        this.padre = padre;
    }

    public Categoria getPadre() {
        return padre;
    }
}
