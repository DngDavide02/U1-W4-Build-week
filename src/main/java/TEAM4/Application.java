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
        Mezzi mezzo1db = mezziDAO.findById(UUID.fromString("c7e071a5-ee6e-4866-8e61-54589b831dbe"));
        Tessera tessera1db = tesseraDAO.findById(UUID.fromString("292b92d8-cc68-4360-bffa-5880756c5d38"));
        Tessera tessera2db = tesseraDAO.findById(UUID.fromString("e69a6b7e-0b99-468b-86d7-343bae635b91"));
        Biglietti biglietto2 = new Biglietti(LocalDate.of(2020,3, 22), mezzo1db);

        Manutenzione manutenzione1 = new Manutenzione(mezzo1db, LocalDate.now().plusDays(5));

        //-----------------------------------------------save-----------------------------------------------
       /*atacDAO.save(biglietto1);
        atacDAO.save(biglietto2);
        tesseraDAO.save(tessera1);

        mezziDAO.save(mezzo1);
        DAO.checkBiglietti(biglietto2);

        tesseraDAO.save(tessera1);
        atacDAO.save(abbonamento2);
        atacDAO.save(biglietto2);
        System.out.println(atacDAO.isObliterated(UUID.fromString("3122ee5c-33b7-4479-bb4b-3c3027a89e0c")));
        Atac biglietto1inbd = atacDAO.findById(UUID.fromString("fa831c99-7e8b-4bd6-86a1-efc66017b7e"));
        atacDAO.checkBiglietti(UUID.fromString("eb11ab6e-d635-4bc1-9999-815677f41de"),mezzodb);
        mezziDAO.obTiketList(UUID.fromString("393500cf-22df-429d-ad88-5a9a77610a8d")).forEach(System.out::println);
        emittentiDAO.emettiBiglietto(mezzodb);
        emittentiDAO.emettiAbbonamento(tessera1db, TipoAbbonamento.MENSILE);
        tesseraDAO.save(tessera2);
        tesseraDAO.rinnovaTessera(tessera2db);
        mezziDAO.save(mezzo1);
        mezziDAO.salvaManutenzione(manutenzione1);
        System.out.println(mezziDAO.isInManutenzione(mezzo1db.getId()));
        mezziDAO.tracciaPeriodiManutenzione(mezzo1db.getId());*/

    }
}
