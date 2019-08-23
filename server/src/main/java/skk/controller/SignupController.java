package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.User;
import skk.repository.UserRepository;
import skk.util.FailedResponse;
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

    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Response signup (@RequestBody SignupRequestBody requestBody) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        // 检查用户名是否存在
        int usernameCount = userRepository.countByUsername(requestBody.username);
        if(usernameCount > 0){
            FailedResponse r = new FailedResponse("用户名已存在");
            return r;
        }

        // 检查密码长度是否符合要求
        if(requestBody.password.length() < 8){
            FailedResponse r = new FailedResponse("密码长度不足8位");
            return r;
        }

        // 检查 role 是否合法
        if(!"BVO".equals(requestBody.role.toUpperCase()) && !"MVO".equals(requestBody.role.toUpperCase())){
            FailedResponse r = new FailedResponse("角色设置不合法");
            return r;
        }

        // 检查无误，开始创建用户
        User newUser = new User();
        newUser.username = requestBody.username;
        newUser.password = requestBody.password;
        newUser.role = requestBody.role;

        userRepository.save(newUser);

        SuccessResponse r = new SuccessResponse("注册成功");
        return r;
    }



}
