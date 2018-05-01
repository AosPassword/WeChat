package org.redrock.web.game.Utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

@Component
public class WeChatUtil {
    //其实吧我应该搞一个类存这玩意来着，哎，就这吧
    private static final String getAccsessTokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static final String REDIRECT_URI="http://scplayer.nat300.top/getcode";
    private static final String STATE="200";
    private static final String SCOPE="snsapi_base";
    private static final String skipurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    private static final String TOKEN="asdasd";
    private static final String APPID="wx3f30a94b82f6a6b7";
    private static final String APPSECRET="e4d8779c6ceebacda26166580da6cb59";
    private static final String accsessUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static String getAccessToken() throws IOException, JSONException {
        String accessTokenUrl=accsessUrl.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject=getJsonObject(accessTokenUrl);
        return jsonObject.getString("access_token");
    }

    /**
     *
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean isWeChat(String timestamp,String nonce,String signature){
        String[] timestampchar=timestamp.split("");
        String[] noncechar=nonce.split("");
        String[] tokenchar=TOKEN.split("");
        Arrays.sort(timestampchar);
        Arrays.sort(noncechar);
        Arrays.sort(tokenchar);

        timestamp=StringUtil.groupToString(timestampchar);
        nonce=StringUtil.groupToString(noncechar);
        String token=StringUtil.groupToString(tokenchar);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(token);
        stringBuilder.append(timestamp);
        stringBuilder.append(nonce);

        String string=Sha1Util.encode(stringBuilder.toString());
        System.out.println(string);
        if (string.equals(signature)){
            return true;
        }else {
            return false;
        }
    }
    public static String getSkipUrl(){
        return skipurl.replace("STATE",STATE).replace("SCOPE",SCOPE).replace("REDIRECT_URI",REDIRECT_URI);
    }

    public static JSONObject getAccessToken(String CODE) throws IOException, JSONException {
        String urlstr=getAccsessTokenUrl.replace("APPID",APPID).replace("SECRET",APPSECRET).replace("CODE",CODE);
        JSONObject jsonObject=getJsonObject(urlstr);
        return jsonObject;
    }

    public static JSONObject getJsonObject(String url_str) throws IOException, JSONException {
        URL url=new URL(url_str);

        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        InputStream inputStream=connection.getInputStream();
        int size=inputStream.available();
        byte[] bs=new byte[size];
        inputStream.read(bs);
        String json=new String(bs,"UTF-8");

        JSONObject jsonObject=new JSONObject(json);
        return jsonObject;
    }

    public static JSONObject getUserInfo(String ACCESS_TOKEN,String OPENID) throws IOException, JSONException {
        String url_str=getUserInfoUrl.replace("ACCESS_TOKEN",ACCESS_TOKEN).replace("OPENID",OPENID);
        JSONObject jsonObject=getJsonObject(url_str);
        return jsonObject;
    }
}
