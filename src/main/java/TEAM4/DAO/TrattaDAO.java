package TEAM4.DAO;

import TEAM4.entities.Tratta;
import TEAM4.exception.notFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em){
        this.em = em;
    }

    public void save(Tratta tratta){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(tratta);
            t.commit();
            System.out.println("Tratta salvata");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save

    public Tratta findById(String id) {
        Tratta found = em.find(Tratta.class, UUID.fromString(id));
        if (found == null) throw new notFoundException();
        return found;
    }//end find

    public void findByIdAndDelete(String id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tratta found = em.find(Tratta.class, UUID.fromString(id));
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tratta cancellata");
            } else System.out.println("Tratta non trovata");
        } catch (Exception e) {
            System.out.println("non trovato");
        }
    }//end delete
    public Tratta lastCreate(){
        TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t", Tratta.class);
        return query.getResultList().get(query.getResultList().size() -1);
    }

    public void modificaParenza(String id, String partenza){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Tratta p SET p.partenza = :partenza WHERE p.id = :id");
        query.setParameter("partenza", partenza);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("il punto di partenza è stato aggiornato");
    }

    public void modificaCapolinea(String id, String capolinea){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Tratta p SET p.capolinea = :capolinea WHERE p.id = :id");
        query.setParameter("capolinea", capolinea);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("il punto di capolinea è stato aggiornato");
    }

    public void modificaTempoPercorrenza(String id, int tempoPercorrenza){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Query query = em.createQuery("UPDATE Tratta p SET p.tempoPercorrenza = :tempoPercorrenza WHERE p.id = :id");
        query.setParameter("tempoPercorrenza", tempoPercorrenza);
        query.setParameter("id", UUID.fromString(id));
        int numModificati = query.executeUpdate();
        t.commit();
        System.out.println("il punto di percorrenza è stato aggiornato");
    }

    public List<Tratta> trovaTratta(String partenza, String capolinea){
        TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t WHERE t.partenza = :partenza AND t.capolinea = :capolinea", Tratta.class);
        query.setParameter("partenza", partenza);
        query.setParameter("capolinea", capolinea);
        if(query.getResultList().isEmpty()){
            System.out.println("Tratte non trovate");
        }
        return query.getResultList();
    }
}
