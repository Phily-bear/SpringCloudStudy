package com.test.controller.client;

import com.test.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//fallback参数指定为我们刚刚编写的实现类
@FeignClient(value = "userservice")
public interface UserClient {

    // 路径保证跟其他的微服务提供的一致即可
    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") int uid); // 参数和返回值也要保持一致
}
