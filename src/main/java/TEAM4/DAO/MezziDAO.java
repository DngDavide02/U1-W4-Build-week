package TEAM4.DAO;

import TEAM4.entities.Atac;
import TEAM4.entities.Mezzi;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MezziDAO {
    private EntityManager em;

    public void MezziDAO(EntityManager em){
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
}
