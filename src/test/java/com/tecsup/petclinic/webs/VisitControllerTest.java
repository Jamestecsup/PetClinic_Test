package com.tecsup.petclinic.webs;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> Fernando
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

<<<<<<< HEAD
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
=======
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
>>>>>>> Fernando
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
<<<<<<< HEAD
@Slf4j
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindByPetId() throws Exception {

        int PET_ID = 7;

        this.mockMvc.perform(get("/visits").param("petId", String.valueOf(PET_ID)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].petId", is(PET_ID)));
    }

    @Test
    public void testFindByVetId() throws Exception {

        int VET_ID = 2;

        this.mockMvc.perform(get("/visits").param("vetId", String.valueOf(VET_ID)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].vetId", is(VET_ID)));
    }

    @Test
    public void testCalculateTotalCostByPet() throws Exception {

        int PET_ID = 7;

        this.mockMvc.perform(get("/visits/pet/" + PET_ID + "/total-cost"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(320.0)));
    }
=======
class VisitControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testFindAllVisits() throws Exception {
		mockMvc.perform(get("/visits"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", greaterThan(0)));
	}

	@Test
	void testFindVisitOK() throws Exception {
		mockMvc.perform(get("/visits/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.description", is("rabies shot")));
	}

	@Test
	void testFindVisitKO() throws Exception {
		mockMvc.perform(get("/visits/666"))
				.andExpect(status().isNotFound());
	}
>>>>>>> Fernando
}
