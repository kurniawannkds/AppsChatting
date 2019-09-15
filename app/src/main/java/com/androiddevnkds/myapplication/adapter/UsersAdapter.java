package com.androiddevnkds.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private Context context;
    private List<UserModel> userModels;

    public UsersAdapter(Context context, List<UserModel> userModels){
        this.context = context;
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_satuan,parent);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        String urlImage = "";
        if(userModels.get(position).getUriImage()!=null && !userModels.get(position).getUriImage().equalsIgnoreCase("")){
            urlImage = userModels.get(position).getUriImage();
            try {
                Picasso.with(context).load(urlImage).into(holder.circleImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String userName = userModels.get(position).getName();
        String userEmail = userModels.get(position).getEmail();

        holder.textViewName.setText(userName);
        holder.textViewEmail.setText(userEmail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"HALO",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        AutofitTextView textViewName, textViewEmail;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = (CircleImageView) itemView.findViewById(R.id.iv_profile);
            textViewName = (AutofitTextView) itemView.findViewById(R.id.tv_name_user);
            textViewEmail = (AutofitTextView) itemView.findViewById(R.id.tv_user_email);
        }
    }
}
