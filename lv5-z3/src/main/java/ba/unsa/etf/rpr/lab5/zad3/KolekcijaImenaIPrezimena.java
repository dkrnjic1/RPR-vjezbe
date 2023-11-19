package ba.unsa.etf.rpr.lab5.zad3;
import java.util.ArrayList;
public class KolekcijaImenaIPrezimena extends KolekcijaImena {
    private ArrayList<String> imena;
    private ArrayList<String> prezimena;

    public KolekcijaImenaIPrezimena(ArrayList<String> lista) {
        super(lista);
        imena = new ArrayList<>();
        prezimena = new ArrayList<>();
        for(int i = 0; i < lista.size(); i++) {
            String[] dijelovi = lista.get(i).split(" ");
            imena.add(dijelovi[0]);
            prezimena.add(dijelovi[1]);
        }
    }

    public int getIndexNajduzegPara() {
        int velicinaNajduzegStringa = imena.get(0).length() + prezimena.get(0).length();
        int indexNajduzegPara = 0;
        for(int i = 1; i < imena.size(); i++) {
            if(imena.get(i).length() + prezimena.get(i).length() > velicinaNajduzegStringa) {
                velicinaNajduzegStringa = imena.get(i).length() + prezimena.get(i).length();
                indexNajduzegPara = i;
            }
        }
        return indexNajduzegPara;
    }
    public String getImeiPrezime(int i) {
        return imena.get(i) + " " + prezimena.get(i);
    }
}
