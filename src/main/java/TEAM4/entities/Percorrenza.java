package TEAM4.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Percorrenza {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Mezzi mezzoPercorrenza;

    @ManyToOne
    private Tratta trattaPercorrenza;

    private int tempoDiPercorrenzaEffettivo;

    public Percorrenza(){}

    public Percorrenza(Mezzi mezzo, Tratta tratta, int tempoDiPercorrenzaEffettivo) {
        this.mezzoPercorrenza = mezzo;
        this.trattaPercorrenza = tratta;
        this.tempoDiPercorrenzaEffettivo = tempoDiPercorrenzaEffettivo;
    }

    public UUID getId() {
        return id;
    }

    public Mezzi getMezzo() {
        return mezzoPercorrenza;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzoPercorrenza = mezzo;
    }

    public Tratta getTratta() {
        return trattaPercorrenza;
    }

    public void setTratta(Tratta tratta) {
        this.trattaPercorrenza = tratta;
    }

    public int getTempoDiPercorrenzaEffettivo() {
        return tempoDiPercorrenzaEffettivo;
    }

    public void setTempoDiPercorrenzaEffettivo(int tempoDiPercorrenzaEffettivo) {
        this.tempoDiPercorrenzaEffettivo = tempoDiPercorrenzaEffettivo;
    }
}
