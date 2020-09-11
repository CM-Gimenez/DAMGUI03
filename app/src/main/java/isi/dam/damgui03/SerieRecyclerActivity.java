package isi.dam.damgui03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import isi.dam.damgui03.adapters.SerieRecyclerAdapter;
import isi.dam.damgui03.dao.SeriesDao;

public class SerieRecyclerActivity extends AppCompatActivity {
    SeriesDao daoSeries;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView generoElegidoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_recycler);
        daoSeries = new SeriesDao();
        String datoGenero = getIntent().getExtras().getString("GENERO");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerSerie);
        generoElegidoTv = findViewById(R.id.generoElegidoTV);

        generoElegidoTv.setText("> "+datoGenero);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SerieRecyclerAdapter(daoSeries.list(),this);
        recyclerView.setAdapter(mAdapter);
    }
}