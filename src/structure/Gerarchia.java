package structure;

import java.util.ArrayList;

public class Gerarchia {
    private String nome;
    private String descrizione;
    private Tree<Categoria> albero;

    public Gerarchia(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        new Tree<Categoria>(new Categoria(nome, descrizione));
    }

    public void inserisciCategoria(String nomeCategoria, String descrioneCategoria){
        albero
    }
}
