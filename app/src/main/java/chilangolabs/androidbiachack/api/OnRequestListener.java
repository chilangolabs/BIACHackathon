package chilangolabs.androidbiachack.api;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gorro on 20/06/16.
 */
public interface OnRequestListener {

    void OnSucces(JSONObject json);

    void OnSucces(JSONArray json);

    void OnError(VolleyError error);

}
