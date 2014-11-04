package pack.daniela.pueba2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        Spinner cmbOpciones;
        final TextView pulse3= (TextView)findViewById(R.id.center2);
        final String[] data=new String[]{"Daniela","Daniel","Alexander","Luis","Gabriel"};
        Button btn5=(Button)findViewById(R.id.btn5);
        ArrayAdapter<String> adap1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data);

        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);

        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adap1);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        pulse3.setText("Seleccionado: " + data[position]);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        pulse3.setText("");
                    }
                });
        AdaptadorTitulares adaptador =  new AdaptadorTitulares(this);

        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                String opcionSeleccionada =
                        ((Titular)a.getAdapter().getItem(position)).getTitulo();


               pulse3.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
        finish();
    }

}
