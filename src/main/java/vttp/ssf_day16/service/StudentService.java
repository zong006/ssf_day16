package vttp.ssf_day16.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf_day16.model.Carpark;
import vttp.ssf_day16.model.Country;
import vttp.ssf_day16.model.DailyStockData;
import vttp.ssf_day16.model.Joke;
import vttp.ssf_day16.model.Student;
import vttp.ssf_day16.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public void addStudent(Student s){
        studentRepo.create(s.getId(), s.getEmail());
    }

    public List<Student> getAllStudents(){
        Set<String> keys = studentRepo.getKeys();
        List<Student> studentList = new ArrayList<>();
        for (String id : keys){
            String entry = studentRepo.getById(id);
            Student s = generateStudent(id, entry);
            studentList.add(s);
        }
        return studentList;
    }

    public Student getStudentById(String id){
        String entry = studentRepo.getById(id);
        return generateStudent(id, entry);
    }

    public void deleteStudentById(String id){
        studentRepo.deleteById(id);
    }

    public Student generateStudent(String id, String entry){
        Student s = new Student();
        s.setId(id);
        s.setEmail(entry);
        return s;
    }

    // public String practice(){
    public List<Country> practice(){
        // String url = "https://api-open.data.gov.sg/v2/real-time/api/twenty-four-hr-forecast";
        String url = "https://api.first.org/data/v1/countries";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        
        String weather = responseEntity.getBody();

        InputStream is = new ByteArrayInputStream(weather.getBytes());
        JsonReader jsonReader = Json.createReader(is);
        JsonObject jsonData = jsonReader.readObject();

        JsonObject data = jsonData.getJsonObject("data");
        
        Set<String> keys = data.keySet();
        List<Country> countries = new ArrayList<>();
        for (String k : keys){
            
            JsonObject entry = data.getJsonObject(k);
            String region = entry.getString("region");
            Country c = new Country();
            c.setCode(k);
            c.setRegion(region);
            countries.add(c);
        }
        return countries;
        
        // JsonObject data = jsonData.getJsonObject("data");
        // JsonObject records = data.getJsonArray("records").getJsonObject(0);
        // JsonObject general = records.getJsonObject("general");
        // JsonObject temperature = general.getJsonObject("temperature");

        // int low = temperature.getInt("low");
        // int high = temperature.getInt("high");

        // DayTemperature dayTemperature = new DayTemperature(low, high);

        // return dayTemperature;

        // return data.toString();
        
    }

    public List<Carpark> carpark(){

        String url = "https://data.gov.sg/api/action/datastore_search?resource_id=d_9f6056bdb6b1dfba57f063593e4f34ae";
        
        JsonObject jsonData = generateJson(url);
        JsonObject result = jsonData.getJsonObject("result");
        JsonArray records = result.getJsonArray("records");
        JsonObject r = records.getJsonObject(0);

        List<Carpark> carparks = new ArrayList<>();
        for (int i = 0 ; i < records.size() ; i++){
            Carpark carpark = new Carpark();
            JsonObject record = records.getJsonObject(i);
            carpark.setId(Integer.toString(i+1));
            carpark.setName(record.getString("carpark"));
            carpark.setCategory(record.getString("category"));
            carpark.setWeekdayRate1(record.getString("weekdays_rate_1"));
            carpark.setWeekdayRate2(record.getString("weekdays_rate_2"));
            carpark.setSaturdayRate(record.getString("saturday_rate"));
            carpark.setSundayPhRate(record.getString("sunday_publicholiday_rate"));
            carparks.add(carpark);
        }
    
        return carparks;
    }


    // public List<Joke> getJoke(){
    //     String url = "http://www.official-joke-api.appspot.com/random_ten";
    //     JsonArray jsonData = generateJson(url);

    //     List<Joke> jokes = new ArrayList<>();
    //     for (int i = 0 ; i < jsonData.size() ; i++){
    //         JsonObject j = jsonData.getJsonObject(i);
    //         Joke joke = new Joke();
    //         joke.setSetup(j.getString("setup"));
    //         joke.setPunchline(j.getString("punchline"));
    //         jokes.add(joke);
    //     }
    //     return jokes;
    // }

    public List<DailyStockData> getAlphavantageData(String function, String symbol, String apikey){
        String url = "https://www.alphavantage.co/query?"
                        + "function=" + function
                        + "symbol=" + symbol
                        + "apikey=" + apikey;
        System.out.println(url);
// function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey=demo
        JsonObject jsonData = generateJson(url);

        System.out.println(jsonData);
        JsonObject timeSeriesDaily = jsonData.getJsonObject("Time Series (Daily)");

        List<DailyStockData> dailyStockDataList = new ArrayList<>();
        Set<String> keys = timeSeriesDaily.keySet();
        for (String key : keys){
            DailyStockData dailyStockData = new DailyStockData();
            JsonObject entry = timeSeriesDaily.getJsonObject(key);
            String dayOpen = entry.getString("1. open");
            String dayClose = entry.getString("4. close");

            dailyStockData.setDayOpen(dayOpen);
            dailyStockData.setDayClose(dayClose);
            dailyStockDataList.add(dailyStockData);
        }
        return dailyStockDataList;
    }

    public JsonObject generateJson(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        
        String respBody = responseEntity.getBody();
        InputStream is = new ByteArrayInputStream(respBody.getBytes());
        JsonReader jsonReader = Json.createReader(is);
        JsonObject jsonData = jsonReader.readObject();
        // JsonArray jsonData = jsonReader.readArray();
        
        return jsonData;
    }

}
