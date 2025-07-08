package TEAM4.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Tratta {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany(mappedBy = "trattaPercorrenza")
    private List<Percorrenza> percorrenzaTratta;
    private String partenza;
    private String capolinea;
    private int tempoPercorrenza;

    public Tratta(String partenza, String capolinea, int tempoPercorrenza) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public Tratta(){}

    public List<Percorrenza> getPercorrenza() {
        return percorrenzaTratta;
    }

    public void setPercorrenza(List<Percorrenza> percorrenzaTratta) {
        this.percorrenzaTratta = Tratta.this.percorrenzaTratta;
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

    public int getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(int tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "percorrenzaTratta=" + percorrenzaTratta +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}
