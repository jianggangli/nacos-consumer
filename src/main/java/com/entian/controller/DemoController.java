package com.entian.controller;

import com.entian.common.standard.resp.result.BaseResult;
import com.entian.common.standard.session.SessionContext;
import com.entian.controller.req.UserReq;
import com.entian.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jianggangli
 * @version 1.0 2020/7/1 9:48
 * 功能:
 */
@Slf4j
@Api(tags = "入参@RequestBody校验")
@RestController
@RefreshScope
@RequestMapping("/demo")
@Validated
public class DemoController {
    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private SessionContext sessionContext;

    @Value("${useLocalCache}")
    private String useLocalCache;

    @Value("${name}")
    private String name;

    @Autowired
    private DemoService demoService;


    @PostMapping("/valid/user")
    @ApiOperation("入参json校验")
    public String validUser(@RequestBody @Valid UserReq user) throws InterruptedException {
        log.info(sessionContext.toString());
        sessionContext.setRoleName("角色名称变更了");
        return useLocalCache+"--"+name;
    }

    @PostMapping(value = "/service-provider/valid")
    @ApiOperation("入参RequestParam校验")
    public BaseResult<String> serviceProvider(@NotNull(message = "username不能为空") String username,
            @NotNull(message = "password不能为空") String password) {
        log.info(sessionContext.toString());
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        HttpEntity<Map> request = new HttpEntity<>(map);
        return restTemplate.getForObject("http://service-provider/get-args-valid?username=" + username + "&password="+
                password, BaseResult.class);
//        return restTemplate.postForObject("http://service-provider/get-args-valid", map, BaseResult.class);
    }

    @PostMapping(value = "/demo/service")
    @ApiOperation("请求demoService校验")
    public BaseResult<String> demoService(@NotNull(message = "username不能为空") String username,
            @NotNull(message = "password不能为空") String password) {
        log.info(sessionContext.toString());
//        return demoService.serviceProvider(username, null);
        return demoService.serviceProvider(username, password);
    }


}
