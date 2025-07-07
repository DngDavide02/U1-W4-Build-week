package TEAM4.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Biglietti extends Atac{
    private LocalDate dataTimbratura;

    public Biglietti(LocalDate dataTimbratura) {
        super();
        this.dataTimbratura = dataTimbratura;
    }

    public Biglietti() {}

    public LocalDate getDataTimbratura() {
        return dataTimbratura;
    }

    public void setDataTimbratura(LocalDate dataTimbratura) {
        this.dataTimbratura = dataTimbratura;
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
