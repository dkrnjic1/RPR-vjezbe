import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static GeografijaDAO dao;
    public static String ispisiGradove() throws SQLException {
        ArrayList<Grad> gradovi = dao.gradovi();
        StringBuilder s = new StringBuilder();
        for(Grad gr : gradovi) {
            s.append(gr.getNaziv()).append(" (").append(dao.nadjiDrzavu(gr.getDrzava())).append(") - ").append(gr.getBrojStanovnika()).append("\n");
        }
        return s.toString();
    }

    public static void glavniGrad() throws SQLException {
        System.out.println("Unesite drzavu: ");
        Scanner ulaz  = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        Drzava d = dao.nadjiDrzavu(drzava);
        if(d != null)
             System.out.println("Glavni grad drzave " + drzava + " je " + dao.nadjiGrad(d.getId()));
        else
            System.out.println("Nepostojeca drzava.");
    }

    public static void main(String[] args) throws SQLException {
        dao = GeografijaDAO.getInstance();
        int komanda = 0;
        while(komanda != 3) {
            System.out.println("Izaberite komandu:\n1 - ispis gradova\n2 - glavni grad drzave\n3 - izlaz");
            Scanner ulaz = new Scanner(System.in);
            komanda = ulaz.nextInt();
            switch (komanda) {
                case 1:
                    String s = ispisiGradove();
                    System.out.println(s);
                    break;
                case 2:
                    glavniGrad();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Niste unijeli nijednu valjanu komandu.");
                    break;
            }
        }
    }
}