package chilangolabs.androidbiachack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.volley.VolleyError;

import org.json.JSONArray;

import chilangolabs.androidbiachack.api.Api;
import chilangolabs.androidbiachack.api.OnRequestListenerListener;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Api.getMedicalCards(this, new JSONArray(), new OnRequestListenerListener() {
            @Override
            public void OnSucces(JSONArray json) {
                super.OnSucces(json);
            }

            @Override
            public void OnError(VolleyError error) {

            }
        });

    }
}
