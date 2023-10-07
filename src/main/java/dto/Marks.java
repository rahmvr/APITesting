package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Marks {

    private String tamil;
    private String english;

/*    public Marks(String tamil, String english) {
        this.tamil = tamil;
        this.english = english;
    }

    public String getTamil() {
        return tamil;
    }

    public void setTamil(String tamil) {
        this.tamil = tamil;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }*/
}
