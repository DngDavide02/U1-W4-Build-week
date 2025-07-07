package TEAM4.DAO;

import TEAM4.entities.Atac;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;
import java.util.UUID;

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

    public Atac findById( UUID id) {
        return em.find(Atac.class, id);
    }//end find

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Atac found = em.find(Atac.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Titolo di viaggio cancellato");
            } else System.out.println("Titolo di viaggio non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete
}
