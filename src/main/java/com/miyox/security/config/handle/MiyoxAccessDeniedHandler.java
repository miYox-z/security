package com.miyox.security.config.handle;

import com.miyox.security.util.HttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MiyoxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(HttpUtil.isAjax(request)){
            HttpUtil.response(response, HttpStatus.OK, accessDeniedException.getMessage());
        }else{
            request.setAttribute(WebAttributes.ACCESS_DENIED_403,
                    accessDeniedException);

            response.setStatus(HttpStatus.FORBIDDEN.value());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
    }
}
