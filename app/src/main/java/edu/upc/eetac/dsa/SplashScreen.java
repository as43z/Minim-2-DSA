package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import edu.upc.eetac.dsa.instances.instanceUser;
import edu.upc.eetac.dsa.models.Repo;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedPref;
    private TextView textWelcome;

    // para darle un toque más profesional:
    private Handler handler;
    private Runnable runnable;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Context context = getApplicationContext();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        sharedPref = context.getSharedPreferences("searched_user", Context.MODE_PRIVATE);
        boolean searched = sharedPref.getBoolean("searched", false);

        if(searched){
            runnable = new Runnable() {
                @Override
                public void run() {
                    String usernameTxt = sharedPref.getString("user", "");
                    Intent intent = new Intent(context, Loader.class);
                    intent.putExtra("username", usernameTxt);
                    startActivity(intent);
                }
            };
        } else {
            runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            };
        }

        handler = new Handler();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnable != null && handler != null)
            handler.removeCallbacks(runnable);
    }
}