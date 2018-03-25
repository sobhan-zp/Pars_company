package com.pars.company.Admin_fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pars.company.ChatActivity;
import com.pars.company.MainActivity;
import com.pars.company.Model.Users;
import com.pars.company.Network.AppController;
import com.pars.company.R;
import com.pars.company.SavePref;

import java.util.List;

import static com.pars.company.MainActivity.pager;
import static com.pars.company.MainActivity.tv_receive;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {

    private int lastPosition = -1;
    private boolean iscolor = true;
    private List<Users> itemList;
    private Context mContext;
    private Typeface font;
    private SavePref save;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name, tv_company, tv_row;
        public LinearLayout ll_bg;

        public MyViewHolder(View view) {
            super(view);

            this.tv_row = (TextView) itemView.findViewById(R.id.tv_row_allSt);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name_allSt);
            this.tv_company = (TextView) itemView.findViewById(R.id.tv_username_allSt);
            this.ll_bg = (LinearLayout) view.findViewById(R.id.ll_bg_row_cal);

        }
    }


    public UserRecyclerAdapter(Context context, List<Users> itemList) {
        this.itemList = itemList;
        this.mContext = context;
        save = new SavePref(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_all_user_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Users Item = itemList.get(position);

        holder.tv_row.setText(String.valueOf(position + 1));
        holder.tv_name.setText(Item.getFullname());
        holder.tv_company.setText(Item.getCompany_name());

        if (iscolor) {
            holder.ll_bg.setBackgroundColor(Color.GRAY);
            iscolor = false;
        } else {
            holder.ll_bg.setBackgroundColor(Color.LTGRAY);
            iscolor = true;
        }

        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(mContext, Item);


            }
        });

        //setAnimation(holder.card_item);
        //setScaleAnimation(holder.click_discount_cardview);
        //setFadeAnimation(holder.card_item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /*private void setAnimation(View viewToAnimate) {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.up_from_bottom);
        animation.setDuration(1000);
        viewToAnimate.startAnimation(animation);


    }*/


    private void showDialog(Context context, final Users item){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);

        Button sendfile = (Button) dialog.findViewById(R.id.btn_dialog_sendfile);
        Button sendmsg = (Button) dialog.findViewById(R.id.btn_dialog_sendmsg);
        Button recmsg = (Button) dialog.findViewById(R.id.btn_dialog_recfile);
        TextView tv_namayandeh = (TextView) dialog.findViewById(R.id.tv_dialog_namayandeh);
        tv_namayandeh.setText(item.getFullname());

        save.save(AppController.SAVE_USER_ID_FOR_SENDFILE, item.getId());

        sendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.setVisibiltyBottomBar(MainActivity.tv_sendFile);
                Animation animation = new TranslateAnimation(0, 0, 15, 0);
                Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
                animation.setDuration(300);
                animation1.setDuration(200);
                MainActivity.img_sendFile.startAnimation(animation);
                MainActivity.tv_sendFile.startAnimation(animation1);

                pager.setCurrentItem(0);

                dialog.dismiss();
            }
        });

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ChatActivity.class));
                dialog.dismiss();
            }
        });


        recmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.setVisibiltyBottomBar(tv_receive);
                Animation animation = new TranslateAnimation(0, 0, 15, 0);
                Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
                animation.setDuration(300);
                animation1.setDuration(200);
                MainActivity.img_receive.startAnimation(animation);
                MainActivity.tv_receive.startAnimation(animation1);

                pager.setCurrentItem(1);

                dialog.dismiss();
            }
        });

        dialog.show();

    }





    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //holder.card_item.clearAnimation();
    }



}
