import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;
    private PreparedStatement upitSviGradovi, upitGrad, upitGlGrad, upitIdDrzave, upitBrisanjeGrada, upitBrisanjeDrzave, upitMaxIdGrada, upitDodajGrad, upitDodajDrzavu;
    private PreparedStatement upitMaxIdDrzave, upitIzmjenaGrada, upitDrzava, upitNazivGrad;

    private void kreirajBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }
    private GeografijaDAO() throws SQLException {
        String url = "jdbc:sqlite:"+ System.getProperty("user.home") + "\\Documents\\baza.db";
        conn = DriverManager.getConnection(url);
        try {
            upitSviGradovi = conn.prepareStatement("SELECT * FROM baza.grad ORDER BY grad.broj_stanovnika ASC");
            upitGlGrad = conn.prepareStatement("SELECT drzava.glavni_grad FROM baza.drzava WHERE drzava.naziv = ?");
            upitGrad = conn.prepareStatement("SELECT * FROM baza.grad WHERE grad.naziv = ?");
            upitIdDrzave = conn.prepareStatement("SELECT drzava.id FROM baza.drzava WHERE drzava.naziv = ?");
            upitBrisanjeGrada = conn.prepareStatement("DELETE FROM baza.drzava WHERE drzava.naziv = ?");
            upitBrisanjeDrzave = conn.prepareStatement("DELETE FROM baza.grad WHERE grad.drzava = ?");
            upitMaxIdGrada = conn.prepareStatement("SELECT MAX(grad.id) + 1 FROM baza.grad");
            upitDodajGrad = conn.prepareStatement("INSERT INTO baza.grad VALUES(?, ?, ?, ?) ");
            upitDodajDrzavu = conn.prepareStatement("INSERT INTO baza.drzava VALUES(?, ?, ?) ");
            upitMaxIdDrzave = conn.prepareStatement("SELECT MAX(drzava.id) + 1 FROM baza.drzava");
            upitIzmjenaGrada = conn.prepareStatement("UPDATE baza.grad SET grad.naziv = ?, grad.broj_stanovnika = ?, grad.drzava = ? WHERE grad.id = ?");
            upitDrzava = conn.prepareStatement("SELECT * FROM baza.drzava WHERE drzava.naziv = ?");
            upitNazivGrad = conn.prepareStatement("SELECT grad.naziv FROM baza.grad WHERE grad.id = ?");

        }catch(SQLException e) {
            kreirajBazu();
            upitSviGradovi = conn.prepareStatement("SELECT * FROM baza.grad ORDER BY grad.broj_stanovnika ASC");
            upitGlGrad = conn.prepareStatement("SELECT drzava.glavni_grad FROM baza.drzava WHERE drzava.naziv = ?");
            upitGrad = conn.prepareStatement("SELECT * FROM baza.grad WHERE grad.naziv = ?");
            upitIdDrzave = conn.prepareStatement("SELECT drzava.id FROM baza.drzava WHERE drzava.naziv = ?");
            upitBrisanjeGrada = conn.prepareStatement("DELETE FROM baza.drzava WHERE drzava.naziv = ?");
            upitBrisanjeDrzave = conn.prepareStatement("DELETE FROM baza.grad WHERE grad.drzava = ?");
            upitMaxIdGrada = conn.prepareStatement("SELECT MAX(grad.id) + 1 FROM baza.grad");
            upitDodajGrad = conn.prepareStatement("INSERT INTO baza.grad VALUES(?, ?, ?, ?) ");
            upitDodajDrzavu = conn.prepareStatement("INSERT INTO baza.drzava VALUES(?, ?, ?) ");
            upitMaxIdDrzave = conn.prepareStatement("SELECT MAX(drzava.id) + 1 FROM baza.drzava");
            upitIzmjenaGrada = conn.prepareStatement("UPDATE baza.grad SET grad.naziv = ?, grad.broj_stanovnika = ?, grad.drzava = ? WHERE grad.id = ?");
            upitDrzava = conn.prepareStatement("SELECT * FROM baza.drzava WHERE drzava.naziv = ?");
            upitGrad = conn.prepareStatement("SELECT grad.naziv FROM baza.grad WHERE grad.id = ?");
        }
    }
    public static GeografijaDAO getInstance() throws SQLException {
        if(instance == null)
            instance = new GeografijaDAO();
        return instance;
    }
    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            ResultSet rs = upitSviGradovi.executeQuery();
            while(rs.next()) {
                gradovi.add(new Grad(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradovi;
    }
    public Grad glavniGrad(String drzava) {
        try {
            upitGlGrad.setString(2, drzava);
            ResultSet rs = upitGlGrad.executeQuery();
            upitGrad.setString(2, rs.getString(3));
            ResultSet rs2 = upitGrad.executeQuery();
            return new Grad(rs2.getInt(1), rs2.getString(2), rs2.getInt(3), rs2.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void obrisiDrzavu(String drzava) {
        try {
            upitIdDrzave.setString(2, drzava);
            ResultSet r = upitIdDrzave.executeQuery();
            int id = r.getInt(1);

            upitBrisanjeGrada.setString(2, drzava);
            upitBrisanjeGrada.execute();

            upitBrisanjeDrzave.setInt(4, id);
            upitBrisanjeDrzave.execute();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajGrad(Grad grad) {
        try {
            ResultSet r = upitMaxIdGrada.executeQuery();
            grad.setId(r.getInt(1));

            upitDodajGrad.setInt(1, grad.getId());
            upitDodajGrad.setString(2, grad.getNaziv());
            upitDodajGrad.setInt(3, grad.getBrojStanovnika());
            upitDodajGrad.setInt(4, grad.getDrzava());
            upitDodajGrad.execute();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet r = upitMaxIdDrzave.executeQuery();
            drzava.setId(r.getInt(1));

            upitDodajDrzavu.setInt(1, drzava.getId());
            upitDodajDrzavu.setString(2, drzava.getNaziv());
            upitDodajDrzavu.setInt(3, drzava.getGlavniGrad());
            upitDodajDrzavu.execute();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmijeniGrad(Grad grad) {
        try {
            upitIzmjenaGrada.setString(2, grad.getNaziv());
            upitIzmjenaGrada.setInt(1, grad.getId());
            upitIzmjenaGrada.setInt(3, grad.getBrojStanovnika());
            upitIzmjenaGrada.setInt(4, grad.getDrzava());
            upitIzmjenaGrada.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public Drzava nadjiDrzavu(String drzava) {
        try {
            upitDrzava.setString(2, drzava);
            ResultSet rs2 = upitDrzava.executeQuery();
            return new Drzava(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String nadjiDrzavu(int id) {
        try {
            upitDrzava = conn.prepareStatement("SELECT drzava.naziv FROM baza.drzava WHERE drzava.id = ?");
            upitDrzava.setInt(1, id);
            ResultSet rs2 = upitDrzava.executeQuery();
            return rs2.getString(1);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String nadjiGrad(int id) {
        try {
            upitNazivGrad.setInt(1, id);
            ResultSet rs2 = upitGrad.executeQuery();
            return rs2.getString(1);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
