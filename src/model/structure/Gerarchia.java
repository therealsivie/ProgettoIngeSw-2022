package model.structure;

public class Gerarchia {
    private Categoria radice;

    public Gerarchia(Categoria radice) {
        this.radice = radice;
    }

    public String getNomeRadice(){
        return this.radice.getNome();
    }

    @Override
    public String toString() {
        return radice.toString();
    }

    public void printGerarchia(){
        System.out.println(this);
    }
}
