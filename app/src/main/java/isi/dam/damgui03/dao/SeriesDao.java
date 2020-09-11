package isi.dam.damgui03.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import isi.dam.damgui03.adapters.SerieAdapter;
import isi.dam.damgui03.domain.Genero;
import isi.dam.damgui03.domain.Serie;

public class SeriesDao {

    private static final List<Serie> series = new ArrayList<>();

    private void iniciar(){
        Log.d(this.getClass().getName(),"INICIA CARGA "+series.isEmpty());
        if(series.isEmpty()){
            Log.d(this.getClass().getName(),"CARGA LISTA!!  "+series.isEmpty());

            series.add(new Serie(1, "Ozark", Genero.DRAMA, 92));
            series.add(new Serie(2, "Billions", Genero.DRAMA, 82,true));
            series.add(new Serie(3, "Game of Throne", Genero.DRAMA, 95));
            series.add(new Serie(4, "Sopranos", Genero.POLICIAL, 78,true));
            series.add(new Serie(5, "Better Call Saul", Genero.POLICIAL, 79));
            series.add(new Serie(6, "Daredevil", Genero.SUPERHEROES, 76));
            series.add(new Serie(7, "Jesica Jones", Genero.SUPERHEROES, 78,true));
            series.add(new Serie(8, "La casa de Papel", Genero.POLICIAL, 81));
            series.add(new Serie(9, "The Good Place", Genero.COMEDIA, 70));
            series.add(new Serie(10, "Glee", Genero.COMEDIA, 74,true));
            series.add(new Serie(11, "Dark", Genero.POLICIAL, 79));
            series.add(new Serie(12, "The Big Bang Theory", Genero.COMEDIA, 65));
            series.add(new Serie(13, "Vikings", Genero.POLICIAL, 67,true));
            series.add(new Serie(14, "Black Mirror", Genero.SUPERHEROES, 66));
            series.add(new Serie(16, "The Sinner", Genero.POLICIAL, 87));
            series.add(new Serie(17, "Arrow", Genero.SUPERHEROES, 75));
            series.add(new Serie(18, "Sons of anarchy", Genero.POLICIAL, 65));
            series.add(new Serie(19, "Peaky Blinders", Genero.DRAMA, 86));
            series.add(new Serie(20, "Flash", Genero.SUPERHEROES, 67));
            series.add(new Serie(21, "Homeland", Genero.POLICIAL, 91));
        }
    }

    public List<Serie> list(){
        iniciar();
        return this.series;
    }

    public void add(Integer id,String titulo,Genero g,Integer rating){
        series.add(new Serie(id, titulo, g, rating));
    }
}
