package structure;

import java.util.ArrayList;
import java.util.TreeMap;

public class Gerarchia {
    //si assume che la categoria radice di una gerarchia sia la radice della gerarchia stessa
    //(la creazione di una nuova gerarchia implica la creazione della sua categoria radice)
    private TreeMap<Integer, Categoria> struttura;

    public Gerarchia(String nome, String descrizione) {
        Categoria radice = new Categoria(nome, descrizione);
        struttura = new TreeMap<Integer, Categoria>();
        struttura.put(1, radice); //UNO perchè nodo radice, seguiranno i successivi numeri naturali per i figli
    }

    public void inserisciCategoria(Integer nodo, Categoria daInserire){
        struttura.put(nodo, daInserire);
        //albero
    }
}
