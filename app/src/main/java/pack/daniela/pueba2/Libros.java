package pack.daniela.pueba2;

import com.orm.SugarRecord;

public class Libros extends SugarRecord<Libros> {
    String titulo;
    String edicion;

    public Libros(){
        super();
    }

    public Libros(String title, String edition){
        super();
        this.titulo = title;
        this.edicion = edition;
    }
    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public void setEdicion(String edicion) {

        this.edicion = edicion;
    }

    public String getEdicion() {

        return edicion;
    }

    public String getTitulo() {

        return titulo;
    }


}