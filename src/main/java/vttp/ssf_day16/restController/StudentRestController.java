package vttp.ssf_day16.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp.ssf_day16.model.Carpark;
import vttp.ssf_day16.model.Country;
import vttp.ssf_day16.model.DailyStockData;
import vttp.ssf_day16.model.Joke;
import vttp.ssf_day16.model.Student;
import vttp.ssf_day16.service.StudentService;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class StudentRestController {
    
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Student s){

        studentService.addStudent(s);
        // return new ResponseEntity<>("true", HttpStatus.OK);
        return ResponseEntity.ok().body("created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getEntry(@PathVariable String id){
        Student s = studentService.getStudentById(id);
        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        studentService.deleteStudentById(id);

        return ResponseEntity.ok().body("deleted");
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.ok().body(studentList);
    }

    @GetMapping("/weather")
    // public ResponseEntity<String> getWeather(){
    public ResponseEntity<List<Country>> getWeather(){
        // String returnValue = studentService.practice();
        List<Country> returnValue = studentService.practice();
        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/carpark")
    public ResponseEntity<List<Carpark>> carpark(){
        List<Carpark> returnValue = studentService.carpark();
        return ResponseEntity.ok().body(returnValue);
    }


    // @GetMapping("/joke")
    // public ResponseEntity<List<Joke>> jokeMethod(){
    //     List<Joke> returnValue = studentService.getJoke();
    //     return ResponseEntity.ok().body(returnValue);
    // }

    @GetMapping("/stock")
    public ResponseEntity<List<DailyStockData>> stockData(
                                                            @RequestParam("function") String function, 
                                                            @RequestParam("symbol") String symbol,
                                                            @RequestParam("apikey") String apikey){
        // function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey=demo
        System.out.println(function);
        System.out.println(symbol);
        System.out.println(apikey);
        System.out.println("===================");
        List<DailyStockData> returnValue = studentService.getAlphavantageData(function, symbol, apikey);
        return ResponseEntity.ok().body(returnValue);
    }


}
