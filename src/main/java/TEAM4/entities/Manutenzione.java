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
    @ManyToOne
    private Mezzi mezzo;

    public Manutenzione(LocalDate dataFineM, Mezzi mezzo) {
        this.dataInizioM = LocalDate.now();
        this.dataFineM = dataFineM;
        this.mezzo = mezzo;
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
