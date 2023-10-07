package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)// use to remove the object if is empty from passing a payload.
@JsonPropertyOrder(value = {"jobs","firstName"}) // to maintain order
@JsonIgnoreProperties(value = {"lastNmae","createdAt"}) // assign here ignore multi json objects
@NoArgsConstructor
public class Employee {

    private int id;
    private String firstName;
    private String lastNmae;
    @JsonIgnore // to ignore a single object
    private String email;
    private FavFood favFood;
    private List<String> jobs;
    private List<Marks> marks;


/*

    public Employee(int id, String firstName, String lastNmae, String email, FavFood favFood, List<String> jobs, List<Marks> marks) {
        this.id = id;
        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.email = email;
        this.favFood = favFood;
        this.jobs = jobs;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FavFood getFavFood() {
        return favFood;
    }

    public void setFavFood(FavFood favFood) {
        this.favFood = favFood;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

*/

}
