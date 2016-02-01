package model.DTO;

public class SmsDTO {
    private String address;   // 발신번호
    private int date_sent;    // 수신일
    private String content;   // 문자 내용

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(int date_sent) {
        this.date_sent = date_sent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
