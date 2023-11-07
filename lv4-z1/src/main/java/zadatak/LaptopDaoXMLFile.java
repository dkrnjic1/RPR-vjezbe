package zadatak;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.Objects;

public class LaptopDaoXMLFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile(File f) {
        file = f;
        laptopi = new ArrayList<Laptop>();
    }


    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            laptopi.add(laptop);

            JAXBContext context = JAXBContext.newInstance(ArrayList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(laptopi, file);
        } catch (JAXBException e) {
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
