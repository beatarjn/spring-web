package workshop.springb.web.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import workshop.springb.web.model.Hobbit;

import javax.servlet.http.HttpServletResponse;
import java.util.function.Predicate;

/*
    TODO 3

    Użycie adnotacji @ControllerAdvice wraz z rozszerzeniem ResponseEntityExceptionHandler
    pozwala na scentralizowaną obsługę wyjątków w metodach z adnotacją @ExceptionHandler zdefiniowanych w tej klasie,

    @Log4j2 - adnotacja lombok, dzięki niej mamy dostęp do loggera
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO 4 stałe użyte w aplikacji i teście
    public static final String SAURON_EXCEPTION_HANDLER_MSG = "Sauron has been spotted!";
    public static final String SARUMAN_EXCEPTION_HANDLER_MSG = "Saruman has been spotted!";
    public static final int CUSTOM_HTTP_STATUS = 4004;

    public static final Predicate<Hobbit> isSauronPredicate = h -> "Sauron".equalsIgnoreCase(h.getName());
    public static final Predicate<Hobbit> isSarumanPredicate = h -> "Saruman".equalsIgnoreCase(h.getName());

    /*
        TODO 5  - tutaj obsługujemy SauronException
        Jesli w warstwie kontrolera pojawi się SauronException, rzucony w warstwie serwisu,
        metoda handleSauron zaloguje błąd i w odpowiedzi ustawi CUSTOM_HTTP_STATUS

        @SneakyThrows - adnotacja Lombok, zamiast użycia throws
     */
    @SneakyThrows
    @ExceptionHandler(value = {SauronException.class})
    public void handleSauron(HttpServletResponse response) {
        log.info(SAURON_EXCEPTION_HANDLER_MSG);
        response.sendError(CUSTOM_HTTP_STATUS);
    }

    @SneakyThrows
    @ExceptionHandler(value = {SarumanException.class})
    public void handleSaruman( HttpServletResponse response) {
        log.info(SARUMAN_EXCEPTION_HANDLER_MSG);
        response.sendError(CUSTOM_HTTP_STATUS);
    }

    /*
        TODO 6
        Zadanie - analogicznie do przykładu, utwórz

        1. SarumanException (rozszerzające Exception)
        2. W HobbitService#validateHobbit dodaj sprawdzenie, czy hobbit nazywa się Saruman
        3. Dodaj odpowiednią logikę w GlobalExceptionHandler
        4. Napisz odpowiedni test

     */
}