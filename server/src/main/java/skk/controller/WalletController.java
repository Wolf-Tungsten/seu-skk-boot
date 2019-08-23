package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import skk.entity.Wallet;
import skk.repository.WalletRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

class WalletSignupRequestBody {
    public String accountName;
    public String password;
    public String email;
}

@RestController
@RequestMapping("/wallet")
public class WalletController
{
    @Autowired
    private WalletRepository walletRepository;

    @PostMapping(consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    @RequestMapping("/signin")
    public @ResponseBody
    Response signin (@RequestBody WalletSignupRequestBody requestBody) {
        //钱包注册逻辑
        Wallet newWallet = new Wallet();
        newWallet.accountName = requestBody.accountName;
        newWallet.email = requestBody.email;
        newWallet.password = requestBody.password;
        newWallet.balance = 0;

        walletRepository.save(newWallet);

        SuccessResponse r = new SuccessResponse("钱包注册成功");
        return r;
    }

//    @RequestMapping("/show")
//    public @ResponseBody
//    Response show()
//    {
//        //查询账户的余额
//
//    }

}
