package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.services.VisitService;
import com.tecsup.petclinic.util.TObjectCreator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VisitControllerMockitoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VisitService visitService;

    @Test
    public void testFindByPetId() throws Exception {

        int PET_ID = 7;
        List<VisitDTO> visits = TObjectCreator.getVisitsForFindByPetId();

        Mockito.when(visitService.findByPetId(PET_ID))
                .thenReturn(visits);

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
        List<VisitDTO> visits = TObjectCreator.getVisitsForFindByVetId();

        Mockito.when(visitService.findByVetId(VET_ID))
                .thenReturn(visits);

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
        BigDecimal TOTAL_COST = new BigDecimal("320.00");

        Mockito.when(visitService.calculateTotalCostByPet(PET_ID))
                .thenReturn(TOTAL_COST);

        this.mockMvc.perform(get("/visits/pet/" + PET_ID + "/total-cost"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(320.0)));
    }
}
