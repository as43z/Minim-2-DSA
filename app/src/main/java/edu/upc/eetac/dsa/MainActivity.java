package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import edu.upc.eetac.dsa.instances.instanceUser;
import edu.upc.eetac.dsa.models.Repo;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //SPACE FOR VARIABLES:
    private APIInterface apiInterface;
    private EditText username;
    private String usernameTxt;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean could = getIntent().getExtras().getBoolean("could");
        apiInterface = APIClient.getClient().create(APIInterface.class);
        username = (EditText) findViewById(R.id.unameText);
        sharedPreferences = getApplicationContext().getSharedPreferences("searched_user", Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();

        if(!could){
            Toast.makeText(getApplicationContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    public void searchUser(View view){
        username = (EditText) findViewById(R.id.unameText);
        usernameTxt = username.getText().toString();
        if(usernameTxt.equals("")){
            Log.d("ERROR", "username cannot be empty");
        } else {
//            Log.d("SearchUser", "Searching user " + usernameTxt);
//
//            Call<User> userCall = apiInterface.getUser(usernameTxt);
//            userCall.enqueue(new Callback<User>(){
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if(response.code() == 200){
//                        User u = response.body();
//                        instanceUser.getInstance().setUser(u);
//
//                        String repos_url = u.getRepos_url();
//                        Log.d("SearchUser", "searching for repos in " + repos_url);
//                        Call<List<Repo>> reposCall = apiInterface.getUserRepos(u.getLogin());
//                        reposCall.enqueue(new Callback<List<Repo>>() {
//                            @Override
//                            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                                if(response.code() == 200){
//                                    Log.d("SearchUser", "Found Repos");
//                                    u.setlRepo(response.body());
//
//                                    sharedEditor.putString("user", u.getLogin());
//                                    sharedEditor.commit();
//                                    sharedEditor.putBoolean("searched", true);
//                                    sharedEditor.commit();
//
//                                    Intent intent = new Intent(getApplicationContext(), UserResultActivity.class);
//                                    startActivity(intent);
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<List<Repo>> call, Throwable t) {
//
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.d("ERROR", "FAILURE");
//                }
//            });
            Intent intent = new Intent(getApplicationContext(), Loader.class);
            intent.putExtra("username", usernameTxt);
            startActivity(intent);
        }
    }
}