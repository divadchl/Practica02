package mx.com.serviciosinformaticosintegrales.practica02;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;
import mx.com.serviciosinformaticosintegrales.practica02.sql.ElementoRecursoDatos;

public class DetalleElemento extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private int intId;
    private ProgressBar prbProgreso;
    private Button btnAbrir, btnActualizar, btnDesinstalar;
    private ElementoRecursoDatos objElementoRecursoDatos;
    private ModeloElemento objModeloElemento;
    private boolean blnBloquearMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_elemento);
        TextView txvNombreDesarrollador = (TextView) findViewById(R.id.detalle_elemento_txvNombreDesarrollador);
        TextView txvDescripcion = (TextView) findViewById(R.id.detalle_elemento_txvDescripcion);
        prbProgreso = (ProgressBar) findViewById(R.id.detalle_elemento_prbProgreso);

        btnAbrir= (Button) findViewById(R.id.detalle_elemento_btnAbrir);
        btnAbrir.setOnClickListener(this);
        btnActualizar = (Button) findViewById(R.id.detalle_elemento_btnActualizar);
        btnActualizar.setOnClickListener(this);
        btnDesinstalar = (Button)findViewById(R.id.detalle_elemento_btnDesinstalar);
        btnDesinstalar.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        intId = extras.getInt("id");
        //intId = getIntent().getExtras().getInt("id");
        //intId2=2;

        objElementoRecursoDatos = new ElementoRecursoDatos(getApplicationContext());
        objModeloElemento = new ModeloElemento();
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
                actualizarAplicacion();
                break;
            case R.id.detalle_elemento_btnDesinstalar:
                desinstalarAplicacion();
                break;
        }
    }

    private void desinstalarAplicacion()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.titulo_borrar_elemento)
                .setMessage(String.format(getString(R.string.mensage_borrar_elemento) + " " + objModeloElemento.strNombreApp))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        prbProgreso.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                prbProgreso.setVisibility(View.GONE);
                                objElementoRecursoDatos.borrarElemento(objModeloElemento);
                                blnBloquearMenu=true;
                                btnActualizar.setVisibility(View.INVISIBLE);
                                btnDesinstalar.setVisibility(View.INVISIBLE);
                                btnAbrir.setVisibility(View.INVISIBLE);
                            }
                        },1000*5);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setCancelable(false).create().show();
    }

    private void actualizarAplicacion()
    {
        Toast.makeText(getApplicationContext(), "Actualizando",Toast.LENGTH_LONG).show();

        btnDesinstalar.setEnabled(false);
        btnAbrir.setEnabled(false);
        prbProgreso.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                prbProgreso.setVisibility(View.GONE);
                objModeloElemento.intInstalacion = 1;
                objElementoRecursoDatos.actualizarAplicacion(objModeloElemento);
                btnAbrir.setEnabled(true);
                btnDesinstalar.setEnabled(true);
                btnActualizar.setEnabled(false);
            }
        },1000*10);


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
                if(!blnBloquearMenu)
                {
                    Intent intent = new Intent(getApplication(), EditarElemento.class);
                    intent.putExtra("id", intId);
                    startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
                }

                return true;
            case android.R.id.home:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(REQUEST_CODE_SECOND_ACTIVITY==requestCode && resultCode==RESULT_OK)
        {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
