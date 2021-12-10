package ba.unsa.etf.rpr;

public class AromaticnaBiljka extends Biljka{
    public AromaticnaBiljka(String naziv, int jacina) {
        super(naziv, jacina);
    }

    @Override
    public String toString() {
        return "Aroma: " + getNaziv() + " - " + getJacina();
    }

    @Override
    public String konzumiraj() {
        return "[AROMA +" + getJacina() + "]";

    }
}
