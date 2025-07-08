package TEAM4.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Atac {
    @Id
    @GeneratedValue
    protected UUID id;
    @ManyToOne
    @JoinColumn(name = "emittenti_id")
    private Emittenti emittenti;

    protected LocalDate dataEmissione;

    public Atac() {
        this.dataEmissione = LocalDate.now();
    }
    public Atac(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
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

    public Emittenti getEmittenti() {
        return emittenti;
    }

    public void setEmittenti(Emittenti emittenti) {
        this.emittenti = emittenti;
    }

    @Override
    public String toString() {
        return "Atac{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
