package skk.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Goods;
import skk.entity.User;
import skk.repository.BvoRepository;
import skk.repository.GoodsRepository;
import skk.repository.UserRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class GoodsInfoRequestBody{
    public String token;
    public String id;
    public String name;
    public Integer length;
    public Integer width;
    public Integer height;
    public Integer weight;
    public String sku;
    public String upc;
    public String ena;
    public String model;
    public Integer price;
    public String maintain;
    public String ebaydis;
    public String amazondis;
}



@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BvoRepository bvoRepository;
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/addgoods")
    public @ResponseBody
    Response addgoods (@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,@RequestBody GoodsInfoRequestBody requestBody) {
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证过期，请重新登录");
            return r;
        }


        Goods newgoods = new Goods();
        newgoods.mvoid = bvoRepository.findAllByUserid(user.id).id;
        newgoods.price = requestBody.price;
        newgoods.adis = requestBody.amazondis;
        newgoods.edis = requestBody.ebaydis;
        newgoods.sku = requestBody.sku;
        newgoods.ena =requestBody.ena;
        newgoods.upc = requestBody.upc;
        newgoods.name = requestBody.name;
        newgoods.weight = requestBody.weight;
        newgoods.height = requestBody.height;
        newgoods.length = requestBody.length;
        newgoods.width = requestBody.width;
        goodsRepository.save(newgoods);
        return new SuccessResponse("success");
    }


    /**
     根据商品名模糊查询
     */
    @GetMapping
    @RequestMapping("/searchgoods")
    public @ResponseBody
    Response searchlike(@RequestParam String name){
        if(name.equals("")){
            List<Goods> goodsList = new LinkedList<>();
            Iterable<Goods> iterable = goodsRepository.findAll();
            Iterator<Goods> itr = iterable.iterator();
            while (itr.hasNext()){
                goodsList.add(itr.next());
            }
            return new SuccessResponse(goodsList);
        }
        else{
            String str = "%";
            str += name;
            str += "%";
            List<Goods> goodslist = goodsRepository.findAllByNameLike(str);
            return new SuccessResponse(goodslist);
        }
    }

    @GetMapping
    @RequestMapping("/deleteAll")
    public @ResponseBody
    Response deleteAll(){
        goodsRepository.deleteAll();
        return new SuccessResponse("deleter success");
    }

    @GetMapping
    @RequestMapping("/deletebyId")
    public @ResponseBody
    Response deleteById(@RequestParam String id){
        goodsRepository.deleteById(id);
        return new SuccessResponse("deleter success");
    }

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size()==1){
            return users.get(0);
        }else{
            return null;
        }
    }
}
