package com.pars.company;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pars.company.Model.History;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    CardView card_malyat, card_hoghoghi, card_hesabdari, card_amozeshi,
            card_hesabrasi, card_moshavere, card_refahi;
    ImageView img_detail;
    TextView tv_one_1, tv_one_2, tv_one_3, tv_one_4, tv_one_5;
    TextView tv_two_1, tv_two_2, tv_three_1, tv_three_2;
    TextView tv_four_1, tv_four_2, tv_four_3, tv_four_4, tv_four_5;
    TextView tv_five_1, tv_five_2, tv_five_3;
    TextView tv_six_1, tv_six_2, tv_six_3, tv_six_4, tv_six_5, tv_six_6, tv_six_7, tv_six_8;
    TextView tv_seven_1, tv_seven_2, tv_seven_3, tv_seven_4, tv_seven_5, tv_seven_6;


    String[] category_item = {"مالیاتی", "حقوقی", "حسابداری", "آموزشی", "حسابرسی", "مشاوره", "رفاهی"};

    String[] arzesh_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] mashaghel_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] hoghogh_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] fasli_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] tanzim_item = {"اظهار نامه مشاغل", "اظهار نامه ارزش افزوده", "گزارش فصلي"};

    String[] mali_item = {"چك", "سفته", "قراردادهاي مالي"};
    String[] shora_item = {"كار و كارگري", "تامين اجتماعي", "ديوان عدالت اداري", "بيمه بيكاري"};

    String[] daftar_item = {"دفاتر ويژه مشاغل", "تنظيم اسناد مالي"};
    String[] ekhtelaf_item = {"آری", "خیر"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindView();

        History item = new History();

        Bundle ex = getIntent().getExtras();
        if (ex != null) {

            item.setImg(ex.getString("img"));
            item.setDate(ex.getString("date"));

            switch (ex.getString("group")) {
                case "0":
                    card_malyat.setVisibility(View.VISIBLE);
                    tv_one_1.setText("ارزش افزوده: " + ex.getString("ma_1"));
                    tv_one_2.setText("مشاغل: " + ex.getString("ma_2"));
                    tv_one_3.setText("حقوق: " + ex.getString("ma_3"));
                    tv_one_4.setText("سامانه گزارش فصلی: " + ex.getString("ma_4"));
                    tv_one_5.setText("تنظیم اظهارنامه: " + tanzim_item[Integer.parseInt(ex.getString("ma_5"))]);

                    break;
                case "1":
                    card_hoghoghi.setVisibility(View.VISIBLE);
                    tv_two_1.setText("ارزش افزوده: " + mali_item[Integer.parseInt(ex.getString("ho_1"))]);
                    tv_two_2.setText("مشاغل: " + shora_item[Integer.parseInt(ex.getString("ho_2"))]);

                    break;
                case "2":
                    card_hesabdari.setVisibility(View.VISIBLE);
                    tv_three_1.setText("دفتر نویسی: " + daftar_item[Integer.parseInt(ex.getString("he_1"))]);
                    tv_three_2.setText("رفع اختلافات مالی: " + ekhtelaf_item[Integer.parseInt(ex.getString("he_2"))]);

                    break;
                case "3":
                    card_amozeshi.setVisibility(View.VISIBLE);
                    tv_four_1.setText("آموزش حسابداری مالی: " + (ex.getString("am_1").equals("1")? " بله " : " خیر "));
                    tv_four_2.setText("آموزش مالیات ارزش افزوده: " + (ex.getString("am_2").equals("1")? " بله " : " خیر "));
                    tv_four_3.setText("آموزش دوره مهارت کارآفرینی: " + (ex.getString("am_3").equals("1")? " بله " : " خیر "));
                    tv_four_4.setText("آموزش بازاریابی: " + (ex.getString("am_4").equals("1")? " بله " : " خیر "));
                    tv_four_5.setText("آموزش بیمه: " + (ex.getString("am_5").equals("1")? " بله " : " خیر "));

                    break;
                case "4":
                    card_hesabrasi.setVisibility(View.VISIBLE);
                    tv_five_1.setText("ممیزی و تایید گزارش های مالی: " + (ex.getString("hesab_1").equals("1")? " بله " : " خیر "));
                    tv_five_2.setText("تایید استاد و رفع اختلاف بین شرکا: " + (ex.getString("hesab_2").equals("1")? " بله " : " خیر "));
                    tv_five_3.setText("تعیین ارزش قدر السهم شرکا: " + (ex.getString("hesab_3").equals("1")? " بله " : " خیر "));

                    break;
                case "5":
                    card_moshavere.setVisibility(View.VISIBLE);
                    tv_six_1.setText("مشاوره حقوقی: " + (ex.getString("mo_1").equals("1")? " بله " : " خیر "));
                    tv_six_2.setText("مشاوره مالی: " + (ex.getString("mo_2").equals("1")? " بله " : " خیر "));
                    tv_six_3.setText("مشاوره فروش: " + (ex.getString("mo_3").equals("1")? " بله " : " خیر "));
                    tv_six_4.setText("مشاوره بازاریابی: " + (ex.getString("mo_4").equals("1")? " بله " : " خیر "));
                    tv_six_5.setText("مشاوره کارو کارگری: " + (ex.getString("mo_5").equals("1")? " بله " : " خیر "));
                    tv_six_6.setText("مشاوره بیمه و بازنشستگی: " + (ex.getString("mo_6").equals("1")? " بله " : " خیر "));
                    tv_six_7.setText("مشاوره مالیاتی: " + (ex.getString("mo_7").equals("1")? " بله " : " خیر "));
                    tv_six_8.setText("مشاوره بیمه بازرگانی: " + (ex.getString("mo_8").equals("1")? " بله " : " خیر "));

                    break;
                case "6":
                    card_refahi.setVisibility(View.VISIBLE);
                    tv_seven_1.setText("برگذاری تور های سیاحتی داخلی و خارجی: " + (ex.getString("mo_1").equals("1")? " بله " : " خیر "));
                    tv_seven_2.setText("برگذاری برنامه های تفریحی نظیر(اجرای تئاتر، کنسرت موسیقی، مسابقه ورزشی و...): " + (ex.getString("re_2").equals("1")? " بله " : " خیر "));
                    tv_seven_3.setText("برگذاری جاسات تخصصی در خصوص بازار یابی فروش، هتلداری و ...: " + (ex.getString("re_3").equals("1")? " بله " : " خیر "));
                    tv_seven_4.setText("بهرهمندی از مراکز اقامتی(هتل های طرف قرارداد) تور های سیاحتی(آژانس های طرف قرارداد) باشگاه ها و اسخر های تخصصی طرف قرارداد: " + (ex.getString("re_4").equals("1")? " بله " : " خیر "));
                    tv_seven_5.setText("بهره مندی از تخفیفات ویژه خرید(هایپر مارکت های طرف قرار داد): " + (ex.getString("re_5").equals("1")? " بله " : " خیر "));
                    tv_seven_6.setText("بهره مندی از تخفیفات ویژه خرید(رستوران های طرف قرار داد): " + (ex.getString("re_6").equals("1")? " بله " : " خیر "));

                    break;
            }

        }

        Glide.with(this).load(item.getImg()).into(img_detail);
    }

    private void bindView() {

        card_malyat = (CardView) findViewById(R.id.card_malyat);
        card_hoghoghi = (CardView) findViewById(R.id.card_hoghoghi);
        card_hesabdari = (CardView) findViewById(R.id.card_hesabdari);
        card_amozeshi = (CardView) findViewById(R.id.card_amozeshi);
        card_hesabrasi = (CardView) findViewById(R.id.card_hesabrasi);
        card_moshavere = (CardView) findViewById(R.id.card_moshavereh);
        card_refahi = (CardView) findViewById(R.id.card_refahi);

        img_detail = (ImageView) findViewById(R.id.img_detail);

        tv_one_1 = (TextView) findViewById(R.id.tv_one_1);
        tv_one_2 = (TextView) findViewById(R.id.tv_one_2);
        tv_one_3 = (TextView) findViewById(R.id.tv_one_3);
        tv_one_4 = (TextView) findViewById(R.id.tv_one_4);
        tv_one_5 = (TextView) findViewById(R.id.tv_one_5);

        tv_two_1 = (TextView) findViewById(R.id.tv_two_1);
        tv_two_2 = (TextView) findViewById(R.id.tv_two_2);

        tv_three_1 = (TextView) findViewById(R.id.tv_three_1);
        tv_three_2 = (TextView) findViewById(R.id.tv_three_2);

        tv_four_1 = (TextView) findViewById(R.id.tv_four_1);
        tv_four_2 = (TextView) findViewById(R.id.tv_four_2);
        tv_four_3 = (TextView) findViewById(R.id.tv_four_3);
        tv_four_4 = (TextView) findViewById(R.id.tv_four_4);
        tv_four_5 = (TextView) findViewById(R.id.tv_four_5);

        tv_five_1 = (TextView) findViewById(R.id.tv_five_1);
        tv_five_2 = (TextView) findViewById(R.id.tv_five_2);
        tv_five_3 = (TextView) findViewById(R.id.tv_five_3);

        tv_six_1 = (TextView) findViewById(R.id.tv_six_1);
        tv_six_2 = (TextView) findViewById(R.id.tv_six_2);
        tv_six_3 = (TextView) findViewById(R.id.tv_six_3);
        tv_six_4 = (TextView) findViewById(R.id.tv_six_4);
        tv_six_5 = (TextView) findViewById(R.id.tv_six_5);
        tv_six_6 = (TextView) findViewById(R.id.tv_six_6);
        tv_six_7 = (TextView) findViewById(R.id.tv_six_7);
        tv_six_8 = (TextView) findViewById(R.id.tv_six_8);

        tv_seven_1 = (TextView) findViewById(R.id.tv_seven_1);
        tv_seven_2 = (TextView) findViewById(R.id.tv_seven_2);
        tv_seven_3 = (TextView) findViewById(R.id.tv_seven_3);
        tv_seven_4 = (TextView) findViewById(R.id.tv_seven_4);
        tv_seven_5 = (TextView) findViewById(R.id.tv_seven_5);
        tv_seven_6 = (TextView) findViewById(R.id.tv_seven_6);
    }

    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
