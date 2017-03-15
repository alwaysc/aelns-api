package com.aelns.api;

import com.aelns.core.support.BaseAction;
import com.aelns.core.support.MediaTypes;
import com.aelns.core.utils.security.Signature;
import com.aelns.service.SignatureService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by aelns on 2017/3/15.
 */

@RestController
public class SignatureEndpoint extends BaseAction {

    @Autowired
    private SignatureService signatureService;

    @RequestMapping(value = "/api/sign/test", produces = MediaTypes.JSON_UTF_8)
    @Signature(desc = "签名测试")
    public Object payment() {
        return wraperResult(new Date());
    }

    /**
     * 初始化公钥与秘钥
     * @param title
     * @param appKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/sign/keys/init", produces = MediaTypes.JSON_UTF_8)
    public Object initKeys(String title, String appKey) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(title), "title 不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(appKey), "appKey 不能为空");
        return wraperResult(signatureService.initKeys(title, appKey));
    }
}
