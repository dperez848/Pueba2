package pack.daniela.pueba2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity7 extends Activity {
    private ContentValues nuevoRegistro = new ContentValues();
    private Button show=(Button)findViewById(R.id.show);
    private TextView txtResultado=(TextView)findViewById(R.id.results);
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity7);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();
        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos

                nuevoRegistro.put("codigo", i);
                nuevoRegistro.put("nombre","Usuario" + i);
                db.insert("Usuarios", null, nuevoRegistro);
            }
            //Establecemos los campos-valores a actualizar
            ContentValues valores = new ContentValues();
            valores.put("nombre","usunuevo");

            //Actualizamos el registro en la base de datos
            db.update("Usuarios", valores, "codigo=2", null);

            //Eliminamos el registro del usuario '6'
            db.delete("Usuarios", "codigo=3", null);

            //Cerramos la base de datos
            db.close();
        }

        Button show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                String[] campos = new String[] {"codigo", "nombre"};
                String[] args = new String[] {"usu1"};

                Cursor c = db.query("Usuarios", campos, "usuario=?", args, null, null, null);
                //Recorremos los resultados para mostrarlos en pantalla
                txtResultado.setText("");
                //Nos aseguramos de que existe al menos un registro
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros

                    while(c.moveToNext()){
                        String codigo= c.getString(0);
                        String nombre = c.getString(1);
                        txtResultado.append(" " + codigo + " - " + nombre + "\n");
                    }

                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity7, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
