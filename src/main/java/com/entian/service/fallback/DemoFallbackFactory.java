package com.entian.service.fallback;

import com.entian.common.standard.resp.result.BaseResult;
import com.entian.common.standard.resp.result.ResultCode;
import com.entian.service.DemoService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author jianggangli
 * @version 1.0 2020/12/4 20:57
 * 功能:
 */
@Component
public class DemoFallbackFactory implements FallbackFactory<DemoService> {
    @Override
    public DemoService create(Throwable throwable) {
        //FallbackFactory优先级高于FallbackMethod
        return new DemoService() {
            @Override
            public BaseResult<String> serviceProvider(String username, String password) {
                return BaseResult.fail(ResultCode.ERROR, "服务器有点忙,请稍后再试");
            }

        };
    }
}
