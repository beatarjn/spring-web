package workshop.springb.web.controller;

import org.springframework.web.bind.annotation.*;
import workshop.springb.web.model.Hobbit;

@RestController
public class HobbitController {

    @GetMapping("/hobbits-path-varialble/{name}/{lastName}")
    public Hobbit pathVariableExample(@PathVariable String name, @PathVariable String lastName) {
        return new Hobbit(name.toUpperCase(), lastName.toUpperCase());
    }

    @GetMapping("/hobbits-arguments")
    public Hobbit requestParamExample(@RequestParam String name, @RequestParam String lastName) {
        return new Hobbit(name.toUpperCase(), lastName.toUpperCase());
    }

    @PostMapping("/hobbits-json-object")
    public Hobbit requestBodyExample(@RequestBody Hobbit hobbit) {
        return new Hobbit(hobbit.getName().toUpperCase(), hobbit.getLastName().toUpperCase());
    }

    @GetMapping("/hobbits-matrix/{hobbit}")
    public Hobbit matrixVariableExample(@MatrixVariable String name, @MatrixVariable String lastName) {
        return new Hobbit(name.toUpperCase(), lastName.toUpperCase());

//    public Hobbit matrixVariableExample(@MatrixVariable Map<String, String> matrixVars) {
//        return new Hobbit(matrixVars.get("name").toUpperCase(), matrixVars.get("lastName").toUpperCase());
    }

}
    /*
    ____________________________________________________________________________________________________________________
    TODO W tym module przećwiczymy 4 sposoby na przyjmowanie danych z żądania HTTP:
     @PathVariable, @RequestParam, @RequestBody, @MatrixVariable

    Dla @PathVariable mamy już implementację.
    Utwórz endpoint'y i testy dla pozostałych przypadków.

    DoD:

    Wszystkie testy w HobbitControllerTest na zielono :).

    Potrzebujemy:

    4 endpointy - każdy z nich przyjmuje dane, tworzy na ich podstawie Hobbit'a.
    Przyjmowane dane (w różnej formie) to name i lastName Hobbita.

    Zwracamy hobbita z name i lastName pisanymi wielką literą:

    Bilbo Baggins - > BILBO BAGGINS

    Uwaga - implementujemy najprostsze wariant - bez zabezpieczenia przed NullPointerException itp.


    Zwróć uwagę na klasę konfiguracyjną workshop.springb.web.config.WebConfig,
    ta dodatkowa konfiguracja potrzebna jest dla użycia @MatrixVariable
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
