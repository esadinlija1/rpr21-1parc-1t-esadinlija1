package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Objects;



public class Eliksir implements Konzumabilno,Comparable{
    String naziv;
    public enum TipEliksira {
        BOOSTER(),
        LIJEK(),
        OTROV(),
        PARFEM();
    };
    int jacina;
    ArrayList<Biljka> biljke;
    Eliksir(String naziv, ArrayList<Biljka> biljke){
        if(naziv==" ")
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez naziva");
        if(biljke==null)
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez biljaka");
        this.naziv=naziv;
        this.biljke=biljke;
    }

    public String getNaziv() {
        return naziv;
    }

    public TipEliksira getTipEliksira() {
        int sumaJacinaLjekovitih,sumaJacinaAroma,sumaJacinaOtrova;
        sumaJacinaAroma=biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.AromaticnaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
        sumaJacinaLjekovitih=biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.LjekovitaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
        sumaJacinaOtrova=biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.OtrovnaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
        if(sumaJacinaAroma>sumaJacinaLjekovitih && sumaJacinaAroma>sumaJacinaOtrova)
            return TipEliksira.PARFEM;
        if(sumaJacinaLjekovitih>sumaJacinaAroma && sumaJacinaLjekovitih>sumaJacinaOtrova)
            return TipEliksira.LIJEK;
        if(sumaJacinaOtrova>sumaJacinaAroma && sumaJacinaOtrova>sumaJacinaLjekovitih)
            return TipEliksira.OTROV;
        return TipEliksira.BOOSTER;
    }

    public int getJacina() {
        if(getTipEliksira().equals(TipEliksira.BOOSTER))
            return biljke.stream().map(Biljka::getJacina).reduce(0,Integer::sum);
        if(getTipEliksira().equals(TipEliksira.LIJEK))
            return biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.LjekovitaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
        if(getTipEliksira().equals(TipEliksira.OTROV))
            return biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.OtrovnaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
        return biljke.stream().filter(o->o.getClass().getName().equals("ba.unsa.etf.rpr.AromaticnaBiljka")).map(Biljka::getJacina).reduce(0,Integer::sum);
    }

    public ArrayList<Biljka> getBiljke() {
        return biljke;
    }

    @Override
    public String konzumiraj() {
        if(getTipEliksira().equals(TipEliksira.BOOSTER))
            return "{Sve +" + getJacina() + "}";
        if(getTipEliksira().equals(TipEliksira.OTROV))
            return "{Zdravlje -" + getJacina() + "}";
        if(getTipEliksira().equals(TipEliksira.LIJEK))
            return "{Zdravlje -" + getJacina() + "}";
        return "{Miris +" + getJacina() + "}";
    }

    @Override
    public int compareTo(Object o) {
        Eliksir e=(Eliksir)o;
        return this.getNaziv().compareTo(e.getNaziv());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eliksir eliksir = (Eliksir) o;
        return naziv.equals(eliksir.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }
}
