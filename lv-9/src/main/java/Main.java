import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String ispisiGradove() throws SQLException {
        GeografijaDAO g = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = g.gradovi();
        StringBuilder s = new StringBuilder();
        for(Grad gr : gradovi) {
            s.append(gr.getNaziv()).append(" (").append(g.nadjiDrzavu(gr.getDrzava())).append(") - ").append(gr.getBrojStanovnika()).append("\n");
        }
        return s.toString();
    }

    public static void glavniGrad() throws SQLException {
        System.out.println("Unesite drzavu: ");
        Scanner ulaz  = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        GeografijaDAO g = GeografijaDAO.getInstance();
        Drzava d = g.nadjiDrzavu(drzava);
        if(d != null)
             System.out.println("Glavni grad drzave " + drzava + " je " + g.nadjiGrad(d.getId()));
        else
            System.out.println("Nepostojeca drzava.");
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Izaberite komandu:\n1 za ispis gradova\n2za glavni grad drzave");
        Scanner ulaz = new Scanner(System.in);
        int komanda = ulaz.nextInt();
        switch(komanda) {
            case 1:
                String s = ispisiGradove();
                System.out.println(s);
                break;
            case 2:
                glavniGrad();
                break;
            default:
                System.out.println("Niste unijeli nijednu valjanu komandu.");
                break;
        }
    }
}