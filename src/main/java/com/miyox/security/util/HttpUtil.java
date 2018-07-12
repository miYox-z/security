package com.miyox.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miyox.security.bean.ResponseMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String requestedWith = request.getHeader("X-Requested-With");
        return StringUtils.equals(requestedWith, "XMLHttpRequest");
    }

    public static void response(HttpServletResponse response, HttpStatus httpStatus, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(httpStatus.value());
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMsg responseMsg = new ResponseMsg(httpStatus.value() == 200, msg);
        objectMapper.writeValue(printWriter, responseMsg);
    }
}
