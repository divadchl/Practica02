package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoLista;
import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoVacio;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.tb_titulo_activity_main);
        getSupportActionBar().setSubtitle(R.string.tb_subtitulo_activity_main);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);



        if (conocerElementosLista())
        {
            cambiarFragmentoLista();
        }
        else
        {
            cambiarFragmentoVacio();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_itmAgregar:
                Intent intent = new Intent();

                break;
            case R.id.menu_itmEditar:
                break;
        }


        return super.onOptionsItemSelected(item);
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
