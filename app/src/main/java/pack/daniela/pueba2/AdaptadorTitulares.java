package pack.daniela.pueba2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by tmachado on 03/11/2014.
 */
class AdaptadorTitulares extends ArrayAdapter<Titular> {

    Activity context;
    private static Titular[] data3 =
            new Titular[]{
                    new Titular("Título 1", "Subtítulo largo 1"),
                    new Titular("Título 2", "Subtítulo largo 2"),
                    new Titular("Título 3", "Subtítulo largo 3"),
                    new Titular("Título 4", "Subtítulo largo 4"),
                    new Titular("Título 5", "Subtítulo largo 5"),
                    new Titular("Título 1", "Subtítulo largo 1"),
                    new Titular("Título 2", "Subtítulo largo 2"),
                    new Titular("Título 3", "Subtítulo largo 3"),
                    new Titular("Título 4", "Subtítulo largo 4"),
                    new Titular("Título 5", "Subtítulo largo 5")};

    AdaptadorTitulares(Activity context) {
        super(context, R.layout.listitem_titular,data3);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = convertView;
        ViewHolder holder;

        if(item == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.listitem_titular, null);

            holder = new ViewHolder();
            holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
            holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);

            item.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)item.getTag();
        }

        holder.titulo.setText(data3[position].getTitulo());
        holder.subtitulo.setText(data3[position].getSubtitulo());

        return(item);
    }

    static class ViewHolder {
        TextView titulo;
        TextView subtitulo;
    }
}