package com.allenway.infrustructure.filter;

import com.allenway.infrustructure.entity.Record;
import com.allenway.infrustructure.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by wuhuachuan on 16/4/11.
 *
 * 用于做管理员的操作记录
 */

@Slf4j
@Component
public class OperationRecordFilter implements Filter{

    @Autowired
    private RecordService recordService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestUrl = httpServletRequest.getRequestURI();
        String addr = httpServletRequest.getRemoteAddr();
        String host = httpServletRequest.getRemoteHost();
        int port = httpServletRequest.getRemotePort();

        Record record = new Record.Builder()
                                    .requestUrl(requestUrl)
                                    .remoteAddress(addr)
                                    .remoteHost(host)
                                    .port(port)
                                    .build();

        recordService.save(record);

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
