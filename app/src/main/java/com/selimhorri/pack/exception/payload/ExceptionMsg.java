package com.selimhorri.pack.exception.payload;

public class ExceptionMsg {

    private String msg;
    private Throwable throwable;
    private String status;
    private String timestamp;

    public ExceptionMsg() {

    }

    public ExceptionMsg(String msg, String status, String timestamp) {
        this.msg = msg;
        this.status = status;
        this.timestamp = timestamp;
    }

    public ExceptionMsg(String msg, Throwable throwable, String status, String timestamp) {
        this.msg = msg;
        this.throwable = throwable;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ExceptionMsg{" +
                "msg='" + msg + '\'' +
                ", throwable=" + throwable +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
