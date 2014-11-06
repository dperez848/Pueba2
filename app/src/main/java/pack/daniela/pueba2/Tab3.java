package pack.daniela.pueba2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab3 extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.tab3, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Soy el tab3");
        return android;
    }}