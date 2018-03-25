package com.pars.company.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pars.company.R;


public class RecyclerAdapter_AllUser extends RecyclerView.Adapter<RecyclerAdapter_AllUser.ViewHolder> {


    private String[] row =
            {
                    "1",
                    "2",
                    "3",
                    "4",
            };

    private String[] name =
            {
                    "علی ناصری",
                    "سجاد ایزدی",
                    "رحمان شجاعی",
                    "رضا نیکخواه",
            };

    private String[] username =
            {
                    "ali.s",
                    "sajad_izadi",
                    "r.sh",
                    "nik.reza",
            };


    boolean iscolor = true;
    MaterialDialog student;
    Context mContext;
    private Button btn_info_alertStudent , btn_voice_alertStudent , btn_message_alertStudent;

    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public TextView row, name, username;
        private LinearLayout ll_allStudent;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext =itemView.getContext();
            row = (TextView) itemView.findViewById(R.id.tv_row_allSt);
            name = (TextView) itemView.findViewById(R.id.tv_name_allSt);
            username = (TextView) itemView.findViewById(R.id.tv_username_allSt);

            ll_allStudent = (LinearLayout) itemView.findViewById(R.id.ll_allStudent);
            ll_allStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickStudent();
                }
            });


        }
    }


    private void ClickStudent() {
        student = new MaterialDialog.Builder(mContext)
                .customView(R.layout.alert_each_user, false)
                .show();

        btn_message_alertStudent = (Button) student.findViewById(R.id.btn_message_alertStudent);
        btn_voice_alertStudent = (Button) student.findViewById(R.id.btn_voice_alertStudent);
        btn_info_alertStudent = (Button) student.findViewById(R.id.btn_info_alertStudent);


        btn_message_alertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new MaterialDialog.Builder(mContext)
                        .title(R.string.input)
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_NORMAL)
                        .positiveText("فرستادن")
                        .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(mContext , "soon" , Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


        btn_voice_alertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext , "soon" , Toast.LENGTH_SHORT).show();
                student.dismiss();
            }
        });


        btn_info_alertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.dismiss();
            }
        });
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_all_user_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);


        final LinearLayout layout = (LinearLayout) v.findViewById(R.id.ll_bg_row_cal);

        if (iscolor) {
            layout.setBackgroundColor(Color.GRAY);
            iscolor = false;
        } else {
            layout.setBackgroundColor(Color.LTGRAY);
            iscolor = true;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.row.setText(row[i]);
        viewHolder.name.setText(name[i]);
        viewHolder.username.setText(username[i]);
        //viewHolder.filish.setText(finish[i]);
    }

    @Override
    public int getItemCount() {
        return row.length;
    }

}