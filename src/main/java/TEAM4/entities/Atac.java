package TEAM4.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public abstract class Atac {
    @Id
    @GeneratedValue
    protected UUID id;
    protected LocalDate dataEmissione;

    public Atac() {
        this.dataEmissione = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        return "Atac{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
