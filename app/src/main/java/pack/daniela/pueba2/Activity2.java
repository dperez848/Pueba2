package pack.daniela.pueba2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Activity2 extends Activity {

    private TextView pulse3;
    private String[] data;
    private String opcionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        pulse3 = (TextView)findViewById(R.id.center2);
        data = new String[]{"Daniela","Daniel","Alexander","Luis","Gabriel"};

        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data);
        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
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

        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        AdaptadorTitulares adaptador =  new AdaptadorTitulares(this);
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                opcionSeleccionada = ((Titular)a.getAdapter().getItem(position)).getTitulo();
                pulse3.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });

        Button btn5 =(Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(lstOpciones);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_lista, menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.CtxLst1Opc1:
                pulse3.setText("Lista[" + info.position + "]: Opcion 1 pulsada!");
                return true;
            case R.id.CtxLst1Opc2:
                pulse3.setText("Lista[" + info.position + "]: Opcion 2 pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
