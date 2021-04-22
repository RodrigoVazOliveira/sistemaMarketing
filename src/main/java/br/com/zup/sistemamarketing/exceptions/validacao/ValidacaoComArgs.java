package br.com.zup.sistemamarketing.exceptions.validacao;

import java.util.List;

public class ValidacaoComArgs {
    private Integer status;
    private String tipoDeErro;
    private String motivo;
    private List<ErroDeValidacao> erros;

    public ValidacaoComArgs() {
    }

    public ValidacaoComArgs(Integer status, String tipoDeErro, String motivo, List<ErroDeValidacao> erros) {
        this.status = status;
        this.tipoDeErro = tipoDeErro;
        this.motivo = motivo;
        this.erros = erros;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTipoDeErro() {
        return tipoDeErro;
    }

    public void setTipoDeErro(String tipoDeErro) {
        this.tipoDeErro = tipoDeErro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<ErroDeValidacao> getErros() {
        return erros;
    }

    public void setErros(List<ErroDeValidacao> erros) {
        this.erros = erros;
    }
}
