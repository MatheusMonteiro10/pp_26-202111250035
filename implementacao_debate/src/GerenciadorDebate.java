import java.util.ArrayList;
import java.util.List;

public class GerenciadorDebate {

    private static GerenciadorDebate instancia;

    private List<Candidato> listaCandidatos;
    private int rodadaAtual;
    private List<RegistroAcao> logAcoes;
    private boolean debateEncerrado;

    private GerenciadorDebate() {
        this.listaCandidatos = new ArrayList<>();
        this.rodadaAtual = 0;
        this.logAcoes = new ArrayList<>();
        this.debateEncerrado = false;
    }

    public static GerenciadorDebate getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorDebate();
        }
        return instancia;
    }

    public void adicionarCandidato(Candidato c) {
        listaCandidatos.add(c);
    }

    public List<Candidato> getCandidatos() {
        return listaCandidatos;
    }

    public List<Candidato> getCandidatosSemPapelInquiridor() {
        List<Candidato> resultado = new ArrayList<>();
        for (Candidato c : listaCandidatos) {
            if (!c.foiInquiridor()) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public int getRodadaAtual() {
        return rodadaAtual;
    }

    public void avancarRodada() {
        rodadaAtual++;
        if (getCandidatosSemPapelInquiridor().isEmpty()) {
            debateEncerrado = true;
        }
    }

    public boolean isDebateEncerrado() {
        return debateEncerrado;
    }

    public void registrarAcao(String tipo, String detalhes) {
        logAcoes.add(new RegistroAcao(tipo, detalhes));
    }

    public List<RegistroAcao> getLogAcoes() {
        return logAcoes;
    }
}