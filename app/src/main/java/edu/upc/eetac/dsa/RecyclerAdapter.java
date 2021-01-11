package edu.upc.eetac.dsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.models.Repo;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<Repo> repoList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView repoName;
        public TextView repoDescription;
        public TextView repoLanguage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            repoName = (TextView) v.findViewById(R.id.repoName);
            repoDescription = (TextView) v.findViewById(R.id.repoDescription);
            repoLanguage = (TextView) v.findViewById(R.id.repoLanguage);
        }

    }

    public RecyclerAdapter (List<Repo> myDataset) {
        this.repoList = myDataset;
        LayoutInflater inflater;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycler_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String name = this.repoList.get(position).getRepoName();
        String description = this.repoList.get(position).getDescription();
        String language = this.repoList.get(position).getLanguage();

        if(description == null){
            description = "No Descripton";
        }

        if(language == null){
            language = "No Language.";
        }

        holder.repoName.setText(name);
        holder.repoDescription.setText("Description: \n" + description);
        holder.repoLanguage.setText("Language: \n" + language);
    }

    @Override
    public int getItemCount() {
        return this.repoList.size();
    }
}
