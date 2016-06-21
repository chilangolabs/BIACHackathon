package chilangolabs.androidbiachack;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import chilangolabs.androidbiachack.adapters.ViewPagerAdapter;
import chilangolabs.androidbiachack.api.Api;
import layout.Cuestionario;


public class ConfigurationFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    public ConfigurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_configuration, container, false);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPagerMainTab);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Cuestionario());
        fragmentList.add(new Cuestionario());
        fragmentList.add(new Cuestionario());
        fragmentList.add(new Cuestionario());

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("ID 1"));
        tabLayout.addTab(tabLayout.newTab().setText("ID 2"));
        tabLayout.addTab(tabLayout.newTab().setText("ID 3"));
        tabLayout.addTab(tabLayout.newTab().setText("ID 4"));

        Log.e("apitoken------->>", Api.getToken());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

}
