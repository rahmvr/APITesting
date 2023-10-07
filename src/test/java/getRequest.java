import com.github.javafaker.Faker;
import dto.Employee;
import dto.FavFood;
import dto.Marks;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;

public class getRequest {


    @Test()
    public void get() {

        Response response = given()
                .get("http://localhost:3000/employees").prettyPeek();

        System.out.println(response.statusCode());

        System.out.println("____________________________________________________________");
/*

        Headers headers = response.headers();
        for(Header header: headers){
            System.out.println(header.getName()+ ":"+header.getValue());
        }
*/

    }

    @Test
    public void post() {

        //1. Passing Json body directly or as string --> Not recommended
        //easy to copy paste --> can be used to quickly check the behavior
        //not recommended for larger json or dynamic

        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body("  {\n" +
                        "      \"id\": 4,\n" +
                        "      \"first_name\": \"Sebastian\",\n" +
                        "      \"last_name\": \"Eschweiler\",\n" +
                        "      \"email\": \"sebastian@codingthesmartway.com\"\n" +
                        "    }")
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }

    @Test
    public void post2() {

        //Passing Json body from external file.
        //1. you cannot get the request from the file and print it on the console
        //2. Use this only for static data.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body(new File(System.getProperty("user.dir") + "/src/test/resources/test.json"))
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }

    @Test
    public void post3() throws IOException {

        String data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/test.json")));
        String payload = data.replace("ids", String.valueOf(new Faker().number().numberBetween(2000, 9999)));

        //Passing Json body from external file.
        //1. you cannot get the request from the file and print it on the console
        //2. Use this only for static data.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body(payload)
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }

    @Test
    public void post4() {
        //Using Map and list form java // need jackson library.
        //{} ->map interface ->> LinkedHashMap to maintain an order
        //[] -> List
        // verbose and not suitable for very big json files.
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("id", new Faker().number().numberBetween(2000, 9999));
        payload.put("fname", new Faker().name().firstName());
        payload.put("lname", new Faker().name().lastName());
        payload.put("email", "xya@gmail.com");
        payload.put("jobs", Arrays.asList("SDET", "Cook"));

        Map<String, Object> food = new LinkedHashMap<>();
        food.put("BFast", "eggs");
        food.put("Dinner", Arrays.asList("bean", "bear"));

        payload.put("food", food);

        //Passing Json body from external file.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body(payload)
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }

    @Test
    public void post5() {
        //Using Json library.
        //{} ->map interface ->> LinkedHashMap to maintain an order
        //[] -> List
        // verbose and not suitable for very big json files.

        JSONObject payload = new JSONObject();
        payload.put("id", new Faker().number().numberBetween(2000, 9999));
        payload.put("fname", new Faker().name().firstName());
        payload.put("lname", new Faker().name().lastName());
        payload.put("email", "xya@gmail.com");
        payload.put("jobs", Arrays.asList("SDET", "Cook"));

        // checks if key isPresent in json payload,then it add this additional to make it an array
        payload.accumulate("email", "abc@gmail.com ");
        // checks if key isPresent in json payload,then it doesn't add if it is preset.
        //payload.putOnce("email", "abc2@gmail.com ");

        JSONObject food = new JSONObject();
        food.put("BFast", "eggs");
        food.put("Dinner", Arrays.asList("bean", "bear"));

        payload.put("food", food);

        //Below error is seen when we pass json data directly, so we use Jackson lib along with json to overcome
        // pass payload object to body as -> payload.toString() or payload.toMap(), like in:line 174

        /*{
            "empty": false,
                "mapType": "java.util.HashMap",
                "id": "612",
                "createdAt": "2023-10-06T20:23:44.846Z"
        }*/

        //Passing Json body from external file.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body(payload.toMap())//Jackson helps to serialize
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }

    @Test
    public void post6() {
        //Using pojo class
        FavFood favFood = new FavFood("idli", Arrays.asList("oat","bread"));

        Marks mark1 = new Marks("10","20");
        Marks mark2 = new Marks ("10","30");

        List<Marks> marks = Arrays.asList(mark1,mark2);

        // sending data via, constructor is better than, using setters in case user wants to send immutable objects, then remove setter from dtos.
        // the setter, getter and constructors from dts can be replaced by annotations of lombok
        Employee paylaod =
                new Employee(123,null,"bha","rdd.gmail.com",favFood,Arrays.asList("SDET", "Cooking"),marks);

        //Passing Json body from external file.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
                .body(paylaod)
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
        Employee resDeEmployee = response.as(Employee.class);
        System.out.println(resDeEmployee.getJobs());

        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
        response.then().body(JsonSchemaValidator.matchesJsonSchema
                (new File(System.getProperty("user.dir") + "/src/test/resources/test.json")));
    }

}
