package zadatak;

public class Laptop {
    private String brend;
    private String model;
    private double cijena;
    private int ram;
    private int hdd;
    private int sdd;
    private String procesor;
    private String grafickaKartica;
    private double velicinaEkrana;

    public Laptop(String brend, String model, double cijena, int ram, int hdd, int sdd, String procesor, String graficka, double velEkrana) {
        this.brend = brend;
        this.cijena = cijena;
        grafickaKartica = graficka;
        this.hdd =hdd;
        this.ram = ram;
        this.model = model;
        this.procesor = procesor;
        this.sdd =  sdd;
        velicinaEkrana = velEkrana;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public int getSdd() {
        return sdd;
    }

    public void setSdd(int sdd) {
        this.sdd = sdd;
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public String getGrafickaKartica() {
        return grafickaKartica;
    }

    public void setGrafickaKartica(String grafickaKartica) {
        this.grafickaKartica = grafickaKartica;
    }

    public double getVelicinaEkrana() {
        return velicinaEkrana;
    }

    public void setVelicinaEkrana(double velicinaEkrana) {
        this.velicinaEkrana = velicinaEkrana;
    }

}
