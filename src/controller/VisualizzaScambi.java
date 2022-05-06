package controller;

public class VisualizzaScambi implements Action {
    @Override
    public boolean execute() throws ExitException {
        this.visualizzaScambi();
        return false;
    }

    private void visualizzaScambi() {
        //metodo per visualizzare gli scambi
        /**
         * visualizza, a beneficio del fruitore, il nome e
         * la descrizione delle categorie radice di tutte le gerarchie, nonché la piazza, i luoghi, i giorni
         * e gli intervalli orari in cui sono possibili gli scambi
         */
        System.out.println("\nvisualizza scambi");
    }
}
