package es.upm.miw.roombasic.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GrupoViewModel extends AndroidViewModel {

    private GrupoRepository mRepository;

    private LiveData<List<GrupoEntity>> mAllGrupos;

    /**
     * Constructor
     *
     * @param application app
     */
    public GrupoViewModel(Application application) {
        super(application);
        mRepository = new GrupoRepository(application);
        mAllGrupos = mRepository.getAllGrupos();
    }

    /**
     * Obtiene todos los grupos
     *
     * @return lista de grupos
     */
    public LiveData<List<GrupoEntity>> getAllGrupos() {
        return mAllGrupos;
    }

    public void insert(GrupoEntity grupo) {
        mRepository.insert(grupo);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteGrupo(GrupoEntity grupo) {
        mRepository.deleteGrupo(grupo);
    }


}
