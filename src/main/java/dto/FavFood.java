package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavFood {

    private String breakFast;
    private List<String> dinner;

/*
    public FavFood(String breakFast, List<String> dinner) {
        this.breakFast = breakFast;
        this.dinner = dinner;
    }

    public String getBreakFast() {
        return breakFast;
    }

    public void setBreakFast(String breakFast) {
        this.breakFast = breakFast;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
*/

}
