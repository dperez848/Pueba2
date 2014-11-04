package pack.daniela.pueba2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Button btn1=(Button)findViewById(R.id.btn1);
        ImageButton btn3=(ImageButton)findViewById(R.id.btn3);
        final ToggleButton btn2 = (ToggleButton)findViewById(R.id.btn2);
        final TextView pulse= (TextView)findViewById(R.id.center);
        final TextView pulse2= (TextView)findViewById(R.id.Hello);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                pulse.setText("Botón 1 pulsado!");
                String texto = pulse2.getText().toString();
                texto += "123";
                pulse2.setText(texto);
                pulse2.setBackgroundColor(Color.BLUE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                if(btn2.isChecked())
                    pulse2.setText("Botón 2: ON");
                else
                    pulse2.setText("Botón 2: OFF");
            }
        });

        btn3.setOnClickListener(new OnClickListener()
        {   public void onClick(View arg0)
            {
                Intent intent = new Intent(Principal.this, Activity2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
