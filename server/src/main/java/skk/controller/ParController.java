package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Par;
import skk.repository.ParRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;


class ParRequestBody{
    public String id;
    public String parKey;
    public String parValue;
    public String parDescribe;
}

@RestController
@CrossOrigin
@RequestMapping("/par")
public class ParController {

    @Autowired
    private ParRepository parRepository;

    @RequestMapping("/addOrUpdate")
    public Response createEntry(@RequestBody ParRequestBody req){

        Par newPar = new Par();
        newPar.id = req.id;
        newPar.parDescribe = req.parDescribe;
        newPar.parKey = req.parKey;
        newPar.parValue = req.parValue;
        parRepository.save(newPar);
        List<Par> list = parRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    @RequestMapping("/delete")
    public Response deleteEntry(@RequestBody DDRequestBody req){

        parRepository.deleteById(req.id);
        List<Par> list = parRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }

    @RequestMapping("/all")
    public Response showAll(){

        List<Par> list = parRepository.findAll();
        SuccessResponse r = new SuccessResponse(list);
        return r;
    }
}
