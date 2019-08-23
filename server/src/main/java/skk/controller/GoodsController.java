package skk.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Goods;
import skk.repository.GoodsRepository;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.ArrayList;
import java.util.List;

class GoodsInfoRequestBody{
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

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/addgoods")
    public @ResponseBody
    Response addgoods (@RequestBody GoodsInfoRequestBody requestBody) {
        //各种判断
        Goods newgoods = new Goods();
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
        String str = "%";
        str +=name;
        str +="%";
        List<Goods> goodslist = goodsRepository.findAllByNameLike(str);
        return new SuccessResponse(goodslist);
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
}
