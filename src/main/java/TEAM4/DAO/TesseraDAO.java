package TEAM4.DAO;

import TEAM4.entities.Mezzi;
import TEAM4.entities.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
