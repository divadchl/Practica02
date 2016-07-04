package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetalleElemento extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_elemento);

        findViewById(R.id.detalle_elemento_btnAbrir);
        findViewById(R.id.detalle_elemento_btnActualizar);
        findViewById(R.id.detalle_elemento_btnDesinstalar);

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
}
