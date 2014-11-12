package pack.daniela.pueba2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MiTareaAsincrona extends Activity{
    private ImageView imgFavorite;
    private TextView results;
    private final int CAMERA_RESULT = 1;
    private final String Tag = getClass().getName();
    private File out;

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
                /*// create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // start the image capture Intent
                startActivityForResult(intent, 0);*/

                PackageManager pm = getPackageManager();
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, MyFileContentProvider.CONTENT_URI);
                    startActivityForResult(i, CAMERA_RESULT);
                } else {
                    Toast.makeText(getBaseContext(), "Camera is not available", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgFavorite.setImageBitmap(bp);*/
       /* // - - - Codifica a Base64
        ByteArrayOutputStream arrayByte = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, arrayByte);
        //byte[] byteArray = arrayByte.toByteArray();
        String encoded = Base64.encodeToString(arrayByte.toByteArray(), Base64.DEFAULT);
        results.setText(encoded);
        // - - - Decodiica a bitmap y muestra en Imageview
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(),Base64.DEFAULT);
        imgFavorite.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));*/

        super.onActivityResult(requestCode, resultCode, data);
        Log.i(Tag, "Receive the camera result");
        if (resultCode == RESULT_OK && requestCode == CAMERA_RESULT) {
            out = new File(getFilesDir(), "newImage.jpg");
            if(!out.exists()) {
                Toast.makeText(getBaseContext(),
                        "Error while capturing image", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            LongOperation run = new LongOperation();
            run.execute();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        imgFavorite = null;
    }
    public static int getImageOrientation(String imagePath){
        int rotate = 0;
        try {

            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int maxWidthHeight) {
        int[] widthHeight = getWidthHeight(bitmap.getWidth(), bitmap.getHeight(), maxWidthHeight);

        bitmap = Bitmap.createScaledBitmap(bitmap, widthHeight[0], widthHeight[1], true);

        return bitmap;
    }

    private int[] getWidthHeight(int width, int height, int maxWidthHeight) {
        int[] result = new int[2];
        float ratio;

        if (width > height) {
            ratio = (float) width/height;
            result[0] = maxWidthHeight;
            result[1] = Math.round(maxWidthHeight / ratio);
        }
        else if (width < height) {
            ratio = (float) height/width;
            result[0] = Math.round(maxWidthHeight / ratio);
            result[1] = maxWidthHeight;
        }
        else {
            result[0] = maxWidthHeight;
            result[1] = maxWidthHeight;
        }

        return result;
    }

    private class LongOperation extends AsyncTask<Void, Void, Bitmap> {
        byte[] imageAsBytes;
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Bitmap doInBackground(Void... params) {
            // Cambia la prosicion de la imagen segun la posicion actual (getImageOrientation())
            Bitmap mBitmap = BitmapFactory.decodeFile(out.getAbsolutePath());
            Matrix matrix = new Matrix();
            matrix.postRotate(getImageOrientation(out.getAbsolutePath()));

            Bitmap rotatedBitmap = resizeBitmap(Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
                    mBitmap.getHeight(), matrix, true),500);
            //Asigna la imagen rotada al ImageView
            //imgFavorite.setImageBitmap(rotatedBitmap);

            // - - - Codifica a Base64
            ByteArrayOutputStream arrayByte = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, arrayByte);
            //byte[] byteArray = arrayByte.toByteArray();
            String encoded = Base64.encodeToString(arrayByte.toByteArray(), Base64.DEFAULT);
           // results.setText(encoded);
            // - - - Decodiica a bitmap y muestra en Imageview

            byte[] imageAsBytes = Base64.decode(encoded.getBytes(),Base64.DEFAULT);
            Bitmap bp= BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            return bp;
            //return imageAsBytes;
        }

        @Override
        protected void onPostExecute(Bitmap imageAsBytes) {
            imgFavorite.setImageBitmap(imageAsBytes);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();

        }
    }

}


