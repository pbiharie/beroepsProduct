package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.entities.FilmVoorstelling;
import sr.unasat.beroeps.product.entities.Zaal;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FilmVoorstellingRepository {
    private Connection connection;

    public FilmVoorstellingRepository() {
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
        }finally {

        }
    }


    public List<FilmVoorstelling> getAllFilmVoorstellingen() {
        List<FilmVoorstelling> filmVoorstellingList = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM `filmvoorstelling` `fmv` " +
                    "INNER JOIN `film` `f` ON `fmv`.`film_id` = `f`.`film_id` " +
                    "INNER JOIN `zaal` `zl` ON `fmv`.`zaal_id` = `zl`.`zaal_id`";

            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int id = result.getInt("filmvoorstelling_id");
                LocalDate startDate = result.getDate("start_datum").toLocalDate();
                LocalTime startTime = result.getTime("start_tijd").toLocalTime();

                int filmId = result.getInt("film_id");
                String filmNaam = result.getString("naam");
                String filmGenre = result.getString("genre");
                String filmDimension = result.getString("dimension_type");
                double prijs = result.getDouble("prijs");
                Film film = new Film(filmId, filmNaam, filmGenre, filmDimension, prijs);

                int zaalId = result.getInt("zaal_id");
                int zaalNummer = result.getInt("zaal_nummer");
                int aantalZitPlaatsen = result.getInt("aantal_zitplaatsen");
                Zaal zaal = new Zaal(zaalId, zaalNummer, aantalZitPlaatsen);

                filmVoorstellingList.add(new FilmVoorstelling(id, startDate, startTime, film, zaal));
            }
            result.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return filmVoorstellingList;
    }



    public int insertFilmVoorstelling(FilmVoorstelling filmVoorstelling) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sqlQuery = "INSERT INTO `filmvoorstelling`(`start_datum`, `start_tijd`, `film_id`, `zaal_id`) " +
                    "VALUES(?, ?, ?, ?)";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setDate(1, Date.valueOf(filmVoorstelling.getStartDatum()));
            stmt.setTime(2, Time.valueOf(filmVoorstelling.getStartTijd()));
            stmt.setInt(3, filmVoorstelling.getFilm().getFilmId());
            stmt.setInt(4, filmVoorstelling.getZaal().getZaalId());
            result = stmt.executeUpdate();
            System.out.println("Inserted record for day: " + filmVoorstelling.getStartDatum());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }

    public int deleteFilmVoorstelling(FilmVoorstelling filmVoorstelling) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "DELETE FROM `filmvoorstelling` WHERE `filmvoorstelling_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, filmVoorstelling.getId());
            result = stmt.executeUpdate();
            System.out.println("Deleted record of day: " + filmVoorstelling.getStartDatum());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    public FilmVoorstelling findFilmVoorstelling(LocalDate startDatum) {
        FilmVoorstelling filmVoorstelling = null;
        PreparedStatement stmt = null;
        try {
            String sqlQuery = "SELECT * FROM `filmvoorstelling` `fmv`" +
                    "INNER JOIN `film` `f` ON `fmv`.`film_id` = `f`.`film_id`" +
                    "INNER JOIN `zaal` `zl` ON `fmv`.`zaal_id` = `zl`.`zaal_id`" +
                    "WHERE `start_datum` = ?";

            stmt = connection.prepareStatement(sqlQuery);
            stmt.setDate(1, Date.valueOf(startDatum));
            ResultSet result = stmt.executeQuery();
            if (result.next()) {

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

                filmVoorstelling = new FilmVoorstelling(id, startDate, startTime, film, zaal);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return filmVoorstelling;
    }


    public int updateFilmVoorstelling(FilmVoorstelling filmVoorstelling) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "UPDATE `filmvoorstelling` SET `start_datum` = ?, `start_tijd` = ?, `film_id` = ?, `zaal_id` = ? " +
                    "WHERE `filmvoorstelling_id` = ?";

            stmt = connection.prepareStatement(sqlQuery);
            stmt.setDate(1, Date.valueOf(filmVoorstelling.getStartDatum()));
            stmt.setTime(2, Time.valueOf(filmVoorstelling.getStartTijd()));
            stmt.setInt(3, filmVoorstelling.getFilm().getFilmId());
            stmt.setInt(4, filmVoorstelling.getZaal().getZaalId());
            stmt.setInt(5, filmVoorstelling.getId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }

}
