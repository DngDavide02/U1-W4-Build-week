package TEAM4;

import TEAM4.DAO.*;
import TEAM4.entities.*;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.Scanner;
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

        //---------------------------------------creazione tabella -------------------------------------------

        //creaTabelle(atacDAO, emittentiDAO, mezziDAO, trattaDAO, tesseraDAO, percorrenzaDAO, faker);

        //---------------------------------------------Scanner------------------------------------------
        Scanner scanner = new Scanner(System.in);
        String password = "abcde";
        int risp = 0;
        do {
            System.out.println("Benvenuto in Atac, premi 1 se sei un Amministratore o 2 se sei un utente 0 se vuoi uscire");
            risp = Integer.parseInt(scanner.nextLine());
                switch (risp) {
                    case 1 -> {
                        for (int i = 0; i < 3; i++) {
                        System.out.print("inserisci password: ");
                        String pw = scanner.nextLine();
                        if (pw.equals(password)){
                            System.out.println("1- Aggiungi");
                            System.out.println("2- Modifica");
                            System.out.println("2- Elimina");
                            System.out.println("3- Calcola media percorsi");
                            int r = Integer.parseInt(scanner.nextLine());
                            switch (r){
                                case 1 -> {
                                    scelteSwitch("Aggiungi");
                                    int c1 = Integer.parseInt(scanner.nextLine());
                                    switch (c1){
                                        case 1 -> {creazioneEmittenti(scanner, emittentiDAO);}
                                        case 2 -> {creazioneMezzi(scanner, mezziDAO);}
                                        case 3 -> {creazionePercorrenze(scanner, percorrenzaDAO, mezziDAO, trattaDAO);}
                                        case 4 -> {creazioneTratta(scanner, trattaDAO);}
                                    }
                                }
                                case 2 -> {
                                    scelteSwitch("Modifica");
                                }
                                case 3 ->{
                                    scelteSwitch("Elimina");
                                }
                                case 4 -> {
                                    System.out.println("Inserisci id");
                                    String id = scanner.nextLine();
                                    System.out.println("La media del mezzo con id " + id + " è: " + percorrenzaDAO.mediaPercorrenze(mezziDAO.findById(UUID.fromString(id))).getAsDouble());
                                }

                            }
                            break;
                        }else {
                            System.out.println("password non corretta");
                        }
                        }
                    }

                    case 0 -> System.out.println("Arrivederci");
                }
        }while (risp != 0);
    }//end main

    //------------------------------------------------------------------------------------------------------------------

    public static void creaTabelle(AtacDAO atacDAO, EmittentiDAO emittentiDAO, MezziDAO mezziDAO, TrattaDAO trattaDAO ,TesseraDAO tesseraDAO, PercorrenzaDAO percorrenzaDAO, Faker faker){
        atacDAO.save(new Biglietti());
        emittentiDAO.save(new Distributori(true));
        mezziDAO.save(new Mezzi(TipoMezzo.TRAM));
        mezziDAO.save(new Mezzi(TipoMezzo.AUTOBUS));
        //------------------------------------------------add-----------------------------------------------
        Biglietti biglietto1 = new Biglietti();
        Biglietti biglietto2 = new Biglietti();
        Tessera tessera1 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(1999, 8, 9));
        Tessera tessera2 = new Tessera(faker.name().firstName().toString(), faker.name().lastName().toString(), LocalDate.of(2002, 4, 6));
        Abbonamenti abbonamento1 = new Abbonamenti(tessera1, TipoAbbonamento.MENSILE);
        Abbonamenti abbonamento2 = new Abbonamenti(tessera1, TipoAbbonamento.SETTIMANALE,LocalDate.of(2021,3, 20));
        Mezzi mezzo1 = new Mezzi(TipoMezzo.AUTOBUS);
        Tratta tratta1 = new Tratta(faker.country().capital().toString(), faker.country().capital(), 30);
        Percorrenza percorrenza1 = new Percorrenza(mezzo1, tratta1, 60);
        Percorrenza percorrenza2 = new Percorrenza(mezzo1, tratta1, 20);


        //-----------------------------------------------save-----------------------------------------------
        atacDAO.save(biglietto1);
        atacDAO.save(biglietto2);
        tesseraDAO.save(tessera1);
        mezziDAO.save(mezzo1);
        tesseraDAO.save(tessera1);
        atacDAO.save(abbonamento2);
        tesseraDAO.save(tessera2);
        mezziDAO.save(mezzo1);
        trattaDAO.save(tratta1);
        percorrenzaDAO.save(percorrenza1);
        percorrenzaDAO.save(percorrenza2);

    }

    public static void scelteSwitch(String s){
        System.out.println("1-"+ s +"emittenti");
        System.out.println("2-"+ s +"mezzi");
        System.out.println("3-"+ s +"percorrenze");
        System.out.println("4-"+ s +"tratta");
    }

    public static void creazioneEmittenti(Scanner scanner, EmittentiDAO emittentiDAO){
        System.out.println("Inserisci tipo Emittente: ");
        System.out.println("1- Rivenditore");
        System.out.println("2- Distributore");
        int emit = Integer.parseInt(scanner.nextLine());
        switch (emit){
            case 1 -> {
                System.out.print("Inserisci orario apertura: ");
                int orarioApertura = Integer.parseInt(scanner.nextLine());
                System.out.println("Inserisci orario chiusura: ");
                int orariooChiusura = Integer.parseInt(scanner.nextLine());
                emittentiDAO.save(new Rivenditori(orarioApertura, orariooChiusura));
            }
            case 2 -> {
                System.out.println("premi 1 per rendere il distributore in servizio");
                System.out.println("premi 2 per rendere il distributore fuori servizio");
                int inServizio = Integer.parseInt(scanner.nextLine());
                emittentiDAO.save(new Distributori(inServizio != 1));
            }
            default -> System.out.println("inserisci un numero corretto");
        }
    }//fine creazione emittenti

    public static void creazioneMezzi(Scanner scanner, MezziDAO mezziDAO){
        System.out.println("Inserisci tipo mezzo: ");
        System.out.println("1- AUTOBUS");
        System.out.println("2- TRAM");
        int mezzo = Integer.parseInt(scanner.nextLine());
        switch (mezzo){
            case 1 -> mezziDAO.save(new Mezzi(TipoMezzo.AUTOBUS));
            case 2 -> mezziDAO.save(new Mezzi(TipoMezzo.TRAM));
            default -> System.out.println("inserisci un numero corretto");
        }

    }//fine creazione mezzi

    public static void creazionePercorrenze(Scanner scanner, PercorrenzaDAO percorrenzaDAO, MezziDAO mezziDAO , TrattaDAO trattaDAO){
        System.out.println("Percorrenze");
        System.out.println("vuoi utilizzare un mezzo già esistente o crearne uno nuovo?");
        System.out.println("1- esistente");
        System.out.println("2- nuovo");
        Mezzi mezzo = null;
        int mezzoPercorso = Integer.parseInt(scanner.nextLine());
        switch (mezzoPercorso){
            case 1 -> {
                System.out.print("Inserisci l'id del mezzo: ");
                String idMezzo = scanner.nextLine();
                mezzo = mezziDAO.findById(UUID.fromString(idMezzo));
            }
            case 2 -> {
                creazioneMezzi(scanner, mezziDAO);//TODO DA RIVEDERE
            }
        }
        System.out.println("vuoi utilizzare una tratta già esistente o crearne una nuova?");
        System.out.println("1- esistente");
        System.out.println("2- nuova");
        Tratta tratta = null;
        int trattaPercorso = Integer.parseInt(scanner.nextLine());
        switch (trattaPercorso){
            case 1 -> {
                System.out.print("Inserisci l'id della tratta: ");
                String idTratta = scanner.nextLine();
                tratta = trattaDAO.findById(UUID.fromString(idTratta));
            }
            case 2 -> {
                //creazioneTratta();//TODO DA RIVEDERE
            }
        }
        System.out.println("inserisci il tempo effettivo di percorrenza");
        int tempoPercorrenza = Integer.parseInt(scanner.nextLine());
        percorrenzaDAO.save(new Percorrenza(mezzo, tratta, tempoPercorrenza));

    }//fine creazione percorrenze

    public static void creazioneTratta(Scanner scanner, TrattaDAO trattaDAO){
        System.out.print("Inserisci punto di partenza: ");
        String puntoPartenza = scanner.nextLine();
        System.out.print("Inserisci capolinea: ");
        String capolinea = scanner.nextLine();
        System.out.print("Inserisci il tempo stimato per la tratta: ");
        int tempoStimato = Integer.parseInt(scanner.nextLine());
        trattaDAO.save(new Tratta(puntoPartenza, capolinea, tempoStimato));
    }
}
