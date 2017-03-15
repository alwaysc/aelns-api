package com.aelns.core.exception.api;

import com.aelns.core.exception.ApplicationException;
import com.aelns.core.exception.ErrorCode;

/**
 * Created by aelns on 2017/3/15.
 */
public class InvalidSignatureException extends ApplicationException {

    public InvalidSignatureException() {
        super(ErrorCode.SIGNATURE_ERROR.getCode(), ErrorCode.SIGNATURE_ERROR.getMessage());
    }
}
