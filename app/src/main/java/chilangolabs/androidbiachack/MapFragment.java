package chilangolabs.androidbiachack;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chilangolabs.androidbiachack.api.Api;
import chilangolabs.androidbiachack.api.OnRequestListenerListener;


public class MapFragment extends Fragment implements OnMapReadyCallback, LocationSource.OnLocationChangedListener {

    private GoogleMap googleMap;
    MaterialDialog materialDialog;
    Button btnFrgMapRequest;
    Spinner spinner;
    List<String> listParent = new ArrayList<>();

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFrgMapRequest = (Button) rootView.findViewById(R.id.btnFrgMapRequest);
        spinner = (Spinner) rootView.findViewById(R.id.spinner);

        listParent.add("Papá");
        listParent.add("Mamá");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, listParent);

//        dataAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        Log.e("apitoken------->>", Api.getToken());
        materialDialog = new MaterialDialog.Builder(getActivity())
                .title("¡Bienvenida Lluvia!")
                .content("5 minutos es todo lo que se necesita para salvar una vida. Completa tu perfil y ayudate en una emergencia.")
                .positiveText("Editar mi perfil ahora")
                .neutralText("Editar en otro momento")
                .show();

        btnFrgMapRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                JSONObject jsonAmbulance = new JSONObject();
                try {
                    jsonAmbulance.put("latitud", "20.9875477");
                    jsonAmbulance.put("longitud", "-86.8295603");
                    jsonAmbulance.put("medicalId", "5768db163c66fe6821564c48");
                    jsonAmbulance.put("phone", "5554053983");
                    jsonAmbulance.put("phone", "5554053983");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("datasend ", jsonAmbulance.toString());

                Api.requestAmbulance(getActivity(), jsonAmbulance, new OnRequestListenerListener() {
                    @Override
                    public void OnSucces(JSONObject json) {
                        super.OnSucces(json);
                    }

                    @Override
                    public void OnError(VolleyError error) {
                        super.OnError(error);
                    }
                });

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
        googleMap.addMarker(new MarkerOptions().position(new LatLng(20.9875477, -86.8295603)).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(20.9875477, -86.8295603)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.9875477, -86.8295603), 16));
    }

    @Override
    public void onLocationChanged(Location location) {
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
    }
}
