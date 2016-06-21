package chilangolabs.androidbiachack.api;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gorro on 20/06/16.
 */
public abstract class OnRequestListenerListener implements OnRequestListener {
    @Override
    public void OnSucces(JSONObject json) {

    }

    @Override
    public void OnSucces(JSONArray json) {

    }

    @Override
    public void OnError(VolleyError error) {

    }
}
