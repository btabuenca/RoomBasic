package es.upm.miw.roombasic.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GrupoRepositorio {
    private GrupoDAO mGrupoDao;
    private LiveData<List<Grupo>> mGrupos;

    /**
     * Constructor
     *
     * @param application app
     */
    public GrupoRepositorio(Application application) {
        GrupoRoomDatabase db = GrupoRoomDatabase.getDatabase(application);
        mGrupoDao = db.grupoDAO();
        mGrupos = mGrupoDao.getAll();
    }

    public LiveData<List<Grupo>> getAllGrupos() {
        return mGrupos;
    }

    public long insert(Grupo grupo) {
        return mGrupoDao.insert(grupo);
    }

    public void deleteAll() {
        mGrupoDao.deleteAll();
    }

    public void deleteGrupo(Grupo grupo)  {
        mGrupoDao.deleteGrupo(grupo);
    }
}
