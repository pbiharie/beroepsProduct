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
        }
    }

    public List<Film> getAllRecords() {
        List<Film> filmList = new ArrayList<Film>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM film;";
            ResultSet result = stmt.executeQuery(sqlQuery);
            while (result.next()) {
                int filmId = result.getInt("film_id");
                String filmName = result.getString("naam");
                String genre = result.getString("genre");
                String dimensionType = result.getString("dimension_type");
                filmList.add(new Film(filmId, filmName, genre, dimensionType));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmList;
    }


    public int insertOneRecord(Film film) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "INSERT INTO `film`(`naam`,`genre`, `dimension_type`) VALUES(?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, film.getFilmName());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getDimensionType());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public int deleteOneRecord(Film film) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "DELETE FROM film WHERE film_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, film.getFilmId());
            result = stmt.executeUpdate();
            System.out.println("deleted: " + film.getFilmId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Film findOneRecord(String filmNaam, String filmgenre) {
        Film film = null;
        PreparedStatement stmt = null;
        try {
            String sqlQuery = "SELECT * FROM film WHERE upper(naam) = ? or upper(genre) = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, filmNaam.toUpperCase());
            stmt.setString(2, filmgenre.toUpperCase());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {

                int filmId = result.getInt("film_id");
                String naam = result.getString("naam");
                String genre = result.getString("genre");
                String dimensionType = result.getString("dimension_type");

                film = new Film(filmId, naam, genre, dimensionType);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return film;
    }


    public int updateOneRecord(Film film) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sqlQuery = "UPDATE `film` SET `naam` = ?, `genre` = ?, `dimension_type` = ? WHERE `film_id` = ?";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, film.getFilmName());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getDimensionType());
            stmt.setInt(4, film.getFilmId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

}
