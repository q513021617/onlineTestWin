package model;

public class Testpaper {

    private String id;

    private String papername;

    private String topictlist;

    private String totaltime;

    private String subject;

    public Testpaper() {

    }

    public Testpaper(String id, String papername, String topictlist, String totaltime, String subject) {
        this.id = id;
        this.papername = papername;
        this.topictlist = topictlist;
        this.totaltime = totaltime;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public String getTopictlist() {
        return topictlist;
    }

    public void setTopictlist(String topictlist) {
        this.topictlist = topictlist == null ? null : topictlist.trim();
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
