package isi.dam.damgui03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import isi.dam.damgui03.adapters.SerieAdapter;
import isi.dam.damgui03.dao.SeriesDao;
import isi.dam.damgui03.domain.Serie;

public class ListaSeries extends AppCompatActivity {

    SeriesDao daoSeries;
    ListView lvSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_series);
        Log.d(this.getClass().getName(),"LISTA SERIES SE VUELVE A CREAR !!  ");
        lvSeries = findViewById(R.id.listaSeries);
        daoSeries = new SeriesDao();
        // ArrayAdapter<Serie> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,daoSeries.list());
        ArrayAdapter<Serie> adapter = new SerieAdapter(this,daoSeries.list());
        lvSeries.setAdapter(adapter);
    }
}