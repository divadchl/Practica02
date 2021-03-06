package mx.com.serviciosinformaticosintegrales.practica02.fragmentos;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mx.com.serviciosinformaticosintegrales.practica02.DetalleElemento;
import mx.com.serviciosinformaticosintegrales.practica02.MainActivity;
import mx.com.serviciosinformaticosintegrales.practica02.R;
import mx.com.serviciosinformaticosintegrales.practica02.adaptador.AdaptadorElementoLista;
import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;


public class FragmentoLista extends Fragment {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private ElementoRecursoDatos objElementoRecursoDatos;
    private ListView lsvElementos;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int intId;
        View view = inflater.inflate(R.layout.fragmento_lista, container, false);
        lsvElementos = (ListView) view.findViewById(R.id.fragmento_lista_lsvElementos);

        lsvElementos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AdaptadorElementoLista adaptador = (AdaptadorElementoLista) parent.getAdapter();
                ModeloElemento objModeloElemento = adaptador.getItem(position);
                Intent intent = new Intent(getActivity(), DetalleElemento.class);
                intent.putExtra("id", objModeloElemento.intId);
                //startActivity(intent);

                startActivityForResult(intent,
                        REQUEST_CODE_SECOND_ACTIVITY);
            }
        }
        );

        List<ModeloElemento> lstModeloElemento = objElementoRecursoDatos.obtenerListaElementos();

        lsvElementos.setAdapter(new AdaptadorElementoLista(getActivity(),lstModeloElemento));



        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objElementoRecursoDatos = new ElementoRecursoDatos(getActivity());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(REQUEST_CODE_SECOND_ACTIVITY==requestCode && resultCode==getActivity().RESULT_OK)
        {
            List<ModeloElemento> lstModeloElemento = objElementoRecursoDatos.obtenerListaElementos();
            lsvElementos.setAdapter(new AdaptadorElementoLista(getActivity(),lstModeloElemento));
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


}
