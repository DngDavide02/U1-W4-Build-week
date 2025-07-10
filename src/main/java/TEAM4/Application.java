package TEAM4;

import TEAM4.DAO.*;
import TEAM4.entities.*;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
                        if (pw.equals(password)){
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
                                            case 2 -> creazioneMezzi(scanner, mezziDAO);
                                            case 3 -> creazionePercorrenze(scanner, percorrenzaDAO, mezziDAO, trattaDAO);
                                            case 4 -> creazioneTratta(scanner, trattaDAO);
                                            default -> System.out.println("non hai inserito il numero corretto");
                                        }
                                    }
                                    case 2 -> {
                                        scelteSwitch("Modifica");
                                        int c1 = Integer.parseInt(scanner.nextLine());
                                        switch (c1) {
                                            case 1 -> modificaEmittenti(scanner, emittentiDAO);
                                            case 2 -> modificaMezzi(scanner, mezziDAO);
                                            case 3 -> modificaPercorrenze(scanner, percorrenzaDAO, mezziDAO, trattaDAO);
                                            case 4 -> modificaTratta(scanner, trattaDAO);
                                            case 0 -> System.out.println("esco...");
                                            default -> System.out.println("non hai inserito il numero corretto");
                                        }

                                    }
                                    case 3 -> {
                                        scelteSwitch("Elimina");
                                        int c1 = Integer.parseInt(scanner.nextLine());
                                        System.out.println("Inserisci id: ");
                                        String ID = scanner.nextLine();
                                        switch (c1) {
                                            case 0 -> System.out.println("esco...");
                                            case 1 -> emittentiDAO.findByIdAndDelete(ID);
                                            case 2 -> mezziDAO.findByIdAndDelete(ID);
                                            case 3 -> percorrenzaDAO.findByIdAndDelete(ID);
                                            case 4 -> trattaDAO.findByIdAndDelete(ID);
                                            default -> System.out.println("non hai inserito il numero corretto");
                                        }
                                    }
                                    case 4 -> statistiche(scanner,mezziDAO,percorrenzaDAO,emittentiDAO);

                                }
                            }while (r != 0);
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
        System.out.println("1-"+ s +" emittenti");
        System.out.println("2-"+ s +" mezzi");
        System.out.println("3-"+ s +" percorrenze");
        System.out.println("4-"+ s +" tratta");
        System.out.println("0- esci");
    }

    public static void creazioneEmittenti(Scanner scanner, EmittentiDAO emittentiDAO){
        System.out.println("Inserisci tipo Emittente: ");
        System.out.println("1- Rivenditore");
        System.out.println("2- Distributore");
        int emit = Integer.parseInt(scanner.nextLine());
        switch (emit){
            case 1 -> {
                System.out.print("Inserisci ora dell'orario di apertura (da 0 a 24): ");
                int oraA = Integer.parseInt(scanner.nextLine());
                System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                int minutiA = Integer.parseInt(scanner.nextLine());
                LocalTime orarioApertura = LocalTime.of(oraA,minutiA);
                System.out.println("Inserisci ora dell'orario di chiusura (da 0 a 24): ");
                int oraC = Integer.parseInt(scanner.nextLine());
                System.out.println("Inserisci minuti dell'orario di chiusura (da 0 a 59): ");
                int minutiC = Integer.parseInt(scanner.nextLine());
                LocalTime orarioChiusura = LocalTime.of(oraC,minutiC);;
                emittentiDAO.save(new Rivenditori(orarioApertura, orarioChiusura));
            }
            case 2 -> {
                System.out.println("premi 1 per rendere il distributore in servizio");
                System.out.println("premi 2 per rendere il distributore fuori servizio");
                int inServizio = Integer.parseInt(scanner.nextLine());
                emittentiDAO.save(new Distributori(inServizio != 1));
            }
            default -> System.out.println("Inserisci un numero corretto");
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
            default -> System.out.println("Inserisci un numero corretto");
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
                mezzo = mezziDAO.findById(idMezzo);
            }
            case 2 -> {
                creazioneMezzi(scanner, mezziDAO);
                mezzo = mezziDAO.lastCreate();
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
                tratta = trattaDAO.findById(idTratta);
            }
            case 2 -> {
                creazioneTratta(scanner, trattaDAO);
                tratta = trattaDAO.lastCreate();
            }
        }
        System.out.println("Inserisci il tempo effettivo di percorrenza");
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
    }//fine creazione tratta

    public  static void  modificaEmittenti(Scanner scanner, EmittentiDAO emittentiDAO){
        System.out.println("Quale tipo di emittente vuoi modificare: ");
        System.out.println("0- Per annullare");
        System.out.println("1- Rivenditore");
        System.out.println("2- Distributore");
        int rT = Integer.parseInt(scanner.nextLine());
        switch (rT){
            case 0 -> System.out.println("esco...");
            case 1 -> {
                System.out.println("Cosa vuoi modificare: ");
                System.out.println("1- Orario Apertura");
                System.out.println("2- Orario Chiusura");
                System.out.println("3- Entrambi gli orari");
                System.out.println("0- esci");
                int oM = Integer.parseInt(scanner.nextLine());
                System.out.println("Inserisci id: ");
                String id = scanner.nextLine();
                int n = 1;
                if (oM == 3) {
                    oM=1;
                    n=2;
                }
                for (int i=0; i<n; i++){
                    switch (oM) {
                        case 0 -> System.out.println("esco...");
                        case 1 -> {
                            System.out.print("Inserisci ora dell'orario di apertura (da 0 a 23): ");
                            int oraA = Integer.parseInt(scanner.nextLine());
                            System.out.print("Inserisci minuti dell'orario di apertura (da 0 a 59): ");
                            int minutiA = Integer.parseInt(scanner.nextLine());
                            LocalTime orarioApertura = LocalTime.of(oraA, minutiA);
                            emittentiDAO.editRivenditoreApertura(id, orarioApertura);
                        }
                        case 2 -> {
                            System.out.print("Inserisci ora dell'orario di chiusura (da 0 a 23): ");
                            int oraC = Integer.parseInt(scanner.nextLine());
                            System.out.print("Inserisci minuti dell'orario di chiusura (da 0 a 59): ");
                            int minutiC = Integer.parseInt(scanner.nextLine());
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
                System.out.println("Vuoi cambiare lo stato del distributore? (y/n)");
                String resp = scanner.nextLine();
                if (resp.equalsIgnoreCase("y")) emittentiDAO.editOutOfService(id,!emittentiDAO.isOutOfService(id));
                else if(resp.equalsIgnoreCase("n")) System.out.println("Grazie arrivederci");
                else System.out.println("Esco lo stesso... ma la prossima volta PREMI N !!!!!");
            }
        }
    }

    public static void modificaMezzi(Scanner scanner, MezziDAO mezziDAO){
        System.out.println("Quale parametro del mezzo vuoi modificare: ");
        System.out.println("1- modifica tipo mezzo");
        System.out.println("2- modifica capienza");
        System.out.println("0- esci");
        int modM = Integer.parseInt(scanner.nextLine());
        System.out.print("Inserisci id del mezzo: ");
        String id = scanner.nextLine();
        switch (modM){
            case 0 -> System.out.println("esco...");
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
    }//modifica mezzo

    public static void modificaPercorrenze(Scanner scanner, PercorrenzaDAO percorrenzaDAO, MezziDAO mezziDAO, TrattaDAO trattaDAO){
        System.out.println("Quale parametro della percorrenza vuoi modificare: ");
        System.out.println("1- modifica tipo del mezzo");
        System.out.println("2- modifica il tratto della percorrenza");
        System.out.println("3- modifica il tempo di percorrenza effettivo");
        System.out.println("4- modifica tutto");
        System.out.println("0- esci");
        int modP = Integer.parseInt(scanner.nextLine());
        System.out.print("Inserisci id Percorrenza: ");
        String idP = scanner.nextLine();
        int n = 1;
        if (modP == 4){
            modP = 1;
            n = 3;
        }
        for (int i=0; i<n; i++){
        switch (modP) {
            case 0 -> System.out.println("esco...");
            case 1 -> {
                System.out.print("Inserisci id del mezzo");
                String idM = scanner.nextLine();
                if (!idM.isEmpty()) percorrenzaDAO.modificaMezzo(idP, mezziDAO.findById(idM));
            }
            case 2 -> {
                System.out.print("Inserisci id della tratta");
                String idT = scanner.nextLine();
                if (!idT.isEmpty()) percorrenzaDAO.modificaTratta(idP, trattaDAO.findById(idT));
            }
            case 3 -> {
                System.out.print("Inserisci tempo effettivo della percorrenza in minuti (premi 0 per uscire): ");
                int temp = Integer.parseInt(scanner.nextLine());
                if (temp != 0) percorrenzaDAO.modificaTempoEffettivo(idP, temp);
            }
        }
        modP++;
        }
    }//fine mod percorrenza

    public static void modificaTratta(Scanner scanner, TrattaDAO trattaDAO){
        System.out.println("Quale parametro della tratta vuoi modificare: ");
        System.out.println("1- modifica punto di partenza");
        System.out.println("2- modifica capolinea");
        System.out.println("3- modifica il tempo di percorrenza");
        System.out.println("4- modifica tutto");
        System.out.println("0- esci");
        int modT = Integer.parseInt(scanner.nextLine());
        String idT = null;
        if (modT != 0){
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
                case 0 -> System.out.println("esco...");
                case 1 -> {
                    System.out.print("Inserisci luogo di partenza");
                    String partenza = scanner.nextLine();
                    if (!partenza.isEmpty()) trattaDAO.modificaParenza(idT, partenza);
                }
                case 2 -> {
                    System.out.print("Inserisci capolinea");
                    String capolinea = scanner.nextLine();
                    if (!capolinea.isEmpty()) trattaDAO.modificaCapolinea(idT, capolinea);
                }
                case 3 -> {
                    System.out.print("Inserisci tempo stimato della percorrenza in minuti (premi 0 per uscire): ");
                    int temp = Integer.parseInt(scanner.nextLine());
                    if (temp != 0) trattaDAO.modificaTempoPercorrenza(idT, temp);
                }
            }
            modT++;
        }
    }
    public static void statistiche (Scanner scanner, MezziDAO mezziDAO, PercorrenzaDAO percorrenzaDAO, EmittentiDAO emittentiDAO ) {
        System.out.println("Quale statistica vuoi visualizzare: ");
        System.out.println("1- statistica biglietti/abbonamenti emessi");
        System.out.println("2- statistica biglietti vidimati");
        System.out.println("3- statistica manutenzione mezzi");
        System.out.println("4- statistica tratte");
        System.out.println("0- esci");
        int select = Integer.parseInt(scanner.nextLine());
        switch (select){
            case 0 -> System.out.println("esco...");
            case 1 ->{
                System.out.println("Seleziona una delle opzioni: ");
                System.out.println("1- visualizza biglietti emessi in un periodo di tempo");
                System.out.println("2- visualizza abbonamenti emessi in un periodo di tempo");
                System.out.println("3- visualizza biglietti e abbonamenti emessi in un periodo di tempo");
                System.out.println("0- esci");
                int stat = Integer.parseInt(scanner.nextLine());
                switch (stat){
                    case 0 -> System.out.println("esco...");
                    case 1-> emessiStats(scanner,emittentiDAO,1);
                    case 2-> emessiStats(scanner,emittentiDAO,2);
                    case 3-> emessiStats(scanner,emittentiDAO,3);
                    default -> System.out.println("Input sbagliato");
                }
            }
            case 2 ->{}
            case 3 ->{}
            case 4 ->{
                System.out.println("Inserisci id");
                String id = scanner.nextLine();
                System.out.println("La media del mezzo con id " + id + " è: " + percorrenzaDAO.mediaPercorrenze(mezziDAO.findById(id)).getAsDouble());
            }

        }

    }

    public static void emessiStats(Scanner scanner, EmittentiDAO emittentiDAO, int c){
        System.out.println("Seleziona una delle opzioni: ");
        System.out.println("1- visualizza totale");
        System.out.println("2- visualizza per emittente");
        System.out.println("0- esci");
        int visE = Integer.parseInt(scanner.nextLine());
        LocalDate inizioPeriodo = localDateCreate(scanner," inizio periodo");
        LocalDate finePeriodo = localDateCreate(scanner," fine periodo");
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
