package workshop.springb.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import workshop.springb.web.model.Hobbit;
import workshop.springb.web.service.HobbitService;

@RestController
public class HobbitController {

    private final HobbitService hobbitService;

    public HobbitController(HobbitService hobbitService) {
        this.hobbitService = hobbitService;
    }

    // TODO 2, endpoint wywołujący serwis, w którym będzie rzucony wyjątek.
    @GetMapping("/validate-hobbit")
    public ResponseEntity<Hobbit> validateHobbit(@RequestBody Hobbit hobbit) {
        hobbitService.validateHobbit(hobbit);

        return ResponseEntity.ok(hobbit);
    }
}