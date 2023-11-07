package zadatak;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LaptopDaoSerializableFileTest {

    private LaptopDaoSerializableFile laptopDao;
    private File testFile;
    @BeforeEach
    public void setUp() {

        testFile = new File("test_laptops.txt");
        laptopDao = new LaptopDaoSerializableFile(testFile);
    }

    @Test
    void LaptopDao() throws NeodgovarajuciProcesorException {
        LaptopDao laptopDaoMock = Mockito.mock(LaptopDao.class);

        Laptop laptop = new Laptop("Brand1", "Model1", 1000.0, 8, 512, 0, "Processor1", "Graphics1", 15.6);

        //mozemo reci da je procesor glavna specifikacija laptopa pa koristimo mock u tom smislu
        Mockito.when(laptopDaoMock.getLaptop("Processor1")).thenReturn(laptop);

        Laptop result = laptopDaoMock.getLaptop("Processor1");

        assertNotNull(result);
        assertEquals("Brand1", result.getBrend());
    }


    @Test
    void dodajLaptopUFile() {
        Laptop laptop = new Laptop("Brand1", "Model1", 1000.0, 8, 512, 0, "Processor1", "Graphics1", 15.6);

        laptopDao.dodajLaptopUFile(laptop);

        ArrayList<Laptop> laptops = laptopDao.vratiPodatkeIzDatoteke();

        assertTrue(laptops.contains(laptop));
    }

    @Test
    void dodajLaptopUListu() {
        Laptop laptop = new Laptop("Brand1", "Model1", 1000.0, 8, 512, 0, "Processor1", "Graphics1", 15.6);

        // Add the laptop to the list
        laptopDao.dodajLaptopUListu(laptop);

        // Retrieve the list from the file
        ArrayList<Laptop> laptops = laptopDao.vratiPodatkeIzDatoteke();

        // Assert that the list contains the added laptop
        assertTrue(laptops.contains(laptop));
    }

    @Test
    void getLaptopExisting() throws NeodgovarajuciProcesorException {
        Laptop laptop = new Laptop("Brand1", "Model1", 1000.0, 8, 510, 0, "Procesor1", "Graphics1", 15.6);
        laptopDao.dodajLaptopUListu(laptop);
        Laptop provjera = laptopDao.getLaptop("Procesor1");
        assertEquals(laptop, provjera);
    }

    @Test
    void getLaptopNonExisting()  {
        assertThrows(NeodgovarajuciProcesorException.class, () -> {
            Laptop provjera = laptopDao.getLaptop("Nepostojeci procesor");
        });
    }

    @Test
    void napuniListu() {
        ArrayList<Laptop> laptopi = new ArrayList<>();
        laptopi.add(new Laptop("Brand1", "Model1", 1000.0, 8, 512, 0, "Processor1", "Graphics1", 15.6));
        laptopi.add(new Laptop("Brand2", "Model2", 1200.0, 16, 512, 256, "Processor2", "Graphics2", 14.0));

        laptopDao.napuniListu(laptopi);

        ArrayList<Laptop> provjera = laptopDao.vratiPodatkeIzDatoteke();

        assertEquals(laptopi, provjera);
    }

}