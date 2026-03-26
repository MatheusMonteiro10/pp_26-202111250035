public class PublicacaoImplBD implements Implementador {

    @Override
    public void getDados(String tipo) {
        System.out.println("PublicacaoImplBD.getDados() chamado para tipo: " + tipo);
    }
}