package TEAM4.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Mezzi {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToMany(mappedBy = "mezzi")
    private List<Tratta> tratta;
    private Integer capienza;
    private TipoMezzo tipoMezzo;

    public Mezzi(Integer capienza, TipoMezzo tipoMezzo) {
        this.capienza = capienza;
        this.tipoMezzo = tipoMezzo;
    }

    public Mezzi(){}

    public UUID getId() {
        return id;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", tipoMezzo=" + tipoMezzo +
                '}';
    }
}
