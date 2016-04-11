package com.allenway.infrustructure.exception;

import com.allenway.infrustructure.entity.Bug;
import com.allenway.infrustructure.service.BugService;
import com.allenway.utils.response.ReturnStatusCode;
import com.allenway.utils.response.ReturnTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by wuhuachuan on 16/3/29.
 */

@Slf4j
@ControllerAdvice
public class ExceptionHandlerBean extends ResponseEntityExceptionHandler {

    @Autowired
    private BugService bugService;

    /**
     * 运行时异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) throws IOException {
        return getResponseEntity(ex,request, ReturnStatusCode.RuntimeException);
    }

    /**
     * 空指针异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(RuntimeException ex, WebRequest request) throws IOException {
        return getResponseEntity(ex,request, ReturnStatusCode.NullPointerException);
    }

    /**
     * 数据找不到异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws IOException {

        return getResponseEntity(ex,request,ReturnStatusCode.DataNotFoundException);
    }

    /**
     * 参数错误异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) throws IOException {
        return getResponseEntity(ex,request, ReturnStatusCode.IllegalArgumentException);
    }

    /**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     * @param ex
     * @param request
     * @param specificException
     * @return
     */
    private ResponseEntity<Object> getResponseEntity(RuntimeException ex, WebRequest request, ReturnStatusCode specificException) {

        Bug bug = new Bug(ex.toString(),((ServletWebRequest)request).getRequest().getRequestURI());
        bugService.save(bug);

        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.setStatusCode(specificException);
        returnTemplate.setErrorMsg(ex.getMessage());

        log.info(ex.getCause().getMessage());

        return handleExceptionInternal(ex, returnTemplate,
                new HttpHeaders(), HttpStatus.OK, request);
    }
}
