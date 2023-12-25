import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;
    private PreparedStatement ps1;
    private PreparedStatement ps2;

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
        String url = "jdbc:sqlite:"+ System.getProperty("user.home") + "baza.db";
        conn = DriverManager.getConnection(url);
        try {
            ps2 = conn.prepareStatement("SELECT * FROM baza.grad ORDER BY grad.broj_stanovnika ASC");
            ps1 = conn.prepareStatement("SELECT * FROM baza.drzava WHERE naziv=? OR glavni_grad=?");
        }catch(SQLException e) {
            kreirajBazu();
            ps2 = conn.prepareStatement("SELECT * FROM baza.grad WHERE naziv=? OR drzava=?");
            ps1 = conn.prepareStatement("SELECT * FROM baza.drzava WHERE naziv=? OR glavni_grad=?");
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
            ResultSet rs = ps2.executeQuery();
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
            ps1 = conn.prepareStatement("SELECT drzava.glavni_grad FROM baza.drzava WHERE drzava.naziv = ?");
            ps1.setString(2, drzava);
            ResultSet rs = ps1.executeQuery();
            ps2 = conn.prepareStatement("SELECT * FROM baza.grad WHERE grad.naziv = ?");
            ps2.setString(2, rs.getString(3));
            ResultSet rs2 = ps2.executeQuery();
            return new Grad(rs2.getInt(1), rs2.getString(2), rs2.getInt(3), rs2.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void obrisiDrzavu(String drzava) {
        try {
            PreparedStatement s = conn.prepareStatement("SELECT drzava.id FROM baza.drzava WHERE drzava.naziv = ?");
            s.setString(2, drzava);
            ResultSet r = s.executeQuery();
            int id = r.getInt(1);

            ps1 = conn.prepareStatement("DELETE FROM baza.drzava WHERE drzava.naziv = ?");
            ps1.setString(2, drzava);
            ResultSet rs = ps1.executeQuery();
            ps2 = conn.prepareStatement("DELETE FROM baza.grad WHERE grad.drzava = ?");
            ps2.setInt(4, id);
            ResultSet rs2 = ps2.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajGrad(Grad grad) {
        try {
            ps2 = conn.prepareStatement("INSERT INTO baza.grad VALUES(?, ?, ?, ?) ");
            ps2.setInt(1, grad.getId());
            ps2.setString(2, grad.getNaziv());
            ps2.setInt(3, grad.getBrojStanovnika());
            ps2.setInt(4, grad.getDrzava());
            ResultSet rs2 = ps2.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void dodajDrzavu(Drzava drzava) {
        try {
            ps1 = conn.prepareStatement("INSERT INTO baza.drzava VALUES(?, ?, ?) ");
            ps1.setInt(1, drzava.getId());
            ps2.setString(2, drzava.getNaziv());
            ps2.setInt(3, drzava.getGlavniGrad());
            ResultSet rs2 = ps2.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmijeniGrad(Grad grad) {
        try {
            ps2 = conn.prepareStatement("UPDATE baza.grad SET grad.naziv = ?, grad.broj_stanovnika = ?, grad.drzava = ? WHERE grad.id = ?");
            ps2.setString(2, grad.getNaziv());
            ps2.setInt(1, grad.getId());
            ps2.setInt(3, grad.getBrojStanovnika());
            ps2.setInt(4, grad.getDrzava());
            ResultSet rs2 = ps2.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public Drzava nadjiDrzavu(String drzava) {
        try {
            ps1 = conn.prepareStatement("SELECT * FROM baza.drzava WHERE drzava.naziv = ?");
            ps1.setString(2, drzava);
            ResultSet rs2 = ps1.executeQuery();
            return new Drzava(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String nadjiDrzavu(int id) {
        try {
            ps1 = conn.prepareStatement("SELECT drzava.naziv FROM baza.drzava WHERE drzava.id = ?");
            ps1.setInt(1, id);
            ResultSet rs2 = ps1.executeQuery();
            return rs2.getString(1);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String nadjiGrad(int id) {
        try {
            ps2 = conn.prepareStatement("SELECT grad.naziv FROM baza.grad WHERE grad.id = ?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            return rs2.getString(1);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
