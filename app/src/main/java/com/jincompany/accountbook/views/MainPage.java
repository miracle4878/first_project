package com.jincompany.accountbook.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jincompany.accountbook.R;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //발신번호가 등록되어 있지 않으면 발신번호를 등록할 페이지로 이동
        if(!isDispatchNumber()){
            Toast.makeText(getApplicationContext(),"발신번호가 등록되어 있지 않으므로 등록 페이지로 이동",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainPage.this, DispatchNumberRegisterPage.class));
        }

        //발신번호가 등록되어 있으면 해당 번호의 sms 정보를 가져옴


    }

    /*
     * 발신번호가 있는지 db에 접속하여 검사.
     */
    protected boolean isDispatchNumber(){
        //todo 디비 생성 후 작업
        return false;
    }
}
