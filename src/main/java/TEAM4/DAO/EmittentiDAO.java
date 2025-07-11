package TEAM4.DAO;

import TEAM4.entities.*;
import TEAM4.exception.notFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EmittentiDAO {
    private EntityManager em;

    public EmittentiDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Emittenti emittenti) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(emittenti);
            t.commit();
            System.out.println("Emittente salvato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//fine save

    public Emittenti findById(String id) {
        Emittenti found = em.find(Emittenti.class, UUID.fromString(id));
        if (found == null) throw new notFoundException();
        return found;
    }//end find

    public Distributori findDistributoreById(String id) {
        Distributori found = em.find(Distributori.class, UUID.fromString(id));
        if (found == null) throw new notFoundException();
        return found;
    }//end find

    public Rivenditori findRivenditoreById(String id) {
        Rivenditori found = em.find(Rivenditori.class, UUID.fromString(id));
        if (found == null) throw new notFoundException();
        return found;
    }//end find

    public void findByIdAndDelete(String id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Emittenti found = em.find(Emittenti.class, UUID.fromString(id));
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Emittente cancellato");
            } else System.out.println("Emittente non trovato");
        } catch (Exception e) {
            System.out.println("input sbagliato, riprova");
        }
    }//end delete


    public int atacEmessiInTolale (LocalDate inizioPeriodo, LocalDate finePeriodo){
        TypedQuery<Atac> query= em.createQuery("SELECT a FROM Atac a WHERE a.dataEmissione >= :inizioPeriodo AND a.dataEmissione <= :finePeriodo", Atac.class);
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        return query.getResultList().size();
    }

    public int bigliettiEmessiInTolale (LocalDate inizioPeriodo, LocalDate finePeriodo){
        TypedQuery<Biglietti> queryBiglietti= em.createQuery("SELECT b FROM Biglietti b WHERE b.dataEmissione >= :inizioPeriodo AND b.dataEmissione <= :finePeriodo", Biglietti.class);
        queryBiglietti.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        queryBiglietti.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        return queryBiglietti.getResultList().size();
    }

    public int abbonamentiEmessiInTolale (LocalDate inizioPeriodo, LocalDate finePeriodo){
        TypedQuery<Abbonamenti> query= em.createQuery("SELECT a FROM Abbonamenti a WHERE a.dataEmissione >= :inizioPeriodo AND a.dataEmissione <= :finePeriodo", Abbonamenti.class);
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        return query.getResultList().size();
    }

    public int atacEmessi (LocalDate inizioPeriodo, LocalDate finePeriodo, String id){
        TypedQuery<Atac> query= em.createQuery("SELECT a FROM Atac a WHERE a.dataEmissione >= :inizioPeriodo AND a.dataEmissione <= :finePeriodo AND a.emittenti = :id ", Atac.class);
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        query.setParameter("id", findById(id));
        return query.getResultList().size();
    }

    public int bigliettiEmessi (LocalDate inizioPeriodo, LocalDate finePeriodo, String id){
        TypedQuery<Biglietti> queryBiglietti= em.createQuery("SELECT b FROM Biglietti b WHERE b.dataEmissione >= :inizioPeriodo AND b.dataEmissione <= :finePeriodo AND b.emittenti = :id ", Biglietti.class);
        queryBiglietti.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        queryBiglietti.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        queryBiglietti.setParameter("id", findById(id));
        return queryBiglietti.getResultList().size();
    }

    public int abbonamentiEmessi (LocalDate inizioPeriodo, LocalDate finePeriodo,  String id){
        TypedQuery<Abbonamenti> query= em.createQuery("SELECT a FROM Abbonamenti a WHERE a.dataEmissione >= :inizioPeriodo AND a.dataEmissione <= :finePeriodo AND a.emittenti = :id", Abbonamenti.class);
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        query.setParameter("id", findById(id));
        return query.getResultList().size();
    }

    public void editRivenditoreApertura(String id, LocalTime orarioApertura ){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Rivenditori r SET r.orarioApertura = :orarioApertura WHERE r.id = :id");
        query.setParameter("orarioApertura", orarioApertura);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("L'orario di apertura è stato aggiornato");
    }
    public void editRivenditoreChiusura(String id, LocalTime orarioChiusura ){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Rivenditori r SET r.orarioChiusura = :orarioChiusura WHERE r.id = :id");
        query.setParameter("orarioChiusura", orarioChiusura);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("L'orario di chiusura è stato aggiornato");
    }

    public boolean isOutOfService(String id){
    return findDistributoreById(id).getFuoriServizio();
    }

    public void editOutOfService(String id, boolean fuoriServizio ){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Distributori d SET d.fuoriServizio = :fuoriServizio WHERE d.id = :id");
        query.setParameter("fuoriServizio", fuoriServizio);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("Lo stato del distributore è stato aggiornato");
    }
}
