package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CategoriaServiceTest {

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;
}
