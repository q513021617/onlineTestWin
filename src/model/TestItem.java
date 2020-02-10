package model;

public class TestItem {
    String testid;
    String testingname;



    public TestItem() {
    }

    public TestItem(String testid, String testingname) {
        this.testid = testid;
        this.testingname = testingname;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTestingname() {
        return testingname;
    }

    public void setTestingname(String testingname) {
        this.testingname = testingname;
    }

    @Override
    public String toString() {
        return testingname;
    }
}
