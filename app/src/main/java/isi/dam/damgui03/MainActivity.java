package isi.dam.damgui03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.util.List;

import isi.dam.damgui03.domain.Genero;
import isi.dam.damgui03.domain.Serie;

public class MainActivity extends AppCompatActivity {

    Button botonElegirLv, botonElegirRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // siempre utiliza la referencia a r.layout.activity_main
        setContentView(R.layout.activity_main);


        botonElegirLv = findViewById(R.id.btnLista1);
        botonElegirRecycler = findViewById(R.id.btnELegirRecycler);

        botonElegirLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListaSeries.class);
                startActivityForResult(i,999);
            }
        });

        botonElegirRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SerieRecyclerActivity.class);
                i.putExtra("GENERO", Genero.POLICIAL.toString());
                startActivityForResult(i,987);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(this.getClass().getName(), "onActivityResult: "+requestCode + "  -"+resultCode);
        if(requestCode==987){
            if(resultCode== RESULT_OK){
                String serieTitulo = data.getExtras().getString("serie");
                Integer indice = data.getExtras().getInt("indiceElegido");
                Log.d(this.getClass().getName(), "RESULTADO PELICULA: "+ serieTitulo);
                Log.d(this.getClass().getName(), "RESULTADO PELICULA: "+ indice);
            }
        }
    }


}