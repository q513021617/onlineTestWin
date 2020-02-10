package model;

public class MsgBean {
    //    状态：请求成功或者失败
    private boolean status;
    //    提示信息
    private String msg;
    //    数据
    private Object data;

    public MsgBean(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public MsgBean(){

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
