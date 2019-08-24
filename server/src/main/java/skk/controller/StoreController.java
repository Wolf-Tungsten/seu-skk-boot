package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Store;
import skk.entity.User;
import skk.repository.StoreRepository;
import skk.repository.UserRepository;
import skk.util.*;

import java.util.List;

class StoreRequestBody{
    public String storeName;
    public String marketplaceId;
    public String storeToken;
    public String type;
}

@RestController
@CrossOrigin
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;

    private User getUserInfo(String token) {
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    @RequestMapping("/addStore")
    public Response addStore(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                             @RequestBody StoreRequestBody reqBody){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        Store newStore = new Store();
        newStore.sellerid = user.id;
        newStore.storename = reqBody.storeName;
        newStore.marketplaceId = reqBody.marketplaceId;
        newStore.type = reqBody.type;
        newStore.storetoken = reqBody.storeToken;

        storeRepository.save(newStore);
        return new SuccessResponse("添加成功");
    }

    @RequestMapping("/all")
    public Response allStore(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token) {
        User user = getUserInfo(token);
        if (user == null) {
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        List<Store> list = storeRepository.findAllBysellerid(user.id);

        return new SuccessResponse(list);
    }

    @DeleteMapping("/delete")
    public Response deleteStore(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
    @RequestParam String id) {
        User user = getUserInfo(token);
        if (user == null) {
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        storeRepository.deleteById(id);
        return new SuccessResponse("删除成功");
    }


}
