package zadatak;

public class MobilniBroj extends TelefonskiBroj {
    private int mobilnaMreza;
    public MobilniBroj(int m, String broj) {
        super(broj);
        mobilnaMreza = m;
    }

    @Override
    public String ispisi() {
        StringBuilder s = new StringBuilder(mobilnaMreza + "/" + getBroj());
        return s.toString();
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
