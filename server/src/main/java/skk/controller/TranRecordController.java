package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.TranRecord;
import skk.entity.User;
import skk.entity.Wallet;
import skk.repository.TranRecordRepository;
import skk.repository.UserRepository;
import skk.repository.WalletRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.*;

class TranRequestBody{
    public String id;
    public Integer operation;
    public String walletId;
    public Integer cost;
    public Date date;
    public Integer state;
    public String reason;
    public String memo;
    public String password;
}

@RestController
@CrossOrigin
@RequestMapping("/tran")
public class TranRecordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TranRecordRepository tranRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    public @GetMapping(path = "/allTran")
    Response allTran(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<TranRecord> list = tranRepository.findAll();
        list.sort((t1,t2) -> t2.date.compareTo(t1.date));
        SuccessResponse r =new SuccessResponse(list);
        return r;
    }

    public @GetMapping(path = "/findTran")
    Response findTran(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                      @RequestBody TranRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<TranRecord> list = tranRepository.findTranRecordByWalletId(req.walletId);
        list.sort((t1,t2) -> t2.date.compareTo(t1.date));
        SuccessResponse r =new SuccessResponse(list);
        return r;
    }

    public @PostMapping(path = "/addTran")
    Response addTran(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                     @RequestBody TranRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        Wallet w = walletRepository.findById(req.walletId).get();
        if(!req.password.equals(w.password))
            return new FailedResponse("密码错误");
        if(req.cost > w.balance)
            return  new FailedResponse("余额不足");

        TranRecord newTran = new TranRecord();
        newTran.state = 0;
        newTran.date = new Date();
        newTran.cost = req.cost;
        newTran.walletId = req.walletId;
        newTran.operation = req.operation;
        tranRepository.save(newTran);

        return new SuccessResponse("申请成功");
    }

    public @PostMapping(path = "/updateTran")
    Response updateState(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                         @RequestBody TranRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        TranRecord newTran = new TranRecord();
        if(req.state == 1){
            Wallet w = walletRepository.findById(req.walletId).get();
            if(req.operation == 0)
                w.balance += req.cost;
            if(req.operation == 1) {
                if (w.balance > req.cost)
                    return new SuccessResponse("余额不足");
                w.balance -= req.cost;
            }
            walletRepository.save(w);
        }
        newTran.id = req.id;
        newTran.state = req.state;
        newTran.reason = req.reason;
        newTran.memo = req.memo;
        tranRepository.save(newTran);

        return new SuccessResponse("审核成功");
    }
}
