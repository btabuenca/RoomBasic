package es.upm.roombasic.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.upm.roombasic.R;
import es.upm.roombasic.models.GrupoEntity;

public class GrupoListAdapter extends RecyclerView.Adapter<GrupoListAdapter.GrupoViewHolder> {

    class GrupoViewHolder extends RecyclerView.ViewHolder {
        private final TextView grupoItemView;

        private GrupoViewHolder(View itemView) {
            super(itemView);
            grupoItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<GrupoEntity> mGrupos;

    /**
     * Constructor
     *
     * @param context context
     */
    public GrupoListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GrupoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GrupoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GrupoViewHolder holder, int position) {
        if (mGrupos != null) {
            GrupoEntity current = mGrupos.get(position);
            holder.grupoItemView.setText(current.getNombre());
        } else {
            // Covers the case of data not being ready yet.
            holder.grupoItemView.setText("No Group");
        }
    }

    public void setGrupos(List<GrupoEntity> grupos){
        mGrupos = grupos;
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (mGrupos == null)
                ? 0
                : mGrupos.size();
    }

    public GrupoEntity getGrupoAtPosition (int position) {
        return mGrupos.get(position);
    }
}
