package TEAM4.DAO;

import TEAM4.entities.*;
import TEAM4.exception.notFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
            System.out.println("Tessera creata con id " + tessera.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//fine save

    public Tessera findById(String id) {
        Tessera found = em.find(Tessera.class, UUID.fromString(id));
        if (found == null)throw  new notFoundException();
        return found;
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

    public Abbonamenti getSub(String id) {
        TypedQuery<Abbonamenti> query = em.createQuery("SELECT a FROM Abbonamenti a WHERE a.tessera = :tessera AND a.dataEmissione <= :oggi AND a.dataScadenza > :oggi", Abbonamenti.class);
        query.setParameter("tessera", findById(id));
        query.setParameter("oggi", LocalDate.now());
        return query.getSingleResult();
    }

    public Boolean checkSub(String id) {
        TypedQuery<Abbonamenti> query = em.createQuery("SELECT a FROM Abbonamenti a WHERE a.tessera = :tessera AND a.dataEmissione <= :oggi AND a.dataScadenza > :oggi", Abbonamenti.class);
        query.setParameter("tessera", findById(id));
        query.setParameter("oggi", LocalDate.now());
        return query.getResultList().isEmpty();
    }
    public Boolean isExpire(String id){
        return findById(id).getDataScadenza().isBefore(LocalDate.now());
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

    public Tessera lastCreate(){
        TypedQuery<Tessera> query = em.createQuery("SELECT t FROM Tessera t", Tessera.class);
        return query.getResultList().get(query.getResultList().size() -1);
    }
}
