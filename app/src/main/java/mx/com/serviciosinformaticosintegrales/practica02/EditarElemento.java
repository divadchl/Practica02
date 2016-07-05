package mx.com.serviciosinformaticosintegrales.practica02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;

public class EditarElemento extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNombreAplicacion, edtNombreDesarrollador;
    private CheckBox chkInstalacion;
    private ElementoRecursoDatos objElementoRecursoDatos;
    private ModeloElemento objModeloElemento;
    private int intInstalacion;
    private int intId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_elemento);
        edtNombreAplicacion = (EditText) findViewById(R.id.editar_elemento_edtNombreAplicacion);
        edtNombreDesarrollador = (EditText) findViewById(R.id.editar_elemento_edtNombreDesarrollador);
        chkInstalacion = (CheckBox) findViewById(R.id.editar_elemento_chkIntalacion);
        findViewById(R.id.editar_elemento_btnGuardar).setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        intId = extras.getInt("id");
        objElementoRecursoDatos = new ElementoRecursoDatos(getApplication());
        objModeloElemento = new ModeloElemento();
        objModeloElemento = objElementoRecursoDatos.seleccionarElemento(intId);

        edtNombreAplicacion.setText(objModeloElemento.strNombreApp);
        edtNombreDesarrollador.setText(objModeloElemento.strNombreDesarrollador);
        if (objModeloElemento.intInstalacion ==0)
        {
            chkInstalacion.setText(R.string.actualizar);
            intInstalacion=0;
        }
        else
        {
            chkInstalacion.setText(R.string.actualizada);
            chkInstalacion.setEnabled(false);
            intInstalacion=1;
        }


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.editar_elemento_btnGuardar:
                objElementoRecursoDatos = new ElementoRecursoDatos(getApplication());
                objModeloElemento = new ModeloElemento();
                objModeloElemento.intId = intId;
                objModeloElemento.strNombreApp = edtNombreAplicacion.getText().toString();
                objModeloElemento.strNombreDesarrollador = edtNombreDesarrollador.getText().toString();
                if(intInstalacion==0)
                {
                    if (chkInstalacion.isChecked())
                    {
                        objModeloElemento.intInstalacion = 1;
                    }
                    else
                    {
                        objModeloElemento.intInstalacion = 0;
                    }
                }
                objElementoRecursoDatos.editarElemento(objModeloElemento);
                finish();
                break;
        }
    }
}
