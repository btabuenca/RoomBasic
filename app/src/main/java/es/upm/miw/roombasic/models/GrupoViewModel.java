package es.upm.miw.roombasic.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GrupoViewModel extends AndroidViewModel {

    private GrupoRepositorio mRepository;

    private LiveData<List<Grupo>> mAllGrupos;

    /**
     * Constructor
     *
     * @param application app
     */
    public GrupoViewModel(Application application) {
        super(application);
        mRepository = new GrupoRepositorio(application);
        mAllGrupos = mRepository.getAllGrupos();
    }

    /**
     * Obtiene todos los grupos
     *
     * @return lista de grupos
     */
    public LiveData<List<Grupo>> getAllGrupos() {
        return mAllGrupos;
    }

    public void insert(Grupo grupo) {
        mRepository.insert(grupo);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteGrupo(Grupo grupo) {
        mRepository.deleteGrupo(grupo);
    }

//    public void update(Grupo grupo) {
//        mRepository.update(grupo);
//    }
}
