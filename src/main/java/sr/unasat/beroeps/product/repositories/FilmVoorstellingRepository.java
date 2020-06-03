package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.entities.FilmVoorstelling;
import sr.unasat.beroeps.product.entities.Zaal;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        }
    }


    public List<FilmVoorstelling> findAllRecords() {
        List<FilmVoorstelling> filmVoorstellingList = new ArrayList<FilmVoorstelling>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM filmvoorstelling fmv" +
                    " INNER JOIN film f ON fmv.film_id = f.film_id " +
                    "INNER JOIN zaal zl ON fmv.zaal_id = zl.zaal_id";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("filmvoorstelling_id");
                LocalDate startDate = rs.getDate("start_datumtijd").toLocalDate();
                LocalTime startTime = rs.getTime("start_datumtijd").toLocalTime();
                LocalDateTime startDatetime = LocalDateTime.of(startDate, startTime);
                String genre = rs.getString("genre");


                int filmId = rs.getInt("film_id");
                String filmNaam = rs.getString("naam");
                String filmGenre = rs.getString("genre");
                String filmDimension = rs.getString("dimension_type");
                Film film = new Film(filmId, filmNaam, filmGenre, filmDimension);

                int zaalId = rs.getInt("zaal_id");
                int zaalNummer = rs.getInt("zaal_nummer");
                int aantalZitPlaatsen = rs.getInt("aantal_zitplaatsen");
                Zaal zaal = new Zaal(zaalId, zaalNummer, aantalZitPlaatsen);

                filmVoorstellingList.add(new FilmVoorstelling(id, startDatetime, film, zaal));
            }
            rs.close();


        } catch (SQLException e) {
            System.out.println(e);
        } finally {

        }
        return filmVoorstellingList;
    }



}
