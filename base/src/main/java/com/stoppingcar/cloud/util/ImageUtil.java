package com.stoppingcar.cloud.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JerryHeng
 * @since 2023/6/27
 */
public class ImageUtil {
    /**
     * 身份证识别数据
     */
    private static final String ID_CARD_POST = "https://cardnumber.market.alicloudapi.com";
    private static final String ID_CORD_PATH = "/rest/160601/ocr/ocr_idcard.json";
    /**
     * 基础必须数据
     */
    public static final String IMG_URL_PREFIX = "https://maistore-1308375907.cos.ap-shanghai.myqcloud.com/";
    private static final String APPCODE = "";
    private static final Integer SUCCESS_CODE = 200;

    /**
     * 身份证识别
     *
     * @param url  链接
     * @param side 正反面
     * @return 识别数据
     * @throws Exception 异常
     */
    public static <HttpResponse> JSONObject idCardRecognition(String url, String side) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APPCODE);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        String configStr = configObj.toString();
        JSONObject requestObj = new JSONObject();
        String base = imgBase(url);
        //如果是本地文件，则
        requestObj.put("image", base);
        if (configObj.size() > 0) {
            requestObj.put("configure", configStr);
        }
        String body = requestObj.toString();
        Map<String, String> queries = new HashMap<>();
        org.apache.http.HttpResponse response = HttpUtils.doPost(ID_CARD_POST, ID_CORD_PATH, headers, queries, body);
        if (response.getStatusLine().getStatusCode() != SUCCESS_CODE) {
            return null;
        }
        String string = EntityUtils.toString(response.getEntity());
        System.out.println(string);
        JSONObject resObj = JSON.parseObject(string);
        System.out.println(resObj.toJSONString());
        return resObj;
    }

    /**
     * 对图片路径进行判断，如果是本地文件就二进制读取并base64编码，如果是url,则返回
     */
    public static String imgBase(String path) {
        String imgBase64 = "";
        String http = "http";
        if (path.startsWith(http)) {
            imgBase64 = path;
        } else {
            try {
                File file = new File(path);
                byte[] content = new byte[(int) file.length()];
                FileInputStream inputStream = new FileInputStream(file);
                inputStream.read(content);
                inputStream.close();
                imgBase64 = new String(Base64.encodeBase64(content));
            } catch (IOException e) {
                e.printStackTrace();
                return imgBase64;
            }
        }
        return imgBase64;
    }
}
