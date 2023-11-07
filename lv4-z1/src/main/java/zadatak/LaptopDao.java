package zadatak;

import java.util.ArrayList;

public interface LaptopDao {
    public void dodajLaptopUFile(Laptop laptop);
    public void dodajLaptopUListu(Laptop laptop);
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException;
    public void napuniListu(ArrayList<Laptop> laptopi);
    public ArrayList<Laptop> vratiPodatkeIzDatoteke();
}
