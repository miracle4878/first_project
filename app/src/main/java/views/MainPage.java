package views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jincompany.accountbook.R;

import java.util.List;

import model.DTO.SmsDTO;
import model.Sms;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
         * 첫 화면 관련
         * 1. 신용 카드 Sms 발신 번호를 등록하지 않은 경우
         * 2. 설치 이후 신용 카드 Sms 발신 번호를 등록한 경우
         */

        // 발신번호가 등록되어 있지 않으면 발신번호를 등록할 페이지로 이동
        if(!isDispatchPhoneNum()){
            Toast.makeText(getApplicationContext(),"발신번호가 등록되어 있지 않으므로 등록 페이지로 이동",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainPage.this, registerPhoneNum.class));
        }

        // 발신번호가 등록되어 있으면 해당 번호의 Sms 정보를 가져옴
        Sms sms = new Sms("content://sms/inbox", getApplicationContext());
        List<SmsDTO> sms_list= sms.selectSmsData();

        // 
    }

    /*
     * 발신번호가 있는지 db에 접속하여 검사.
     */
    protected boolean isDispatchPhoneNum(){
        //todo 디비 생성 후 작업
        return true;
    }
}
