import java.util.Timer;
import java.util.TimerTask;

public class Cronometro extends ComponenteDebate {

    private int duracaoSegundos;
    private int restante;
    private boolean emExecucao;
    private Timer timer;

    public Cronometro() {
        this.duracaoSegundos = 0;
        this.restante = 0;
        this.emExecucao = false;
    }

    public void configurar(int segundos) {
        this.duracaoSegundos = segundos;
        this.restante = segundos;
        this.emExecucao = false;
    }

    public void iniciar() {
        if (emExecucao) return;
        emExecucao = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (restante > 0) {
                    restante--;
                } else {
                    pausar();
                    onTempoEsgotado();
                }
            }
        }, 1000, 1000);
    }

    public void pausar() {
        emExecucao = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void zerar() {
        pausar();
        restante = 0;
    }

    public void onTempoEsgotado() {
        notificarMediator("TEMPO_ESGOTADO");
    }

    public int getRestante() {
        return restante;
    }

    public boolean isEmExecucao() {
        return emExecucao;
    }
}