package workshop.springb.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import workshop.springb.web.model.Hobbit;
import workshop.springb.web.service.HobbitService;

import javax.validation.Valid;

@RestController
public class GreetController {

    private final HobbitService hobbitService;

    public GreetController(HobbitService hobbitService) {
        this.hobbitService = hobbitService;
    }

    @PostMapping("/validate-hobbit")
    public ResponseEntity<Hobbit> validateHobbit(@Valid @RequestBody Hobbit hobbit) {
        hobbitService.validate(hobbit);

        return ResponseEntity.ok(hobbit);
    }

}

/*
    ____________________________________________________________________________________________________________________

    TODO 1

    W tym module poćwiczymy walidacje.

    Ten przykład jest bardzo podobny do ćwiczena z @ControllerAdvice, różnice - nie rzucamy
    wyjątku w metodzie serwisowej, walidujemy dane już na wejściu.

    Zwróć uwagę na adnotację @Valid:
    validateHobbit(@Valid @RequestBody Hobbit hobbit)

    Zajrzyjmy do kolejnego todos'a w klasie Hobbit, żeby zobaczyć co jest walidowane.


    ____________________________________________________________________________________________________________________
                                                 \
                                                  \
                                                /  \~~~/  \
                                                (    ..     )----,
                                                \__     __/      \
                                                  )|  /)         |\
                                                   | /\  /___\   / ^
                                                    "-|__|   |__|
 */