package com.br.desafio.votacao.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.br.desafio.votacao.dto.AssociadoDto;
import com.br.desafio.votacao.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssociadoController.class)
@Import(AssociadoService.class)
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService associadoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCadastra() throws Exception {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setNome("Teste");


        mockMvc.perform(post("/associados")
                .content(objectMapper.writeValueAsString(associadoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(associadoService, times(1)).save(associadoDto);
    }

}