package workshop.springb.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import workshop.springb.web.model.Hobbit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class HobbitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Hobbit hobbitToBeSent = new Hobbit("Billbo", "Baggins");

    @Test
    @DisplayName("GET http://localhost/hobbits-path-varialble/Bilbo/Baggins -> a Hobbit [BILLBO BAGGINS]")
    public void hobbitsPathVariable_shouldReturnBilbo() throws Exception {

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/hobbits-path-varialble/%s/%s", hobbitToBeSent.getName(), hobbitToBeSent.getLastName()))
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertAll(
                () -> assertEquals(hobbitToBeSent.getName().toUpperCase(), returnedHobbit.getName()),
                () -> assertEquals(hobbitToBeSent.getLastName().toUpperCase(), returnedHobbit.getLastName())
        );
    }

    @Test
    @DisplayName("GET http://localhost/hobbits-arguments?name=Bilbo&lastName=Baggins -> a Hobbit [BILLBO BAGGINS]")
    public void hobbitsArguments_shouldReturnBilbo() throws Exception {

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/hobbits-arguments?name=%s&lastName=%s", hobbitToBeSent.getName(), hobbitToBeSent.getLastName()))
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertAll(
                () -> assertEquals(hobbitToBeSent.getName().toUpperCase(), returnedHobbit.getName()),
                () -> assertEquals(hobbitToBeSent.getLastName().toUpperCase(), returnedHobbit.getLastName())
        );
    }

    @Test
    @DisplayName("POST http://localhost/hobbits-json-object -> a Hobbit [BILLBO BAGGINS]")
    public void hobbitsJsonObject_shouldReturnBilbo() throws Exception {

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/hobbits-json-object")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(hobbitToBeSent)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertAll(
                () -> assertEquals(hobbitToBeSent.getName().toUpperCase(), returnedHobbit.getName()),
                () -> assertEquals(hobbitToBeSent.getLastName().toUpperCase(), returnedHobbit.getLastName())
        );
    }

    @Test
    @DisplayName("GET http://localhost/hobbits-matrix/hobbit-data/name=Billbo;lastName=Baggins -> a Hobbit [BILLBO BAGGINS]")
    public void hobbitsMatrix_shouldReturnBilbo() throws Exception {

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/hobbits-matrix/name=%s;lastName=%s", hobbitToBeSent.getName(), hobbitToBeSent.getLastName()))
                .contentType("application/json"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Hobbit returnedHobbit = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Hobbit.class);

        assertAll(
                () -> assertEquals(hobbitToBeSent.getName().toUpperCase(), returnedHobbit.getName()),
                () -> assertEquals(hobbitToBeSent.getLastName().toUpperCase(), returnedHobbit.getLastName())
        );
    }
}