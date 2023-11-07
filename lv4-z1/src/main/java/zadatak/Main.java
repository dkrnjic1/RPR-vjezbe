package zadatak;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("laptopi.txt"); // Specify the filename
        LaptopDao laptopDao = new LaptopDaoSerializableFile(file);

        // Create a few Laptop objects
        Laptop laptop1 = new Laptop("Brand1", "Model1", 1000.0, 8, 512, 0, "Processor1", "Graphics1", 15.6);
        Laptop laptop2 = new Laptop("Brand2", "Model2", 1200.0, 16, 512, 256, "Processor2", "Graphics2", 14.0);

        // Add laptops to the list
        laptopDao.dodajLaptopUFile(laptop1);
        laptopDao.dodajLaptopUFile(laptop2);

        // Read laptops from the file
        ArrayList<Laptop> laptopi = laptopDao.vratiPodatkeIzDatoteke();
        for (Laptop laptop : laptopi) {
            System.out.println(laptop);
        }
    }
}
