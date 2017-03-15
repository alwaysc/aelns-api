package com.aelns.core.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * 统一异常处理
 *
 */
@ResponseBody
@CrossOrigin
@Configuration
@ControllerAdvice
public class ExceptionAdvice {


    @Value("${aelns.exception.trace}")
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
     * 404 - Bad Request
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionWrapper handleNoHandlerFoundException(
            NoHandlerFoundException e) {
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
     * 400 - Business exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public ExceptionWrapper handleApplicationException(Throwable e) {
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
            result.setMessage(getFormatStackTrace(e));
        }
        return result;
    }

    /**
     * 获取格式化的错误堆栈信息, 提高易读性
     *
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