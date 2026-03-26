public class PublicacaoImplXML implements Implementador {

    @Override
    public void getDados(String tipo) {
        System.out.println("PublicacaoImplXML.getDados() chamado para tipo: " + tipo);
    }
}