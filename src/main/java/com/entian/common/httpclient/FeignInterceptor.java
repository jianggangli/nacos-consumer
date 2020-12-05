package com.entian.common.httpclient;

import com.entian.common.standard.session.SessionContextFacade;
import com.entian.common.standard.session.jwt.JwtHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianggangli
 * @version 1.0 2020/12/4 19:52
 * 功能:
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(JwtHandler.TOKEN_HEADER, SessionContextFacade.getSessionContext().getToken());
    }

}
