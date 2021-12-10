package ba.unsa.etf.rpr;

public abstract class Biljka implements Konzumabilno{
    String naziv;
    int jacina;

    private static boolean validnostJacine(int jacina){
        return jacina>=0;
    }
    public Biljka(String naziv, int jacina) {
        if(!validnostJacine(jacina))
            throw new IllegalArgumentException("Jacina mora biti nenegativan cijeli broj");
        this.naziv = naziv;
        this.jacina = jacina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getJacina() {
        return jacina;
    }

    public void setJacina(int jacina) {
        if(!validnostJacine(jacina))
            throw new IllegalArgumentException("Jacina mora biti nenegativan cijeli broj");
        this.jacina = jacina;
    }
    @Override
    public abstract String toString();
}
