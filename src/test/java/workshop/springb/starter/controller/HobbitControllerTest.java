package workshop.springb.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import workshop.springb.starter.model.Hobbit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static workshop.springb.starter.controller.HobbitController.*;

@SpringBootTest
@AutoConfigureMockMvc
class HobbitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Test
    @DisplayName("GET http://localhost/hobbit-as-string-> Bilbo Baggins as String")
    public void hobbitAsString_shouldReturnBilboAsString() throws Exception {

        /*
            Żądanie GET /hobbit-as-string
            Oczekiwana odpowiedź: Hobbit(name=Bilbo, lastName=Baggins)

         */
        mockMvc.perform(MockMvcRequestBuilders.get("/hobbit-as-string")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Hobbit(name=Bilbo, lastName=Baggins)"));

    }

    @Test
    @DisplayName("GET http://localhost/hobbit-as-object-> Bilbo Baggins as JSON")
    public void hobbitAsObject_shouldReturnBilboAsJson() throws Exception {


        /*
            Żądanie GET /hobbit-as-object
            Oczekiwana odpowiedź: Hobbit w postaci JSON

            MAPPER zmienia odpowiedż do postci obiektu Javy.

         */
        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hobbit-as-object")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertEquals(hobbit, returnedHobbit);

    }

    @Test
    @DisplayName("GET http://localhost/hobbit-as-response-entity -> Bilbo Baggins  as JSON, with a custom header")
    public void hobbitAsResponseEntity_shouldReturnBilboAsJson() throws Exception {


        /*
            Żądanie GET /hobbit-as-response-entity
            Oczekiwana odpowiedź: Hobbit w postaci JSON
            Własna wartość w nagłówku HTTP

         */

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hobbit-as-response-entity"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertAll(
                () -> assertEquals(mvcResult.getResponse().getHeader(CUSTOM_HEADER), CUSTOM_HEADERS_VALUE),
                () -> assertEquals(hobbit, returnedHobbit)
        );
    }

    @Test
    @DisplayName("POST http://localhost/hobbit-as-html -> Bilbo Baggins as a HTML page")
    public void hobbitAsHTML_shouldReturnBilboAsHTML() throws Exception {

        /*
            Żądanie GET /hobbit-as-html
            Oczekiwana odpowiedź: przekierowanie na stronę HTML (użycie xpath dla przetestowania odpowiedzi)

         */
        mockMvc.perform(MockMvcRequestBuilders.get("/hobbit-as-html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(xpath("//p[1]").string("Name: Bilbo"))
                .andExpect(xpath("//p[2]").string("Last name: Baggins"));

    }

}