package skk.controller;

import org.springframework.web.bind.annotation.*;
import skk.util.Response;
import skk.util.SuccessResponse;

class SignupRequestBody {
    public String username;
    public String password;
    public String role;
}

@RestController
@RequestMapping("/signup")
public class SignupController {

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Response signup (@RequestBody SignupRequestBody requestBody) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Response r = new SuccessResponse("注册成功"+requestBody.username);
        return r;
    }



}
