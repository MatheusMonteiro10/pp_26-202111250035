import java.util.List;

public class GeradorRelatorio {

    public String gerarRelatorio(GerenciadorDebate gerenciador) {
        List<RegistroAcao> acoes = gerenciador.getLogAcoes();
        StringBuilder sb = new StringBuilder();

        sb.append("===========================================\n");
        sb.append("       RELATÓRIO DO DEBATE POLÍTICO        \n");
        sb.append("===========================================\n\n");

        for (RegistroAcao acao : acoes) {
            sb.append("[").append(acao.getTimestamp()).append("] ");
            sb.append(acao.getTipoAcao()).append(": ");
            sb.append(acao.getDetalhes()).append("\n");
        }

        sb.append("\n===========================================\n");
        sb.append("Total de rodadas: ").append(gerenciador.getRodadaAtual()).append("\n");
        sb.append("===========================================\n");

        return sb.toString();
    }
}