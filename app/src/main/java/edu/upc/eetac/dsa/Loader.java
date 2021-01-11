package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import edu.upc.eetac.dsa.instances.instanceUser;
import edu.upc.eetac.dsa.models.Repo;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loader extends AppCompatActivity {

    private ProgressBar loader;
    private String username;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        loader = (ProgressBar) findViewById(R.id.loader);
        username = getIntent().getExtras().getString("username");
        if(username == null){
            Intent intent = new Intent(getApplicationContext(), UserResultActivity.class);
            startActivity(intent);
        } else {
            searchUser();
        }
    }

    public void searchUser(){
        if(username.equals("")){
            Log.d("ERROR", "username cannot be empty");
        } else {
            Log.d("SearchUser", "Searching user " + username);

            Call<User> userCall = apiInterface.getUser(username);
            userCall.enqueue(new Callback<User>(){
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code() == 200){
                        User u = response.body();
                        instanceUser.getInstance().setUser(u);

                        String repos_url = u.getRepos_url();
                        Log.d("SearchUser", "searching for repos in " + repos_url);
                        Call<List<Repo>> reposCall = apiInterface.getUserRepos(u.getLogin());
                        reposCall.enqueue(new Callback<List<Repo>>() {
                            @Override
                            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                                if(response.code() == 200){
                                    Log.d("SearchUser", "Found Repos");
                                    u.setlRepo(response.body());

                                    Intent intent = new Intent(getApplicationContext(), UserResultActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Repo>> call, Throwable t) {
                                Log.d("ERROR", "FAILURE");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("could", false);
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("ERROR", "FAILURE");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("could", false);
                    startActivity(intent);
                }
            });
        }
    }
}