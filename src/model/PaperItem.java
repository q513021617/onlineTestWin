package model;

public class PaperItem {

    String subject;
    String papername;

    public PaperItem() {
    }

    public PaperItem(String subject, String papername) {
        this.subject = subject;
        this.papername = papername;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    @Override
    public String toString() {
        return papername;
    }
}