import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroAcao {

    private String timestamp;
    private String tipoAcao;
    private String detalhes;

    public RegistroAcao(String tipoAcao, String detalhes) {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.tipoAcao = tipoAcao;
        this.detalhes = detalhes;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public String getDetalhes() {
        return detalhes;
    }
}