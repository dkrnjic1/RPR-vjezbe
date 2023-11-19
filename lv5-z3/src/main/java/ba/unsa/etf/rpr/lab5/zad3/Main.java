package ba.unsa.etf.rpr.lab5.zad3;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Dzana Krnjic");
        lista.add("Hadzimulamuhasanovic Muhamed");
        KolekcijaImenaIPrezimena kolekcija = new KolekcijaImenaIPrezimena(lista);
        Pobjednik pobjednik = new Pobjednik(kolekcija);
        System.out.print(pobjednik.getPrezime());
    }
}
