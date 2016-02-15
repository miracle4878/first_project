package model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import model.DTO.SmsDTO;

public class Sms {

    Cursor cur;

    public Sms(String smsURI, Context context) {
        cur = context.getContentResolver().query(Uri.parse(smsURI), null, null, null, null);
    }

    public List<SmsDTO> selectSmsData() {
        List<SmsDTO> smsDTO_list = new ArrayList();

        while (cur.moveToNext()) {
            SmsDTO smsDTO = new SmsDTO();

            smsDTO.setAddress(cur.getString(2));
            smsDTO.setDate_sent(cur.getInt(5));
            smsDTO.setContent(cur.getString(12));

            smsDTO_list.add(smsDTO);
        }
        return smsDTO_list;
    }
}
