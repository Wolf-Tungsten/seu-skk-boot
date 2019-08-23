package skk.util;

public class SuccessResponse extends Response{
    public SuccessResponse(Object result){
        this.result = result;
        this.reason = null;
        this.success = true;
    }
}
