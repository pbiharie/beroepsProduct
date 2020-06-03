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
        }
    }

    public List<Zaal> getAllRecords() {
        List<Zaal> zaalList = new ArrayList<Zaal>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM `zaal`;";
            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int zaalId = result.getInt("film_id");
                int zaalNummer = result.getInt("naam");
                int aantalZitPlaatsen = result.getInt("genre");
                zaalList.add(new Zaal(zaalId, zaalNummer, aantalZitPlaatsen));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zaalList;
    }


    public int insertOneRecord(Zaal zaal) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "INSERT INTO `zaal`(`zaal_nummer`, `aantal_zitplaatsen`) VALUES(?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, zaal.getZaalNummer());
            stmt.setInt(2, zaal.getAantalZitPlaatsen());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
