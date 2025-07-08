package TEAM4.DAO;

import TEAM4.entities.Mezzi;
import TEAM4.entities.Percorrenza;
import TEAM4.entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;

public class PercorrenzaDAO {
    private EntityManager em;

    public PercorrenzaDAO(EntityManager em){
        this.em = em;
    }

    public void save(Percorrenza percorrenza){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(percorrenza);
            t.commit();
            System.out.println("Percorrenza salvata");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save

    public Percorrenza findById(UUID id) {
        return em.find(Percorrenza.class, id);
    }//end find

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Percorrenza found = em.find(Percorrenza.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Percorrenza cancellata");
            } else System.out.println("Percorrenza non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete

    public int getPercorrenzaMezzo(Mezzi mezzo, Tratta tratta) {
        TypedQuery<Percorrenza> query = em.createQuery("SELECT p FROM Percorrenza p WHERE p.mezzoPercorrenza = :mezzoPercorrenza AND p.trattaPercorrenza = :trattaPercorrenza", Percorrenza.class);
        query.setParameter("mezzoPercorrenza", mezzo);
        query.setParameter("trattaPercorrenza", tratta);
        return query.getResultList().size();
    }

    public OptionalDouble mediaPercorrenze(Mezzi mezzo){
        TypedQuery<Percorrenza> query = em.createQuery("SELECT p FROM Percorrenza p WHERE p.mezzoPercorrenza = :mezzoPercorrenza", Percorrenza.class);
        query.setParameter("mezzoPercorrenza", mezzo);
        return query.getResultList().stream().mapToDouble(value -> value.getTempoDiPercorrenzaEffettivo()).average();
    }


}
