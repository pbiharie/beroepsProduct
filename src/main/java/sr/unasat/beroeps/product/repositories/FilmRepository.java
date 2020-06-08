package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {
    private Connection connection;

    public FilmRepository() {
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

    public List<Film> getAllFilms() {
        List<Film> filmList = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM `film`";
            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int filmId = result.getInt("film_id");
                String filmNaam = result.getString("naam");
                String genre = result.getString("genre");
                String dimensionType = result.getString("dimension_type");
                double prijs = result.getDouble("prijs");
                filmList.add(new Film(filmId, filmNaam, genre, dimensionType, prijs));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return filmList;
    }


    public int insertFilm(Film film) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sqlQuery = "INSERT INTO `film`(`naam`,`genre`, `dimension_type`, `prijs`) VALUES(?, ?, ?, ?)";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, film.getFilmNaam());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getDimensionType());
            stmt.setDouble(4, film.getPrijs());
            result = stmt.executeUpdate();
            System.out.println("Added Film: " + film.getFilmNaam());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    public int deleteFilm(Film film) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "DELETE FROM `film` WHERE `film_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, film.getFilmId());
            result = stmt.executeUpdate();
            System.out.println("Deleted: " + film.getFilmNaam());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    public Film findFilm(String filmNaam) {
        Film film = null;
        PreparedStatement stmt = null;
        try {
            String sqlQuery = "SELECT * FROM `film` WHERE upper(`naam`) = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, filmNaam.toUpperCase());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {

                int filmId = result.getInt("film_id");
                String name = result.getString("naam");
                String genre = result.getString("genre");
                String dimensionType = result.getString("dimension_type");
                double prijs = result.getDouble("prijs");

                film = new Film(filmId, name, genre, dimensionType, prijs);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return film;
    }


    public int updateFilm(Film film) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "UPDATE `film` SET `naam` = ?, `genre` = ?, `dimension_type` = ?, `prijs` = ? WHERE `film_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, film.getFilmNaam());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getDimensionType());
            stmt.setDouble(4, film.getPrijs());
            stmt.setInt(5, film.getFilmId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }

}
