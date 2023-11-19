package ba.unsa.etf.rpr.lab5.zad3;
import java.util.ArrayList;

public abstract class KolekcijaImena {
    protected ArrayList<String> imenaIPrezimena;
    public KolekcijaImena(ArrayList<String> lista) {
        imenaIPrezimena = new ArrayList<>();
        imenaIPrezimena.addAll(lista);
    }
    public String getNajduzeIme() {
        int najduziVelicina = imenaIPrezimena.get(0).length();
        String najduziString = imenaIPrezimena.get(0);
        for(String s : imenaIPrezimena) {
            if(s.length() > najduziVelicina) {
                najduziVelicina = s.length();
                najduziString = s;
            }
        }
        return najduziString;
    }
}
