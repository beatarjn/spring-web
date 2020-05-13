package workshop.springb.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Hobbit {

    private String name;
    private boolean isValid;
}
