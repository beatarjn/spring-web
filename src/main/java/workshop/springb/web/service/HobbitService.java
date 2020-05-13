package workshop.springb.web.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import workshop.springb.web.controller.SarumanException;
import workshop.springb.web.controller.SauronException;
import workshop.springb.web.model.Hobbit;
import workshop.springb.web.controller.GlobalExceptionHandler;

@Service
public class HobbitService {

    /*
        TODO 3 jeśli predykat isSauronPredicate zwróci true, rzucony zostanie wyjątek
         SauronException

         Zobaczmy klasę, w której zdefiniowany jest isSauronPredicate
     */
    @SneakyThrows
    public void validateHobbit(Hobbit hobbit) {
        if (GlobalExceptionHandler.isSauronPredicate.test(hobbit)) {
            throw new SauronException();
        } else if (GlobalExceptionHandler.isSarumanPredicate.test(hobbit)) {
            throw new SarumanException();
        }

        hobbit.setValid(true);
    }

}