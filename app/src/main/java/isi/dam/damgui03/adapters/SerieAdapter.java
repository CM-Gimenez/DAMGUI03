package isi.dam.damgui03.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import isi.dam.damgui03.R;
import isi.dam.damgui03.domain.Serie;

public class SerieAdapter extends ArrayAdapter<Serie> {

    public SerieAdapter(Context ctx, List<Serie> datos){
        ///super(ctx, R.layout.fila_serie,R.id.rowTitulo,datos);
        super(ctx,0,datos);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(SerieAdapter.class.getName(), "Invoca metodo getView: "+ position);
        final Serie serie = super.getItem(position);
        Log.d(SerieAdapter.class.getName(), "Obtuvo serie: "+serie);
        Log.d(SerieAdapter.class.getName(), "Convert VIEW: "+(convertView==null? null: convertView.hashCode()));
        View vistaAux;

        LayoutInflater inflador = LayoutInflater.from(this.getContext());
        // EL IF QUE RECICLA
        // si la convertView que me pasa android  es null la tengo que crear
        // si ya fue creada la reuso, cambiandole los datos (cambia el titulo, la imagen)
         if(convertView==null) {
            // vistaAux = super.getView(position, convertView, parent); el Inflator lo usa el padre de manera impliciata
            vistaAux = inflador.inflate(R.layout.fila_serie,parent,false); // METODOD COSTOSO
        }
        else {
            vistaAux = convertView;
        }
        SerieHolder serieHolder = (SerieHolder) vistaAux.getTag();
        if(serieHolder == null){
            serieHolder = new SerieHolder(vistaAux);
            vistaAux.setTag(serieHolder);

            serieHolder.rbCalificacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    Log.d(SerieAdapter.class.getName(), "ON onRatingChanged POSICION: "+position);
                    Log.d(SerieAdapter.class.getName(), "ON rating rating nuevo: "+rating);
                    Integer pos = (Integer)ratingBar.getTag();
                    Log.d(SerieAdapter.class.getName(), "ON onRatingChanged POSICION: "+pos);
                    Log.d(SerieAdapter.class.getName(), "ON rating rating nuevo: "+getItem(pos));
                    getItem(pos).setCalificacion(rating);
                }
            });
            serieHolder.imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer)v.getTag();
                    Log.d(SerieAdapter.class.getName(), "ON onRatingChanged POSICION: "+pos);
                    Serie aux = getItem(pos);
                    aux.setFavorita(!aux.getFavorita());
                    if(aux.getFavorita()){
                        ( (ImageButton)v).setImageResource(android.R.drawable.star_big_on);
                    } else {
                        ( (ImageButton)v).setImageResource(android.R.drawable.star_big_off);
                    }
                }
            });
            serieHolder.btnSerie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(SerieAdapter.class.getName(), "ON CLICK: "+position);
                }
            });
        }
        // configurar cada view con que fila del arreglo de datos coincide.
        serieHolder.rbCalificacion.setTag(position);
        serieHolder.imgFav.setTag(position);
        serieHolder.btnSerie.setTag(position);

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

        return vistaAux;
    }

    public class SerieHolder {
        TextView titulo;
        ProgressBar pbRating;
        ImageView imgGenero;
        ImageView imgFav;
        Button btnSerie;
        RatingBar rbCalificacion;

        public  SerieHolder(View v){
            Log.d(SerieAdapter.class.getName(), "Invoca Holder: "+v.hashCode());
            titulo = v.findViewById(R.id.rowTitulo);
            pbRating = v.findViewById(R.id.rowRating);
            imgGenero = v.findViewById(R.id.rowImgGenero);
            btnSerie = v.findViewById(R.id.rowBtnElegir);
            rbCalificacion = v.findViewById(R.id.rowCalificacion);
            imgFav = v.findViewById(R.id.rowBtnFavorito);
        }
    }
}
