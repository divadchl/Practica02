package mx.com.serviciosinformaticosintegrales.practica02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoLista;
import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoVacio;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (conocerElementosLista())
        {
            cambiarFragmentoLista();
        }
        else
        {
            cambiarFragmentoVacio();
        }



    }


    private boolean conocerElementosLista()
    {
        ElementoListView objElementoListView = new ElementoListView(getApplicationContext());
        return objElementoListView.conocerExistenElementos();
    }

    private void cambiarFragmentoVacio()
    {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_frlPrincipal, new FragmentoVacio()).commit();


    }

    private void cambiarFragmentoLista()
    {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_frlPrincipal, new FragmentoLista()).commit();
    }

}
