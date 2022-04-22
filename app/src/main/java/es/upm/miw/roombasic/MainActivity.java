package es.upm.miw.roombasic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.upm.miw.roombasic.models.GrupoEntity;
import es.upm.miw.roombasic.models.GrupoViewModel;
import es.upm.miw.roombasic.views.GrupoListAdapter;

public class MainActivity extends AppCompatActivity {

    static String LOG_TAG = "MiW";
    public static final int NEW_GROUP_ACTIVITY_REQUEST_CODE = 2022;

    GrupoViewModel grupoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add the RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final GrupoListAdapter adapter = new GrupoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Use ViewModelProviders to associate the ViewModel with the UI controller
        // Get a new or existing ViewModel from the ViewModelProvider.
        grupoViewModel = ViewModelProviders.of(this).get(GrupoViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedGrupos.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        grupoViewModel.getAllGrupos().observe(this, new Observer<List<GrupoEntity>>() {
            @Override
            public void onChanged(@Nullable final List<GrupoEntity> grupos) {
                // Update the cached copy of the grupos in the adapter.
                adapter.setGrupos(grupos);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewGroupActivity.class);
                startActivityForResult(intent, NEW_GROUP_ACTIVITY_REQUEST_CODE);
            }
        });

        // Add the functionality to swipe items in the recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        GrupoEntity grupo = adapter.getGrupoAtPosition(position);
                        Snackbar.make(
                                recyclerView,
                                "Borrando " + grupo.getNombre(),
                                Snackbar.LENGTH_SHORT
                                ).show();

                        // Elimina el grupo
                        grupoViewModel.deleteGrupo(grupo);
                    }
                });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_delete_all:
                Toast.makeText(this, getString(R.string.menu_delete_all), Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "opción=" + getString(R.string.menu_delete_all));
                grupoViewModel.deleteAll();
                break;
            default:
                Log.i(LOG_TAG, "opción desconocida");
        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_GROUP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            GrupoEntity grupo = new GrupoEntity(data.getStringExtra(NewGroupActivity.EXTRA_REPLY));
            grupoViewModel.insert(grupo);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}