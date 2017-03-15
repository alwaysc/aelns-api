package com.aelns.core.utils.security;

import com.aelns.core.exception.ApplicationException;
import com.aelns.core.exception.ErrorCode;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by aelns on 2017/3/15.
 */
public class SignUtil {

    private static final String SIGN = "sign";

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 验证参数是否有效
     * @param parameterMap 客户端参数
     * @param sign 客户端的签名
     * @param publicKey 客户端公钥, base64编码
     * @return 签名是否有效
     */
    public static boolean verifyParam(Map<String, String[]> parameterMap, String sign, String privateKey, String publicKey) {
        List<String> paramNames = Lists.newArrayList(parameterMap.keySet());

        // for natural ordering parameter names
        Collections.sort(paramNames);

        // build parameter name and values
        StringBuilder builder = new StringBuilder();
        paramNames.forEach(parameterName -> {
            if (!parameterName.equals(SIGN)) {
                String[] values = parameterMap.get(parameterName);
                String parameterValue = StringUtils.join(values, "");
                builder.append(parameterName).append("=").append(parameterValue).append("&");
            }
        });
        // delete last '&' character
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        try {
            // encrypt by private key
            byte[] encodedData = RSAUtil.encryptByPrivateKey(builder.toString().getBytes(), privateKey);
            String serverSign = RSAUtil.sign(encodedData, privateKey);
            // verify sign by public key
            return serverSign.equals(sign);
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.SIGNATURE_ERROR);
        }
    }
}
