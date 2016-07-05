package mx.com.serviciosinformaticosintegrales.practica02.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.serviciosinformaticosintegrales.practica02.modelo.ModeloElemento;

public class ElementoRecursoDatos {
    private final SQLiteDatabase bd;
    private ModeloElemento elemento;
    private Cursor cursor;

    public ElementoRecursoDatos(Context context)
    {
        BDSqliteHelper helper = new BDSqliteHelper(context);
        bd = helper.getWritableDatabase();
    }

    public void guadarElemento(ModeloElemento modeloElemento)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BDSqliteHelper.strColNombreDesarrollador, modeloElemento.strNombreDesarrollador);
        contentValues.put(BDSqliteHelper.strColNombreApp, modeloElemento.strNombreApp);
        contentValues.put(BDSqliteHelper.intColImagenRecurso, modeloElemento.intImagenRecurso);
        contentValues.put(BDSqliteHelper.strColInstalacion, modeloElemento.intInstalacion);
        contentValues.put(BDSqliteHelper.strColDescripcion, modeloElemento.strDescripcion);
        bd.insert(BDSqliteHelper.strTablaNombre, null,contentValues);
    }

    public void editarElemento(ModeloElemento modelElemento)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BDSqliteHelper.strColNombreDesarrollador, modelElemento.strNombreDesarrollador);
        contentValues.put(BDSqliteHelper.strColNombreApp, modelElemento.strNombreApp);
        contentValues.put(BDSqliteHelper.intColImagenRecurso, modelElemento.intImagenRecurso);
        contentValues.put(BDSqliteHelper.strColInstalacion, modelElemento.intInstalacion);
        contentValues.put(BDSqliteHelper.strColDescripcion, modelElemento.strDescripcion);
        bd.update(BDSqliteHelper.strTablaNombre,contentValues,BDSqliteHelper.strColId + "=?",
                new String[]{String.valueOf(modelElemento.intId)});
    }

    public void borrarElemento(ModeloElemento modeloElemento)
    {
       bd.delete(BDSqliteHelper.strTablaNombre,BDSqliteHelper.strColId + "=?",
               new String[]{String.valueOf(modeloElemento.intId)});
    }

    public ModeloElemento seleccionarElemento(int intIdElemento)
    {
        String[] param = new String[1];
        param[0] = Integer.toString(intIdElemento);
        cursor = bd.query(BDSqliteHelper.strTablaNombre, null, BDSqliteHelper.strColId + "=?",
                param,null,null,null);
        if (cursor.getCount() > 0)
        {
            while(cursor.moveToNext()) {
                elemento = new ModeloElemento();
                elemento.intId = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColId));
                elemento.strNombreDesarrollador = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColNombreDesarrollador));
                elemento.strNombreApp = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColNombreApp));
                elemento.intImagenRecurso = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.intColImagenRecurso));
                elemento.intInstalacion = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColInstalacion));
                elemento.strDescripcion = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColDescripcion));
            }
        }
        return elemento;
    }

    public List<ModeloElemento> obtenerListaElementos()
    {
        List<ModeloElemento> lstModeloElementos = new ArrayList<>();
        cursor = bd.query(BDSqliteHelper.strTablaNombre,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            elemento = new ModeloElemento();
            elemento.intId = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColId));
            elemento.strNombreDesarrollador = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColNombreDesarrollador));
            elemento.strNombreApp = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColNombreApp));
            elemento.intImagenRecurso = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.intColImagenRecurso));
            elemento.intInstalacion = cursor.getInt(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColInstalacion));
            elemento.strDescripcion = cursor.getString(cursor.getColumnIndexOrThrow(BDSqliteHelper.strColDescripcion));
            lstModeloElementos.add(elemento);
        }
        return lstModeloElementos;
    }

    public boolean conocerExistenElementos()
    {
        boolean blnRegistro = false;
        cursor = bd.query(BDSqliteHelper.strTablaNombre,null,null,null,null,null,null);
        if(cursor.getCount()>0)
        {
            blnRegistro = true;
        }
        return blnRegistro;
    }
}

