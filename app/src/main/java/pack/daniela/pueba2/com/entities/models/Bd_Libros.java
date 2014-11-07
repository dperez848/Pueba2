package pack.daniela.pueba2.com.entities.models;

import com.orm.SugarRecord;

public class Bd_Libros extends SugarRecord<Bd_Libros> {
    String titulo;
    String edicion;

    public Bd_Libros(){
    }

    public Bd_Libros(String title, String edition){
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