package skk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import skk.repository.UserRepository;
import skk.util.Response;

class DDRequestBody{
    String Id;
    String type;
    String describe;
    String code;
    String value;
}

@RestController
@RequestMapping("/dd")
public class DDController {

    @Autowired
    private UserRepository userRepository;

    public @RequestMapping("/delete")
    Response deleteEntry(@RequestBody DDRequestBody ddRequestBody){


        retur r;
    }
}
