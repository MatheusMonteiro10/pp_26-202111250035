public class Main {

    public static void main(String[] args) {

        System.out.println("=== Teste com Banco de Dados ===");
        Implementador implBD = new PublicacaoImplBD();
        Publicacao livro = new Livro(implBD);

        livro.obterDados("Livro");
        livro.getTitulo();
        livro.getAutor(1);
        ((Livro) livro).getISBN();

        System.out.println("\n=== Teste com XML ===");
        Implementador implXML = new PublicacaoImplXML();
        Publicacao revista = new Revista(implXML);

        revista.obterDados("Revista");
        revista.getTitulo();
        revista.getAutor(2);
        ((Revista) revista).getArtigo();
    }
}