import java.util.HashMap;
import java.util.Map;

public class ControladorFala implements IControladorFala {

    public enum FaseInteracao {
        PERGUNTA,
        RESPOSTA,
        REPLICA,
        TREPLICA,
        ENCERRADA
    }

    private FaseInteracao faseAtual;
    private Microfone micInquiridor;
    private Microfone micInquirido;
    private Cronometro cronInquiridor;
    private Cronometro cronInquirido;
    private Map<FaseInteracao, Integer> tempos;

    public ControladorFala() {
        this.tempos = new HashMap<>();
        this.micInquiridor = new Microfone(null);
        this.micInquirido = new Microfone(null);
        this.cronInquiridor = new Cronometro();
        this.cronInquirido = new Cronometro();

        micInquiridor.setMediator(this);
        micInquirido.setMediator(this);
        cronInquiridor.setMediator(this);
        cronInquirido.setMediator(this);
    }

    public void configurarParticipantes(Candidato inq, Candidato inqd) {
        this.micInquiridor = new Microfone(inq.getId());
        this.micInquirido = new Microfone(inqd.getId());
        this.cronInquiridor = new Cronometro();
        this.cronInquirido = new Cronometro();

        micInquiridor.setMediator(this);
        micInquirido.setMediator(this);
        cronInquiridor.setMediator(this);
        cronInquirido.setMediator(this);
    }

    public void configurarTempos(int tPergunta, int tResposta, int tReplica, int tTreplica) {
        tempos.put(FaseInteracao.PERGUNTA,  tPergunta);
        tempos.put(FaseInteracao.RESPOSTA,  tResposta);
        tempos.put(FaseInteracao.REPLICA,   tReplica);
        tempos.put(FaseInteracao.TREPLICA,  tTreplica);
    }

    public void iniciarInteracao() {
        faseAtual = FaseInteracao.PERGUNTA;
        micInquiridor.ativar();
        cronInquiridor.configurar(tempos.get(FaseInteracao.PERGUNTA));
        cronInquiridor.iniciar();
    }

    @Override
    public void notificar(Object remetente, String evento) {
        if ("TEMPO_ESGOTADO".equals(evento)) {
            avancarFase();
        }
    }

    private void avancarFase() {
        switch (faseAtual) {
            case PERGUNTA:
                faseAtual = FaseInteracao.RESPOSTA;
                micInquiridor.desativar();
                micInquirido.ativar();
                cronInquirido.configurar(tempos.get(FaseInteracao.RESPOSTA));
                cronInquirido.iniciar();
                break;

            case RESPOSTA:
                faseAtual = FaseInteracao.REPLICA;
                micInquirido.desativar();
                micInquiridor.ativar();
                cronInquiridor.configurar(tempos.get(FaseInteracao.REPLICA));
                cronInquiridor.iniciar();
                break;

            case REPLICA:
                faseAtual = FaseInteracao.TREPLICA;
                micInquiridor.desativar();
                micInquirido.ativar();
                cronInquirido.configurar(tempos.get(FaseInteracao.TREPLICA));
                cronInquirido.iniciar();
                break;

            case TREPLICA:
                micInquirido.desativar();
                faseAtual = FaseInteracao.ENCERRADA;
                break;

            default:
                break;
        }
    }

    public FaseInteracao getFaseAtual() {
        return faseAtual;
    }

    public Microfone getMicInquiridor() {
        return micInquiridor;
    }

    public Microfone getMicInquirido() {
        return micInquirido;
    }

    public Cronometro getCronInquiridor() {
        return cronInquiridor;
    }

    public Cronometro getCronInquirido() {
        return cronInquirido;
    }
}