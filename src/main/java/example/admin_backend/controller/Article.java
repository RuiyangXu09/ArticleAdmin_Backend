package example.admin_backend.controller;

import example.admin_backend.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/article")
public class Article {
    @GetMapping(value = "/listArticle")
    public Result listArticle(){
        return Result.success();
    }
}
