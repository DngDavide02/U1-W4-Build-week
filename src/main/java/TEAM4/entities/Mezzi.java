package TEAM4.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Mezzi {

    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany(mappedBy = "mezzoPercorrenza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Percorrenza> percorrenzaMezzi;
    @OneToMany(mappedBy = "mezzo")
    private  List<Biglietti> biglietti;
    private int capienza;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Manutenzione> manutenzioni;

    public Mezzi(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
        if (tipoMezzo == TipoMezzo.TRAM){
            this.capienza = 150;
        }else{
            this.capienza = 70;
        }
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
