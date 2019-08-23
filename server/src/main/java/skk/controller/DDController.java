package skk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.DD;
import skk.repository.DDRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;

class DDRequestBody{
    public String id;
    public String entryType;
    public String entryDescribe;
    public String code;
    public String entryValue;
}

@RestController
@CrossOrigin
@RequestMapping("/dd")
public class DDController {

    @Autowired
    private DDRepository ddRepository;

    @RequestMapping("/addOrUpdate")
    public Response createEntry(@RequestBody DDRequestBody req){

        DD newDD = new DD();
        newDD.id = req.id;
        newDD.entryType = req.entryType;
        newDD.entryDescribe = req.entryDescribe;
        newDD.code = req.code;
        newDD.entryValue = req.entryValue;
        ddRepository.save(newDD);
        List<DD> list = ddRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    @RequestMapping("/delete")
    public Response deleteEntry(@RequestBody DDRequestBody req){

        ddRepository.deleteById(req.id);
        List<DD> list = ddRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    @RequestMapping("/all")
    public Response showAll(){

        List<DD> list = ddRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }
}