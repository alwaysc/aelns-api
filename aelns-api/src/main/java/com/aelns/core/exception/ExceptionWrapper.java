package com.aelns.core.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 将异常信息封装成JSON格式返回
 */
public class ExceptionWrapper {

    /**
     * error code
     */
    private String code;

    /**
     * error message
     */
    private String message;

    /**
     * 如果是Application错误, 返回简单的message信息<br/>
     * 如果是客户端参数有问题, 统一返回参数错误编码 @see ErrorCode.ARGUMENTS_INCORRECT <br/>
     * 如果是服务器异常, 返回简单的message, 另外根据application.properties配置的参数$("{aelns.exception.print.stack.trace}")<br/>
     * 决定是否返回详细的错误堆栈信息<br>
     * @param ex the exception instance
     */
    public ExceptionWrapper(Throwable ex) {
        if (ex instanceof ApplicationException) {
            // Application(自定义)异常, 返回简单的message
            ApplicationException exception = (ApplicationException) ex;
            this.code = exception.getCode();
            this.message = exception.getMessage();
        } else if (
                // 客户端参数有问题, 统一返回参数错误编码
                ex instanceof IllegalArgumentException
                        || ex instanceof MissingServletRequestParameterException
                        || ex instanceof HttpMessageNotReadableException
                        || ex instanceof HttpRequestMethodNotSupportedException
                        || ex instanceof HttpMediaTypeNotSupportedException
                        || ex instanceof MethodArgumentTypeMismatchException) {
            this.code = ErrorCode.ARGUMENTS_INCORRECT.getCode();
            this.message = ex.getMessage();

        } else {
            // 服务器内部异常
            this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
            this.message = ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
        }
    }

    public ExceptionWrapper() {
        super();
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorJsonWrapper [code=" + code + ", message=" + message + "]";
    }
}
