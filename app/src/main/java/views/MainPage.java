package views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jincompany.accountbook.R;

import java.text.NumberFormat;
import java.util.Locale;

import controller.SmsController;

public class MainPage extends AppCompatActivity {

    private int expenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
         * 첫 화면 관련
         * 1. 카드 Sms 발신 번호를 등록하지 않은 경우
         * 2. 카드 Sms 발신 번호를 등록한 경우
         */

        // 1. 발신번호가 등록되어 있지 않으면 발신번호를 등록할 수 있는 페이지로 이동
        if (!isDispatchPhoneNum()) {
            Toast.makeText(getApplicationContext(), "발신번호가 등록되어 있지 않으므로 등록 페이지로 이동", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainPage.this, registerPhoneNum.class));
        }

        // 2. 발신번호가 등록 되어 있는 경우 해당 번호의 정보를 가져옴
        SmsController smsController = new SmsController(getApplicationContext());
        // 지출 금액
        expenditure = smsController.getExpenditure();
        // 지출 금액 표시
        ((TextView) findViewById(R.id.expenditure_var_text)).setText(getCurrencyFormat(expenditure));

        // 달력 클릭
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 달력 날짜 클릭시 해당 일의 지출 내역 표시 페이지 이동

                Toast.makeText(getApplicationContext(), "" + dayOfMonth, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 발신번호가 있는지 db에 접속하여 검사.
     */
    protected boolean isDispatchPhoneNum() {
        //todo 디비 생성 후 작업
        return true;
    }

    /**
     * 총 예산 입력 EditText
     * 1. 입력된 총 예산 화폐단위로 budget_var_text 에 표시
     * 2. 남은 예산(총 예산 - 지출 금액) 화폐단위로 rest_budget_var_text 에 표시
     */
    public void budgetEditTextClicked(View view) {
        // 한달예산 클릭값 가져옮
        String budget_edit_text = ((EditText) findViewById(R.id.budget_edit_text)).getText().toString();
        // 한달 예산 입력값이 있으면
        if (!budget_edit_text.equals("")) {
            int budget = Integer.parseInt(budget_edit_text);
            // 1번
            ((TextView) findViewById(R.id.budget_var_text)).setText(getCurrencyFormat(budget));
            // 2번
            ((TextView) findViewById(R.id.rest_budget_var_text)).setText(getCurrencyFormat(budget - expenditure));
            // 총 예산 입력 EditText 초기화
            ((EditText) findViewById(R.id.budget_edit_text)).setText("");
        }
    }

    /**
     * int 값을 화페 단위로 변경
     * todo 유틸로 이동
     */
    private String getCurrencyFormat(int won) {
        return NumberFormat.getCurrencyInstance(Locale.KOREA).format(won);
    }
}
