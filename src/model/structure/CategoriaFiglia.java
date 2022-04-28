package model.structure;

import java.util.ArrayList;

public class CategoriaFiglia extends Categoria{

    public CategoriaFiglia(String nome, String descrizione, ArrayList<CampoNativo> campi,CategoriaPadre catPadre){
        this.setNome(nome);
        this.setDescrizione(descrizione);
        this.setCampi(campi);
        this.setFather(catPadre);

    }
    public CategoriaPadre convertiCategoria(){
        this.getPadre().removeFiglio(this);
        return new CategoriaPadre(this.getNome(), this.getDescrizione(), this.getCampi(), null);
    }
}
