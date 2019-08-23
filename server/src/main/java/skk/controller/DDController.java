package skk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.DD;
import skk.repository.DDRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

class DDRequestBody{
    public String entryType;
    public String describe;
    public String code;
    public String entryValue;
}

@RestController
@CrossOrigin
@RequestMapping("/dd")
public class DDController {

    @Autowired
    private DDRepository DDRepository;

    public @RequestMapping("/create")
    Response createEntry(@RequestBody DDRequestBody ddRequestBody){

        DD newDD = new DD();
        newDD.entryType = ddRequestBody.entryType;
        newDD.describe = ddRequestBody.describe;
        newDD.code = ddRequestBody.code;
        newDD.entryValue = ddRequestBody.entryValue;
        DDRepository.save(newDD);

        SuccessResponse r = new SuccessResponse("注册成功");
        return r;
    }
}
