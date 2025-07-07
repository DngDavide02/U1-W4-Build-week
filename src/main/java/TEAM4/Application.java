package TEAM4;

import TEAM4.DAO.*;
import TEAM4.entities.Abbonamenti;
import TEAM4.entities.Biglietti;
import TEAM4.entities.Tessera;
import TEAM4.entities.Tratta;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BWT4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();
        AtacDAO atacDAO = new AtacDAO(em);
        EmittentiDAO emittentiDAO = new EmittentiDAO();
        MezziDAO mezziDAO = new MezziDAO();
        TesseraDAO tesseraDAO = new TesseraDAO();
        TrattaDAO trattaDAO = new TrattaDAO();
        Random rndm = new Random();


        //------------------------------------------------add-----------------------------------------------
        Biglietti biglietto1 = new Biglietti();
        Biglietti biglietto2 = new Biglietti(LocalDate.of(2020,03, 22));

        //-----------------------------------------------save-----------------------------------------------
        //atacDAO.save(biglietto1);
        //atacDAO.save(biglietto2);

    }
}
