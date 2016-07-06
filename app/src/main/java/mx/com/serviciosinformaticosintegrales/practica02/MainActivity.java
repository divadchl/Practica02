package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoLista;
import mx.com.serviciosinformaticosintegrales.practica02.fragmentos.FragmentoVacio;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    public static boolean blnBanderaImagen;
    private ElementoRecursoDatos objElementoRecursoDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objElementoRecursoDatos = new ElementoRecursoDatos(getApplication());

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
                startActivityForResult(
                        new Intent(getApplicationContext(),NuevoElemento.class),
                        REQUEST_CODE_SECOND_ACTIVITY);

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private boolean conocerElementosLista()
    {
        return objElementoRecursoDatos.conocerExistenElementos();
    }

    private void cambiarFragmentoVacio()
    {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_frlPrincipal, new FragmentoVacio()).commit();
        blnBanderaImagen=true;
    }

    private void cambiarFragmentoLista()
    {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_frlPrincipal, new FragmentoLista()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(REQUEST_CODE_SECOND_ACTIVITY==requestCode && resultCode==RESULT_OK)
        {
            getFragmentManager().beginTransaction().replace(R.id.activity_main_frlPrincipal, new FragmentoLista()).commit();
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
