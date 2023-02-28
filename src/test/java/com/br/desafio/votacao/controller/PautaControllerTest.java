package com.br.desafio.votacao.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.br.desafio.votacao.dto.ExpiracaoPautaDto;
import com.br.desafio.votacao.dto.PautaDto;
import com.br.desafio.votacao.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PautaController.class)
@Import(PautaService.class)
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;
    
    @InjectMocks
    private PautaController pautaController;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    


    @Test
    public void testCadastra() throws Exception {
        PautaDto pautaDto = PautaDto.builder()
        		.descricao("teste descricao")
        		.titulo("teste")
        		.dataFim("2022-11-11 00:10:00")
        		.dataFim("2022-11-11 00:00:00")
        		.build();

        mockMvc.perform(post("/pautas")
                .content(objectMapper.writeValueAsString(pautaDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pautaService, times(1)).save(pautaDto);
    }

}