import java.util.Scanner;

public class InterfaceCLI {

    private DebateFacade facade;
    private Scanner scanner;

    public InterfaceCLI(DebateFacade facade) {
        this.facade = facade;
        this.scanner = new Scanner(System.in);
    }

    public void lerComando(String cmd) {
        String[] partes = cmd.trim().split("\\s+");
        String acao = partes[0].toLowerCase();

        switch (acao) {
            case "sortear":
                Candidato inq = facade.sortearInquiridor();
                if (inq != null) {
                    exibirSaida("Inquiridor sorteado: " + inq.getNome());
                } else {
                    exibirSaida("Todos os candidatos ja foram inquiridores.");
                }
                break;

            case "tempos":
                // uso: tempos <pergunta> <resposta> <replica> <treplica>
                if (partes.length < 5) {
                    exibirSaida("Uso: tempos <pergunta> <resposta> <replica> <treplica>");
                    break;
                }
                facade.configurarTempos(
                        Integer.parseInt(partes[1]),
                        Integer.parseInt(partes[2]),
                        Integer.parseInt(partes[3]),
                        Integer.parseInt(partes[4]));
                exibirSaida("Tempos configurados.");
                break;

            case "iniciar":
                facade.iniciarRodada();
                exibirSaida("Rodada iniciada.");
                break;

            case "estado":
                EstadoDebate estado = facade.getEstadoAtual();
                exibirSaida("Fase: " + estado.faseAtual);
                exibirSaida("Inquiridor : " + estado.nomeInquiridor
                        + " (mic " + (estado.micInquiridorAtivo ? "ON" : "OFF") + ")");
                exibirSaida("Inquirido  : " + estado.nomeInquirido
                        + " (mic " + (estado.micInquiridoAtivo ? "ON" : "OFF") + ")");
                exibirSaida("Tempo restante: " + estado.tempoRestante + "s");
                exibirSaida("Rodada: " + estado.rodadaAtual);
                break;

            case "relatorio":
                exibirSaida(facade.gerarRelatorioFinal());
                break;

            case "ajuda":
                exibirSaida("Comandos disponiveis:");
                exibirSaida("  sortear                              - sorteia o proximo inquiridor");
                exibirSaida("  tempos <p> <r> <rep> <trep>         - define os tempos em segundos");
                exibirSaida("  iniciar                              - inicia a rodada atual");
                exibirSaida("  estado                               - exibe o estado atual do debate");
                exibirSaida("  relatorio                            - gera o relatorio final");
                exibirSaida("  sair                                 - encerra o programa");
                break;

            case "sair":
                exibirSaida("Encerrando...");
                System.exit(0);
                break;

            default:
                exibirSaida("Comando desconhecido: " + acao + ". Digite 'ajuda' para ver os comandos.");
        }
    }

    public void exibirSaida(String texto) {
        System.out.println("[CLI] " + texto);
    }

    public void iniciarLoop() {
        exibirSaida("Sistema de Debate Politico iniciado. Digite 'ajuda' para ver os comandos.");
        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            lerComando(cmd);
        }
    }
}