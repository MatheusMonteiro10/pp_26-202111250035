import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        DebateFacade facade = new DebateFacade();

        List<String[]> candidatos = new ArrayList<>();
        candidatos.add(new String[]{"1", "Lula",  "Partido A"});
        candidatos.add(new String[]{"2", "Bolsonaro",   "Partido B"});
        candidatos.add(new String[]{"3", "Dilma", "Partido C"});

        facade.inicializarDebate(candidatos);
        System.out.println("=== Debate iniciado com " + candidatos.size() + " candidatos ===\n");

        int numeroRodada = 0;

        // Continua até todos terem passado pelo papel de inquiridor
        while (!facade.isDebateEncerrado()) {
            numeroRodada++;
            System.out.println("--- RODADA " + numeroRodada + " ---");

            // Sorteio aleatório do inquiridor entre os que ainda não exerceram o papel
            Candidato inquiridor = facade.sortearInquiridor();
            System.out.println("Inquiridor sorteado: " + inquiridor.getNome());

            // Sorteio aleatório do inquirido entre os demais candidatos
            List<Candidato> demaisCandidatos = new ArrayList<>();
            for (Candidato c : GerenciadorDebate.getInstance().getCandidatos()) {
                if (!c.getId().equals(inquiridor.getId())) {
                    demaisCandidatos.add(c);
                }
            }
            Candidato inquirido = demaisCandidatos.get(random.nextInt(demaisCandidatos.size()));
            System.out.println("Inquirido escolhido: " + inquirido.getNome());

            facade.escolherInquirido(inquiridor, inquirido);

            // Configurando os tempos para a rodada
            facade.configurarTempos(3, 3, 2, 2);
            System.out.println("Tempos: pergunta=3s, resposta=3s, replica=2s, treplica=2s\n");

            facade.iniciarRodada();

            System.out.println("Rodada em andamento");
            atualizarEstadoRodada(facade);

            System.out.println("Fase final: " + facade.getEstadoAtual().faseAtual);
            System.out.println("--- Rodada " + numeroRodada + " encerrada ---\n");
        }

        // Debate encerrado
        System.out.println("Todos os candidatos exerceram o papel de inquiridor.");
        System.out.println("Debate encerrado: " + facade.isDebateEncerrado());
        System.out.println("Total de rodadas realizadas: " + numeroRodada + "\n");

        // Relatório final
        System.out.println(facade.gerarRelatorioFinal());
    }

    // Aguarda até a fase ENCERRADA, exibindo mudanças de estado
    private static void atualizarEstadoRodada(DebateFacade facade) throws InterruptedException {

        ControladorFala.FaseInteracao faseAnterior = null;

        // A cada 1s verifica o estado atual da rodada e imprime na tela
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            EstadoDebate estado = facade.getEstadoAtual();

            if (estado.faseAtual != faseAnterior) {
                System.out.println("  [" + estado.faseAtual + "]"
                        + "  " + estado.nomeInquiridor
                        + " (mic " + (estado.micInquiridorAtivo ? "ON" : "OFF") + ")"
                        + "  x  " + estado.nomeInquirido
                        + " (mic " + (estado.micInquiridoAtivo ? "ON" : "OFF") + ")"
                        + "  tempo: " + estado.tempoRestante + "s");
                faseAnterior = estado.faseAtual;
            }

            if (estado.faseAtual == ControladorFala.FaseInteracao.ENCERRADA) {
                break;
            }
        }
    }
}