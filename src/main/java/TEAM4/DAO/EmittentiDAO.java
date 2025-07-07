package TEAM4.DAO;

import TEAM4.entities.Atac;
import TEAM4.entities.Emittenti;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EmittentiDAO {
    private EntityManager em;

    public void EmittentiDAO(EntityManager em){
        this.em = em;
    }

        public void save(Emittenti emittenti){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(emittenti);
            t.commit();
            System.out.println("Emittente salvato");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save
}
