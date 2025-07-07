package TEAM4.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Abbonamenti extends Atac {
    private LocalDate dataScadenza;

    @ManyToOne
    private Tessera tessera;

    private TipoAbbonamento tipo;

    public Abbonamenti(UUID id, LocalDate dataEmissione, LocalDate dataScadenza, Tessera tessera, TipoAbbonamento tipo) {
        super(id, dataEmissione);
        this.dataScadenza = dataScadenza;
        this.tessera = tessera;
        this.tipo = tipo;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public TipoAbbonamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAbbonamento tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Abbonamenti{" +
                "id=" + getId() +
                ", dataEmissione=" + getDataEmissione() +
                ", dataScadenza=" + dataScadenza +
                ", tessera=" + tessera +
                ", tipo=" + tipo +
                '}';
    }
}
