package zadatak;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class LaptopDaoSerializableFile implements LaptopDao {
    private  File file;
    private  ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(File file) {
        this.file = file;
        laptopi = new ArrayList<>();
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        vratiPodatkeIzDatoteke();

        laptopi.add(laptop);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(laptopi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        for(Laptop l : laptopi) {
            if(Objects.equals(l.getProcesor(), procesor)) return l;
        }
        throw new NeodgovarajuciProcesorException("Ne postoji laptop sa ovim procesorom");
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi = laptopi;
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            laptopi = (ArrayList<Laptop>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Ako datoteka ne postoji ili nije ispravna, nastavite s praznom listom
            laptopi = new ArrayList<>();
        }
        return laptopi;
    }
}
