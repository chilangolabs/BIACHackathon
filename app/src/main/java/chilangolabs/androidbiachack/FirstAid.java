package chilangolabs.androidbiachack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import chilangolabs.androidbiachack.adapters.RCFirstAidsAdapter;
import chilangolabs.androidbiachack.entitys.ItemFirstAid;


public class FirstAid extends Fragment {

    RecyclerView rcFirst;
    RCFirstAidsAdapter adapter;
    List<ItemFirstAid> itemPerfiles = new ArrayList<>();

    public FirstAid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first_aid, container, false);

        rcFirst = (RecyclerView) rootView.findViewById(R.id.rcFirstAid);

        rcFirst.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemPerfiles.add(new ItemFirstAid("¿Qué son los primeros auxilios?", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("¿Cómo actuar en caso de emergencia?", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("Asfixia", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("Reanimación Cardio Pulmonar", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("Quemaduras", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("Golpe de calor y deshidratación por calor", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));
        itemPerfiles.add(new ItemFirstAid("Lipotimia y coma", "One of my favorite places on this planet! I am fortunate enough to have a home near Cabo Pulmo."));

        adapter = new RCFirstAidsAdapter(itemPerfiles, getActivity());
        rcFirst.setAdapter(adapter);

        return rootView;
    }

}
