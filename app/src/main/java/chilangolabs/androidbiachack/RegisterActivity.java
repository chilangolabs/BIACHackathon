package chilangolabs.androidbiachack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import chilangolabs.androidbiachack.api.Api;
import chilangolabs.androidbiachack.api.OnRequestListenerListener;

public class RegisterActivity extends AppCompatActivity {

    TextView txtRegisterGeneralData, txtRegisterPrivaci, txtRegisterAlReady;
    EditText edtxRegisterName, edtxRegisterLastName, edtxRegisterPhone, edtxRegisterMail, edtxRegisterPass;
    FloatingActionButton fabGo;

    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtRegisterGeneralData = (TextView) findViewById(R.id.txtRegisterGeneralData);
        txtRegisterPrivaci = (TextView) findViewById(R.id.txtRegisterPrivaci);
        txtRegisterAlReady = (TextView) findViewById(R.id.txtRegisterAlReady);

        edtxRegisterName = (EditText) findViewById(R.id.edtxRegisterName);
        edtxRegisterLastName = (EditText) findViewById(R.id.edtxRegisterLastName);
        edtxRegisterPhone = (EditText) findViewById(R.id.edtxRegisterPhone);
        edtxRegisterMail = (EditText) findViewById(R.id.edtxRegisterMail);
        edtxRegisterPass = (EditText) findViewById(R.id.edtxRegisterPass);

        fabGo = (FloatingActionButton) findViewById(R.id.btnRegisterGo);

        materialDialog = new MaterialDialog.Builder(this).content(R.string.wait_please).progress(true, 100).build();

        txtRegisterGeneralData.setText(Html.fromHtml("Datos <b>Generales</b>"));
        txtRegisterPrivaci.setText(Html.fromHtml("Al registrarte aceptas nuestros <b>Terminos y <br/>condiciones</b> y <b>Aviso de privacidad</b>"));
        txtRegisterAlReady.setText(Html.fromHtml("<b>¿Ya tienes cuenta?</b><br/> <u><b>Iniciar sesion</b></u>"));

        fabGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        materialDialog.show();

        JSONObject jsonRegister = new JSONObject();
        try {
            jsonRegister.put("name", edtxRegisterName.getText().toString() + " " + edtxRegisterLastName.getText().toString());
            jsonRegister.put("phone", edtxRegisterPhone.getText().toString());
            jsonRegister.put("email", edtxRegisterMail.getText().toString());
            jsonRegister.put("password", edtxRegisterPass.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api.registerUser(this, jsonRegister, new OnRequestListenerListener() {

            @Override
            public void OnSucces(JSONObject json) {
                super.OnSucces(json);
                try {
                    Log.e("json", json.getString("accessToken"));
                    Api.setToken(json.getString("accessToken"));
                    Log.e("accestoken", Api.getToken());
                    materialDialog.hide();
                    startActivity(new Intent(RegisterActivity.this, MainTabActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnError(VolleyError error) {
                materialDialog.hide();
//                Log.e("error", new String(error.networkResponse.data) + "");
            }
        });

    }
}
