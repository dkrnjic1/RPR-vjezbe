public class Drzava {
    private int id, glavniGrad;
    private String naziv;

    public Drzava(int id, String naziv, int glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }
    public Drzava() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(int glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
