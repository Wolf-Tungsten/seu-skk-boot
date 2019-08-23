package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.BrandExtraInfo;
import skk.entity.User;
import skk.repository.BrandExtraInfoRepository;
import skk.repository.UserRepository;
import skk.service.UserService;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.List;

@RestController
@RequestMapping("/mvo")
@CrossOrigin
public class MvoController {

    @Autowired
    private BrandExtraInfoRepository brandExtraInfoRepository;

    @Autowired
    private UserRepository userRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    @GetMapping(path = "/brandExtraInfo")
    public @ResponseBody
    Response getBrandExtraInfo(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<BrandExtraInfo> infos = brandExtraInfoRepository.findBrandExtraInfoByMvoId(user.id);
        if(infos.size() == 1){
            return new SuccessResponse(infos.get(0));
        } else {
            FailedResponse r = new FailedResponse("信息不存在");
            return r;
        }
    }


    @PostMapping(path = "/brandExtraInfo")
    public @ResponseBody
    Response postBrandExtraInfo(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
    @RequestBody BrandExtraInfoRequest requestBody){

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        // 先检查扩展信息是否已经存在

        BrandExtraInfo info;

        List<BrandExtraInfo> infos = brandExtraInfoRepository.findBrandExtraInfoByMvoId(user.id);
        if(infos.size() == 1){
            info = infos.get(0);
        } else {
            info = new BrandExtraInfo();
        }

        info.mvoId = user.id;
        info.brief = requestBody.brief;
        info.companyNameChinese = requestBody.companyNameChinese;
        info.companyNameEnglish = requestBody.companyNameEnglish;
        info.gcmReportType = requestBody.gcmReportType;
        info.gcmReportUrl = requestBody.gcmReportUrl;

        brandExtraInfoRepository.save(info);

        return new SuccessResponse("更新成功");
    }
}


class BrandExtraInfoRequest {
    public String companyNameChinese;
    public String companyNameEnglish;
    public String brief;
    public String gcmReportType;
    public String gcmReportUrl;
}
