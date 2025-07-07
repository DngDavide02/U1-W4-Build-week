package TEAM4.DAO;

import TEAM4.entities.Atac;
import TEAM4.entities.Tessera;
import TEAM4.entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public Tratta findById(UUID id) {
        return em.find(Tratta.class, id);
    }//end find

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tratta found = em.find(Tratta.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tratta cancellata");
            } else System.out.println("Tratta non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete
}
