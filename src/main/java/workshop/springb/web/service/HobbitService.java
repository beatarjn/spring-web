package workshop.springb.web.service;

import org.springframework.stereotype.Service;
import workshop.springb.web.model.Hobbit;

@Service
public class HobbitService {

    public void validate(Hobbit hobbit) {
        hobbit.setValid(true);
    }

}