package com.example.samuray.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Integer> mId = new ArrayList<>();
    private ArrayList<Integer> mBrandId = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();


    private Context mContext;

    public RecyclerAdapter(Context context, ArrayList<Integer> idShowPost, ArrayList<String> Name, ArrayList<Integer> idBrand) {
        mId = idShowPost;
        mBrandId = idBrand;
        mName = Name;

        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_show_post, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.BrandNameTV.setText(String.valueOf(mName.get(position)));

        final Integer id_res = mBrandId.get(position);
        final String  name_res = mName.get(position);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                if (mContext instanceof Activity)
                    intent.putExtra("Brand_id_extra", id_res);
                    intent.putExtra("Brand_name_extra", name_res);
                    ((Activity) mContext).setResult(Activity.RESULT_OK, intent);
                    ((Activity) mContext).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mId.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView BrandNameTV;


        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            BrandNameTV = itemView.findViewById(R.id.brand_name);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
