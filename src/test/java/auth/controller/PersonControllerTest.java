package auth.controller;

import auth.Person;
import auth.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonRepository personRepository;

    @Test
    @WithMockUser
    public void findAllTest() throws Exception {

        Person first = new Person(1, "New PersonOne", "123");
        Person second = new Person(2, "New PersonTwo", "456");
        List persons = new ArrayList();
        persons.add(first);
        persons.add(second);

        Mockito.when(personRepository.findAll()).thenReturn(persons);
        mockMvc.perform(
                get("/")
                        .content(objectMapper.writeValueAsString(persons))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(persons)));



//        Person first = new Person(1, "New PersonOne", "123");
//        Person second = new Person(2, "New PersonTwo", "456");
//        List persons = new ArrayList();
//        persons.add(first);
//        persons.add(second);
//
//        when(personRepository.findAll()).thenReturn(persons);
//
//        this.mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("student-list"))
//                .andExpect(model().attribute("students", persons))
//                .andExpect(model().attribute("students", Matchers.hasSize(2)))
//                .andDo(print());
    }


    @Test
    public void findById() throws Exception {

        Person first = new Person(1, "New PersonOne", "123");

        when(personRepository.findById(1)).thenReturn(Optional.of(first));

        this.mockMvc.perform(get("/{1}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"id\":1,\"login\":\"New PersonOne\",\"password\":\"123\"}")))
                .andDo(print());

    }

    @Test
    public void create() throws Exception {

        Person first = new Person(1, "New PersonOne", "123");

        when(personRepository.save(first)).thenReturn(first);

        this.mockMvc
                .perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\",\"login\": \"New PersonOne\",\"password\": \"123\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.containsString(
                        "http://localhost/1"))).andDo(print());

    }

    @Test
    public void update() throws Exception {

        Person first = new Person(1, "New PersonOne", "123");

        this.mockMvc.perform(post("/foo/update")
                .param("name", "")
                .sessionAttr("foo", first))
                .andExpect(forwardedUrl("foo/detail"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("foo", "name"));

    }

    @Test
    public void delete() {

        Person first = new Person(1, "New PersonOne", "123");
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(Optional.of(first));
        mockMvc.perform(delete("/{1}"))
                .andExpect(status().isOk());
    }
}