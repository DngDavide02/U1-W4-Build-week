package TEAM4.DAO;

import TEAM4.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.UUID;

public class EmittentiDAO {
    private EntityManager em;

    public EmittentiDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Emittenti emittenti) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(emittenti);
            t.commit();
            System.out.println("Emittente salvato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//fine save

    public Emittenti findById(UUID id) {
        return em.find(Emittenti.class, id);
    }//end find

    public void findByIdAndDelete(UUID id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Emittenti found = em.find(Emittenti.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Emittente cancellato");
            } else System.out.println("Emittente non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//end delete

    public void emettiBiglietto(Mezzi mezzo) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();

            Biglietti biglietto = new Biglietti();
            biglietto.setDataEmissione(LocalDate.now());
            biglietto.setDataTimbratura(LocalDate.now());
            biglietto.setMezzo(mezzo);
            em.persist(biglietto);
            t.commit();
            System.out.println("Biglietto emesso con successo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//fine emetti biglietto

    public void emettiAbbonamento(Tessera tessera, TipoAbbonamento tipo){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Abbonamenti abbonamento = new Abbonamenti(tessera, tipo, LocalDate.now());
            em.persist(abbonamento);
            t.commit();
            System.out.println("Abbonamento emesso con successo");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int bigliettiEmessiInTolale (LocalDate data){
        TypedQuery<Atac> queryBiglietti= em.createQuery("SELECT a FROM Atac a WHERE a.dataEmissione <= :data", Atac.class);
        queryBiglietti.setParameter("data", data);
        return queryBiglietti.getResultList().size();
    }

    public int bigliettiEmessi (LocalDate data, UUID id){
        TypedQuery<Atac> queryBiglietti= em.createQuery("SELECT a FROM Atac a WHERE a.dataEmissione <= :data AND a.emittenti = :id ", Atac.class);
        queryBiglietti.setParameter("data", data);
        queryBiglietti.setParameter("id", findById(id));
        return queryBiglietti.getResultList().size();
    }


}
