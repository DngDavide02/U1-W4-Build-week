package TEAM4.DAO;

import TEAM4.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
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

    public Tessera findById(String id) {
        return em.find(Tessera.class, UUID.fromString(id));
    }//end find

    public void findByIdAndDelete(String id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tessera found = em.find(Tessera.class, UUID.fromString(id));
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tessera cancellata");
            } else System.out.println("Tessera non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete

    public Abbonamenti checkSub(String id) {
        TypedQuery<Abbonamenti> query = em.createQuery("SELECT a FROM Abbonamenti a WHERE a.tessera = :tessera AND a.dataEmissione <= :oggi AND a.dataScadenza > :oggi", Abbonamenti.class);
        query.setParameter("tessera", findById(id));
        query.setParameter("oggi", LocalDate.now());
        return query.getSingleResult();
    }

    public void rinnovaTessera(Tessera tessera){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();

            if (tessera != null){
                tessera.setDataEmissione(LocalDate.now());
                tessera.setDataScadenza(LocalDate.now().plusYears(1));
                em.merge(tessera);
                t.commit();
                System.out.println("Tessera rinnovata con successo");
            }else {
                System.out.println("Tessera non trovata");
                t.rollback();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
