package jittakan58070012.kmitl.lab07.lazyinstagram.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jittakan58070012.kmitl.lab07.lazyinstagram.Model.postsModel;
import jittakan58070012.kmitl.lab07.lazyinstagram.R;

class  Holder extends RecyclerView.ViewHolder{

    public ImageView image;
    public TextView like;
    public TextView Comments;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageProfileSidebar);
        like = itemView.findViewById(R.id.Like);
        Comments = itemView.findViewById(R.id.Comment);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

    private List<postsModel> postdata ;
    private Activity activity;
    private Context context;

    public PostAdapter(Activity activity,List<postsModel> postdata) {
        this.activity = activity;
        this.postdata = new ArrayList<>();
        setPostdata(postdata);
    }

    public void setPostdata(List<postsModel> postdata) {
        this.postdata = postdata;
    }


//    public PostAdapter(Context context) {
//        this.context = context;
//    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(activity).load(postdata.get(position).getUrl()).into(image);

        holder.like.setText("  "+String.valueOf(this.postdata.get(position).getLike()));
        holder.Comments.setText("  " + String.valueOf(this.postdata.get(position).getComment()));
    }

    @Override
    public int getItemCount() {
        //how much data you have
        return postdata.size();
    }

}
