package mx.com.serviciosinformaticosintegrales.practica02.adaptador;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import com.squareup.picasso.Picasso;
import mx.com.serviciosinformaticosintegrales.practica02.R;
import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;

public class AdaptadorElementoLista extends ArrayAdapter<ModeloElemento> {

    private final String url1 = "https://pbs.twimg.com/profile_images/572284141131943936/3CyobuK2.png";
    private final String url2 = "http://www.azcona.eu/apps-aplicaciones-para-moviles-y-tabletas/";

    public AdaptadorElementoLista(Context context, List<ModeloElemento> objects) {
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_elementos,parent,false);
        }

        TextView txvNombreApp = (TextView) convertView.findViewById(R.id.lista_elementos_txvNombreApp);
        TextView txvNombreDesarrollador = (TextView) convertView.findViewById(R.id.lista_elementos_txvNombreDesarrollador);
        TextView txvInstalacion = (TextView) convertView.findViewById(R.id.lista_elementos_txvIntalacion);
        ImageView imvImagenApp = (ImageView) convertView.findViewById(R.id.lista_elementos_imvImagenApp);

        ModeloElemento objModeloElemento = getItem(position);
        Picasso.with(getContext()).load(objModeloElemento.intImagenRecurso==R.drawable.ic_action_name? url1:url2).into(imvImagenApp);

        txvNombreApp.setText(objModeloElemento.strNombreApp);
        txvNombreDesarrollador.setText(objModeloElemento.strNombreDesarrollador);
        if(objModeloElemento.intInstalacion==0)
        {
            txvInstalacion.setText("Instalada");
        }
        else
        {
            txvInstalacion.setText("Actualizada");
        }

        return convertView;
    }
}
