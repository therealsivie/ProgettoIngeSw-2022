package model.structure;

public class Gerarchia {
    private final Categoria categoriaRadice;

    public Gerarchia(CategoriaPadre categoria) {
        this.categoriaRadice = categoria;
    }

    public Categoria getCategoriaRadice() {
        return this.categoriaRadice;
    }

    public String toString(){
        return categoriaRadice.toString();
    }

    public boolean nomeRipetuto(String nome){
        return this.categoriaRadice.checkNomeRipetuto(nome);
    }
}
