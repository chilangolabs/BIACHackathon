package chilangolabs.androidbiachack.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gorro on 20/06/16.
 */
public class Api {
    private static RequestQueue requestQueue;
    private static JsonObjectRequest jsonObjectRequest;
    private static JsonArrayRequest jsonArrayRequest;
    public static Context context;

    public static void initVolley(Context ctx) {
        context = ctx;
        requestQueue = Volley.newRequestQueue(ctx);
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static void registerUser(final Context ctx, JSONObject jsonUser, final OnRequestListenerListener l) {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "", jsonUser, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                l.OnSucces(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                l.OnError(error);
            }
        });
        getRequestQueue().add(jsonObjectRequest);
    }

    public static void addMedicCard(final Context ctx, JSONObject jsonUser, final OnRequestListenerListener l) {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "", jsonUser, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                l.OnSucces(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                l.OnError(error);
            }
        });
        getRequestQueue().add(jsonObjectRequest);
    }

    public static void getMedicalCards(final Context ctx, JSONArray jsonMedicalCard, final OnRequestListenerListener l) {
        jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, "", jsonMedicalCard, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                l.OnSucces(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                l.OnError(error);
            }
        });
        getRequestQueue().add(jsonObjectRequest);
    }

}
