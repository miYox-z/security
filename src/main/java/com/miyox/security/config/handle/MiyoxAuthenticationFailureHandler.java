package com.miyox.security.config.handle;

import com.miyox.security.util.HttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MiyoxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(HttpUtil.isAjax(request)){
            HttpUtil.response(response, HttpStatus.OK, exception.getMessage());
        }else{
            request.getRequestDispatcher("/login?error").forward(request, response);
        }
    }
}
