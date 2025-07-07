package TEAM4.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Emittenti {
    @Id
    @GeneratedValue
    protected UUID id;
    @OneToMany
    protected List<Atac> listaBiglietti;

    public Emittenti(List<Atac> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    public Emittenti() {}

    public UUID getId() {
        return id;
    }

    public List<Atac> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(List<Atac> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    @Override
    public String toString() {
        return "Emittenti{" +
                "id=" + id +
                ", listaBiglietti=" + listaBiglietti +
                '}';
    }
}
