package pack.daniela.pueba2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class Activity7 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity7);

        final EditText eTitulo=(EditText)findViewById(R.id.txt71);
        final EditText eEdicion=(EditText)findViewById(R.id.txt72);
        final TextView results=(TextView)findViewById(R.id.results);

        Button add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
               Libros book = new Libros(eTitulo.getText().toString(), eEdicion.getText().toString());
               book.save();
               eTitulo.setText("");
               eEdicion.setText("");
               // long numberOfBooks = Libros.count(Libros.class, null, null);
               // results.setText("" + (int) numberOfBooks);
            }
        });

        Button show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                List<Libros> books= new ArrayList<Libros>();
                books = Libros.listAll(Libros.class);
                for( int i = 0 ; i < books.size() ; i++ ){
                    results.append(books.get(i).getTitulo() + " " + books.get(i).getEdicion());
                    results.append(Html.fromHtml("<br />"));
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
