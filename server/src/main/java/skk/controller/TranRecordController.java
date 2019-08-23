package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skk.entity.TranRecord;
import skk.repository.TranRecordRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;

class TranRequestBody{
    public String id;
    public String fromId;
    public String toId;
    public int cost;
    public String date;
    public int state;
    public String reason;
    public String memo;
}

@RestController
@CrossOrigin
@RequestMapping("/tran")
public class TranRecordController {

    @Autowired
    private TranRecordRepository tranRepository;

    @RequestMapping("/all")
    public Response showAll(){

        List<TranRecord> list = tranRepository.findAll();
        SuccessResponse r =new SuccessResponse(list);
        return r;
    }
}
