package br.com.zup.sistemamarketing.exceptions.validacao;

public class ValidacaoSemArgs {
    private Integer status;
    private String tipoDeErro;
    private String motivo;
    private String messagem;

    public ValidacaoSemArgs() {
    }

    public ValidacaoSemArgs(Integer status, String tipoDeErro, String motivo, String messagem) {
        this.status = status;
        this.tipoDeErro = tipoDeErro;
        this.motivo = motivo;
        this.messagem = messagem;
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

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
