package CRUD.TEST1.Api;

import CRUD.TEST1.dto.requestDto.MovieRequestDto;
import CRUD.TEST1.dto.responseDto.GetActorResponseDto;
import CRUD.TEST1.dto.responseDto.GetBoxofficeResponseDto;
import CRUD.TEST1.dto.responseDto.GetByDateResponseDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
@Slf4j
@Component
public class MovieApi {
    private final String AUTH_KEY = "bb47b879db92cf5cb2121fe2d22178c7";
    ObjectMapper mapper = new ObjectMapper();
    public String makeQueryString(Map<String,String> map,String requestUrl){
        StringBuilder sb = new StringBuilder();
        map.entrySet().forEach((entry) -> {
            if(sb.isEmpty()){
                sb.append(requestUrl + "?");
            }else if (!sb.isEmpty()) {
                sb.append("&");
            }
            sb.append(entry.getKey() + "=" + entry.getValue());
        });
        return sb.toString();
    }

    public ResponseDto<?> getByActorName(MovieRequestDto requestDto){
        String requestUrl = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleList.json";
        JSONObject jsonObject = requestApi(requestDto, requestUrl);
        System.out.println(jsonObject);
        JSONObject peopleListResult = (JSONObject)jsonObject.get("peopleListResult");
        JSONArray peopleList = (JSONArray) peopleListResult.get("peopleList");

//        List<MovieResponseDto> movieResponseDtoList =
//                Arrays.asList(mapper.readValue(peopleList.toString(), MovieResponseDto[].class));
        try {
            List<GetActorResponseDto> getActorResponseDtoList = mapper.readValue(peopleList.toString(), new TypeReference<>() {});
            return ResponseDto.success(getActorResponseDtoList);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return ResponseDto.fail("안되부러잉","이유는 나두 몰러잉");
    }

    public ResponseDto<?> getBoxOffice(MovieRequestDto requestDto){
        String requestUrl = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
        JSONObject jsonObject = requestApi(requestDto, requestUrl);
        JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
        JSONArray weeklyBoxOfficeList = (JSONArray) boxOfficeResult.get("weeklyBoxOfficeList");
        try {
            List<GetBoxofficeResponseDto> getBoxofficeResponseDtoList = mapper.readValue(weeklyBoxOfficeList.toString(), new TypeReference<>() {});
            return ResponseDto.success(getBoxofficeResponseDtoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseDto.fail("안되부러잉","이유는 나두 몰러잉");
    }

    public ResponseDto<?> getByDate(MovieRequestDto requestDto){
        String requestUrl = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
        JSONObject jsonObject = requestApi(requestDto, requestUrl);
        JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
        JSONArray movieList = (JSONArray) movieListResult.get("movieList");
        try {
           List<GetByDateResponseDto> getByDateResponseDtoList = mapper.readValue(movieList.toString(), new TypeReference<>() {
            });
           return ResponseDto.success(getByDateResponseDtoList);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseDto.fail("안되부러잉","이유는 나두 몰러잉");
    }

    public JSONObject requestApi(MovieRequestDto requestDto, String requestUrl){
        Map<String,String> map = new HashMap<>();
        map.put("key",AUTH_KEY);
        if(requestUrl.contains("searchPeopleList")){
            map.put("peopleNm",requestDto.getPeopleNm());
            map.put("curPage",requestDto.getCurPage());
            map.put("itemPerPage",requestDto.getItemPerPage());
            map.put("filmoNames",requestDto.getFilmoNames());
        } else if (requestUrl.contains("searchWeeklyBoxOfficeList")) {
            map.put("targetDt",requestDto.getTargetDt());
            map.put("weekGb",requestDto.getWeekGb());
            map.put("multiMovieYn",requestDto.getMultiMovieYn());
            map.put("itemPerPage",requestDto.getItemPerPage());
            map.put("repNationCd",requestDto.getRepNationCd());
            map.put("wideAreaCd",requestDto.getWideAreaCd());
        } else if (requestUrl.contains("searchMovieList")) {
            map.put("itemPerPage",requestDto.getItemPerPage());
            map.put("openStartDt",requestDto.getOpenStartDt());
            map.put("openEndDt",requestDto.getOpenEndDt());
        }
        try {
            URL url = new URL(makeQueryString(map,requestUrl));
            System.out.println("url = " + makeQueryString(map,requestUrl));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            return (JSONObject) new JSONParser().parse(br.readLine());
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return null;
    }


}
