package pack.daniela.pueba2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import pack.daniela.pueba2.com.entities.models.Bd_Libros;


public class Activity7 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity7);
        final EditText eTitulo=(EditText)findViewById(R.id.txt71);
        final EditText eEdicion=(EditText)findViewById(R.id.txt72);
        final
        TextView results=(TextView)findViewById(R.id.results);

        Button show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                Bd_Libros book = new Bd_Libros(eTitulo.getText().toString(), eEdicion.getText().toString());
                book.save();
            }
        });

        Button add=(Button)findViewById(R.id.add);
        show.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                results.setText("");
                //Bd_Libros book = Bd_Libros.findById(Bd_Libros.class,1);


                List<Bd_Libros> books = Bd_Libros.listAll(Bd_Libros.class);
                /*int i=0;
                Iterator iter = books.iterator();
                while (iter.hasNext()){
                    i++;
                    results.append(books.get(i).getTitulo() + " " + books.get(i).getEdicion());
                }*/
                results.append(books.get(1).getTitulo() + " " + books.get(1).getEdicion());

                     // ArrayList<Bd_Libros> books= new ArrayList<Bd_Libros>();
                //books = Bd_Libros.listAll(Bd_Libros.class);
                        //.listAll(Bd_Libros.class);
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
