package TEAM4.entities;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.List;
@Entity
public class Rivenditori extends Emittenti{
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public Rivenditori(LocalTime orarioApertura, LocalTime orarioChiusura) {
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public Rivenditori(){}

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalTime orarioChiusura) {
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
