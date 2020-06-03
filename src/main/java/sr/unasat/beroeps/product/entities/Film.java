package sr.unasat.beroeps.product.entities;

public class Film {
    private int filmId;
    private String filmName;
    private String genre;
    private String dimensionType;

    public Film(int filmId, String filmName, String genre, String dimensionType) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.genre = genre;
        this.dimensionType = dimensionType;
    }

    public Film(String filmNaam, String genre, String dimensionType) {
        this.filmName = filmNaam;
        this.genre = genre;
        this.dimensionType = dimensionType;
    }

    public Film(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getGenre() {
        return genre;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", genre='" + genre + '\'' +
                ", dimensionType='" + dimensionType + '\'' +
                '}';
    }
}
