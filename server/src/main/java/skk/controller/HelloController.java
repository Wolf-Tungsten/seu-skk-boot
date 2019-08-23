package skk.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @PostMapping("")
    public @ResponseBody
    Response addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Response r = new Response();
        r.email = email;
        r.name = name;
        return r;
    }

    class Response {
        public String name;
        public String email;
    }
}


