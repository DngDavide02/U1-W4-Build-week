package TEAM4.DAO;

import TEAM4.entities.*;
import com.sun.jdi.request.StepRequest;
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

    public Mezzi findById( String id) {
        return em.find(Mezzi.class, UUID.fromString(id));
    }//end find

    public void findByIdAndDelete(String id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi found = em.find(Mezzi.class, UUID.fromString(id));
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Mezzo cancellato");
            } else System.out.println("Mezzo non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete
    public List<Biglietti> obTiketList(){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.mezzo IS NOT NULL AND b.dataTimbratura IS NOT NULL", Biglietti.class);
        return query.getResultList();
    }
    public List<Biglietti> obTiketList(String id){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.mezzo = :mezzo", Biglietti.class);
        query.setParameter("mezzo", findById(id));
        return query.getResultList();
    }
    public List<Biglietti> obTiketListDate(LocalDate inizioPeriodo,LocalDate finePeriodo ){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.dataEmissione >= :inizioPeriodo AND b.dataEmissione <= :finePeriodo", Biglietti.class);
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
        return query.getResultList();
    }
    public List<Biglietti> obTiketListDate(String id, LocalDate inizioPeriodo,LocalDate finePeriodo){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.mezzo = :id AND b.dataEmissione >= :inizioPeriodo AND b.dataEmissione <= :finePeriodo", Biglietti.class);
        query.setParameter("id", findById(id));
        query.setParameter("inizioPeriodo", inizioPeriodo.isBefore(finePeriodo)? inizioPeriodo : finePeriodo);
        query.setParameter("finePeriodo", finePeriodo.isAfter(inizioPeriodo)? finePeriodo : inizioPeriodo);
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

    public boolean isInManutenzione(String id){
        TypedQuery<Manutenzione> query = em.createQuery("SELECT m FROM Manutenzione m WHERE m.mezzo = :mezzo AND m.dataFineM IS NULL OR m.dataFineM > :data", Manutenzione.class);
        query.setParameter("mezzo", findById(id));
        query.setParameter("data", LocalDate.now());
        return !query.getResultList().isEmpty();
    }

    public void tracciaPeriodiManutenzione(String id){
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

    public Mezzi lastCreate(){
        TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m", Mezzi.class);
        return query.getResultList().get(query.getResultList().size() -1);
    }

    public void editMezzo(String id, int newCapienza){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Mezzi m SET m.capienza = :newCapienza WHERE m.id = :id");
        query.setParameter("newCapienza", newCapienza);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("La capienza del mezzo è stata aggiornata");
    }

    public void editMezzo(String id, TipoMezzo tipoMezzo){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Mezzi m SET m.tipoMezzo = :tipoMezzo WHERE m.id = :id");
        query.setParameter("tipoMezzo", tipoMezzo);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("Il tipo del mezzo è stato aggiornato");
    }
}
