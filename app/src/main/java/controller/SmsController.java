package controller;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.DTO.SmsDTO;
import model.Sms;

public class SmsController {

    private Sms sms;
    private List<SmsDTO> smsDTO_list;

    public SmsController(Context applicationContext) {
        sms = new Sms("content://sms/inbox", applicationContext);
        smsDTO_list = sms.selectSmsData();
    }

    public int getExpenditure() {
        // sms 문자 내용중 '300,000won' 이 있으면 숫자 300,000을 가져오는 패턴
        Pattern p = Pattern.compile("(\\S*)won");
        int expenditure = 0;
        for (SmsDTO e : smsDTO_list) {
            Matcher m = p.matcher(e.getContent());
            while (m.find()) {
                // 지출 합계 계산
                expenditure += Integer.parseInt(m.group(1).replaceAll(",", ""));
                Log.d("jin", m.group(1));
            }
        }
        return expenditure;
    }
}
