package TEAM4;

import TEAM4.DAO.*;
import TEAM4.entities.*;
import TEAM4.exception.notFoundException;
import com.github.javafaker.Faker;
import org.hibernate.sql.HSQLCaseFragment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;


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
        //LocalTime ora = LocalTime.of(23,30);
       // Distributori distributori1 = new Distributori(false);
        //Rivenditori rivenditori1 = new Rivenditori(8,19);
       // emittentiDAO.save(rivenditori1);
       // mezziDAO.editMezzo(UUID.fromString("93b373df-4aa1-4ab0-a822-2127c003753e"),TipoMezzo.TRAM);
        //creaTabelle(atacDAO, emittentiDAO, mezziDAO, trattaDAO, tesseraDAO, percorrenzaDAO, faker);
       // emittentiDAO.editRivenditoreChiusura(UUID.fromString("a7ab2710-d818-4f12-9a07-ed7a32654b89"), 10);
        //---------------------------------------------Scanner------------------------------------------
        Scanner scanner = new Scanner(System.in);
        boolean riprova = true;
        while (riprova) {
            try {
                String password = "abcde";
                int risp = 0;
                do {
                    System.out.println("Benvenuto in Atac, premi 1 se sei un Amministratore o 2 se sei un utente 0 se vuoi uscire");
                    risp = Integer.parseInt(scanner.nextLine());
                    switch (risp) {
                        case 1 -> {
                            for (int i = 0; i < 3; i++) {
                                System.out.print("Inserisci password: ");
                                String pw = scanner.nextLine();
                                if (pw.equals(password)) {
                                    int r = 0;
                                    do {
                                        System.out.println();
                                        System.out.println(" ------------------------------ AMMINISTRATORE -------------------------");
                                        System.out.println("Inserisci operazione da eseguire: ");
                                        System.out.println("1- Aggiungi");
                                        System.out.println("2- Modifica");
                                        System.out.println("3- Elimina");
                                        System.out.println("4- Statistiche");
                                        System.out.println("0- Uscita");
                                        r = Integer.parseInt(scanner.nextLine());
                                        switch (r) {
                                            case 1 -> {
                                                scelteSwitch("Aggiungi");
                                                int c1 = Integer.parseInt(scanner.nextLine());
                                                switch (c1) {
                                                    case 0 -> System.out.println("esco...");
                                                    case 1 -> creazioneEmittenti(scanner, emittentiDAO);
                                                    case 2 -> creazioneMezzi(scanner, mezziDAO,true);
                                                    case 3 ->
                                                            creazionePercorrenze(scanner, percorrenzaDAO, mezziDAO, trattaDAO);
                                                    case 4 -> creazioneTratta(scanner, trattaDAO);
                                                    case 5 -> creaManutenzione(mezziDAO, scanner);
                                                    default ->
                                                            System.out.println("non hai inserito il numero corretto");
                                                }
                                            }
                                            case 2 -> {
                                                scelteSwitch("Modifica");
                                                int c1 = Integer.parseInt(scanner.nextLine());
                                                switch (c1) {
                                                    case 1 -> modificaEmittenti(scanner, emittentiDAO);
                                                    case 2 -> modificaMezzi(scanner, mezziDAO);
                                                    case 3 ->
                                                            modificaPercorrenze(scanner, percorrenzaDAO, mezziDAO, trattaDAO);
                                                    case 4 -> modificaTratta(scanner, trattaDAO);
                                                    case 5 -> modificaManutenzioni(scanner, mezziDAO);
                                                    case 0 -> System.out.println("esco...");
                                                    default ->
                                                            System.out.println("non hai inserito il numero corretto");
                                                }

                                            }
                                            case 3 -> {
                                                scelteSwitch("Elimina");
                                                int c1 = Integer.parseInt(scanner.nextLine());
                                                String ID = null;
                                                if(c1 != 0 && c1 < 6) {
                                                    System.out.println("Inserisci id: ");
                                                    ID = scanner.nextLine();
                                                }
                                                switch (c1) {
                                                    case 0 -> System.out.println("esco...");
                                                    case 1 -> emittentiDAO.findByIdAndDelete(ID);
                                                    case 2 -> mezziDAO.findByIdAndDelete(ID);
                                                    case 3 -> percorrenzaDAO.findByIdAndDelete(ID);
                                                    case 4 -> trattaDAO.findByIdAndDelete(ID);
                                                    case 5 -> mezziDAO.eliminaManutenzione(ID);
                                                    default ->
                                                            System.out.println("non hai inserito il numero corretto");
                                                }
                                            }
                                            case 4 ->
                                                    statistiche(scanner, mezziDAO, percorrenzaDAO, emittentiDAO, trattaDAO);

                                        }
                                    } while (r != 0);
                                    break;
                                } else {
                                    System.out.println("password non corretta");
                                }
                            }
                        }

                        case 2 -> {
                            int scelta = 0;
                            do {
                                System.out.println();
                                System.out.println(" ------------------------------ UTENTE -------------------------");
                                System.out.println("Inserisci operazione da eseguire: ");
                                System.out.println("1- Acquista");
                                System.out.println("2- Visualizza");
                                System.out.println("3- Timbra biglietto");
                                System.out.println("0- Uscita");
                                scelta = Integer.parseInt(scanner.nextLine());
                                switch (scelta) {
                                    case 1 -> acquista(scanner, atacDAO, tesseraDAO);
                                    case 2 -> visualizza(scanner, tesseraDAO, atacDAO, trattaDAO);
                                    case 3 -> {
                                        System.out.print("inserisci id del biglietto: ");
                                        String idB = scanner.nextLine();
                                        System.out.print("inserisci id del mezzo: ");
                                        String idM = scanner.nextLine();
                                        atacDAO.checkBiglietti(idB, mezziDAO.findById(idM));
                                    }
                                    case 0 -> System.out.println("uscita...");
                                    default -> System.out.println("hai sbagliato numero, riprova");
                                }
                            }while (scelta != 0);
                        }

                        case 0 -> {
                            System.out.println("Arrivederci");
                            riprova=false;
                        }
                    }
                } while (risp != 0);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            }catch (IllegalArgumentException e){
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            }catch (DateTimeException | IllegalStateException e){
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            }catch (NullPointerException e){
                System.out.println("id non valido");
                System.out.println();
            }catch (notFoundException e){}
        }
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
        System.out.println("1-"+ s +" emittenti");
        System.out.println("2-"+ s +" mezzi");
        System.out.println("3-"+ s +" percorrenze");
        System.out.println("4-"+ s +" tratta");
        System.out.println("5-"+ s +" manutenzione");
        System.out.println("0- esci");
    }

    public static void creazioneEmittenti(Scanner scanner, EmittentiDAO emittentiDAO){
        boolean riprova = true;
        while (riprova) {
            try {
        System.out.println("Inserisci tipo Emittente: ");
        System.out.println("1- Rivenditore");
        System.out.println("2- Distributore");
        System.out.println("0- esci");
        int emit = Integer.parseInt(scanner.nextLine());
        switch (emit){
            case 0 ->{
                riprova =false;
                System.out.println("esco...");
            }
            case 1 -> {
                int n=1;
                int oraA= -1;
                for (int i=0; i<n ;i++) {
                    System.out.print("Inserisci ora dell'orario di apertura (da 0 a 23): ");
                    oraA = Integer.parseInt(scanner.nextLine());
                    if (oraA>23 || oraA<0){
                        System.out.println("Non hai inerito un numero tra 0 e 23, riprova");
                        n++;
                    }
                }
                n=1;
                int minutiA= -1;
                for (int i=0; i<n ;i++) {
                    System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                    minutiA = Integer.parseInt(scanner.nextLine());
                    if (minutiA > 59 || minutiA < 0) {
                        System.out.println("Non hai inerito un numero tra 0 e 59, riprova");
                        n++;
                    }
                }
                LocalTime orarioApertura = LocalTime.of(oraA,minutiA);
                n=1;
                int oraC= -1;
                for (int i=0; i<n ;i++) {
                    System.out.print("Inserisci ora dell'orario di apertura (da 0 a 23): ");
                    oraC = Integer.parseInt(scanner.nextLine());
                    if (oraC>23 || oraC<0){
                        System.out.println("Non hai inerito un numero tra 0 e 23, riprova");
                        n++;
                    }
                }
                n=1;
                int minutiC= -1;
                for (int i=0; i<n ;i++) {
                    System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                    minutiC = Integer.parseInt(scanner.nextLine());
                    if (minutiC > 59 || minutiC < 0) {
                        System.out.println("Non hai inerito un numero tra 0 e 59, riprova");
                        n++;
                    }
                }
                LocalTime orarioChiusura = LocalTime.of(oraC,minutiC);;
                emittentiDAO.save(new Rivenditori(orarioApertura, orarioChiusura));
            }
            case 2 -> {
                System.out.println("premi 1 per rendere il distributore in servizio");
                System.out.println("premi 2 per rendere il distributore fuori servizio");
                int inServizio = Integer.parseInt(scanner.nextLine());
                if (inServizio == 1 || inServizio == 2) {
                    emittentiDAO.save(new Distributori(inServizio != 1));
                }else {
                    System.out.println("hai sbagliato numero, riprova");
                }
            }
            default -> System.out.println("Inserisci un numero corretto");
        }
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//fine creazione emittenti

    public static void creazioneMezzi(Scanner scanner, MezziDAO mezziDAO, boolean repeat){
        boolean riprova = true;
        while (riprova) {
            try {
                int mezzo = 0;
                do {
                    System.out.println("Inserisci tipo mezzo: ");
                    System.out.println("1- AUTOBUS");
                    System.out.println("2- TRAM");
                    System.out.println("0- esci");
                    mezzo = Integer.parseInt(scanner.nextLine());
                    switch (mezzo) {
                        case 0 -> {
                            riprova = false;
                            System.out.println("esco...");
                        }
                        case 1 -> mezziDAO.save(new Mezzi(TipoMezzo.AUTOBUS));
                        case 2 -> mezziDAO.save(new Mezzi(TipoMezzo.TRAM));
                        default -> System.out.println("Inserisci un numero corretto");
                    }
                    String s = null;
                    if (repeat && mezzo != 0 && mezzo < 3) {
                        while (true) {
                            System.out.print("Vuoi creare altri mezzi? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                mezzo = 0;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                    if (!repeat){
                        mezzo=0;
                        riprova=false;
                    }
                } while (mezzo != 0);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//fine creazione mezzi

    public static void creazionePercorrenze(Scanner scanner, PercorrenzaDAO percorrenzaDAO, MezziDAO mezziDAO , TrattaDAO trattaDAO){
        boolean riprova = true;
        while (riprova) {
            try {
                int mezzoPercorso = 0;
                int trattaPercorso = 0;
                do {
                    System.out.println("Vuoi utilizzare un mezzo già esistente o crearne uno nuovo?");
                    System.out.println("1- esistente");
                    System.out.println("2- nuovo");
                    System.out.println("0- esci");
                    Mezzi mezzo = null;
                    int numMezzi = mezziDAO.totalMezzi();
                    mezzoPercorso = Integer.parseInt(scanner.nextLine());
                    switch (mezzoPercorso) {
                        case 0 -> {
                            riprova = false;
                            System.out.println("esco...");
                        }
                        case 1 -> {
                            System.out.print("Inserisci l'id del mezzo: ");
                            String idMezzo = scanner.nextLine();
                            mezzo = mezziDAO.findById(idMezzo);
                        }
                        case 2 -> {
                            creazioneMezzi(scanner, mezziDAO, false);
                            mezzo = mezziDAO.lastCreate();
                            if (numMezzi >= mezziDAO.totalMezzi()) mezzoPercorso=0;
                        }
                        default -> System.out.println("hai sbagliato numero");
                    }
                    if (mezzoPercorso !=0 && mezzoPercorso < 3){
                    System.out.println("vuoi utilizzare una tratta già esistente o crearne una nuova?");
                    System.out.println("1- esistente");
                    System.out.println("2- nuova");
                    System.out.println("0- esci");
                    Tratta tratta = null;
                    trattaPercorso = Integer.parseInt(scanner.nextLine());
                    switch (trattaPercorso) {
                        case 0 -> {
                            riprova = false;
                            System.out.println("esco...");
                        }
                        case 1 -> {
                            System.out.print("Inserisci l'id della tratta: ");
                            String idTratta = scanner.nextLine();
                            tratta = trattaDAO.findById(idTratta);
                        }
                        case 2 -> {
                            creazioneTratta(scanner, trattaDAO);
                            tratta = trattaDAO.lastCreate();
                        }
                        default -> System.out.println("hai sbagliato numero");
                    }
                    System.out.println("Inserisci il tempo effettivo di percorrenza in minuti");
                    int tempoPercorrenza = Integer.parseInt(scanner.nextLine());
                    percorrenzaDAO.save(new Percorrenza(mezzo, tratta, tempoPercorrenza));
                    String s = null;
                    if (trattaPercorso != 0 && mezzoPercorso < 3 && trattaPercorso < 3) {
                        while (true) {
                            System.out.print("Vuoi creare altre percorrenze? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                trattaPercorso = 0;
                                riprova = false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                }
                } while (mezzoPercorso != 0 || trattaPercorso !=0);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//fine creazione percorrenze

    public static void creazioneTratta(Scanner scanner, TrattaDAO trattaDAO){
        boolean riprova = true;
        while (riprova) {
            try {
                System.out.print("Inserisci punto di partenza: ");
                String puntoPartenza = scanner.nextLine();
                System.out.print("Inserisci capolinea: ");
                String capolinea = scanner.nextLine();
                System.out.print("Inserisci il tempo stimato per la tratta in minuti: ");
                int tempoStimato = Integer.parseInt(scanner.nextLine());
                trattaDAO.save(new Tratta(puntoPartenza, capolinea, tempoStimato));
                String s = null;
                while (true) {
                    System.out.print("Vuoi creare altre tratte? (y/n): ");
                    s = scanner.nextLine();
                    if (s.equalsIgnoreCase("n")) {
                        riprova = false;
                        System.out.println("esco...");
                    }
                    if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                    else System.out.println("non hai inserito la lettera corretta, riprova");
                }
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//fine creazione tratta

    public  static void  modificaEmittenti(Scanner scanner, EmittentiDAO emittentiDAO){
        boolean riprova = true;
        while (riprova) {
            try {
                int rT = 0;
                int oM = 0;
                boolean esci = false;
                do {
        System.out.println("Quale tipo di emittente vuoi modificare: ");
        System.out.println("1- Rivenditore");
        System.out.println("2- Distributore");
        System.out.println("0- esci");
         rT = Integer.parseInt(scanner.nextLine());
        switch (rT){
            case 0 -> {
                riprova = false;
                System.out.println("esco...");
            }
            case 1 -> {
                System.out.println("Cosa vuoi modificare: ");
                System.out.println("1- Orario Apertura");
                System.out.println("2- Orario Chiusura");
                System.out.println("3- Entrambi gli orari");
                System.out.println("0- esci");
                oM = Integer.parseInt(scanner.nextLine());
                String id= null;
                if (oM >0 && oM<4) {
                    System.out.println("Inserisci id: ");
                    id = scanner.nextLine();
                }
                int n = 1;
                if (oM == 3) {
                    oM=1;
                    n=2;
                }
                for (int i=0; i<n; i++){
                    switch (oM) {
                        case 0 -> {
                            esci = true;
                            System.out.println("esco...");
                        }
                        case 1 -> {
                            int r=1;
                            int oraA= -1;
                            for (int j=0; j<r ;j++) {
                                System.out.print("Inserisci ora dell'orario di apertura (da 0 a 23): ");
                                oraA = Integer.parseInt(scanner.nextLine());
                                if (oraA>23 || oraA<0){
                                    System.out.println("Non hai inerito un numero tra 0 e 23, riprova");
                                    r++;
                                }
                            }
                            r=1;
                            int minutiA= -1;
                            for (int j=0; j<r ;j++) {
                                System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                                minutiA = Integer.parseInt(scanner.nextLine());
                                if (minutiA > 59 || minutiA < 0) {
                                    System.out.println("Non hai inerito un numero tra 0 e 59, riprova");
                                    r++;
                                }
                            }
                            LocalTime orarioApertura = LocalTime.of(oraA, minutiA);
                            emittentiDAO.editRivenditoreApertura(id, orarioApertura);
                        }
                        case 2 -> {
                            int r=1;
                            int oraC= -1;
                            for (int j=0; j<r ;j++) {
                                System.out.print("Inserisci ora dell'orario di apertura (da 0 a 23): ");
                                oraC = Integer.parseInt(scanner.nextLine());
                                if (oraC>23 || oraC<0){
                                    System.out.println("Non hai inerito un numero tra 0 e 23, riprova");
                                    r++;
                                }
                            }
                            r=1;
                            int minutiC= -1;
                            for (int j=0; j<r ;j++) {
                                System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                                minutiC = Integer.parseInt(scanner.nextLine());
                                if (minutiC > 59 || minutiC < 0) {
                                    System.out.println("Non hai inerito un numero tra 0 e 59, riprova");
                                    r++;
                                }
                            }
                            LocalTime orarioChiusura = LocalTime.of(oraC, minutiC);
                            emittentiDAO.editRivenditoreChiusura(id, orarioChiusura);
                        }
                        default -> System.out.println("Non hai selezionato un operazione possibile");
                    }
                    oM=2;
                }
            }
            case 2-> {
                System.out.println("Inserisci id: ");
                String id = scanner.nextLine();
                System.out.println("Il distributore selezionato è " + (emittentiDAO.isOutOfService(id)? "fuori servizio" : "in servizio"));
                String resp = null;
                while (true) {
                    System.out.println("Vuoi cambiare lo stato del distributore? (y/n)");
                    resp = scanner.nextLine();
                    if (resp.equalsIgnoreCase("y") || resp.equalsIgnoreCase("n")) break;
                    else System.out.println("non hai inserito la lettera corretta, riprova");
                }
                if (resp.equalsIgnoreCase("y")) emittentiDAO.editOutOfService(id,!emittentiDAO.isOutOfService(id));
                else System.out.println("esco...");
            }
            default -> System.out.println("hai sbagliato numero");
        }
                    String s = null;
                    if (rT > 0 && !esci && rT < 3 && oM> 0 && oM < 4) {
                        while (true) {
                            System.out.print("Vuoi modificare altri emittenti? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                rT = 0;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (rT != 0);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }

    public static void modificaMezzi(Scanner scanner, MezziDAO mezziDAO){
        boolean riprova = true;
        while (riprova) {
            try {
                int modM = 0;
                do {
        System.out.println("Quale parametro del mezzo vuoi modificare: ");
        System.out.println("1- modifica tipo mezzo");
        System.out.println("2- modifica capienza");
        System.out.println("0- esci");
        modM = Integer.parseInt(scanner.nextLine());
        String id = null;
        if(modM > 0 && modM < 3) {
            System.out.println("Inserisci id del mezzo: ");
            id = scanner.nextLine();
        }
        switch (modM){
            case 0 -> {
                System.out.println("esco...");
                riprova = false;
            }
            case 1 -> {
                System.out.println("Il mezzo con id " + id + " è un " + mezziDAO.findById(id).getTipoMezzo() + ", vuoi cambiarlo? (y/n)");
                String resp = scanner.nextLine();
                if(resp.equalsIgnoreCase("y")) {
                    if (TipoMezzo.AUTOBUS == mezziDAO.findById(id).getTipoMezzo()) {
                        mezziDAO.editMezzo(id, TipoMezzo.TRAM);
                    } else {
                        mezziDAO.editMezzo(id, TipoMezzo.AUTOBUS);
                    }
                } else System.out.println("esco...");
            }
            case 2 -> {
                System.out.print("Inserisci la nuova capienza del mezzo: ");
                int newCapienza = Integer.parseInt(scanner.nextLine());
                mezziDAO.editMezzo(id, newCapienza);
            }
            default -> System.out.println("Input sbagliato");
        }
                    String s = null;
                    if (modM > 0 && modM < 3) {
                        while (true) {
                            System.out.print("Vuoi modificare altri mezzi? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                modM = 0;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (modM != 0);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//modifica mezzo

    public static void modificaPercorrenze(Scanner scanner, PercorrenzaDAO percorrenzaDAO, MezziDAO mezziDAO, TrattaDAO trattaDAO){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int modP = 0;
                do {
        System.out.println("Quale parametro della percorrenza vuoi modificare: ");
        System.out.println("1- modifica id mezzo");
        System.out.println("2- modifica il tratto della percorrenza");
        System.out.println("3- modifica il tempo di percorrenza effettivo");
        System.out.println("4- modifica tutto");
        System.out.println("0- esci");
        modP = Integer.parseInt(scanner.nextLine());
        String idP = null;
        if(modP > 0 && modP < 5 ) {
            System.out.print("Inserisci id Percorrenza: ");
            idP = scanner.nextLine();
        }
        int n = 1;
        if (modP == 4){
            modP = 1;
            n = 3;
        }
        for (int i=0; i<n; i++){
        switch (modP) {
            case 0 ->{
                System.out.println("esco...");
                riprova = false;
                esci = true;
            }
            case 1 -> {
                System.out.print("Inserisci id del mezzo: ");
                String idM = scanner.nextLine();
                if (!idM.isEmpty()) percorrenzaDAO.modificaMezzo(idP, mezziDAO.findById(idM));
                else System.out.println("Parametro non modificato");
            }
            case 2 -> {
                System.out.print("Inserisci id della tratta: ");
                String idT = scanner.nextLine();
                if (!idT.isEmpty()) percorrenzaDAO.modificaTratta(idP, trattaDAO.findById(idT));
                else System.out.println("Parametro non modificato");
            }
            case 3 -> {
                System.out.print("Inserisci tempo effettivo della percorrenza in minuti (premi 0 per uscire): ");
                int temp = Integer.parseInt(scanner.nextLine());
                if (temp != 0) percorrenzaDAO.modificaTempoEffettivo(idP, temp);
                else System.out.println("Parametro non modificato");
            }
            default -> System.out.println("hai sbagliato numero");
        }
        modP++;
        }
        String s = null;
        if (riprova && modP > 0 && modP < 5) {
            while (true) {
                System.out.print("Vuoi modificare altre percorrenze? (y/n): ");
                s = scanner.nextLine();
                if (s.equalsIgnoreCase("n")) {
                    modP = 0;
                    esci = true;
                    riprova=false;
                    System.out.println("esco...");
                }
                if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                else System.out.println("non hai inserito la lettera corretta, riprova");
            }
        }
    } while (!esci);
} catch (NumberFormatException e) {
        System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
        System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
        System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
        System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
        }
        }
    }//fine mod percorrenza

    public static void modificaTratta(Scanner scanner, TrattaDAO trattaDAO){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int modT = 0;
                do {
        System.out.println("Quale parametro della tratta vuoi modificare: ");
        System.out.println("1- modifica punto di partenza");
        System.out.println("2- modifica capolinea");
        System.out.println("3- modifica il tempo di percorrenza");
        System.out.println("4- modifica tutto");
        System.out.println("0- esci");
        modT = Integer.parseInt(scanner.nextLine());
        String idT = null;
        if (modT > 0 && modT < 5){
            System.out.print("Inserisci id Tratta: ");
            idT = scanner.nextLine();
        }
        int n = 1;
        if (modT == 4){
            modT = 1;
            n = 3;
        }
        for (int i=0; i<n; i++){
            switch (modT) {
                case 0 -> {
                    riprova = false;
                    esci = true;
                    System.out.println("esco...");
                }
                case 1 -> {
                    System.out.print("Inserisci luogo di partenza: ");
                    String partenza = scanner.nextLine();
                    if (!partenza.isEmpty()) trattaDAO.modificaParenza(idT, partenza);
                    else System.out.println("Parametro non modificato");
                }
                case 2 -> {
                    System.out.print("Inserisci capolinea: ");
                    String capolinea = scanner.nextLine();
                    if (!capolinea.isEmpty()) trattaDAO.modificaCapolinea(idT, capolinea);
                    else System.out.println("Parametro non modificato");
                }
                case 3 -> {
                    System.out.print("Inserisci tempo stimato della percorrenza in minuti (premi 0 per uscire): ");
                    int temp = Integer.parseInt(scanner.nextLine());
                    if (temp != 0) trattaDAO.modificaTempoPercorrenza(idT, temp);
                    else System.out.println("Parametro non modificato");
                }
                default -> System.out.println("hai sbagliato numero");
            }
            modT++;
        }
                    String s = null;
                    if (riprova && modT > 0 && modT < 5) {
                        while (true) {
                            System.out.print("Vuoi modificare altre tratte? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                modT = 0;
                                esci = true;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (!esci);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }
    public static void statistiche (Scanner scanner, MezziDAO mezziDAO, PercorrenzaDAO percorrenzaDAO, EmittentiDAO emittentiDAO, TrattaDAO trattaDAO ) {
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int select = 0;
                int scel = 0;
                int scel2 = 0;
                int stat = 0;
                do {
        System.out.println("Quale statistica vuoi visualizzare: ");
        System.out.println("1- statistica biglietti/abbonamenti emessi");
        System.out.println("2- statistica biglietti vidimati");
        System.out.println("3- statistica manutenzione mezzi");
        System.out.println("4- statistica tratte");
        System.out.println("0- esci");
        select = Integer.parseInt(scanner.nextLine());
        switch (select){
            case 0 -> {
                riprova = false;
                esci = true;
                System.out.println("esco...");
            }
            case 1 ->{
                System.out.println("Seleziona una delle opzioni: ");
                System.out.println("1- visualizza biglietti emessi in un periodo di tempo");
                System.out.println("2- visualizza abbonamenti emessi in un periodo di tempo");
                System.out.println("3- visualizza biglietti e abbonamenti emessi in un periodo di tempo");
                System.out.println("0- esci");
                stat = Integer.parseInt(scanner.nextLine());
                switch (stat){
                    case 0 -> System.out.println("esco...");
                    case 1-> emessiStats(scanner,emittentiDAO,1);
                    case 2-> emessiStats(scanner,emittentiDAO,2);
                    case 3-> emessiStats(scanner,emittentiDAO,3);
                    default -> System.out.println("Input sbagliato");
                }
            }
            case 2 ->{
                System.out.println("scegli una delle opzioni: ");
                System.out.println("1- visualizza biglietti per periodo di tempo per tutti i mezzi");
                System.out.println("2- visualizza biglietti per periodo di tempo con id mezzo");
                System.out.println("3- visualizza biglietti in totale");
                System.out.println("4- visualizza tutti i biglietti per un mezzo");
                System.out.println("0- esci");
                scel = Integer.parseInt(scanner.nextLine());
                String idM = null;
                LocalDate peridoInizio = null;
                LocalDate periodoFine = null;
                if(scel == 2 || scel == 4){
                    System.out.print("inierisci id del mezzo: ");
                    idM = scanner.nextLine();
                }
                if (scel == 1 || scel == 2){
                    peridoInizio = localDateCreate(scanner, " della data di inizio controllo");
                    periodoFine =  localDateCreate(scanner, " della data di fine controllo");
                }
                switch (scel){
                    case 1 -> mezziDAO.obTiketListDate(peridoInizio, periodoFine).forEach(System.out::println);
                    case 2 -> mezziDAO.obTiketListDate(idM, peridoInizio, periodoFine).forEach(System.out::println);
                    case 3 -> mezziDAO.obTiketList().forEach(System.out::println);
                    case 4 -> mezziDAO.obTiketList(idM).forEach(System.out::println);
                    case 0 -> System.out.println("uscita...");
                    default -> System.out.println("hai sbagliato numero");

                }

            }
            case 3 -> {
                System.out.print("inserisci id del mezzo: ");
                String idM = scanner.nextLine();
                System.out.print("inserisci data inizio controllo: ");
                LocalDate dataInizio = localDateCreate(scanner, "");
                System.out.println("inserisci data fine controllo: ");
                LocalDate dataFine = localDateCreate(scanner, "");
                mezziDAO.tracciaPeriodiManutenzione(idM, dataInizio, dataFine);
            }
            case 4 ->{
                System.out.println("1- visualizza media percorrenza mezzo");
                System.out.println("2- visualizza numero tratte per il mezzo");
                System.out.println("0- uscita");
                scel2 = Integer.parseInt(scanner.nextLine());
                String idM = null;
                if (scel2>0 && scel2 <3){
                System.out.print("Inserisci id mezzo");
                idM = scanner.nextLine();
                }
                switch (scel2){
                    case 1 -> System.out.println("La media del mezzo con id " + idM + " è: " + percorrenzaDAO.mediaPercorrenze(mezziDAO.findById(idM)).getAsDouble());
                    case 2 -> {
                        System.out.print("inserisci id della tratta: ");
                        String idT = scanner.nextLine();
                        System.out.println("il numero di tratte per il mezzo selezionato è di: " +  percorrenzaDAO.getPercorrenzaMezzo(mezziDAO.findById(idM), trattaDAO.findById(idT)));
                    }
                    case 0 -> System.out.println("Uscita...");
                    default -> System.out.println("hai sbagliato numero");
                }
            }
            default -> System.out.println("hai sbagliato numero");

        }
                    String s = null;
                    if (riprova && select > 0 && select < 5 && scel2>0 && scel2 <3 && scel>0 && scel <5 && stat>0 && stat <4) {
                        while (true) {
                            System.out.print("Vuoi visualizzare altre statistiche? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                esci = true;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (!esci);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }

    }

    public static void emessiStats(Scanner scanner, EmittentiDAO emittentiDAO, int c){
        System.out.println("Seleziona una delle opzioni: ");
        System.out.println("1- visualizza totale");
        System.out.println("2- visualizza per emittente");
        System.out.println("0- esci");
        int visE = Integer.parseInt(scanner.nextLine());
        LocalDate inizioPeriodo= null;
        LocalDate finePeriodo = null;
        if (visE >0 && visE<3) {
             inizioPeriodo = localDateCreate(scanner, " inizio periodo");
             finePeriodo = localDateCreate(scanner, " fine periodo");
        }
        switch (visE){
            case 0 -> System.out.println("esco...");
            case 1 -> System.out.println("Sono stati emessi un totale di " +
                    switch (c){
                    case 1 -> emittentiDAO.bigliettiEmessiInTolale(inizioPeriodo,finePeriodo) + " biglietti";
                    case 2 -> emittentiDAO.abbonamentiEmessiInTolale(inizioPeriodo,finePeriodo) + " abbonamenti";
                    case 3 -> emittentiDAO.atacEmessiInTolale(inizioPeriodo,finePeriodo) + "biglietti e abbonamenti";
                    default -> "errore";
                    });
            case 2 -> {
                System.out.print("Inserisci id Emittente:");
                String id = scanner.nextLine();
                System.out.println("Sono stati emessi " +
                        switch (c){
                            case 1 -> emittentiDAO.bigliettiEmessi(inizioPeriodo,finePeriodo,id) + " biglietti nel emittente " + id;
                            case 2 -> emittentiDAO.abbonamentiEmessi(inizioPeriodo,finePeriodo,id) + " abbonamenti nel emittente " + id;
                            case 3 -> emittentiDAO.atacEmessi(inizioPeriodo,finePeriodo,id) + "biglietti e abbonamenti";
                            default -> "errore";
                        });
            }
            default -> System.out.println("hai sbagliato numero");
        }
    }

    public static void creaManutenzione(MezziDAO mezziDAO, Scanner scanner){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int scel = 0;
                do {
        System.out.println("1- aggiungi manutenzione gia effettuata");
        System.out.println("2- aggiungi nuova manutenzione");
        System.out.println("0- esci");
        scel = Integer.parseInt(scanner.nextLine());
        String idM = null;
        if (scel > 0 && scel < 3) {
            System.out.println("inserisci un id di un veicolo");
            idM = scanner.nextLine();
        }
        switch (scel){
            case 1 -> {
                System.out.print("inserisci data di inizio: ");
                LocalDate dataInizio = localDateCreate(scanner, "");
                System.out.println("inserisci data fine: ");
                LocalDate dataFine = localDateCreate(scanner, "");
                System.out.println("inserisci causale: ");
                String causale = scanner.nextLine();
                mezziDAO.salvaManutenzione(new Manutenzione(mezziDAO.findById(idM), dataInizio, dataFine, causale));
            }
            case 2 -> {
                System.out.println("inserisci causale: ");
                String causale = scanner.nextLine();
                mezziDAO.salvaManutenzione(new Manutenzione(mezziDAO.findById(idM), causale));
            }
            case 0 -> {
                riprova = false;
                esci = true;
                System.out.println("uscita...");
            }
            default -> System.out.println("hai sbagliato numero");
        }
                    String s = null;
                    if (riprova && scel > 0 && scel < 3) {
                        while (true) {
                            System.out.print("Vuoi modificare altre manutenzioni? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                scel = 0;
                                esci = true;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (!esci);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }//fine crea man

    public static void modificaManutenzioni(Scanner scanner, MezziDAO mezziDAO){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int modM = 0;
                do {
        System.out.println("Quale parametro della manutenzione vuoi modificare: ");
        System.out.println("1- modifica data di inizio");
        System.out.println("2- modifica data di fine");
        System.out.println("3- modifica mezzo");
        System.out.println("4- modifica causale");
        System.out.println("5- modifica tutto");
        System.out.println("0- esci");
        modM = Integer.parseInt(scanner.nextLine());
        String idM = null;
        if (modM > 0 && modM < 6){
            System.out.print("Inserisci id Manutenzione: ");
            idM = scanner.nextLine();
        }
        int n = 1;
        if (modM == 5){
            modM = 1;
            n = 4;
        }

        for (int i=0; i<n; i++){
            switch (modM) {
                case 0 -> {
                    riprova = false;
                    esci = true;
                    System.out.println("esco...");
                }
                case 1 -> {
                    System.out.print("Inserisci data di inizio manutenzione: ");
                    mezziDAO.modificaDataInizioManutenzioni(idM ,localDateCreate(scanner, ""));
                }
                case 2 -> {
                    System.out.print("Inserisci data fine manutenzione: ");
                    mezziDAO.modificaDataFineManutenzioni(idM ,localDateCreate(scanner, ""));
                }
                case 3 -> {
                    System.out.print("Inserisci il mezzo: ");
                    String idMezzo = scanner.nextLine();
                    mezziDAO.modificaMezziManutenzioni(idM, mezziDAO.findById(idMezzo));
                }
                case 4 -> {
                    System.out.println("inserisci modifica per la causale (premi 0 per uscire): ");
                    String causaleMod = scanner.nextLine();
                    mezziDAO.modificaCausaleManutenzioni(idM, causaleMod);
                }
                default -> System.out.println("hai sbagliato numero");
            }
            modM++;
        }
        String s = null;
        if (riprova && modM > 0 && modM < 6) {
            while (true) {
                System.out.print("Vuoi modificare altre manutenzioni? (y/n): ");
                s = scanner.nextLine();
                if (s.equalsIgnoreCase("n")) {
                    modM = 0;
                    esci = true;
                    riprova=false;
                    System.out.println("esco...");
                }
                if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                else System.out.println("non hai inserito la lettera corretta, riprova");
            }
        }
    } while (!esci);
} catch (NumberFormatException e) {
        System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
        System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
        System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
        System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
        }
        }
    }

    public static void acquista(Scanner scanner, AtacDAO atacDAO, TesseraDAO tesseraDAO){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int scel = 0;
                do {
        System.out.println("1- acquista biglietto");
        System.out.println("2- acquista abbonamento");
        System.out.println("3- acquista tessera");
        System.out.println("0- uscita");
        Tessera myTessera = null;
        scel = Integer.parseInt(scanner.nextLine());
        int count=1;
        for (int i=0; i<count; i++) {
            switch (scel) {
                case 0-> {
                    riprova = false;
                    esci = true;
                    System.out.println("esco...");
                }
                case 1 -> atacDAO.save(new Biglietti());
                case 2 -> {
                    if (myTessera==null || count == 1) {
                        String risp = null;
                        while (true){
                            System.out.print("Possiedi una tessera? (y/n): ");
                            risp = scanner.nextLine();
                            if (risp.equalsIgnoreCase("y") || risp.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                        if (risp.equalsIgnoreCase("y")) {
                            System.out.println("Inserisci id della tessera: ");
                            String tessera = scanner.nextLine();
                            if (!tesseraDAO.checkSub(tessera)){
                                System.out.println("Abbonamento già presente");
                            } else if (tesseraDAO.isExpire(tessera)) {
                                System.out.println("Tessera scaduta");
                            }else {
                                System.out.println("Inserisci tipo abbonamento");
                                System.out.println("1- mensile");
                                System.out.println("2- settimanale");
                                System.out.println("0- uscita");
                                int s = Integer.parseInt(scanner.nextLine());
                                TipoAbbonamento tipoAbbonamento = null;
                                switch (s) {
                                    case 0 -> System.out.println("esco...");
                                    case 1 -> tipoAbbonamento = TipoAbbonamento.MENSILE;
                                    case 2 -> tipoAbbonamento = TipoAbbonamento.SETTIMANALE;
                                    default -> System.out.println("hai sbagliato tipo");
                                }
                                if (s != 0)
                                    atacDAO.save(new Abbonamenti(tesseraDAO.findById(tessera), tipoAbbonamento));
                            }
                            } else {
                            String s = null;
                            while (true) {
                                System.out.print("Per acquistare un abbonamento bisogna possedere una tessera, vuoi acquistarne una? (y/n): ");
                                s = scanner.nextLine();
                                if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                                else System.out.println("non hai inserito la lettera corretta, riprova");
                            }
                            if (s.equalsIgnoreCase("y")) {
                                scel = 3;
                                count++;
                            } else System.out.println("esco...");
                        }
                    } else {
                        System.out.println("1- mensile");
                        System.out.println("2- settimanale");
                        System.out.println("0- uscita");
                        int s = Integer.parseInt(scanner.nextLine());
                        TipoAbbonamento tipoAbbonamento = null;
                        switch (s) {
                            case 0 -> System.out.println("esco...");
                            case 1 -> tipoAbbonamento = TipoAbbonamento.MENSILE;
                            case 2 -> tipoAbbonamento = TipoAbbonamento.SETTIMANALE;
                            default -> System.out.println("hai sbagliato tipo");
                        }
                        if(s != 0) atacDAO.save(new Abbonamenti(myTessera, tipoAbbonamento));
                    }
                }
                case 3 -> {
                    System.out.println("------Creazione tessera------");
                    System.out.print("inserisci il tuo nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("inserisci cognome: ");
                    String cognome = scanner.nextLine();
                    tesseraDAO.save(new Tessera(nome, cognome, localDateCreate(scanner, " di nascita")));
                    if (count>1){
                        String s = null;
                        while (true) {
                            System.out.print("Vuoi acquistare un abbonamento per questa tessera? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                        if (s.equalsIgnoreCase("y")) {
                            myTessera = tesseraDAO.lastCreate();
                            scel = 2;
                            count++;
                        } else System.out.println("esco...");
                    }
                }
                default -> System.out.println("hai sbagliato numero");
            }
        }
                    String s = null;
                    if (riprova && scel > 0 && scel < 4) {
                        while (true) {
                            System.out.print("Vuoi continuare ad acquistare? (y/n): ");
                            s = scanner.nextLine();
                            if (s.equalsIgnoreCase("n")) {
                                scel = 0;
                                esci = true;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (!esci);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }

    public static void visualizza(Scanner scanner, TesseraDAO tesseraDAO, AtacDAO atacDAO, TrattaDAO trattaDAO){
        boolean riprova = true;
        boolean esci = false;
        while (riprova) {
            try {
                int s = 0;
                int scel = 0;
                do {
        System.out.println("1- visualizza Titoli di viaggio");
        System.out.println("2- visualizza tratte");
        System.out.println("0- uscire");
        s = Integer.parseInt(scanner.nextLine());
        switch (s){
            case 0 -> {
                riprova = false;
                esci = true;
                System.out.println("esco...");
            }
            case 1 -> {
                System.out.println("1- visualizza tessera");
                System.out.println("2- visualizza abbonamento");
                System.out.println("0- uscire");
                scel = Integer.parseInt(scanner.nextLine());
                switch (scel){
                    case 0 -> {
                        System.out.println("esco...");
                        esci = true;
                    }
                    case 1 -> {
                        System.out.println("Inserisci id della tua tessera: ");
                        String idTessera = scanner.nextLine();
                        System.out.println(tesseraDAO.findById(idTessera));
                    }
                    case 2 -> {
                        System.out.println("Inserisci id dell'abbonamento");
                        String idAbbonamento = scanner.nextLine();
                        System.out.println(atacDAO.findAbbonamentoById(idAbbonamento));
                    }
                    default -> System.out.println("hai sbagliato numero");
                }
            }
            case 2 -> {
                System.out.print("inserisci partenza: ");
                String partenza = scanner.nextLine();
                System.out.print("inserisci arrivo: ");
                String arrivo = scanner.nextLine();
                trattaDAO.trovaTratta(partenza, arrivo).forEach(System.out::println);
            }
            default -> System.out.println("hai sbagliato numero");
        }
                    String st = null;
                    if (riprova && s > 0 && s < 3 && scel>0 && scel<3) {
                        while (true) {
                            System.out.print("Vuoi visualizzare altro? (y/n): ");
                            st = scanner.nextLine();
                            if (st.equalsIgnoreCase("n")) {
                                s = 0;
                                esci = true;
                                riprova=false;
                                System.out.println("esco...");
                            }
                            if (st.equalsIgnoreCase("y") || st.equalsIgnoreCase("n")) break;
                            else System.out.println("non hai inserito la lettera corretta, riprova");
                        }
                    }
                } while (!esci);
            } catch (NumberFormatException e) {
                System.out.println("non hai inserito un dato corretto, riprova");
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("hai sbagliato input, riprova");
                System.out.println();
            } catch (DateTimeException | IllegalStateException e) {
                System.out.println("hai inserito un dato sbagliato, riprova");
                System.out.println();
            } catch (NullPointerException e) {
                System.out.println("id non valido");
                System.out.println();
            } catch (notFoundException e) {
            }
        }
    }

    public static LocalDate localDateCreate(Scanner scanner, String str) {
        System.out.print("Inserisci anno" + str + ": ");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.print("Inserisci mese" + str + ": ");
        int m = Integer.parseInt(scanner.nextLine());
        System.out.print("Inserisci giorno" + str + ": ");
        int g = Integer.parseInt(scanner.nextLine());
        return LocalDate.of(a,m,g);
    }

    }
