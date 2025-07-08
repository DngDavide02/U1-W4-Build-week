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
import java.util.List;
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

    public List<Biglietti> obTiketList(UUID id){
        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.mezzo =: mezzo", Biglietti.class);
        query.setParameter("mezzo", findById(id));
        return query.getResultList();
    }
}
