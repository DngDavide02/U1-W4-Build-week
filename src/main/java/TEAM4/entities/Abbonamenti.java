package TEAM4.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Abbonamenti extends Atac {
    private LocalDate dataScadenza;

    @ManyToOne
    private Tessera tessera;

    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipo;

    public  Abbonamenti(){
    }

    public Abbonamenti(Tessera tessera, TipoAbbonamento tipo) {
        if (tipo == TipoAbbonamento.MENSILE){
            this.dataScadenza = super.dataEmissione.plusMonths(1);
        } else {
            this.dataScadenza = super.dataEmissione.plusDays(7);
        }
        this.tessera = tessera;
        this.tipo = tipo;
    }
    public Abbonamenti(Tessera tessera, TipoAbbonamento tipo, LocalDate dataEmissione) {
       super(dataEmissione);
        if (tipo == TipoAbbonamento.MENSILE){
            this.dataScadenza = dataEmissione.plusMonths(1);
        } else {
            this.dataScadenza = dataEmissione.plusDays(7);
        }
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
