package com.e.whatasillylife;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.e.whatasillylife.R.layout.start_page;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    public void setVideo(VideoView video) {
        String videoPath = "andriod.resourse://" + getPackageName() + "/" + R.raw.openingvideo;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
    }

    public void retrieveData(String userAnswer) {
        OutputPage fragobj = new OutputPage();
        fragobj.setAns(userAnswer);
    }

    public void apiImage(String query, final ImageView img) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String API = "https://grx8xhhk0b.execute-api.ap-southeast-2.amazonaws.com/default/answer-get?ansID=" + query;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String output = response.getString("ansImage");
                            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(output).getContent());
                            img.setImageBitmap(bitmap);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }

    public void apiFunction(final int code, String query, final TextView t) {
        String URL = "";
        if (code == 1 || code == 2) {
            URL = "https://7j0m82yzrg.execute-api.ap-southeast-2.amazonaws.com/default/question-get?queID=" + query;
        } else if (code == 3 || code == 4 || code == 5) {
            URL = "https://7j0m82yzrg.execute-api.ap-southeast-2.amazonaws.com/default/answer-get?ansID=" + query;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d("code", Integer.toString(code));
//                        Log.e("response", response.toString());
                        try {
                            String output = "";
                            if (code == 1) {
                                output = response.getString("queDesc");
                            } else if (code == 2) {
                                output = response.getString("queHint");
                            } else if (code == 3) {
                                output = response.getString("ansDesc");
                            } else if (code == 4) {
                                output = response.getString("ansImage");
                            } else if (code == 5) {
                                output = response.getString("queComment");
                            }
                            t.append(output);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }
}
