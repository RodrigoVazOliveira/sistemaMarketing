package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.dtos.categoria.entrada.CadastrarCategoriaDTO;
import br.com.zup.sistemamarketing.exceptions.categoria.CategoriaNaoExisteException;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoriaServiceTest {

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    private CadastrarCategoriaDTO cadastrarCategoriaDTO;
    private Categoria categoria;
    private List<Categoria> categorias;

    @BeforeEach
    public void setup() {
        cadastrarCategoriaDTO = new CadastrarCategoriaDTO("NovaCategoria");
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("NovaCategoria");

        categorias = new ArrayList<>();
        for (int i = 0; i < 10; i++ ) {
            Categoria item = new Categoria();
            item.setId(i);
            item.setNome("NovaCategoria " + i);
            categorias.add(item);
        }
    }

    @Test
    public void testarCadastrarCategoriaOK() {
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(categoria);
        Categoria testCategoria = categoriaService.cadastrarNovaCategoria(categoria);
        Assertions.assertEquals(testCategoria, categoria);
    }

    @Test
    public void testarCadastrarCategoriaErro() {
        Mockito.when(categoriaRepository.save(Mockito.any()));
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoriaService.cadastrarNovaCategoria(null);
        });
    }

    @Test
    public void testarCadastroCategoriaComNomeNulo() {
        Mockito.when(categoriaRepository.save(Mockito.any()));
        categoria.setNome(null);
        Assertions.assertThrows(RuntimeException.class, () -> {
           categoriaService.cadastrarNovaCategoria(categoria);
        });
    }

    @Test
    public void testarCadastroCategoriaComNomeExecedeValor() {
        Mockito.when(categoriaRepository.save(Mockito.any()));
        categoria.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoriaService.cadastrarNovaCategoria(categoria);
        });
    }

    @Test
    public void testarProcurarCategoriaPorIdOk() {
        Optional<Categoria> optionalCategoria = Optional.of(categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);
        Categoria teste = categoriaService.procurarCategoriaPorId(1);
        Assertions.assertEquals(teste, categoria);
    }

    @Test
    public void testarProcurarCategoriaPorIdError() {
        Optional<Categoria> optionalCategoria = Optional.empty();
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);
        Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.procurarCategoriaPorId(1);
        });
    }

    @Test
    public void testarProcurarCategoriaPorNomeOk() {
        Optional<Categoria> optionalCategoria = Optional.of(categoria);
        Mockito.when(categoriaRepository.findByNome(Mockito.anyString())).thenReturn(optionalCategoria);
        Categoria teste = categoriaService.procurarCategoriaPorNome("NovaCategoria");
        Assertions.assertEquals(teste, categoria);
    }

    @Test
    public void testarProcurarCategoriaPorNomeError() {
        Optional<Categoria> optionalCategoria = Optional.empty();
        Mockito.when(categoriaRepository.findByNome(Mockito.anyString())).thenReturn(optionalCategoria);
        Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.procurarCategoriaPorNome("NovaCategoria");
        });
    }

    @Test
    public void testarProcurarCategoriaPorIdOuNomeOk() {
        Iterable<Categoria> resultado = categorias;
        Mockito.when(categoriaRepository.findAllByIdOrNome(Mockito.anyInt(), Mockito
                .anyString())).thenReturn(resultado);
        Iterable<Categoria> teste = categoriaService.procurarCategoriaPorIdOuNome(1, "NovaCategoria");
        Assertions.assertEquals(teste, resultado);
    }

    @Test
    public void testarProcurarCategoriaPorIdOuNomeVazio() {
        Iterable<Categoria> resultado = new ArrayList<>();
        Mockito.when(categoriaRepository.findAllByIdOrNome(Mockito.anyInt(), Mockito
                .anyString())).thenReturn(resultado);
        Iterable<Categoria> teste = categoriaService.procurarCategoriaPorIdOuNome(1, "NovaCategoria");
        Assertions.assertEquals(teste, resultado);
    }

    @Test
    public void testarObterTodasCategoriaOk() {
        Iterable<Categoria> resultado = categorias;
        Mockito.when(categoriaRepository.findAll()).thenReturn(resultado);
        Iterable<Categoria> teste = categoriaService.obterTodasCategoria();
        Assertions.assertEquals(teste, resultado);
    }

    @Test
    public void testarAtualizarCategoriaOk() {
        Optional<Categoria> optionalCategoria = Optional.of(categoria);
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);

        Categoria novaCategoria = new Categoria();
        novaCategoria.setId(1);
        novaCategoria.setNome("NovaCategoria");

        Categoria teste = categoriaService.atualizarCategoria(novaCategoria);

        Assertions.assertEquals(teste, categoria);
    }

    @Test
    public void testarAtualizarCategoriaError() {
        Optional<Categoria> optionalCategoria = Optional.empty();
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(categoria);
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);
        Categoria novaCategoria = new Categoria();
        novaCategoria.setId(1);
        novaCategoria.setNome("NovaCategoria");
        Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.atualizarCategoria(novaCategoria);
        });
    }

    @Test
    public void testarExcluirCategoriaPorIdOk() {
        Optional<Categoria> optionalCategoria = Optional.of(categoria);
        Mockito.doNothing().when(categoriaRepository).delete(Mockito.any());
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);
        categoriaService.excluirCategoriaPorId(1);
        Mockito.verify(categoriaRepository, Mockito.times(1)).delete(categoria);
    }


    @Test
    public void testarExcluirCategoriaPorIdError() {
        Optional<Categoria> optionalCategoria = Optional.empty();
        Mockito.when(categoriaRepository.findById(Mockito.anyInt())).thenReturn(optionalCategoria);
        Assertions.assertThrows(CategoriaNaoExisteException.class, () -> {
            categoriaService.excluirCategoriaPorId(1);
        });
    }
}
