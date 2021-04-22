package br.com.zup.sistemamarketing.exceptions;

import br.com.zup.sistemamarketing.exceptions.categoria.CategoriaNaoExisteException;
import br.com.zup.sistemamarketing.exceptions.produto.ProdutoNaoExisteException;
import br.com.zup.sistemamarketing.exceptions.validacao.ErroDeValidacao;
import br.com.zup.sistemamarketing.exceptions.validacao.ValidacaoComArgs;
import br.com.zup.sistemamarketing.exceptions.validacao.ValidacaoSemArgs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ValidacaoComArgs validacaoComArgs = new ValidacaoComArgs(
                status.value(),
                "Validação",
                status.getReasonPhrase(),
                gerarListaDeErroDeCampo(ex)
        );
        return ResponseEntity.status(status).body(validacaoComArgs);
    }

    private List<ErroDeValidacao> gerarListaDeErroDeCampo(MethodArgumentNotValidException e) {
        List<ErroDeValidacao> erros = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            erros.add(new ErroDeValidacao(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return erros;
    }

    @ExceptionHandler({CategoriaNaoExisteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidacaoSemArgs categoriaNaoExiste(CategoriaNaoExisteException e) {
        return new ValidacaoSemArgs(e.getStatus(), e.getTipoDeErro(), e.getMotivo(), e.getMessage());
    }

    @ExceptionHandler({ProdutoNaoExisteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidacaoSemArgs produtoNaoExisteException(ProdutoNaoExisteException e) {
        return new ValidacaoSemArgs(e.getStatus(), e.getTipoDeErro(), e.getMotivo(), e.getMessage());
    }
}