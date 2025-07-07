package TEAM4.entities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Rivenditori extends Emittenti{
    private LocalDate orarioApertura;
    private LocalDate orarioChiusura;

    public Rivenditori(List<Atac> listaBiglietti, LocalDate orarioApertura, LocalDate orarioChiusura) {
        super(listaBiglietti);
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public Rivenditori(){}

    public LocalDate getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalDate orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalDate getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalDate orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }

    @Override
    public String toString() {
        return "Rivenditori{" +
                "orarioApertura=" + orarioApertura +
                ", orarioChiusura=" + orarioChiusura +
                ", id=" + id +
                ", listaBiglietti=" + listaBiglietti +
                '}';
    }
}
