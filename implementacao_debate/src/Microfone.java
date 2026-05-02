public class Microfone extends ComponenteDebate {

    private String candidatoId;
    private boolean ativo;

    public Microfone(String candidatoId) {
        this.candidatoId = candidatoId;
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
        notificarMediator("MICROFONE_ATIVADO");
    }

    public void desativar() {
        this.ativo = false;
        notificarMediator("MICROFONE_DESATIVADO");
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getCandidatoId() {
        return candidatoId;
    }
}