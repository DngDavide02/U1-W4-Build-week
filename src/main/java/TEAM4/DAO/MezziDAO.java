package TEAM4.DAO;

import TEAM4.entities.*;
import org.hibernate.sql.Update;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MezziDAO {
    private EntityManager em;

    public MezziDAO(EntityManager em){
        this.em = em;
    }

    public void save(Mezzi mezzi){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(mezzi);
            t.commit();
            System.out.println("Mezzo salvato");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save

    public Mezzi findById( UUID id) {
        return em.find(Mezzi.class, id);
    }//end find

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi found = em.find(Mezzi.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Mezzo cancellato");
            } else System.out.println("Mezzo non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete

    public List<Biglietti> obTiketList(UUID id){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.mezzo =: mezzo", Biglietti.class);
        query.setParameter("mezzo", findById(id));
        return query.getResultList();
    }
    public List<Tratta> getTrattaMezzo(UUID idM) {
        TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t WHERE t.mezzo = :tmezzo ", Tratta.class);
        query.setParameter("mezzo", findById(idM));
        return query.getResultList();
    }

    public void salvaManutenzione(Manutenzione manutenzione){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(manutenzione);
            t.commit();
            System.out.println("Manutenzione salvata");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isInManutenzione(UUID id){
        TypedQuery<Manutenzione> query = em.createQuery("SELECT m FROM Manutenzione m WHERE m.mezzo = :mezzo AND m.dataFineM IS NULL OR m.dataFineM > :data", Manutenzione.class);
        query.setParameter("mezzo", findById(id));
        query.setParameter("data", LocalDate.now());
        return !query.getResultList().isEmpty();
    }

    public void tracciaPeriodiManutenzione(UUID id){
        TypedQuery<Manutenzione> query = em.createQuery("SELECT m FROM Manutenzione m WHERE m.mezzo = :mezzo AND m.dataFineM IS NOT NULL", Manutenzione.class);
        query.setParameter("mezzo", findById(id));
        if (isInManutenzione(id)){
            System.out.println("il mezzo con id " + id + "è in manutenzione");
        }else {
            System.out.println("il mezzo non è in manutenzione");
        }
        System.out.println("storico manutenzioni: ");
        query.getResultList().forEach(manutenzione -> System.out.println("Data inizio manutenzione: " + manutenzione.getDataInizioM() + " Data fine manutenzione " + manutenzione.getDataFineM()));
    }
}
