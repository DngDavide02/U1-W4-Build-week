package TEAM4.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Biglietti extends Atac{
    private LocalDate dataTimbratura;
    @ManyToOne
    @JoinColumn(name="mezzo_id")
    private Mezzi mezzo;

    public Biglietti(LocalDate dataTimbratura, Mezzi mezzo) {
        this.dataTimbratura = dataTimbratura;
        this.mezzo = mezzo;
    }

    public Biglietti() {}

    public LocalDate getDataTimbratura() {
        return dataTimbratura;
    }

    public void setDataTimbratura(LocalDate dataTimbratura) {
        this.dataTimbratura = dataTimbratura;
    }

    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "dataEmissione=" + dataEmissione +
                ", dataTimbratura=" + dataTimbratura +
                ", id=" + id +
                '}';
    }
}
