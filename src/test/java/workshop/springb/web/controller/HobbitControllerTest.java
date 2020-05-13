package workshop.springb.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workshop.springb.web.model.Hobbit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static workshop.springb.web.controller.GlobalExceptionHandler.SARUMAN_EXCEPTION_HANDLER_MSG;
import static workshop.springb.web.controller.GlobalExceptionHandler.SAURON_EXCEPTION_HANDLER_MSG;

@SpringBootTest
@AutoConfigureMockMvc
class HobbitControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setOut() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void validateHobbit_sauron_shouldLogAndReturn4004() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/validate-hobbit")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(new Hobbit("Sauron", false)))
        )
                .andExpect(status().is(4004));

        assertTrue(outContent.toString().contains(SAURON_EXCEPTION_HANDLER_MSG));
    }

    @Test
    void validateHobbit_saruman_shouldLogAndReturn4004() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/validate-hobbit")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(new Hobbit("Saruman", false)))
        )
                .andExpect(status().is(4004));

        assertTrue(outContent.toString().contains(SARUMAN_EXCEPTION_HANDLER_MSG));
    }

    @Test
    void validateHobbit_notSauron_shouldNotLogAndReturn200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/validate-hobbit")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(new Hobbit("Bilbo", false)))
        )
                .andExpect(status().isOk());

        assertTrue(outContent.toString().isEmpty());
    }
}

/*
    ____________________________________________________________________________________________________________________

    TODO 1

    W tym module zapoznamy się z obsługą wyjątków w warstwie kontrolera.

    W HobbitControllerTest mamy dwa testy

    validateHobbit_sauron_shouldLogAndReturn4004:

    Wysyłamy Hobbita z name = Sauron, oczekujemy błędu 4004 ( tak, to nasz własny kod ;)),
    oraz zalogowania błędu.

    validateHobbit_notSauron_shouldNotLogAndReturn200:

    Wysyłamy Hobbita z name != Sauron, oczekujemy kodu 200 i braku logowania błędu.

    Przejdźmy do kolejnego todos'a, żeby zobaczyć co dzieje się w logice aplikacji.
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
