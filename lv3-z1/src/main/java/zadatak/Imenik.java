package zadatak;

import java.util.*;
import java.util.stream.Collectors;

public class Imenik {
    private HashMap<String, TelefonskiBroj> imenik;
    public void dodaj(String ime, TelefonskiBroj broj) {
        imenik.put(ime, broj);
    }
    public String dajBroj(String ime) {
        return imenik.get(ime).ispisi();
    }
    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> entry : imenik.entrySet()) {
            if(entry.getValue() == broj) return entry.getKey();
        }
        return null;
    }
    public Set<String> naSlovo(char c) {
        Set<String> skup = new HashSet<String>();
        for(Map.Entry<String, TelefonskiBroj> entry : imenik.entrySet()) {

            if(entry.getKey().charAt(0) == c) {
                skup.add(entry.getValue().ispisi());
            }
        }
        return skup;
    }
    public Set<String> izGrada(Grad g) {
        Set<String> skup = new HashSet<String>();
        for(Map.Entry<String, TelefonskiBroj> entry : imenik.entrySet()) {
            TelefonskiBroj telefonskiBroj = entry.getValue();

            if(telefonskiBroj instanceof FiksniBroj) {
                FiksniBroj fiksniBroj = (FiksniBroj) telefonskiBroj;

                if(fiksniBroj.getGrad() == g)
                    skup.add(entry.getKey());
            }
        }
        Set<String> sortedSet = skup.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        return skup;
    }
    public Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> skup = new HashSet<TelefonskiBroj>();
        for(Map.Entry<String, TelefonskiBroj> entry : imenik.entrySet()) {
            TelefonskiBroj telefonskiBroj = entry.getValue();

            if(telefonskiBroj instanceof FiksniBroj) {
                FiksniBroj fiksniBroj = (FiksniBroj) telefonskiBroj;

                if(fiksniBroj.getGrad() == g)
                    skup.add(entry.getValue());
            }
        }
        Set<TelefonskiBroj> sortedSet = skup.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        return skup;
    }
}
