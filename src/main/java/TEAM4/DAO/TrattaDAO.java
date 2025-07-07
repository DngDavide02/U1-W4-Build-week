package TEAM4.DAO;

import TEAM4.entities.Tessera;
import TEAM4.entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
