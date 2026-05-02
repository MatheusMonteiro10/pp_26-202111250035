public class Usuario {
    private String nome;
    private String dataNascimento;
    private String genero;
    private String cor;
    private String email;
    private String whatsapp;
    private String categoriaUsuario;
    private String cpf;
    private String matriculaSuap;

    Usuario() {}

    // Setters e Getters

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getDataNascimento() { return dataNascimento; }

    public void setGenero(String genero) { this.genero = genero; }
    public String getGenero() { return genero; }

    public void setCor(String cor) { this.cor = cor; }
    public String getCor() { return cor; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }
    public String getWhatsapp() { return whatsapp; }

    public void setCategoriaUsuario(String categoriaUsuario) { this.categoriaUsuario = categoriaUsuario; }
    public String getCategoriaUsuario() { return categoriaUsuario; }

    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCpf() { return cpf; }

    public void setMatriculaSuap(String matriculaSuap) { this.matriculaSuap = matriculaSuap; }
    public String getMatriculaSuap() { return matriculaSuap; }
}
