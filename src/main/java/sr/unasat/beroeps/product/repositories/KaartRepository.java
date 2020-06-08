package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.entities.FilmVoorstelling;
import sr.unasat.beroeps.product.entities.Kaart;
import sr.unasat.beroeps.product.entities.Zaal;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class KaartRepository {
    private Connection connection;

    public KaartRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/bioscoop";
            String USR = "root";
            String PSWD = "971999";
            connection = DriverManager.getConnection(URL, USR, PSWD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Kaart> getAllVerkochteKaarten() {
        List<Kaart> kaartList = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM `kaart` `krt` " +
                    "INNER JOIN `filmvoorstelling` `fmv` ON `fmv`.`filmvoorstelling_id` = `krt`.`filmvoorstelling_id` " +
                    "INNER JOIN `film` `f` ON `f`.`film_id` = `fmv`.`film_id` " +
                    "INNER JOIN `zaal` `zl` ON `zl`.`zaal_id` = `fmv`.`zaal_id` ";

            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int kaartId = result.getInt("kaart_id");
                int kaartNummer = result.getInt("kaart_nummer");
                int zitplaatsNummer = result.getInt("zitplaats_nummer");
                LocalDate verkoopDatum = result.getDate("verkoop_datum").toLocalDate();

                int id = result.getInt("filmvoorstelling_id");
                LocalDate startDate = result.getDate("start_datum").toLocalDate();
                LocalTime startTime = result.getTime("start_tijd").toLocalTime();

                int filmId = result.getInt("film_id");
                String filmName = result.getString("naam");
                String filmGenre = result.getString("genre");
                String filmDimension = result.getString("dimension_type");
                double prijs = result.getDouble("prijs");
                Film film = new Film(filmId, filmName, filmGenre, filmDimension, prijs);

                int zaalId = result.getInt("zaal_id");
                int zaalNummer = result.getInt("zaal_nummer");
                int aantalZitPlaatsen = result.getInt("aantal_zitplaatsen");
                Zaal zaal = new Zaal(zaalId, zaalNummer, aantalZitPlaatsen);

                FilmVoorstelling filmVoorstelling = new FilmVoorstelling(id, startDate, startTime, film, zaal);
                kaartList.add(new Kaart(kaartId, kaartNummer, zitplaatsNummer, verkoopDatum, filmVoorstelling));

            }
            result.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return kaartList;
    }


    public ResultSet getMax() {
        ResultSet result = null;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT max(`kaart_nummer`) as `max_kaartnummer`, max(`zitplaats_nummer`) as `max_zitplaatsnummber` " +
                    "FROM `kaart`";
            result = stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    public int sellKaart(Kaart kaart) {
        PreparedStatement stmt = null;
        int result = 0;
        int maxKaartNummer = 0;
        int maxZitPlaatsNummer = 0;
        
        try {
            ResultSet max = getMax();
            while(max.next()){
                 maxKaartNummer = max.getInt("max_kaartnummer");
                 maxZitPlaatsNummer = max.getInt("max_zitplaatsnummber");
            }

            if(maxZitPlaatsNummer < kaart.getFilmVoorstelling().getZaal().getAantalZitPlaatsen()){
                String sqlQuery = "INSERT INTO `kaart`(`kaart_nummer`,`zitplaats_nummer`, `verkoop_datum`, `filmvoorstelling_id`)" +
                        " VALUES(?, ?, ?, ?)";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(1, ++maxKaartNummer);
                stmt.setInt(2, ++maxZitPlaatsNummer);
                stmt.setDate(3, Date.valueOf(LocalDate.now()));
                stmt.setInt(4, kaart.getFilmVoorstelling().getId());
                result = stmt.executeUpdate();
                System.out.println("Sold kaart: " + maxKaartNummer);
            } else{
                System.out.println("Sold Out");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }


}
