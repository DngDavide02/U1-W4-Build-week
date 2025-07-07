package TEAM4.DAO;

import TEAM4.entities.Atac;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;

public class AtacDAO {
    private EntityManager em;

    public AtacDAO(EntityManager em){
        this.em = em;
    }

    public void save(Atac atac){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(atac);
            t.commit();
            System.out.println("Titolo di viaggio salvato");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save
}
