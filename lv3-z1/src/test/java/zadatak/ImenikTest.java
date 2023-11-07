package zadatak;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ImenikTest {
    public static Imenik imenik = new Imenik();
    @BeforeAll
    public static void setup() {
        imenik.dodaj("Alma", new FiksniBroj(Grad.SARAJEVO, "650-453"));
    }
    @Test
    public void dajBrojFound() {

        String broj = imenik.dajBroj("Alma");
        assertEquals(broj, "033/650-453");
    }
    @Test
    public void dajBrojNotFound() {
        String broj = imenik.dajBroj("Selma");
        assertNull(broj);
    }
    @Test
    public void dodajTestPositive() throws MobilniBroj.PogresnaMobilnaMreza {
        TelefonskiBroj broj = new MobilniBroj(61, "650-453");
        Imenik.dodaj("Hana", broj);
        String br = imenik.dajBroj("Hana");
        assertEquals(br, "061/650-453");
    }
    @Test
    public void testMockExternal() {
        Imenik i = Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Alma")).thenReturn("Nothing");

        String test = i.dajBroj("Alma");
        assertEquals(test, "Nothing");
    }
    @Test
    public void testMockInternal() {
        Map<String, TelefonskiBroj> mapa = Mockito.mock(Map.class);
        Mockito.when(mapa.get("Alma")).thenReturn(new fiksniBroj(Grad.MOSTAR, "650-453"));
        imenik.setBrojevi(mapa);

        String broj = imenik.dajBroj("Alma");
        assertNotEquals(broj, "033/650-453");
        assertEquals(broj, "036/650-453");
    }
}
