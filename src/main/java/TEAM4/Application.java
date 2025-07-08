package TEAM4;

import TEAM4.DAO.*;
import TEAM4.entities.*;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BWT4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();
        AtacDAO atacDAO = new AtacDAO(em);
        EmittentiDAO emittentiDAO = new EmittentiDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        Random rndm = new Random();


        //------------------------------------------------add-----------------------------------------------
        Biglietti biglietto1 = new Biglietti();
        Tessera tessera1 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(1999, 8, 9));
        Tessera tessera2 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(2002, 4, 6));
        tessera2.setDataScadenza(LocalDate.now().minusDays(5));
        Abbonamenti abbonamento1 = new Abbonamenti(tessera1, TipoAbbonamento.MENSILE);
        Abbonamenti abbonamento2 = new Abbonamenti(tessera1, TipoAbbonamento.SETTIMANALE,LocalDate.of(2021,3, 20));
        Mezzi mezzo1 = new Mezzi(50,TipoMezzo.AUTOBUS);
        Biglietti biglietto3 = new Biglietti();

        //-----------------------------------------------save-----------------------------------------------
//        atacDAO.save(biglietto1);
//        atacDAO.save(biglietto3);
//        tesseraDAO.save(tessera1);
//        mezziDAO.save(mezzo1);
//        tesseraDAO.save(tessera1);
//        atacDAO.save(abbonamento2);
//        tesseraDAO.save(tessera2);
//        mezziDAO.save(mezzo1);

    }
}
