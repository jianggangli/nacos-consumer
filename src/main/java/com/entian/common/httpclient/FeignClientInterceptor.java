package com.entian.common.httpclient;

import com.entian.common.standard.session.SessionContextFacade;
import com.entian.common.standard.session.jwt.JwtHandler;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author jianggangli
 * @version 1.0 2020/12/4 18:21
 * 功能:
 */

public class FeignClientInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        request.getHeaders().add(JwtHandler.TOKEN_HEADER, SessionContextFacade.getSessionContext().getToken());
        return execution.execute(request, body);
    }

}

