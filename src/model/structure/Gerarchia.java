package model.structure;

import java.util.ArrayList;

public class Gerarchia {
    private String nomeRadice;
    private ArrayList<CategoriaDB> categorie;

    public Gerarchia(CategoriaPadre categoria) {
        this.categorie = new ArrayList<CategoriaDB>();
        this.nomeRadice = categoria.getNome();
        this.categorie.add(new CategoriaDB(categoria.getNome(), categoria.getDescrizione(), categoria.getCampi(), categoria.getPadre()));
    }

    public Categoria getRadice() {
        return this.categorie.get(0).getPadre();
    }

    public String toString(){
        if(this.categorie.get(0).getPadre() == null)
            return "";
        else {
            return this.categorie.get(0).getPadre().toString();
        }
    }

    public boolean nomeRipetuto(String nome){
        return this.categorie.get(0).getPadre().checkNomeRipetuto(nome);
    }

    public void addCategoria(Categoria categoria){
        categorie.add(new CategoriaDB(categoria.getNome(), categoria.getDescrizione(), categoria.getCampi() ,categoria.getPadre()));
    }

    public String getNomeRadice() {
        return this.nomeRadice;
    }
}
