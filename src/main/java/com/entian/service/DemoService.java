package com.entian.service;

import com.entian.common.standard.resp.result.BaseResult;
import com.entian.service.fallback.DemoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jianggangli
 * @version 1.0 2020/12/4 19:42
 * 功能:
 */
@FeignClient(value = "service-provider", fallbackFactory = DemoFallbackFactory.class)
public interface DemoService {

    @RequestMapping(method = RequestMethod.POST, value = "/get-args-valid?name={userName}&password={password}")
    BaseResult<String> serviceProvider(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

}
