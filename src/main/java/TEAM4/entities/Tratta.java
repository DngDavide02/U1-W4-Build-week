package TEAM4.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Tratta {
    @Id
    @ManyToMany
    @JoinTable(
            name = "tratta",
            joinColumns = @JoinColumn(name = "trattaId"),
            inverseJoinColumns = @JoinColumn(name = "mezziId")
    )
    private List<Mezzi> mezzi;
    private String partenza;
    private String capolinea;
    private LocalDate tempoPercorrenza;

    public Tratta(List<Mezzi> mezzi, String partenza, String capolinea, LocalDate tempoPercorrenza) {
        this.mezzi = mezzi;
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public Tratta(){}

    public List<Mezzi> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzi> mezzi) {
        this.mezzi = mezzi;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public LocalDate getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(LocalDate tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "mezzi=" + mezzi +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}
