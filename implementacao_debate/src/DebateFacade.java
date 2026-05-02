import java.util.List;
import java.util.Random;

public class DebateFacade {

    private GerenciadorDebate gerenciador;
    private ControladorFala controlador;
    private GeradorRelatorio relatorio;

    private Candidato inquiridorAtual;
    private Candidato inquiridoAtual;

    public DebateFacade() {
        this.gerenciador = GerenciadorDebate.getInstance();
        this.controlador = new ControladorFala();
        this.relatorio = new GeradorRelatorio();
    }

    public void inicializarDebate(List<String[]> candidatos) {
        // cada elemento de candidatos e um array: [id, nome, partido]
        for (String[] dados : candidatos) {
            gerenciador.adicionarCandidato(new Candidato(dados[0], dados[1], dados[2]));
        }
        gerenciador.registrarAcao("INICIO_DEBATE",
                "Debate iniciado com " + candidatos.size() + " candidatos");
    }

    public Candidato sortearInquiridor() {
        List<Candidato> lista = gerenciador.getCandidatosSemPapelInquiridor();
        if (lista.isEmpty()) return null;

        Candidato inq = lista.get(new Random().nextInt(lista.size()));
        inq.marcarComoInquiridor();
        inquiridorAtual = inq;
        gerenciador.registrarAcao("SORTEIO",
                "Inquiridor sorteado: " + inq.getNome());
        return inq;
    }

    public void escolherInquirido(Candidato inq, Candidato inqd) {
        inquiridoAtual = inqd;
        controlador.configurarParticipantes(inq, inqd);
        gerenciador.registrarAcao("ESCOLHA_INQUIRIDO",
                inq.getNome() + " escolheu inquirir " + inqd.getNome());
    }

    public void configurarTempos(int tempoPergunta, int tempoResposta,
                                 int tempoReplica, int tempoTreplica) {
        controlador.configurarTempos(tempoPergunta, tempoResposta,
                tempoReplica, tempoTreplica);
        gerenciador.registrarAcao("CONFIG_TEMPOS",
                "Pergunta=" + tempoPergunta + "s, Resposta=" + tempoResposta +
                        "s, Replica=" + tempoReplica + "s, Treplica=" + tempoTreplica + "s");
    }

    public void iniciarRodada() {
        gerenciador.avancarRodada();
        controlador.iniciarInteracao();
        gerenciador.registrarAcao("INICIO_RODADA",
                "Rodada " + gerenciador.getRodadaAtual() + " iniciada");
    }

    public EstadoDebate getEstadoAtual() {
        EstadoDebate estado = new EstadoDebate();
        estado.faseAtual        = controlador.getFaseAtual();
        estado.nomeInquiridor   = inquiridorAtual != null ? inquiridorAtual.getNome() : "";
        estado.nomeInquirido    = inquiridoAtual  != null ? inquiridoAtual.getNome()  : "";
        estado.tempoRestante    = controlador.getCronInquiridor().isEmExecucao()
                ? controlador.getCronInquiridor().getRestante()
                : controlador.getCronInquirido().getRestante();
        estado.rodadaAtual      = gerenciador.getRodadaAtual();
        estado.micInquiridorAtivo = controlador.getMicInquiridor().isAtivo();
        estado.micInquiridoAtivo  = controlador.getMicInquirido().isAtivo();
        return estado;
    }

    public boolean isDebateEncerrado() {
        return gerenciador.isDebateEncerrado();
    }

    public String gerarRelatorioFinal() {
        gerenciador.registrarAcao("FIM_DEBATE", "Debate encerrado");
        return relatorio.gerarRelatorio(gerenciador);
    }
}