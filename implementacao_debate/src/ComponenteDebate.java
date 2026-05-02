public abstract class ComponenteDebate {

    protected IControladorFala mediator;

    public void setMediator(IControladorFala mediator) {
        this.mediator = mediator;
    }

    public void notificarMediator(String evento) {
        mediator.notificar(this, evento);
    }
}