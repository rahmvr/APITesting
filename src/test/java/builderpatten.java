import com.github.javafaker.Faker;
import dto.Student;
import dto.Studentlombok;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class builderpatten {

    @Test
    public void postBuilderPatten() {

        //Normal -> builder patten is acheived by creating setter and getter for class variables, then use static inner class
        // then create a static method for static inner class with builder() and then for build or perform methods to pass inner static data
        // main class constructor.

        //Alternatively,you can also use lombok api use  @builder @Data @AllArguments @NoArguments.
        Student payload = Student.builder()
                .setId(new Faker().number().numberBetween(11,99))
                .setfName(new Faker().name().firstName())
                .setlName(new Faker().name().lastName())
                .setEmail(new Faker().name().fullName())
                .build();

        Studentlombok payload2 = Studentlombok.builder()
                .id(new Faker().number().numberBetween(11,99))
                .fName(new Faker().name().firstName())
                .lName(new Faker().name().lastName())
                .email(new Faker().name().fullName())
                .build();

        //Passing Json body from external file.
        Response response = given()
                //.header("Content-Type","application/json")
                .header("Content-Type", ContentType.JSON)
             //   .body(payload)
                .body(payload2)
                .log().all()
                .post("https://reqres.in/api/users")
                .prettyPeek();

        System.out.println("____________________________________________________________");
        System.out.println("Response code: " + response.statusCode());
    }
}
