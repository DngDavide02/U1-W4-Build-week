package TEAM4.DAO;

import TEAM4.entities.Abbonamenti;
import TEAM4.entities.Atac;
import TEAM4.entities.Biglietti;
import TEAM4.entities.Mezzi;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
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

    public Atac findById( String id) {
        Atac found = em.find(Atac.class, UUID.fromString(id));
        if (found == null) throw new RuntimeException();
        return found;
    }//end find

    public Biglietti findBigliettoById( String id) {
        Biglietti found = em.find(Biglietti.class, UUID.fromString(id));
        if (found == null) throw new RuntimeException();
        return found;
    }//end find
    public Abbonamenti findAbbonamentoById(String id) {
        Abbonamenti found = em.find(Abbonamenti.class, UUID.fromString(id));
        if (found == null) throw new RuntimeException();
        return found;
    }//end find

    public void findByIdAndDelete(String id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Atac found = em.find(Atac.class, UUID.fromString(id));
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Titolo di viaggio cancellato");
            } else System.out.println("Titolo di viaggio non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete

    public boolean isObliterated (String id){
        return findBigliettoById(id).getDataTimbratura() != null;
    }

    public void checkBiglietti (String idB, Mezzi mezzo) {
        if (mezzo == null) System.out.println("Mezzo inserito non valido");
        else {
            if (!isObliterated(idB)) {
                EntityTransaction t = em.getTransaction();
                t.begin();
                Query query = em.createQuery("UPDATE Biglietti b SET b.dataTimbratura = :data, b.mezzi = :mezzo WHERE b.id = :id");
                query.setParameter("data", LocalDate.now());
                query.setParameter("mezzi", mezzo);
                query.setParameter("id", idB);
                int numModificati = query.executeUpdate();
                t.commit();
                System.out.println("Il biglietto è stato timbrato");
            } else {
                System.out.println("Il biglietto è già stato timbrato " + findBigliettoById(idB).getDataTimbratura());
            }
        }
    }
}
