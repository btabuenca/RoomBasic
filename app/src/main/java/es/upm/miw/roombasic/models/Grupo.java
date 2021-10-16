package es.upm.miw.roombasic.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Grupo.TABLA)
public class Grupo {
    static public final String TABLA = "grupos";

    @PrimaryKey(autoGenerate = true)
    protected int uid;

    protected String nombre;

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public int getUid() {
        return uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
