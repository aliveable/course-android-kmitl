package jittakan58070012.kmitl.lab07.classretrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jittakan58070012.kmitl.lab07.classretrofit.MainActivity;
import jittakan58070012.kmitl.lab07.classretrofit.R;

class  Holder extends RecyclerView.ViewHolder{

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
    }
}
public class PostAdapter extends RecyclerView.Adapter<Holder>{

    String[] data = {"https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n5.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n6.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n7.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n8.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n9.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n10.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n11.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n12.jpg",
                    "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n13.jpg"};

    private Context context;
    public PostAdapter(Context context) {
        this.context = context;
    }


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
        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        //how much data you have
        return data.length;
    }
}
