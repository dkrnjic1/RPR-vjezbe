package zadatak;

public class MobilniBroj extends TelefonskiBroj {
    private int mobilnaMreza;

    public class PogresnaMobilnaMreza extends Exception {
        public PogresnaMobilnaMreza(String poruka) {
            super(poruka);
        }
    }

    public MobilniBroj(int m, String broj) throws PogresnaMobilnaMreza {
        super(broj);
        if(m < 60 || m > 67)
            throw new PogresnaMobilnaMreza("Nepostojeca mobilna mreza.");
        mobilnaMreza = m;
    }

    public int getMobilnaMreza() {
        return mobilnaMreza;
    }

    public void setMobilnaMreza(int m) throws PogresnaMobilnaMreza {
        if(m < 60 || m > 67)
            throw new PogresnaMobilnaMreza("Nepostojeca mobilna mreza.");
        this.mobilnaMreza = m;
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
