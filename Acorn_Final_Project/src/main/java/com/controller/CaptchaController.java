package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CaptchaController {
	
	@RequestMapping(value="/captchaCheck", method=RequestMethod.POST)
	public @ResponseBody String APICaptchaNkeyResult(@RequestParam("key") String key, @RequestParam("value") String value) {
        System.out.println("1515");
		String clientId = "v9iNMJ29o1IXrn316w_6";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "azAMB01CHe";//애플리케이션 클라이언트 시크릿값";
        String result = "";
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            // key 캡차 키 발급시 받은 키값
            // value 사용자가 입력한 캡차 이미지 글자값
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            result = response.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
	
	@RequestMapping(value="/getCaptcha", method=RequestMethod.POST)
	public @ResponseBody HashMap<String, String> APICaptchaNkey(HttpServletRequest request) {
		String clientId = "v9iNMJ29o1IXrn316w_6";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "azAMB01CHe";//애플리케이션 클라이언트 시크릿값";
        String key = "";
        HashMap<String, String> map = new HashMap<>();
        try {
            String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer result = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	result.append(inputLine);
            }
            br.close();
            key = (result.toString().substring(8, result.toString().length()-2));
        } catch (Exception e) {
            System.out.println(e);
        }
        // 이미지 추출
        try {
            String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File("c:\\upload\\captcha", tempname + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
                
                map.put("key", key);
                map.put("img", f.getName());
                System.out.println(key + "   " + f.getName());
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer result = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                	result.append(inputLine);
                }
                br.close();
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
	}
}
