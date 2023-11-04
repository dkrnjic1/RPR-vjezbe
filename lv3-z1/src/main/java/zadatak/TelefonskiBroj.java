package zadatak;

public abstract class TelefonskiBroj {
    private String broj;
    public abstract String ispisi();
    public abstract int hashCode();

    public TelefonskiBroj(String broj) {
        this.broj = broj;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }
}
