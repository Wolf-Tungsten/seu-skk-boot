package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.User;
import skk.repository.UserRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;
import java.util.UUID;

class SigninRequestBody {
    public String username;
    public String password;
}

class SigninResponseBody {
    public String role;
    public String token;
}


@RestController
@CrossOrigin
@RequestMapping("/signin")
public class SigninController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Response signin (@RequestBody SigninRequestBody requestBody) {

        // 检查用户是否存在
        List<User> users = userRepository.findByUsername(requestBody.username);
        if(users.size() != 1){
            FailedResponse r = new FailedResponse("用户不存在");
            return r;
        }

        // 检查密码
        User user = users.get(0);
        if(!user.password.equals(requestBody.password)){
            FailedResponse r = new FailedResponse("密码错误");
            return r;
        }

        user.token = UUID.randomUUID().toString();
        userRepository.save(user);

        SigninResponseBody rb = new SigninResponseBody();
        rb.role = user.role;
        rb.token = user.token;

        SuccessResponse r = new SuccessResponse(rb);
        return r;
    }

}
