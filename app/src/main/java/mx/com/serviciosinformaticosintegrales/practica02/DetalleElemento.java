package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;

public class DetalleElemento extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_elemento);
        TextView txvNombreDesarrollador = (TextView) findViewById(R.id.detalle_elemento_txvNombreDesarrollador);
        TextView txvDescripcion = (TextView) findViewById(R.id.detalle_elemento_txvDescripcion);

        findViewById(R.id.detalle_elemento_btnAbrir).setOnClickListener(this);
        findViewById(R.id.detalle_elemento_btnActualizar).setOnClickListener(this);
        findViewById(R.id.detalle_elemento_btnDesinstalar).setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        int intId = extras.getInt("id");
        ElementoRecursoDatos objElementoRecursoDatos = new ElementoRecursoDatos(getApplication());
        ModeloElemento objModeloElemento = new ModeloElemento();
                objModeloElemento = objElementoRecursoDatos.seleccionarElemento(intId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detalle_elemento_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(objModeloElemento.strNombreApp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txvNombreDesarrollador.setText(objModeloElemento.strNombreDesarrollador);
        txvDescripcion.setText(objModeloElemento.strDescripcion);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.detalle_elemento_btnAbrir:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.calculadorasat.com/"));
                startActivity(intent);
                break;
            case R.id.detalle_elemento_btnActualizar:
                break;
            case R.id.detalle_elemento_btnDesinstalar:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_itmEditar:
                Intent intent = new Intent(getApplication(), EditarElemento.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
