package es.upm.miw.roombasic.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GrupoDAO {
    @Query("SELECT * FROM " + Grupo.TABLA)
    LiveData<List<Grupo>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Grupo grupo);

    @Query("DELETE FROM " + Grupo.TABLA)
    void deleteAll();

    @Delete
    void deleteGrupo(Grupo grupo);
}
