package skk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class WalletSighupRequestBody{
    public String username;
    public String password;
    public String email;
}

@RestController
@RequestMapping("/wallet")
public class WalletController
{
}
