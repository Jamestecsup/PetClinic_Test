package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dtos.VisitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateVisit() throws Exception {
        VisitDTO newVisit = newVisit("integration create visit", "2025-03-12");

        mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVisit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.petId", is(1)))
                .andExpect(jsonPath("$.vetId", is(1)))
                .andExpect(jsonPath("$.description", is("integration create visit")))
                .andExpect(jsonPath("$.cost", is(75.5)));
    }

    @Test
    void testUpdateVisit() throws Exception {
        Integer id = createVisit("integration visit before update");
        VisitDTO update = newVisit("integration visit updated", "2025-03-14");
        update.setCost(new BigDecimal("99.00"));

        mockMvc.perform(put("/visits/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.description", is("integration visit updated")))
                .andExpect(jsonPath("$.visitDate", is("2025-03-14")))
                .andExpect(jsonPath("$.cost", is(99.0)));

    }

    @Test
    void testDeleteVisit() throws Exception {
        Integer id = createVisit("integration visit to delete");

        mockMvc.perform(delete("/visits/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/visits/{id}", id))
                .andExpect(status().isNotFound());
    }

    private Integer createVisit(String description) throws Exception {
        MvcResult result = mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVisit(description, "2025-03-13"))))
                .andExpect(status().isCreated())
                .andReturn();

        return JsonPath.parse(result.getResponse().getContentAsString()).read("$.id", Integer.class);
    }

    private VisitDTO newVisit(String description, String date) {
        return VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.parse(date))
                .description(description)
                .cost(new BigDecimal("75.50"))
                .build();
    }
}
