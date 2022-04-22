package es.upm.miw.roombasic.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IGrupoDAO {
    @Query("SELECT * FROM " + GrupoEntity.TABLA)
    LiveData<List<GrupoEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(GrupoEntity grupo);

    @Query("DELETE FROM " + GrupoEntity.TABLA)
    void deleteAll();

    @Delete
    void deleteGrupo(GrupoEntity grupo);
}
