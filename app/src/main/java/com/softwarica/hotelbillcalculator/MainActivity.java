package com.softwarica.hotelbillcalculator;



import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView checkindate, checkoutdate, tvprice, tvresult;
    Spinner sroom;
    Button btncal;
    EditText etadult, etChild, etRoom;
    int year2, year3;
    int month2, month3;
    int day2, day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkindate = findViewById(R.id.checkindate);
        checkoutdate = findViewById(R.id.checkoutdate);
        sroom = findViewById(R.id.sroom);
        etadult = findViewById(R.id.etadult);
        etChild = findViewById(R.id.etchildren);
        etRoom = findViewById(R.id.etroom);
        tvprice = findViewById(R.id.tvprice);
        btncal = findViewById(R.id.btncal);
        tvresult = findViewById(R.id.tvresult);


        checkindate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate1();
            }
        });

        checkoutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate2();
            }
        });
        String room[] = {"Select room type", "Deluxe -Rs. 2000", "Presidential -Rs. 5000", "Premium- Rs. 4000"};
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,room);
        sroom.setAdapter(adapter);

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(checkindate.getText()))
                {
                    checkindate.setError("Please enter Check in Date");
                    return;
                }

                if(TextUtils.isEmpty(checkoutdate.getText()))
                {
                    checkoutdate.setError("Please enter Check out Date");
                    return;
                }


                if (sroom.getSelectedItem()=="Select room type")
                {

                }
                if (TextUtils.isEmpty(etadult.getText())) {
                    etadult.setError("Enter no of Adults");
                    return;
                }
                if (TextUtils.isEmpty(etChild.getText())) {
                    etChild.setError("Enter no of childs");
                    return;
                }
                if (TextUtils.isEmpty(etRoom.getText())) {
                    etRoom.setError("Enter no of rooms");
                    return;
                }


                if (sroom.getSelectedItem() == "Deluxe -Rs. 2000") {
                    tvprice.setText("2000");


                }
                if (sroom.getSelectedItem() == "Presidential -Rs. 5000") {
                    tvprice.setText("5000");


                }
                if (sroom.getSelectedItem() == "Premium- Rs. 4000") {
                    tvprice.setText("4000");

                }

                int adult, child, room;
                double total, price, vat, GrossTotal;

                adult = Integer.parseInt(etadult.getText().toString());
                child = Integer.parseInt(etChild.getText().toString());
                room = Integer.parseInt(etRoom.getText().toString());

                Calendar cal1=Calendar.getInstance();
                Calendar cal2=Calendar.getInstance();
                cal1.set(year2,month2,day2);
                cal2.set(year3,month3,day3);
                long millis1=cal1.getTimeInMillis();
                long millis2= cal2.getTimeInMillis();
                long diff=millis2-millis1;
                long diffDays=(diff/86400000);

                price = Integer.parseInt(tvprice.getText().toString());
                total = price * room * diffDays;
                vat = 0.13 * total;
                GrossTotal = total + vat;

                if (diff <=0)
                {
                    checkindate.setError("Checkin date is invalid");
                    checkoutdate.setError("Checkout date is invalid");
                    tvresult.setText("Invalid Date");
                    return;
                }
                else
                {
                    tvresult.setText("Total: Rs." + total + "\n" + "Vat Rs.:" + vat + "\n" + "Gross Total: Rs." + GrossTotal);
                }





            }
        });

    }

    private void loadDatePickerDate2() {
        final Calendar c1=Calendar.getInstance();
        int year= c1.get(Calendar.YEAR);
        final int month = c1.get(Calendar.MONTH);
        int day= c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month, int dayOfMonth1) {
                month=month+1;
                String date = "Checking out Date: "+month + "/" + dayOfMonth1 + "/" + year1;

                month3=month;
                day3=dayOfMonth1;
                year3=year1;
                checkoutdate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();


    }

    private void loadDatePickerDate1() {
        final Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = "Checking Date: "+month + "/" + dayOfMonth + "/" + year;
                month2=month;
                day2=dayOfMonth;
                year2=year;
                checkindate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();



    }

}