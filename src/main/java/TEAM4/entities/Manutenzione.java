package TEAM4.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Manutenzione {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate dataInizioM;
    private LocalDate dataFineM;
    private String causale;
    @ManyToOne
    private Mezzi mezzo;

    public Manutenzione( Mezzi mezzo, String causale) {
        this.dataInizioM = LocalDate.now();
        this.mezzo = mezzo;
        this.causale=causale;
    }

    public Manutenzione( Mezzi mezzo, LocalDate dataInizioM, LocalDate dataFineM , String causale) {
        this.dataInizioM = dataInizioM;
        this.dataFineM = dataFineM;
        this.mezzo = mezzo;
        this.causale = causale;
    }

    public Manutenzione(){}

    public UUID getId() {
        return id;
    }

    public LocalDate getDataInizioM() {
        return dataInizioM;
    }

    public void setDataInizioM(LocalDate dataInizioM) {
        this.dataInizioM = dataInizioM;
    }

    public LocalDate getDataFineM() {
        return dataFineM;
    }

    public void setDataFineM(LocalDate dataFineM) {
        this.dataFineM = dataFineM;
    }

    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }

    public String getCausale() {
        return causale;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", dataInizioM=" + dataInizioM +
                ", dataFineM=" + dataFineM +
                ", mezzo=" + mezzo +
                '}';
    }
}
