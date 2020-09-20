package com.e.whatasillylife;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public String answer = "";
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
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Sorry, you can't change your destiny ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return true;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void setVideo(VideoView video) {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.openingvideo;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
    }

    public void retrieveData(String userAnswer) {
          this.answer = userAnswer;
    }
    public String getAnswer(){
        return answer;
    }

    public void apiOutput(String query,final TextView ans, final ImageView img, final TextView comment) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.e("error", query);
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
                            ans.setText(response.getString("ansDesc"));
                            comment.setText(response.getString("ansComment"));
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

    public void apiFunction(String query, final TextView que, final TextView hint) {
        String URL = "https://7j0m82yzrg.execute-api.ap-southeast-2.amazonaws.com/default/question-get?queID=" + query;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            que.setText(response.getString("queDesc"));
                            hint.append(" " + response.getString("queHint"));
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
