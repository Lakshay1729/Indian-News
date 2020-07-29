package com.example.indiannew;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.example.hp.indiannews.AlbumsAdapter */
public class AlbumsAdapter extends Adapter<AlbumsAdapter.MyViewHolder> {
    /* access modifiers changed from: private */
    public NewsFragment fragment;
    /* access modifiers changed from: private */
    public ArrayList<HashMap<String, String>> list;
    public MainActivity mContext;
    /* renamed from: j */
    int f2j;
    private LinearLayout lyt_parent;

    public AlbumsAdapter(Context ctx, ArrayList<HashMap<String, String>> list2) {
        this.mContext = (MainActivity) ctx;
        this.list = list2;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reso, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.heading.setText((CharSequence) ((HashMap) this.list.get(i)).get("title"));
        myViewHolder.date.setText("Dated: ");
        myViewHolder.date.append(list.get(i).get("publishedAt").split("T")[0]);
        String str = "urlToImage";

        this.f2j = i;

        myViewHolder.content.setText("Source: ");
        myViewHolder.content.append((CharSequence) ((HashMap) this.list.get(i)).get("source"));
        myViewHolder.read.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlbumsAdapter albumsAdapter = AlbumsAdapter.this;
                albumsAdapter.fragment = new NewsFragment(albumsAdapter.mContext);
                Bundle bundle = new Bundle();
                String str = "url";
                bundle.putString(str, (String) ((HashMap) AlbumsAdapter.this.list.get(i)).get(str));
                AlbumsAdapter.this.fragment.setArguments(bundle);
                mContext.getSupportFragmentManager().beginTransaction().add((Fragment) AlbumsAdapter.this.fragment, "dialog").commit();
            }
        });
        myViewHolder.share.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                 mContext.startActivity(Intent.createChooser(new Intent().setAction("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", (String) ((HashMap) AlbumsAdapter.this.list.get(i)).get("url")), "Share via"));
            }
        });
        Glide.with(this.mContext).load((String) ((HashMap) this.list.get(i)).get(str)).into(myViewHolder.image);
    }

    public int getItemCount() {
        return this.list.size();
    }

    /* renamed from: com.example.hp.indiannews.AlbumsAdapter$MyViewHolder */
    public class MyViewHolder extends ViewHolder {
        public CardView cardView;
        public TextView content;
        public TextView heading;
        public ImageView image;
        public LinearLayout lyt_parent;
        public AppCompatImageButton read;
        public AppCompatImageButton share;
        public TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.heading = (TextView) itemView.findViewById(R.id.t1);
            this.image = (ImageView) itemView.findViewById(R.id.im1);
            this.content = (TextView) itemView.findViewById(R.id.t2);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.share = (AppCompatImageButton) itemView.findViewById(R.id.share);
            this.read = (AppCompatImageButton) itemView.findViewById(R.id.read);
            this.date=((TextView)itemView.findViewById(R.id.date));
        }
    }
}
