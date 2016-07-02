package mx.com.serviciosinformaticosintegrales.practica02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class NuevoElemento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_elemento);

        EditText edtNombreAplicacion = (EditText) findViewById(R.id.nuevo_elemento_edtNombreAplicacion);
        EditText edtNombreDesarrollador = (EditText) findViewById(R.id.nuevo_elemento_edtNombreDesarrollador);
        EditText edtDescripcion = (EditText) findViewById(R.id.nuevo_elemento_edtDescripcion);
        CheckBox chkInstalacion = (CheckBox) findViewById(R.id.nuevo_elemento_chkIntalacion);




        findViewById(R.id.nuevo_elemento_btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
