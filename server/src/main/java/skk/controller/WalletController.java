package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.User;
import skk.entity.Wallet;
import skk.repository.UserRepository;
import skk.repository.WalletRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;

class WalletSignupRequestBody {
    public String accountName;
    public String password;
    public String email;
}
class ModifyWalletPwdReqBody{
    public String oldPwd;
    public String newPwd;
}

@RestController
@RequestMapping("/wallet")
public class WalletController
{
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    @PostMapping(consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    @RequestMapping("/signin")
    public @ResponseBody
    Response signin (@RequestHeader(name = "x-skk-token",required = false,defaultValue = "null")String token,
                     @RequestBody WalletSignupRequestBody requestBody) {
        //钱包注册逻辑
        User user = getUserInfo(token);
        if(user == null){
            return new FailedResponse("身份认证失效，请重新登录");
        }
        List<Wallet> wl = walletRepository.findAllByUserid(user.id);
        if(wl.size()!=0)return new FailedResponse("您已有账户");
        Wallet newWallet = new Wallet();
        newWallet.userid = user.id;
        newWallet.accountName = requestBody.accountName;
        newWallet.email = requestBody.email;
        newWallet.password = requestBody.password;
        walletRepository.save(newWallet);

        SuccessResponse r = new SuccessResponse("钱包注册成功");
        return r;
    }
    @PostMapping(consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    @RequestMapping("modifyPwd")
    Response modifyPassWord(@RequestHeader(name = "x-skk-token",required = false,defaultValue = "null")String token,
        @RequestBody ModifyWalletPwdReqBody reqbody){
        User user = getUserInfo(token);
        if(user == null){
            return new FailedResponse("身份认证失效，请重新登录");
        }
        Wallet wallet = walletRepository.findAllByUserid(user.id).get(0);
        if(reqbody.oldPwd.equals(wallet.password))
            wallet.password = reqbody.newPwd;
        else{
            return new FailedResponse("旧密码错误");
        }
        return new SuccessResponse("修改成功!");

    }
    //查询账户的余额,返回一个
    @GetMapping
    @RequestMapping("/show")
    Response show (@RequestHeader(name = "x-skk-token",required = false,defaultValue = "null")String token
                    ) {

        User user = getUserInfo(token);
        if(user == null) {
            return new FailedResponse("身份认证失效，请重新登录");
        }
        List<Wallet> walletList = walletRepository.findAllByUserid(user.id);
        return  new SuccessResponse(walletList.get(0));
    }
}
