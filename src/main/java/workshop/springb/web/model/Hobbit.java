package workshop.springb.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import workshop.springb.web.validators.IsNotSaruman;
import workshop.springb.web.validators.IsNotSauron;

@Data
@AllArgsConstructor
public class Hobbit {

    /*
        TODO 2
        Adnotacja @Valid, podczas mapowania Hobbita z formatu JSON do obiektu Javy,
        sprawdzi pola z adnotacjami (gowymi jak np @Min lub własnymi jak @IsNotSauron).

        W naszym przykłądzie mamy własną adntotację. Żeby cały mechanizm zadziałał musieliśmy
        utworzyć naszą adnotacją, oraz zaimplementować ConstraintValidator (todosy 3 i 4)
     */
    @IsNotSauron
    @IsNotSaruman
    private String name;
    private boolean isValid;

}
