package isi.dam.damgui03.domain;

public class Serie {

    private Integer id;
    private String titulo;
    private Genero genero;
    private Integer rating; // rating del publico
    private Float calificacion;  // calificacion mia
    private Boolean favorita;

    public Serie(Integer id, String titulo, Genero genero, Integer rating) {
        this();
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.rating = rating;
    }

    public Serie(Integer id, String titulo, Genero genero, Integer rating, Boolean favorita) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.rating = rating;
        this.favorita = favorita;
    }

    public Serie(String titulo, Genero genero, Integer rating) {
        this();
        this.titulo = titulo;
        this.genero = genero;
        this.rating = rating;
    }

    public Serie() {
        this.favorita = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return
                calificacion+ " <- titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", favorita=" + favorita ;
    }
}
