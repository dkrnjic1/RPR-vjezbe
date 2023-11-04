package zadatak;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj {
    private String pozivniBrojDrzave;
    public MedunarodniBroj(String d, String broj) {
        super(broj);
        pozivniBrojDrzave = d;
    }

    @Override
    public String ispisi() {
        StringBuilder s = new StringBuilder(pozivniBrojDrzave + " " + getBroj());
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedunarodniBroj that = (MedunarodniBroj) o;
        return Objects.equals(pozivniBrojDrzave, that.pozivniBrojDrzave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pozivniBrojDrzave);
    }
}
