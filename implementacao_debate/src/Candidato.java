public class Candidato {

    private String id;
    private String nome;
    private String partido;
    private boolean jaFoiInquiridor;

    public Candidato(String id, String nome, String partido) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.jaFoiInquiridor = false;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public boolean foiInquiridor() {
        return jaFoiInquiridor;
    }

    public void marcarComoInquiridor() {
        this.jaFoiInquiridor = true;
    }
}