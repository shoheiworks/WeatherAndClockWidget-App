package com.designseisaku.weatherandclockwidget;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    TextView telopT;
    private SharedPreferences sharedPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        telopT = (TextView) findViewById(R.id.telopTV);

        setWeather();

        /*SharedPre*/
        sharedPre = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("temp", telopT.getText().toString());
        editor.commit();
    }

    public class WeatherService extends Service {
        @Override
        public void onCreate(){
            super.onCreate();
        }

        @Override
        public IBinder onBind(Intent intent){
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId){
            return START_STICKY;
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
        }


    }


    private void setWeather() {
        String urlApi = "http://weather.livedoor.com/forecast/webservice/json/v1?city=400010";
        RequestQueue mQueue = Volley.newRequestQueue(this);

        mQueue.add(new JsonObjectRequest(Request.Method.GET, urlApi, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String telopJ = response.getString("title");
                            telopT.setText(telopJ);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // エラー処理 error.networkResponseで確認
                        // エラー表示など
                    }
                }
        ));

        Intent intent = new Intent(MainActivity.this, WidgetProvider.class);
        intent.putExtra("temp", telopT.getText().toString());
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
}
