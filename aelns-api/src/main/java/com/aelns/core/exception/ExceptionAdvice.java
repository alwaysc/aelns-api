package com.aelns.core.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

@ResponseBody
@CrossOrigin
@Configuration
@ControllerAdvice(annotations = { RestController.class })
public class ExceptionAdvice {


    @Value("${aelns.exception.print.stack.trace}")
    private boolean printStackTrace;

    private static final Logger logger = Logger
            .getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionWrapper handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return wrapperException(e);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionWrapper handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        return wrapperException(e);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ExceptionWrapper handleHttpMediaTypeNotSupportedException(
            Exception e) {
        return wrapperException(e);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ExceptionWrapper handleException(Throwable e) {
        return wrapperException(e);
    }

    private ExceptionWrapper wrapperException(Throwable e) {
        logger.error(e, e);
        ExceptionWrapper result = new ExceptionWrapper(e);

        // print the exception stack trace
        if (printStackTrace) {
            String exceptionTrace = getFormatStackTrace(e);
            result.setMessage("服务器内部错误: " + exceptionTrace);
        }
        // TODO log the exception for third party storage
        return result;
    }

    /**
     * 获取格式化的错误堆栈信息, 便于阅读
     * @param exception
     * @return
     */
    public String getFormatStackTrace(Throwable exception) {
        if (exception == null) {
            return "";
        }
        String stackTrace = exception.getStackTrace().toString();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        try {
            exception.printStackTrace(printWriter);
            printWriter.flush();
            writer.flush();
            stackTrace = writer.toString();
            printWriter.close();
            writer.close();
        } catch (Exception e) {
            logger.error("print stack trace error", e);
        }
        return stackTrace;
    }
}