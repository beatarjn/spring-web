package workshop.springb.starter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import workshop.springb.starter.model.Hobbit;

/*
    TODO 2
    Zwróć uwagę, że klasa ma adnotację @Controller
    Domyślne zachowanie - metody kontrolera będą przekierowywały na widok html.
    Jeśli chcemy, żeby metody kontrolera nie przekierowywały na widko, a zwracały String, obiekt w postaci JSON itp.
    Dodajemy adnotaccję @ResponseBody

    Wcześniej nie robilismy tego, bo zamiast @Controller używaliśmy @RestController.
    @RestController = @Controller + @ResponseBody
 */
@Controller
public class HobbitController {

    public static final Hobbit hobbit = new Hobbit("Bilbo", "Baggins");
    public static final String CUSTOM_HEADER = "CUSTOM_HEADER";
    public static final String CUSTOM_HEADERS_VALUE = "Just a value";

    /*
        TODO 4 zaimplementuj poniższe endpointy tak, żeby przeszły wszystkie testy w HobbitControllerTest

        /hobbit-as-string - zwraca Hobbit#toString

        /hobbit-as-object - zwraca obiekt Hobbit

        /hobbit-as-response-entity zwraca ResponseEntity z Hobbit'em i własnym nagłówkiem (dowolny)

     */
    @GetMapping("/hobbit-as-string")
    @ResponseBody
    public String returnString() {
        return hobbit.toString();
    }

    @GetMapping("/hobbit-as-object")
    @ResponseBody
    public Hobbit returnObject() {
        return hobbit;
    }


    @GetMapping("/hobbit-as-response-entity")
    @ResponseBody
    public ResponseEntity<Hobbit> returnResponseEntity() {
        return ResponseEntity.ok()
                .header(CUSTOM_HEADER, CUSTOM_HEADERS_VALUE)
                .body(hobbit);

    }

    /*
        TODO 3
        W metodzie returnHtmlView użyta adnotacja  @RequestMapping zamiast @GetMapping,
        @RequestMapping jest starsza, dostarcza więcej funkcjonalności (np. możemy jendą metodą
        obsługiwać GET i POST). Możesz poeksperymentować i zamienić @RequestMapping na @GetMapping (lub odwrotnie).

     */
    @RequestMapping("/hobbit-as-html")
    public ModelAndView returnHtmlView(ModelAndView modelAndView) {
        modelAndView.addObject(hobbit);
        modelAndView.setViewName("hobbit");
        return modelAndView;
    }
}

/*
    ____________________________________________________________________________________________________________________

    TODO 1 W tym module przećwiczymy 4 sposoby, na zwracanie danych z kontrolera:
     typ prosty (String),
     Obiekt,
     ResponseEntity (Hobbit i własne dane w nagłówku HTTP),
     przekierowanie na widok HTML  - jest już zaimplementowane.

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
