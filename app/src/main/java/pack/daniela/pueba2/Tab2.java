package pack.daniela.pueba2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Tab2 extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.tab2, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Soy el tab 2");

        Button btn9=(Button)android.findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View arg0)
            {
                Intent intent = new Intent(getActivity(), Activity6.class);
                startActivity(intent);
            }
        });
        return android;
    }}