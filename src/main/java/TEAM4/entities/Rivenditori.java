package TEAM4.entities;

import javax.persistence.Entity;
import java.util.List;
@Entity
public class Rivenditori extends Emittenti{
    private int orarioApertura;
    private int orarioChiusura;

    public Rivenditori(int orarioApertura, int orarioChiusura) {
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public Rivenditori(){}

    public int getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(int orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public int getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(int orarioChiusura) {
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
