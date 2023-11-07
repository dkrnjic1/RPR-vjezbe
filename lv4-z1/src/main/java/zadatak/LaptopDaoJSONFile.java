package zadatak;

import java.io.File;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoJSONFile(ArrayList<Laptop> laptopi, File f) {
        this.laptopi = laptopi;
        file = f;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<Laptop> getLaptopi() {
        return laptopi;
    }

    public void setLaptopi(ArrayList<Laptop> laptopi) {
        this.laptopi = laptopi;
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {

    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {

    }

    @Override
    public void getLaptop(String procesor) {

    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {

    }

    @Override
    public void vratiPodatkeIzDatoteke() {

    }
}
