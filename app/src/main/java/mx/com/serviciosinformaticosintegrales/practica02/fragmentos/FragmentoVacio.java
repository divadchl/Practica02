package mx.com.serviciosinformaticosintegrales.practica02.fragmentos;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.serviciosinformaticosintegrales.practica02.R;

public class FragmentoVacio extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_vacio, container, false);


        return view;
    }
}
