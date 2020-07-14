package workshop.springb.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workshop.springb.web.model.Hobbit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /*
        TODO 5 testy - wysyłamy Saurona (domyślnie błąd walidacji zmapuje się do HTTP 400) i Bilba
     */
    @Test
    void validateHobbit_sauron_shouRetun400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/validate-hobbit")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(new Hobbit("Sauron", false)))
        )
                .andExpect(status().isBadRequest());

    }

    @Test
    void validateHobbit_notSauron_shouldReturn200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/validate-hobbit")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(new Hobbit("Bilbo", false)))
        )
                .andExpect(status().isOk());
    }

    /*
        TODO 6 po zapoznaniu się z zaimplementowaną walidacją, utwórz analogiczne rozwiązanie,
         z walidacją Saruman'a.
     */
}