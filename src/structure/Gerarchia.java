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
        return categoriaRadice.toString();
    }
}
