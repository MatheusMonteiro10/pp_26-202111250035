public class InterfaceGUI {

    private DebateFacade facade;

    public InterfaceGUI(DebateFacade facade) {
        this.facade = facade;
    }

    public void onSortearClick() {
        Candidato inquiridor = facade.sortearInquiridor();
        if (inquiridor != null) {
            System.out.println("[GUI] Inquiridor sorteado: " + inquiridor.getNome());
        } else {
            System.out.println("[GUI] Todos os candidatos já foram inquiridores.");
        }
    }

    public void onDefinirTemposClick(int tPergunta, int tResposta,
                                     int tReplica, int tTreplica) {
        facade.configurarTempos(tPergunta, tResposta, tReplica, tTreplica);
        System.out.println("[GUI] Tempos configurados.");
    }

    public void onIniciarInteracaoClick() {
        facade.iniciarRodada();
        System.out.println("[GUI] Rodada iniciada.");
    }

    public void renderizarEstado(EstadoDebate estado) {
        System.out.println("[GUI] Estado atual:");
        System.out.println("  Fase         : " + estado.faseAtual);
        System.out.println("  Inquiridor   : " + estado.nomeInquiridor
                + " (mic " + (estado.micInquiridorAtivo ? "ON" : "OFF") + ")");
        System.out.println("  Inquirido    : " + estado.nomeInquirido
                + " (mic " + (estado.micInquiridoAtivo ? "ON" : "OFF") + ")");
        System.out.println("  Tempo restante: " + estado.tempoRestante + "s");
        System.out.println("  Rodada       : " + estado.rodadaAtual);
    }
}