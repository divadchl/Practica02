package mx.com.serviciosinformaticosintegrales.practica02.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class BDSqliteHelper extends SQLiteOpenHelper {

    private final static String strBaseDatosNombre = "BDAplicaciones";
    private final static int intBaseDatosVersion = 1;
    public static final String strTablaNombre = "aplicaciones";
    public static final String strColId = BaseColumns._ID;
    public static final String strColNombreDesarrollador = "nombreDesarrollador";
    public static final String strColNombreApp = "nombreApp";
    public static final String strColImagenRecurso = "imagenRecurso";
    public static final String strColInstalacion = "instalacion";
    public static final String strColDescripcion = "descripcion";
    private String strCrearTabla = "CREATE TABLE " + strTablaNombre +
            " (" + strColId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            strColNombreDesarrollador + " TEXT NOT NULL, " +
            strColNombreApp + " TEXT NOT NULL, " +
            strColImagenRecurso + " TEXT NOT NULL, " +
            strColInstalacion + " INT NOT NULL, " +
            strColDescripcion + " TEXT NOT NULL)";

    public BDSqliteHelper(Context context)
    {
        super(context, strBaseDatosNombre, null, intBaseDatosVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase bd)
    {
        bd.execSQL(strCrearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
