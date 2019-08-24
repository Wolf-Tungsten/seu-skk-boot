package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Par;
import skk.entity.User;
import skk.repository.ParRepository;
import skk.repository.UserRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;


class CreateOrUpdateParRequestBody{
    public String id;
    public String parKey;
    public String parValue;
    public String parDescribe;
}

class DeleteParRequestBody{
    public String id;
}

@RestController
@CrossOrigin
@RequestMapping("/par")
public class ParController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParRepository parRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    public @GetMapping(path = "/parameter")
    Response allPar(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<Par> list = parRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    public @PostMapping(path = "/parameter")
    Response createEntry(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                         @RequestBody CreateOrUpdateParRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        Par newPar = new Par();
        newPar.id = req.id;
        newPar.parDescribe = req.parDescribe;
        newPar.parKey = req.parKey;
        newPar.parValue = req.parValue;
        parRepository.save(newPar);

        return new SuccessResponse("修改成功");
    }

    public @PostMapping(path = "/delete")
    Response deleteEntry(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                         @RequestBody DeleteParRequestBody req){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        parRepository.deleteById(req.id);

        return new SuccessResponse("删除成功");
    }

}
