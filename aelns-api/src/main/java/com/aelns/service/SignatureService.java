package com.aelns.service;

import com.aelns.core.exception.ApplicationException;
import com.aelns.core.exception.ErrorCode;
import com.aelns.core.utils.number.RandomUtil;
import com.aelns.core.utils.security.RSAUtil;
import com.aelns.core.utils.security.SignUtil;
import com.aelns.dao.PlatformApplicationMapper;
import com.aelns.model.PlatformApplication;
import com.aelns.model.PlatformApplicationExample;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * 签名验证服务
 *
 * Created by aelns on 2017/3/15.
 */

@Service
public class SignatureService {

    private static final String APP_KEY = "appKey";

    private static final String SIGN = "sign";

    private static final String TIMESTAMP = "timestamp";

    @Autowired
    private PlatformApplicationMapper platformApplicationMapper;

    /**
     * init application keys
     * @param title
     * @param appkey
     * @return
     * @throws Exception
     */
    public Map<String, String> initKeys(String title, String appkey) throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        PlatformApplication application = new PlatformApplication();
        application.setId(RandomUtil.nextInt());
        application.setAppKey(appkey);
        application.setPrivateKey(RSAUtil.getPrivateKey(keyMap));
        application.setPublicKey(RSAUtil.getPublicKey(keyMap));
        application.setTitle(title);
        platformApplicationMapper.insertSelective(application);

        Map<String, String> resultMap = Maps.newHashMap();
        resultMap.put(RSAUtil.PUBLIC_KEY, application.getPublicKey());
        resultMap.put(RSAUtil.PRIVATE_KEY, application.getPrivateKey());
        return resultMap;
    }

    /**
     * 验证签名
     * @param request
     */
    public void validSignature(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.containsKey(APP_KEY)
                || !parameterMap.containsKey(SIGN)
                || !parameterMap.containsKey(TIMESTAMP)) {
            throw new ApplicationException(ErrorCode.WRONG_PARAMS);
        }
        String timestamp = parameterMap.get(TIMESTAMP)[0];
        String appKey = parameterMap.get(APP_KEY)[0];
        String sign = parameterMap.get(SIGN)[0].replaceAll(" ", "+");
        // 验证请求时间
        validteTimestamp(timestamp);
        //判断是否是有效的Application
        PlatformApplication platformApplicant = findApplicationByAppKey(appKey);
        if (null == platformApplicant) {
            throw new ApplicationException(ErrorCode.SIGNATURE_INVALID_APPLICATION);
        }
        // 获取用户的秘钥
        String privateKey = platformApplicant.getPrivateKey();
        String publicKey = platformApplicant.getPublicKey();
        // 根据验证签名是否有效
        boolean isValidSign = SignUtil.verifyParam(request.getParameterMap(), sign, privateKey, publicKey);
        if (!isValidSign) {
            throw new ApplicationException(ErrorCode.SIGNATURE_ERROR);
        }
    }

    /**
     * validate the request timestamp
     * @param timestamp
     */
    private void validteTimestamp(String timestamp) {
        try {
            if (isExpriedRequest(Long.parseLong(timestamp))) {
                throw new ApplicationException(ErrorCode.SIGNATURE_TIME_OUT);
            }
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.SIGNATURE_TIME_OUT);
        }
    }

    /**
     * Calculate the timestamp to check if it's expired requests to refuse replay attacks.
     * @param timestamp client request timestamp
     * @return is expired request
     */
    private boolean isExpriedRequest(long timestamp) {
        // TODO
        return false;
    }

    /**
     * find application information for given app key
     * @param appKey user app key
     * @return  PlatformApplication, will be null
     */
    private PlatformApplication findApplicationByAppKey(String appKey) {
        PlatformApplicationExample example = new PlatformApplicationExample();
        example.createCriteria().andAppKeyEqualTo(appKey);
        List<PlatformApplication> list = platformApplicationMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }
}
