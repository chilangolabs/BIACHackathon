package chilangolabs.androidbiachack;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    MaterialDialog materialDialog;
    Button btnFrgMapRequest;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFrgMapRequest = (Button) rootView.findViewById(R.id.btnFrgMapRequest);

        materialDialog = new MaterialDialog.Builder(getActivity())
                .title("¡Bienvenida Lluvia!")
                .content("5 minutos es todo lo que se necesita para salvar una vida. Completa tu perfil y ayudate en una emergencia.")
                .positiveText("Editar mi perfil ahora")
                .neutralText("Editar en otro momento")
                .show();

        btnFrgMapRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getActivity())
                        .title("¿Que hacer en caso de asfixia?")
                        .content("1. Párese detrás de la persona y rodéela con los brazos por la cintura. Para un niño, es posible que deba hincarse.\n" +
                                "\n" +
                                "2. Forme un puño con una mano. Coloque el puño por el lado del pulgar justo encima del ombligo de la persona, bien por debajo del esternón.\n" +
                                "\n" +
                                "3. Agarre el puño firmemente con la otra mano.\n" +
                                "Realice una compresión rápida hacia arriba y hacia adentro con el puño.\n" +
                                "\n" +
                                "4. Verifique si el objeto ha salido.\n" +
                                "\n" +
                                "5. Continúe con dichas compresiones hasta que el objeto salga o la persona pierda el conocimiento.")
                        .positiveText("Cerrar")
                        .show();
            }
        });

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
