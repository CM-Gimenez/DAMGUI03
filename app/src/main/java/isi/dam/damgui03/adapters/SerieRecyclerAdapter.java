package isi.dam.damgui03.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import isi.dam.damgui03.R;
import isi.dam.damgui03.domain.Serie;

public class SerieRecyclerAdapter extends RecyclerView.Adapter<SerieRecyclerAdapter.SerieViewHolder> {
    private List<Serie> mDataset;
    private AppCompatActivity activity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public SerieRecyclerAdapter(List<Serie> myDataset,AppCompatActivity act) {
        mDataset = myDataset;
        activity = act;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class SerieViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView card;
        TextView titulo;
        ProgressBar pbRating;
        ImageView imgGenero;
        ImageView imgFav;
        Button btnSerie;
        RatingBar rbCalificacion;

        public SerieViewHolder(View v){
            super(v);
            card = v.findViewById(R.id.cardSerie);
            titulo = v.findViewById(R.id.rowTitulo);
            pbRating = v.findViewById(R.id.rowRating);
            imgGenero = v.findViewById(R.id.rowImgGenero);
            btnSerie = v.findViewById(R.id.rowBtnElegir);
            rbCalificacion = v.findViewById(R.id.rowCalificacion);
            imgFav = v.findViewById(R.id.rowBtnFavorito);
            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer)v.getTag();
                    Log.d(SerieAdapter.class.getName(), "ON onRatingChanged POSICION: "+pos);
                    Serie aux = mDataset.get(pos);
                    aux.setFavorita(!aux.getFavorita());
                    if(aux.getFavorita()){
                        ( (ImageButton)v).setImageResource(android.R.drawable.star_big_on);
                    } else {
                        ( (ImageButton)v).setImageResource(android.R.drawable.star_big_off);
                    }
                }
            });
            rbCalificacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    Integer pos = (Integer)ratingBar.getTag();
                    Log.d(SerieAdapter.class.getName(), "ON onRatingChanged POSICION: "+pos);
                    Log.d(SerieAdapter.class.getName(), "ON rating rating nuevo: "+mDataset.get(pos));
                    mDataset.get(pos).setCalificacion(rating);
                }
            });
            btnSerie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer)v.getTag();
                    Intent intentResultado = new Intent();
                    intentResultado.putExtra("indiceElegido", pos);
                    intentResultado.putExtra("serie",mDataset.get(pos).toString());
                    activity.setResult(Activity.RESULT_OK,intentResultado);
                    activity.finish();
                }
            });
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public SerieRecyclerAdapter.SerieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_serie_recycler, parent, false);
            //...
        SerieViewHolder  vh = new SerieViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SerieViewHolder serieHolder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // configurar cada view con que fila del arreglo de datos coincide.
        serieHolder.rbCalificacion.setTag(position);
        serieHolder.imgFav.setTag(position);
        serieHolder.btnSerie.setTag(position);
        Serie serie = mDataset.get(position);
        // ImageView rowGenero = vistaAux.findViewById(R.id.rowImgGenero);
        switch (serie.getGenero()){
            case DRAMA:
                serieHolder.imgGenero.setImageResource(R.drawable.accion);
                break;
            case POLICIAL:
                serieHolder.imgGenero.setImageResource(R.drawable.policial);
                break;
            case SUPERHEROES:
                serieHolder.imgGenero.setImageResource(R.drawable.superheroes);
                break;
            case COMEDIA:
                serieHolder.imgGenero.setImageResource(R.drawable.comedia);
                break;
        }

        if(serie.getFavorita()){
            serieHolder.imgFav.setImageResource(android.R.drawable.star_big_on);
        } else {
            serieHolder.imgFav.setImageResource(android.R.drawable.star_big_off);
        }

        // TextView titulo = vistaAux.findViewById(R.id.rowTitulo);
        serieHolder.titulo.setText(serie.getTitulo()+ "("+serie.getRating()+")");
        // ProgressBar pb = vistaAux.findViewById(R.id.rowRating);
        serieHolder.pbRating.setProgress(serie.getRating());


        float auxCalificacion = serie.getCalificacion()==null? 0.0F : serie.getCalificacion().floatValue();
        Log.d(SerieAdapter.class.getName(), serie.getTitulo()+" ON serie.getCalificacion!!!!(): "+serie.getCalificacion()+ " - "+auxCalificacion);

        serieHolder.rbCalificacion.setRating(auxCalificacion);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
