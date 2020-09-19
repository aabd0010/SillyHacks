package com.e.whatasillylife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> queArray = new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String apiFunction(int code, String query) {

//        if (code == 1) {
//            URL = "https://grx8xhhk0b.execute-api.ap-southeast-2.amazonaws.com/default/question-get?queID=" + query;
//        }else if (code ==2){
//            URL = "https://grx8xhhk0b.execute-api.ap-southeast-2.amazonaws.com/default/answer-get?ansID=" + query;
//        }
        String URL = "https://grx8xhhk0b.execute-api.ap-southeast-2.amazonaws.com/default/question-get?queID=1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response", response.toString());
//                        try {
//                            JSONArray jsonArray = response.getJSONArray(response.toString());
//                            for( int i= 0;i<jsonArray.length();i++){
//                                JSONObject questions = jsonArray.getJSONObject(i);
//                                String desc = questions.getString("queDesc");
//                                String hint = questions.getString("queHint");
//                                queArray.add(desc);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("error", error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
//        Log.e("error", queArray.get(0));
        return "";
    }
}

class Question{
    public String desc;
    public Question(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

}
