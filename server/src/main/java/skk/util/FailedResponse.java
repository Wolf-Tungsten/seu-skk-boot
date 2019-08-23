package skk.util;

public class FailedResponse extends Response {
    FailedResponse(String reason){
        this.result = null;
        this.reason = reason;
    }
}
