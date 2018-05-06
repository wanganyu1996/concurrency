package com.concurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanganyu on 2018/05/06.
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {
    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
      return RequestHolder.getId();
    }

}
