package structure;

import java.util.TreeMap;

public class Gerarchia {
    private final Categoria categoriaRadice;

    public Gerarchia(Categoria categoria) {
        this.categoriaRadice = categoria;
    }

    public Categoria getCategoriaRadice() {
        return this.categoriaRadice;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Nome: ").append(categoriaRadice.getNome())
                .append("Descrizione: ").append(categoriaRadice.getDescrizione());
        return str.toString();
    }
}
