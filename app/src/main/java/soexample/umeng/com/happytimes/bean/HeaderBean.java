package soexample.umeng.com.happytimes.bean;

/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public class HeaderBean {
    private String sessionId;
    private int userId;

    public HeaderBean(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
}
