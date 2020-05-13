package workshop.springb.web.controller;

import org.springframework.web.bind.annotation.*;
import workshop.springb.web.model.Hobbit;

import java.util.Map;

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
        hobbit.setName(hobbit.getName().toUpperCase());
        hobbit.setLastName(hobbit.getLastName().toUpperCase());
        return hobbit;

    }

    @GetMapping("/hobbits-matrix/{hobbit-data}")
    public Hobbit matrixVariableExample(@MatrixVariable Map<String, String> matrix) {
        return new Hobbit(matrix.get("name").toUpperCase(), matrix.get("lastName").toUpperCase());
    }

}