package z2;

public class Racun {
    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private Double stanjeRacuna;
    private Double prekoracenje;
    private boolean odobrenjePrekoracenja;
    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
    }
    public boolean provjeriOdobrenjePrekoracenja(double iznos) {
        return this.prekoracenje > iznos;
    }
    public boolean izvrsiUplatu(Double iznos) {
        this.stanjeRacuna += iznos;
        return true;
    }
    public boolean izvrsiIsplatu(Double iznos) {
        if(this.stanjeRacuna + this.prekoracenje < iznos) return false;
        else {
            this.stanjeRacuna -= iznos;
            return true;
        }
    }
    public void odobriPrekoracenje(Double iznos) { this.prekoracenje = iznos; }
    public Long getBrojRacuna() { return brojRacuna; }

    public void setBrojRacuna(Long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Osoba getKorisnikRacuna() {
        return korisnikRacuna;
    }

    public void setKorisnikRacuna(Osoba korisnikRacuna) {
        this.korisnikRacuna = korisnikRacuna;
    }

    public void setOdobrenjePrekoracenja(boolean odobrenjePrekoracenja) {
        this.odobrenjePrekoracenja = odobrenjePrekoracenja;
    }

    public Double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public void setStanjeRacuna(Double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }

    public Double getPrekoracenje() {
        return prekoracenje;
    }

    public void setPrekoracenje(Double prekoracenje) {
        this.prekoracenje = prekoracenje;
    }
    public boolean isOdobrenjePrekoracenja() {
        return odobrenjePrekoracenja;}
}
