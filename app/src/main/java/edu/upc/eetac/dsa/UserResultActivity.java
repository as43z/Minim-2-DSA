package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.instances.instanceUser;
import edu.upc.eetac.dsa.models.Repo;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserResultActivity extends AppCompatActivity {

    private User user;
    private ImageView userIcon;
    private TextView userLabel;
    private TextView labelView;
    private TextView userFollowers;
    private TextView fullName;
    private APIInterface apiInterface;

    //recycle
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        user = instanceUser.getInstance().getUser();

        userIcon = (ImageView) findViewById(R.id.userIcon);
        userLabel = findViewById(R.id.userLogin);
        labelView = findViewById(R.id.labelView);
        fullName = findViewById(R.id.fullName);
        userFollowers = findViewById(R.id.followTag);
        recyclerView = (RecyclerView) findViewById(R.id.repoList);

        userLabel.setText(user.getLogin());
        fullName.setText(user.getName());
        userFollowers.setText("Following: " + user.getFollowing() + "  Followers: " + user.getFollowers());

        Context context = userIcon.getContext();
        Picasso.with(context).load(user.getAvatar_url()).into(userIcon);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Repo> inp = user.getlRepo();
        mAdapter = new RecyclerAdapter(inp);
        recyclerView.setAdapter(mAdapter);
    }
}