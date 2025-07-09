package TEAM4.entities;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Distributori extends Emittenti{
    private Boolean fuoriServizio;

    public Distributori(Boolean fuoriServizio) {
        this.fuoriServizio = fuoriServizio;
    }

    public Distributori(){}

    public Boolean getFuoriServizio() {
        return fuoriServizio;
    }

    public void setFuoriServizio(Boolean fuoriServizio) {
        this.fuoriServizio = fuoriServizio;
    }

    @Override
    public String toString() {
        return "Distributori{" +
                "fuoriServizio=" + fuoriServizio +
                ", id=" + id +
                ", listaBiglietti=" + listaBiglietti +
                '}';
    }
}
