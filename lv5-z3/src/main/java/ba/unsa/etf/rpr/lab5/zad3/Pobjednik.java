package ba.unsa.etf.rpr.lab5.zad3;

public class Pobjednik {
    private String ime;
    private String prezime;
    private Integer brojZnakova;

    private void razdvojiImeiPrezime(String s) {
        String[] dijelovi = s.split(" ");
        ime = dijelovi[1];
        prezime = dijelovi[0];
        brojZnakova = dijelovi[1].length();
    }

    public Pobjednik(KolekcijaImena kolekcijaImena) {
        if(kolekcijaImena instanceof KolekcijaImenaIPrezimena) {
            int index = ((KolekcijaImenaIPrezimena) kolekcijaImena).getIndexNajduzegPara();
            razdvojiImeiPrezime(((KolekcijaImenaIPrezimena) kolekcijaImena).getImeiPrezime(index));
        }
        razdvojiImeiPrezime(kolekcijaImena.getNajduzeIme());
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }
}
