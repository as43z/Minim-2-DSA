package edu.upc.eetac.dsa;

import java.util.List;

import edu.upc.eetac.dsa.models.Repo;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("{user}/repos")
    Call<List<Repo>> getUserRepos(@Path("user") String user);
}
