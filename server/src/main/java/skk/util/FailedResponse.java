package skk.util;

public class FailedResponse extends Response {
    public FailedResponse(String reason){
        this.result = null;
        this.reason = reason;
        this.success = false;
    }
}
