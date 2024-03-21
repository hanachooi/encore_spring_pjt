package com.example.encore_spring_pjt.day0314;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserResponse loginService(UserRequest params) {
        System.out.println("debug >>>> sevice loginService ");
        return userMapper.loginRow(params);
    }

    // insert
    public void registerService(UserRequest params){
        userMapper.registerRow(params);
    }

    /*
    API 흐름 구조
    - 사용자가 카카오 API 사용을 호출하면 서버는 호출이 됌.
    - 로그인 페이지가 보여지고 정보를 입력받는다.
    - 문제 없으면 카카오는 redirect_url 로 인증 코드를 보낸다.
    - 사용자는 해당 인증 코드를 가지고 서버에게 토큰(access token)을 요구함.
    - 문제가 없으면 서버는 사용자에게 토큰을 넘겨줌.
    - 받은 토큰을 카카오서버에게 전달하여 사용자 정보를 요청하게 됌.
    - 문제 없으면 토큰에 해당하는 사용자의 정보를 보내줌.
    - 해당 정보를 받아서 처리(테이블에서 비교)
    */

    // 나의 토큰을 가져오는 코드
    public String getAccessToken(String code){
        String accessToken = null, refreshToken = null;
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(kakaoUrl);
            // url 을 통해서, 연결을 함.
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            System.out.println("debug service getAccessToekn conn , " + conn);

            // header setting
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            // 연결한 conn 으로, 출력해줌.
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuffer buffer = new StringBuffer();
            buffer.append("grant_type=authorization_code");
            buffer.append("&client_id=fb0e7d80d7a723a4a8d4b65e6afe125d");
            buffer.append("&redirect_url=http://localhost:8888/user/kakao_login.hanwha");
            buffer.append("&code=" + code);

            writer.write(buffer.toString());
            writer.flush();

            int http_status = conn.getResponseCode();
            System.out.println("debug service http_status , " + http_status);

            // token을 얻어오기 위해서 BufferedReader 객체를 준비
            // 연결한 걸로, 읽어옴
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String responseResult = "";
            while((line = reader.readLine()) != null){
                responseResult += line;
            }
            System.out.println("debug service responseResult json, " + responseResult);

            // json -> Object (gson lib 이용)
            JsonParser parser = new JsonParser();
            JsonElement jObj = parser.parse(responseResult);
            System.out.println("debug service jObj Object , " + jObj);
            accessToken = jObj.getAsJsonObject().get("access_token").getAsString();
            refreshToken = jObj.getAsJsonObject().get("refresh_token").getAsString();
            System.out.println("debug service access token , " + accessToken);
            System.out.println("debug service refresh token , " + refreshToken);

            writer.close();
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return accessToken;
    }

    // 나의 토큰으로 user 정보를 가져옴.
    public Map<String, Object> getUserInfo(String accessToken){

        String kakaoUrl = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> map = new HashMap<>();

        try {

            URL url = new URL(kakaoUrl);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            // Header에 access Token 심어서 보내주어야 한다.
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int http_status = conn.getResponseCode();
            System.out.println("debug >> service http_status : " + http_status);

            // 사용자 정보를 얻기 위한 BufferedReader 객체
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            String responseResult = "";

            while( (line = reader.readLine()) != null ){
                responseResult += line;
            }

            System.out.println("debug >> service responseresult json : " + responseResult);

            // Json으로 변환
            JsonParser parser = new JsonParser();
            JsonElement jobj = parser.parse(responseResult);
            System.out.println("debug >> service job object : " + jobj );

            JsonObject properties = jobj.getAsJsonObject().get("properties").getAsJsonObject();
            String name = properties.getAsJsonObject().get("nickname").getAsString();

            map.put("name", name);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

    public String logout(Map<String, String> map){

        String kakaoUrl = "https://kapi.kakao.com/v1/user/logout";
        StringBuffer buffer = new StringBuffer();

        try {

            URL url = new URL(kakaoUrl);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();

            // header setting
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            if(map.get("Authorization") != null){
                conn.setRequestProperty("Authorization", map.get("Authorization"));
            }

            // 서버에 파라미터를 전달하는 스트림
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            System.out.println("debug >> service ready writer");

            // writer
            StringBuffer sb = new StringBuffer();
            int cnt = 0;
            for( String key : map.keySet() ){
                if(cnt >= 1){
                    sb.append("&");
                }
                cnt += 1;
                sb.append(key + map.get(key));
            }
            writer.write(sb.toString());


            // for(String key : map.keySet()){
            //     System.out.println("debug >> key : " + key);
            // }

            int http_status = conn.getResponseCode();
            System.out.println("debug >> service http_status : " + http_status);
            // 서버로부터 응답된 내용을 전달하는 스트림
            BufferedReader reader = null;
            if(http_status == 200){
                System.out.println("debug >> service status 200");
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }else{
                System.out.println("debug >> service status != 200");
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
