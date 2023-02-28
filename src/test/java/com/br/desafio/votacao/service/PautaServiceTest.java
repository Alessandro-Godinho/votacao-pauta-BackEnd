package com.br.desafio.votacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.dto.PautaDto;
import com.br.desafio.votacao.exception.RegistroNaoProcessadoException;
import com.br.desafio.votacao.repository.PautaRepository;


@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @Test
    void testSave_ShouldReturnPauta() {
        // arrange
        PautaDto pautaDto = PautaDto.builder()
                            .titulo("Teste de título")
                            .descricao("Teste de descrição")
                            .dataInicio("2023-02-11 10:00:00")
                            .dataFim("2023-02-11 12:00:00")
                            .build();
        Pauta expectedPauta = Pauta.builder()
                            .titulo("Teste de título")
                            .descricao("Teste de descrição")
                            .dataInicio(LocalDateTime.of(2023, 2, 11, 10, 0, 0))
                            .dataFim(LocalDateTime.of(2023, 2, 11, 12, 0, 0))
                            .build();
        when(pautaRepository.save(expectedPauta)).thenReturn(expectedPauta);

        // act
        Pauta actualPauta = pautaService.save(pautaDto);

        // assert
        assertThat(actualPauta).isEqualTo(expectedPauta);
        verify(pautaRepository).save(expectedPauta);
    }

    @Test
    void testSave_ShouldThrowRegistroNaoProcessadoException() {
        // arrange
        PautaDto pautaDto = PautaDto.builder()
                            .titulo("Teste de título")
                            .descricao("Teste de descrição")
                            .dataInicio("2023-02-11 10:00:00")
                            .dataFim("2023-02-11 12:00:00")
                            .build();
        Pauta expectedPauta = Pauta.builder()
                            .titulo("Teste de título")
                            .descricao("Teste de descrição")
                            .dataInicio(LocalDateTime.of(2023, 2, 11, 10, 0, 0))
                            .dataFim(LocalDateTime.of(2023, 2, 11, 12, 0, 0))
                            .build();
        when(pautaRepository.save(expectedPauta)).thenThrow(new RuntimeException());

        // act and assert
        assertThatThrownBy(() -> pautaService.save(pautaDto))
                .isInstanceOf(RegistroNaoProcessadoException.class)
                .hasMessage("Pauta não registrada");
        verify(pautaRepository).save(expectedPauta);
    }

	
	@Test
	public void testGetAll() {
		Pauta pauta1 = Pauta.builder().id(1L).descricao("Descrição 1").titulo("Título 1").dataInicio(LocalDateTime.now()).dataFim(LocalDateTime.now().plusDays(7)).build();
		Pauta pauta2 = Pauta.builder().id(2L).descricao("Descrição 2").titulo("Título 2").dataInicio(LocalDateTime.now()).dataFim(LocalDateTime.now().plusDays(7)).build();
		List<Pauta> list = Arrays.asList(pauta1, pauta2);
		
		Mockito.when(pautaRepository.findAll()).thenReturn(list);
		
		List<PautaDto> result = pautaService.getAll();
		
		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1L);
		assertThat(result.get(0).getDescricao()).isEqualTo("Descrição 1");
		assertThat(result.get(0).getTitulo()).isEqualTo("Título 1");
		assertThat(result.get(0).getDataInicio()).isNotNull();
		assertThat(result.get(0).getDataFim()).isNotNull();
		
		assertThat(result.get(1).getId()).isEqualTo(2L);
		assertThat(result.get(1).getDescricao()).isEqualTo("Descrição 2");
		assertThat(result.get(1).getTitulo()).isEqualTo("Título 2");
		assertThat(result.get(1).getDataInicio()).isNotNull();
		assertThat(result.get(1).getDataFim()).isNotNull();
	}
	

}