package com.pars.company.Admin_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pars.company.DetailActivity;
import com.pars.company.Model.History;
import com.pars.company.R;
import com.pars.company.SavePref;
import com.pars.company.Shamsi;

import java.util.List;

/**
 * Created by Maziar on 11/25/2017.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder> {

    private int lastPosition = -1;
    private boolean iscolor = true;
    private List<History> itemList;
    private Context mContext;
    private Typeface font;
    private SavePref save;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_namayandeh, tv_subject, tv_date;
        public ImageView img_history;
        public CardView click_linear;

        public MyViewHolder(View view) {
            super(view);

            this.img_history = (ImageView) itemView.findViewById(R.id.img_history);

            this.tv_date = (TextView) itemView.findViewById(R.id.tv_history_time);
            this.tv_namayandeh = (TextView) itemView.findViewById(R.id.tv_history_namayandeh);
            this.tv_subject = (TextView) itemView.findViewById(R.id.tv_history_subject);

            this.click_linear = (CardView) itemView.findViewById(R.id.click_history_cardview);

        }
    }


    public HistoryRecyclerAdapter(Context context, List<History> itemList) {
        this.itemList = itemList;
        this.mContext = context;
        save = new SavePref(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final History Item = itemList.get(position);


        int year = Integer.parseInt(Item.getDate().substring(0, 4));
        int month = Integer.parseInt(Item.getDate().substring(5, 7));
        int day = Integer.parseInt(Item.getDate().substring(8, 10));

        Shamsi conDate = new Shamsi();
        conDate.GregorianToPersian(year, month, day);

        Glide.with(mContext).load(Item.getImg()).into(holder.img_history);
        holder.tv_date.setText("زمان: "+conDate.toString() + " : " + Item.getDate().substring(11, 16));
        holder.tv_namayandeh.setText("نماینده: " + Item.getNamayandeh());

        String subject = "";
        switch (Item.getId_group()) {
            case "0":
                subject = "مالیاتی";
                break;
            case "1":
                subject = "حقوقی";
                break;
            case "2":
                subject = "حسابداری";
                break;
            case "3":
                subject = "آموزشی";
                break;
            case "4":
                subject = "حسابرسی";
                break;
            case "5":
                subject = "مشاوره";
                break;
            case "6":
                subject = "رفاهی";
                break;

            default:
                subject = "نامشخص";
        }
        holder.tv_subject.setText(subject);

        holder.click_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("date", Item.getDate());
                intent.putExtra("group", Item.getId_group());
                intent.putExtra("img", Item.getImg());
                intent.putExtra("namayandeh", Item.getNamayandeh());
                intent.putExtra("id", Item.getId());

                intent.putExtra("am_1", Item.getAm_1());
                intent.putExtra("am_2", Item.getAm_2());
                intent.putExtra("am_3", Item.getAm_3());
                intent.putExtra("am_4", Item.getAm_4());
                intent.putExtra("am_5", Item.getAm_5());
                intent.putExtra("he_1", Item.getHe_1());
                intent.putExtra("he_2", Item.getHe_2());
                intent.putExtra("hesab_1", Item.getHesab_1());
                intent.putExtra("hesab_2", Item.getHesab_2());
                intent.putExtra("hesab_3", Item.getHesab_3());
                intent.putExtra("ho_1", Item.getHo_1());
                intent.putExtra("ho_2", Item.getHo_2());
                intent.putExtra("ma_1", Item.getMa_1());
                intent.putExtra("ma_2", Item.getMa_2());
                intent.putExtra("ma_3", Item.getMa_3());
                intent.putExtra("ma_4", Item.getMa_4());
                intent.putExtra("ma_5", Item.getMa_5());
                intent.putExtra("mo_1", Item.getMo_1());
                intent.putExtra("mo_2", Item.getMo_2());
                intent.putExtra("mo_3", Item.getMo_3());
                intent.putExtra("mo_4", Item.getMo_4());
                intent.putExtra("mo_5", Item.getMo_5());
                intent.putExtra("mo_6", Item.getMo_6());
                intent.putExtra("mo_7", Item.getMo_7());
                intent.putExtra("mo_8", Item.getMo_8());
                intent.putExtra("re_1", Item.getRe_1());
                intent.putExtra("re_2", Item.getRe_2());
                intent.putExtra("re_3", Item.getRe_3());
                intent.putExtra("re_4", Item.getRe_4());
                intent.putExtra("re_5", Item.getRe_5());
                intent.putExtra("re_6", Item.getRe_6());

                mContext.startActivity(intent);
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


    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //holder.card_item.clearAnimation();
    }

}
