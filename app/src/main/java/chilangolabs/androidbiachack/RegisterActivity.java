package chilangolabs.androidbiachack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import chilangolabs.androidbiachack.api.Api;
import chilangolabs.androidbiachack.api.OnRequestListenerListener;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtRegisterGeneralData, txtRegisterPrivaci, txtRegisterAlReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Registro");
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        txtRegisterGeneralData = (TextView) findViewById(R.id.txtRegisterGeneralData);
        txtRegisterPrivaci = (TextView) findViewById(R.id.txtRegisterPrivaci);
        txtRegisterAlReady = (TextView) findViewById(R.id.txtRegisterAlReady);

        txtRegisterGeneralData.setText(Html.fromHtml("Datos <b>Generales</b>"));
        txtRegisterPrivaci.setText(Html.fromHtml("Al registrarte aceptas nuestros <b>Terminos y <br/>condiciones</b> y <b>Aviso de privacidad</b>"));
        txtRegisterAlReady.setText(Html.fromHtml("<b>¿Ya tienes cuenta?</b><br/> <u><b>Iniciar sesion</b></u>"));


        Api.registerUser(this, new JSONObject(), new OnRequestListenerListener() {

            @Override
            public void OnSucces(JSONObject json) {
                super.OnSucces(json);
                try {
                    Api.setToken(json.getString("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnError(VolleyError error) {

            }
        });

    }
}
