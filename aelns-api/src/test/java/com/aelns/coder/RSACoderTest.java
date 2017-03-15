package com.aelns.coder;

import static org.junit.Assert.*;

import com.aelns.core.utils.security.RSAUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 *
 *
 * Two keys:
 a. public API key
 b. private access key
 Flow:
 (1)	Client combine the data to send
 (2)	Hash data with private key
 (3)	Send the public api key, hashed data, data, timestamp
 (4)	Server receive data
 (5)	Server use public api key to find the user/client in db
 (6)	Recombine the same data together from client, do the same hash as the client with the client's private key get from db.
 (7)	Calculate the timestamp to check if it's expired requests to refuse replay attacks.
 (8)	Compare the hash you and check with the hash get from client.

 *
 */
public class RSACoderTest {
    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        publicKey = RSAUtil.getPublicKey(keyMap);
        privateKey = RSAUtil.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSAUtil.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData,
                privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "age=14&appKey=seller&name=aaa&timestamp=17238298392839";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSAUtil.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSAUtil
                .decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSAUtil.sign(encodedData, privateKey);
        System.err.println("签名: " + sign);

        // 验证签名
        boolean status = RSAUtil.verify(encodedData, publicKey, sign);
        System.err.println("状态: " + status);
        assertTrue(status);

    }

}
