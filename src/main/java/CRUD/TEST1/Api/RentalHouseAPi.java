//package CRUD.TEST1.Api;
//
//import CRUD.TEST1.dto.requestDto.RentalHouseRequestDto;
//import CRUD.TEST1.dto.responseDto.ResponseDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RentalHouseAPi {
//    private final String AUTH_KEY = "IiNP9e1Uaedglxf5MdYo%2FCGVsoqduFVRYD6A4oW2sE7lYbseNu0L0FCAyzf67OtXYg2VQ1yxEfI0JLvO%2BWCgSQ%3D%3D";
//    private final String REQUEST_URL = "https://data.myhome.go.kr:443/rentalHouseList";
//
//    public ResponseDto<?> getRentalHouse(RentalHouseRequestDto requestDto){
//        JSONObject jsonObject = requestApi(requestDto);
//    }
//
//    public JSONObject requestApi(RentalHouseRequestDto requestDto){
//        Map<String,String> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        map.put("brtcCode",requestDto.getBrtcCode());
//        map.put("signguCode",requestDto.getSignguCode());
//        map.put("numOfRows",requestDto.getNumOfRows());
//        map.put("pageNo",requestDto.getPageNo());
//        try {
//            URL url = new URL(makeQueryString(map));
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            return (JSONObject) new JSONParser().parse(br);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String makeQueryString(Map<String,String> map){
//        StringBuilder sb = new StringBuilder();
//        map.entrySet().forEach((entry) ->{
//            if(sb.isEmpty()) {
//                sb.append(AUTH_KEY + "?");
//            } else {
//                sb.append("&");
//            }
//            sb.append(entry.getKey() + "=" + entry.getValue());
//        });
//        return sb.toString();
//    }
//
//}
