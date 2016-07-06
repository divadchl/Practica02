package mx.com.serviciosinformaticosintegrales.practica02.servicio;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import mx.com.serviciosinformaticosintegrales.practica02.MainActivity;
import mx.com.serviciosinformaticosintegrales.practica02.R;

public class ServicioNotificacion extends Service {

    private String strNombreAplicacion;
    private static final int ID_NOTIFICACION_CREAR = 1;
    private MyAsyncTask myAsyncTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if(intent.getExtras()!=null && intent.getExtras().containsKey("nombre_aplicacion"))
        {
            strNombreAplicacion = intent.getExtras().getString("nombre_aplicacion");
        }

        if(myAsyncTask==null)
        {
            myAsyncTask= new MyAsyncTask();
            myAsyncTask.execute();
        }

        return START_STICKY;

    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>
    {
        private NotificationCompat.Builder mNotif;

        @Override
        protected void onPreExecute() {

            mNotif = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle(getString(R.string.titutlo_notificacion))
                    .setContentText(getString(R.string.text_notificacion) + " " +  strNombreAplicacion)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_name))
                    .setSmallIcon(android.R.drawable.ic_dialog_email);
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (int i=0;i<6;i++)
            {
                publishProgress(i);
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mNotif.setProgress(6,values[0],false);
            NotificationManager manager  = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(ID_NOTIFICACION_CREAR, mNotif.build());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                //elimina progres cuando lo seteamos a 0
                mNotif.setProgress(0,0,false);
                mNotif.setContentTitle(getString(R.string.titulo_notificacion_post));
                mNotif.setContentText(getString(R.string.text_notificacion_post));
                mNotif.setAutoCancel(true);
                mNotif.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.bigText_notificacion_post) + "\n" + strNombreAplicacion));
                PendingIntent pendingIntent =PendingIntent.
                        getActivity(getApplicationContext(),
                                0,new Intent(getApplicationContext(),
                                        MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                mNotif.setContentIntent(pendingIntent);
                PendingIntent piService = PendingIntent.
                        getService(getApplicationContext(),1,
                                new Intent(getApplicationContext()
                                        ,ServicioNotificacion.class)
                                        .putExtra("key_id",ID_NOTIFICACION_CREAR+1)
                                ,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager manager  = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(ID_NOTIFICACION_CREAR, mNotif.build());
            }
            myAsyncTask=null;
            stopSelf();
        }
    }



}
