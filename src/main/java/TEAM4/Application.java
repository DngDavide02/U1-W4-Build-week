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
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        Random rndm = new Random();


        //------------------------------------------------add-----------------------------------------------
//        Biglietti biglietto1 = new Biglietti();
//        Tessera tessera1 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(1999, 8, 9));
//        Tessera tessera2 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(2002, 4, 6));
//        tessera2.setDataScadenza(LocalDate.now().minusDays(5));
//        Abbonamenti abbonamento1 = new Abbonamenti(tessera1, TipoAbbonamento.MENSILE);
//        Abbonamenti abbonamento2 = new Abbonamenti(tessera1, TipoAbbonamento.SETTIMANALE,LocalDate.of(2021,3, 20));
//        Mezzi mezzo1 = new Mezzi(50,TipoMezzo.AUTOBUS);
//        Biglietti biglietto3 = new Biglietti();
//        Tratta tratta1 = new Tratta(faker.country().capital().toString(), faker.country().capital(), 30);
//        Tratta tratta1dm = trattaDAO.findById(UUID.fromString("009b0c8a-b3a7-4168-9c1a-03ace7469b28"));
//        Mezzi mezzo1dm = mezziDAO.findById(UUID.fromString("656c524b-e6ed-4d4e-b33c-8433b4b54cfa"));
//        Percorrenza percorrenza1 = new Percorrenza(mezzo1dm, tratta1dm, 60);
//        Percorrenza percorrenza2 = new Percorrenza(mezzo1dm, tratta1dm, 20);

        //-----------------------------------------------save-----------------------------------------------
//        atacDAO.save(biglietto1);
//        atacDAO.save(biglietto3);
//        tesseraDAO.save(tessera1);
//        mezziDAO.save(mezzo1);
//        tesseraDAO.save(tessera1);
//        atacDAO.save(abbonamento2);
//        tesseraDAO.save(tessera2);
//        mezziDAO.save(mezzo1);
//        trattaDAO.save(tratta1);
//        percorrenzaDAO.save(percorrenza1);
//        percorrenzaDAO.save(percorrenza2);
//        System.out.println(percorrenzaDAO.getPercorrenzaMezzo(mezzo1dm, tratta1dm));
//        System.out.println(percorrenzaDAO.mediaPercorrenze(mezzo1dm));

        //---------------------------------------------Scanner------------------------------------------

    }
}
