package TEAM4.DAO;

import TEAM4.entities.Atac;
import TEAM4.entities.Mezzi;
import TEAM4.entities.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class TesseraDAO {
    private EntityManager em;

    public TesseraDAO(EntityManager em){
        this.em = em;
    }

    public void save(Tessera tessera){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(tessera);
            t.commit();
            System.out.println("Tessera salvata");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save

    public Tessera findById(UUID id) {
        return em.find(Tessera.class, id);
    }//end find

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tessera found = em.find(Tessera.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tessera cancellata");
            } else System.out.println("Tessera non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete
}
