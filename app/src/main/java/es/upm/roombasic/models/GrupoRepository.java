package es.upm.roombasic.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GrupoRepository {
    private IGrupoDAO mGrupoDao;
    private LiveData<List<GrupoEntity>> mGrupos;

    /**
     * Constructor
     *
     * @param application app
     */
    public GrupoRepository(Application application) {
        GrupoRoomDatabase db = GrupoRoomDatabase.getDatabase(application);
        mGrupoDao = db.grupoDAO();
        mGrupos = mGrupoDao.getAll();
    }

    public LiveData<List<GrupoEntity>> getAllGrupos() {
        return mGrupos;
    }

    public long insert(GrupoEntity grupo) {
        return mGrupoDao.insert(grupo);
    }

    public void deleteAll() {
        mGrupoDao.deleteAll();
    }

    public void deleteGrupo(GrupoEntity grupo)  {
        mGrupoDao.deleteGrupo(grupo);
    }
}
