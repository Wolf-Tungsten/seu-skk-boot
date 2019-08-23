package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Bvo;
import skk.repository.BvoRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

class  BvoInfochangeReqBody{
    public String name;
    public String email;
    public String phone;
    public String userid;
}

@RestController
@CrossOrigin
@RequestMapping("/bvo")
public class Bvocontroller {
    @Autowired
    private BvoRepository bvoRepository;
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/updatebvo")
    public @ResponseBody
    Response signup (@RequestBody BvoInfochangeReqBody requestBody) {
        //各种判断
        Bvo newBvo = new Bvo();
        newBvo.name = requestBody.name;
        newBvo.email = requestBody.email;
        newBvo.phone = requestBody.phone;
        newBvo.userid = requestBody.userid;
        bvoRepository.save(newBvo);
        return new SuccessResponse("success");
    }


}

