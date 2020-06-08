package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.entities.Zaal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZaalRepository {
    private Connection connection;

    public ZaalRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/bioscoop";
            String USR = "root";
            String PSWD = "971999";
            connection = DriverManager.getConnection(URL, USR, PSWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public List<Zaal> getAllZaal() {
        List<Zaal> zaalList = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM `zaal`;";
            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int zaalId = result.getInt("zaal_id");
                int zaalNummer = result.getInt("zaal_nummer");
                int aantalZitPlaatsen = result.getInt("aantal_zitplaatsen");
                zaalList.add(new Zaal(zaalId, zaalNummer, aantalZitPlaatsen));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return zaalList;
    }


    public int insertZaal(Zaal zaal) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sqlQuery = "INSERT INTO `zaal`(`zaal_nummer`, `aantal_zitplaatsen`) VALUES(?, ?)";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, zaal.getZaalNummer());
            stmt.setInt(2, zaal.getAantalZitPlaatsen());
            result = stmt.executeUpdate();
            System.out.println("Added: Zaal " + zaal.getZaalNummer());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    public int deleteZaal(Zaal zaal) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "DELETE FROM `zaal` WHERE `zaal_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, zaal.getZaalId());
            result = stmt.executeUpdate();
            System.out.println("Deleted: Zaal " + zaal.getZaalNummer());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    public Zaal findZaal(int zaalNummer) {
        Zaal zaal = null;
        PreparedStatement stmt = null;
        try {
            String sqlQuery = "SELECT * FROM `zaal` WHERE `zaal_nummer` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, zaalNummer);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {

                int zaalId = result.getInt("zaal_id");
                int zaalNr = result.getInt("zaal_nummer");
                int aantalZitPlaatsen = result.getInt("aantal_zitplaatsen");

                zaal = new Zaal(zaalId, zaalNr, aantalZitPlaatsen);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return zaal;
    }


    public int updateZaal(Zaal zaal) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "UPDATE `zaal` SET `zaal_nummer` = ?, `aantal_zitplaatsen` = ? WHERE `zaal_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, zaal.getZaalNummer());
            stmt.setInt(2, zaal.getAantalZitPlaatsen());
            stmt.setInt(3, zaal.getZaalId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }




}
