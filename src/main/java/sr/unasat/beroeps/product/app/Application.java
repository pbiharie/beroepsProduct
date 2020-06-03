package sr.unasat.beroeps.product.app;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.repositories.FilmRepository;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        FilmRepository filmRepo = new FilmRepository();

        //-----Listing all the record from the table
        List<Film> filmList = filmRepo.getAllRecords();
        for (Film film : filmList){
            System.out.println(film);
        }


        //-----Inserting one record in the table
       /* Film The_Conjuring = new Film("The Conjuring", "Horror","2D");
        filmRepo.insertOneRecord(The_Conjuring);*/


        //-----Deleting a record
       /* Film theNun = new Film(8);
        filmRepo.deleteOneRecord(theNun);*/


        //-----Finding a record with the parameters
        /*Film foundRecord = filmRepo.findOneRecord("the conjuring", "orror");
        System.out.println("Found record: = " + foundRecord.toString());*/


        //-----Updating the found record
        /*foundRecord.setFilmName("Bad boys");
        foundRecord.setGenre("Action");
        foundRecord.setDimensionType("2D");
        filmRepo.updateOneRecord(foundRecord);*/

    }
}
