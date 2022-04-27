package model.structure;

import java.util.ArrayList;

public class CategoriaFiglia extends Categoria{
    public CategoriaFiglia(String nome, String descrizione, CategoriaPadre catPadre){
        this.setNome(nome);
        this.setDescrizione(descrizione);
        this.setFather(catPadre);

    }
    public CategoriaPadre convertiCategoria(ArrayList<CampoNativo> campi){
        this.getPadre().removeFiglio(this);
        return new CategoriaPadre(this.getNome(), this.getDescrizione(), campi, null);
    }
}
