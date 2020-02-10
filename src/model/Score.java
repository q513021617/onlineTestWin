package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Score {
    private String id;

    private String stuid;

    private String subject;

    private Integer testscore;
    private long time;
    private String timestr;

    public Score() {

    }

    public Score(String id, String stuid, String subject, Integer testscore, long time) {
        this.id = id;
        this.stuid = stuid;
        this.subject = subject;
        this.testscore = testscore;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTestscore() {
        return testscore;
    }

    public void setTestscore(Integer testscore) {
        this.testscore = testscore;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTimestr() {

        return this.timestr;
    }

    public void setTimestr(String timestr) {
        this.timestr = timestr;
    }

}
