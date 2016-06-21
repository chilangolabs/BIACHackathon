package chilangolabs.androidbiachack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chilangolabs.androidbiachack.adapters.RCProfileAdapter;
import chilangolabs.androidbiachack.api.Api;
import chilangolabs.androidbiachack.api.OnRequestListenerListener;
import chilangolabs.androidbiachack.entitys.ItemPerfiles;


public class PerfilesFragment extends Fragment {

    RecyclerView rcProfiles;
    RCProfileAdapter adapter;
    List<ItemPerfiles> itemPerfiles = new ArrayList<>();
    MaterialDialog materialDialog;

    public PerfilesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_perfiles, container, false);

        rcProfiles = (RecyclerView) rootView.findViewById(R.id.rcViewProfiles);
        rcProfiles.setLayoutManager(new LinearLayoutManager(getActivity()));

        materialDialog = new MaterialDialog.Builder(getActivity())
                .content(R.string.wait_please)
                .progress(true, 100)
                .build();

//        requestProfiles();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        requestProfiles();
        try {
            JSONObject json = new JSONObject("{\n" +
                    "  \"ok\": true,\n" +
                    "  \"medicalIds\": [\n" +
                    "    {\n" +
                    "      \"medicalNotes\": \"Colesterol alto\",\n" +
                    "      \"allergies\": \"Ninguna\",\n" +
                    "      \"medicalConditions\": \"Hipercalsuria idiopatica\",\n" +
                    "      \"medications\": \"Hidroclorotiazida\",\n" +
                    "      \"weight\": 80,\n" +
                    "      \"height\": 167,\n" +
                    "      \"bloodType\": \"a+\",\n" +
                    "      \"relation\": \"self\",\n" +
                    "      \"_id\": \"5768db163c66fe6821564c48\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"medicalNotes\": \"Colesterol alto\",\n" +
                    "      \"allergies\": \"Ninguna\",\n" +
                    "      \"medicalConditions\": \"Hipercalsuria idiopatica\",\n" +
                    "      \"medications\": \"Hidroclorotiazida\",\n" +
                    "      \"weight\": 80,\n" +
                    "      \"height\": 167,\n" +
                    "      \"bloodType\": \"a+\",\n" +
                    "      \"relation\": \"self\",\n" +
                    "      \"_id\": \"576968b99fe396ae8fcd3e63\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}");
            parseProfiles(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void requestProfiles() {
        materialDialog.show();
        Api.getMedicalCards(getActivity(), new JSONObject(), new OnRequestListenerListener() {
            @Override
            public void OnSucces(JSONObject json) {
                super.OnSucces(json);
                materialDialog.dismiss();
                parseProfiles(json);
            }

            @Override
            public void OnError(VolleyError error) {
                super.OnError(error);
                materialDialog.dismiss();
//                requestProfiles();
            }
        });
    }

    private void parseProfiles(JSONObject json) {
        try {
            for (int i = 0; i < json.getJSONArray("medicalIds").length(); i++) {
                itemPerfiles.add(new ItemPerfiles("Ricardo Gomez", json.getJSONArray("medicalIds").getJSONObject(i).getString("relation"), 8));
            }
            adapter = new RCProfileAdapter(itemPerfiles, getActivity());
            rcProfiles.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
