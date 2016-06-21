package chilangolabs.androidbiachack;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import chilangolabs.androidbiachack.adapters.ViewPagerAdapter;

public class MainTabActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPagerMainTab);

        toolbar.setTitle("Emergencia");
        toolbar.setSubtitle("Solicita ayuda de inmediato");

        setSupportActionBar(toolbar);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MapFragment());
        fragmentList.add(new PerfilesFragment());
        fragmentList.add(new FirstAid());
        fragmentList.add(new ConfigurationFragment());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Emergencia").setIcon(R.drawable.emergencia));
        tabLayout.addTab(tabLayout.newTab().setText("Perfiles").setIcon(R.drawable.perfiles));
        tabLayout.addTab(tabLayout.newTab().setText("Primeros auxilios").setIcon(R.drawable.firstaid));
        tabLayout.addTab(tabLayout.newTab().setText("Configuracion").setIcon(R.drawable.configuration));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.setTitle("Emergencia");
                        toolbar.setSubtitle("Solicita ayuda de inmediato");
                        break;
                    case 1:
                        toolbar.setTitle("Primeros Auxilios");
                        toolbar.setSubtitle("No pierdas tiempo. ¡Actúa de inmediato!");
                        break;
                    case 2:
                        toolbar.setTitle("Perfiles");
                        toolbar.setSubtitle("Ayuda a los que más amas");
                        break;
                    case 3:
                        toolbar.setTitle("Configuracion");
//                        toolbar.setSubtitle("Solicita ayuda de inmediato");
                        break;
                    default:
                        toolbar.setTitle("Emergencia");
                        toolbar.setSubtitle("Solicita ayuda de inmediato");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
