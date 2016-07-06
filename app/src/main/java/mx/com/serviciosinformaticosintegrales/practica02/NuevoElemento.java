package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;

public class NuevoElemento extends AppCompatActivity {

    private boolean blnBandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_elemento);

        final EditText edtNombreAplicacion = (EditText) findViewById(R.id.nuevo_elemento_edtNombreAplicacion);
        final EditText edtNombreDesarrollador = (EditText) findViewById(R.id.nuevo_elemento_edtNombreDesarrollador);
        final EditText edtDescripcion = (EditText) findViewById(R.id.nuevo_elemento_edtDescripcion);
        final CheckBox chkInstalacion = (CheckBox) findViewById(R.id.nuevo_elemento_chkIntalacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nuevo_elemento_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.tb_titulo_nuevo_elemento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.nuevo_elemento_btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNombreAplicacion.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Debe escribir el nombre de la aplicación",Toast.LENGTH_SHORT).show();
                    blnBandera= false;
                    
                }
                if (edtNombreDesarrollador.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Debe escribir el nombre del desarrollador",Toast.LENGTH_SHORT).show();
                    blnBandera= false;
                }

                if (edtDescripcion.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Debe escribir la descripción de la aplicación",Toast.LENGTH_SHORT).show();
                    blnBandera= false;
                }


                if (blnBandera)
                {
                    ElementoRecursoDatos objElementoRecursoDatos = new ElementoRecursoDatos(getApplicationContext());
                    ModeloElemento objModeloElemento = new ModeloElemento();
                    objModeloElemento.strNombreApp = edtNombreAplicacion.getText().toString();
                    objModeloElemento.strNombreDesarrollador = edtNombreDesarrollador.getText().toString();
                    objModeloElemento.strDescripcion = edtDescripcion.getText().toString();

                    List<ModeloElemento> lstModeloElemento = objElementoRecursoDatos.obtenerListaElementos();
                    MainActivity.blnBanderaImagen = !(lstModeloElemento.size() % 2 == 0);
                    objModeloElemento.intImagenRecurso = MainActivity.blnBanderaImagen ? R.drawable.ic_action_name : R.drawable.ic_action_name2;

                    if (chkInstalacion.isChecked()) {
                        objModeloElemento.intInstalacion = 0;
                    } else {
                        objModeloElemento.intInstalacion = 1;
                    }
                    objElementoRecursoDatos.guadarElemento(objModeloElemento);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
