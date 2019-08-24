package skk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.DD;
import skk.entity.User;
import skk.repository.DDRepository;
import skk.repository.UserRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;

class CreateOrUpdateDDRequestBody{
    public String id;
    public String entryType;
    public String entryDescribe;
    public String code;
    public String entryValue;
}

class DeleteDDRequestBody{
    public String id;
}

@RestController
@CrossOrigin
@RequestMapping("/dd")
public class DDController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DDRepository ddRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    public @GetMapping(path = "/entry")
    Response allEntry(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<DD> list = ddRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    public @PostMapping(path = "/entry")
    Response createEntry(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                                @RequestBody CreateOrUpdateDDRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        DD newDD = new DD();
        newDD.id = req.id;
        newDD.entryType = req.entryType;
        newDD.entryDescribe = req.entryDescribe;
        newDD.code = req.code;
        newDD.entryValue = req.entryValue;
        ddRepository.save(newDD);

        return new SuccessResponse("修改成功");
    }

    public @DeleteMapping(path = "/entry")
    Response deleteEntry(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                                @RequestBody DeleteDDRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        ddRepository.deleteById(req.id);

        return new SuccessResponse("删除成功");
    }
}