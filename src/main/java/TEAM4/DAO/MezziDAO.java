package TEAM4.DAO;

import TEAM4.entities.Atac;
import TEAM4.entities.Biglietti;
import TEAM4.entities.Mezzi;
import org.hibernate.sql.Update;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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

    public void checkBiglietti (Biglietti biglietto){
        try {
            if (biglietto.getDataTimbratura() == null){
                TypedQuery<Biglietti> query = em.createQuery("UPDATE biglietti SET datatimbratura = :data WHERE id = : id", Biglietti.class);
                query.setParameter("date", LocalDate.now());
                query.setParameter("id", biglietto.getId());
                System.out.println("Il biglietto è stato timbrato");
            }else {
                throw new RuntimeException();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
