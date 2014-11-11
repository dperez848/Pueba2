package pack.daniela.pueba2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Activity7 extends Activity {

    private ImageView imgFavorite;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity7);

        final EditText eTitulo=(EditText)findViewById(R.id.txt71);
        final EditText eEdicion=(EditText)findViewById(R.id.txt72);
        results=(TextView)findViewById(R.id.results);
        imgFavorite = (ImageView)findViewById(R.id.imageView1);
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

        Button cam=(Button)findViewById(R.id.camera);
        cam.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // start the image capture Intent
                startActivityForResult(intent, 0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        // - - - Codifica a Base64
        ByteArrayOutputStream arrayByte = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, arrayByte);
        //byte[] byteArray = arrayByte.toByteArray();
        String encoded = Base64.encodeToString(arrayByte.toByteArray(), Base64.DEFAULT);
        results.setText(encoded);
        // - - - Decodiica a bitmap y muestra en Imageview
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(),Base64.DEFAULT);
        imgFavorite.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
    }

}
