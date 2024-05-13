package Game.ModelLayer;

/**
 * Classe que representa um obstáculo dentro do jogo.
 * Um obstáculo é definido por um polígono que pode ser estático ou dinâmico,
 * permitindo rotações em torno de um ponto especificado ou de seu centro geométrico.
 *
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 
 */

public class Obstaculo{

    protected Poligono poligono;
    protected boolean dinamico;
    protected int anguloRotacao;
    protected Ponto pontoRotacao;


    /**
     * Construtor para criar um novo obstáculo.
     *
     * @param poligono O polígono que forma o obstáculo.
     * @param dinamico Indica se o obstáculo é dinâmico.
     * @param angulo O ângulo de rotação inicial para obstáculos dinâmicos.
     * @param p Ponto de rotação, pode ser nulo para usar o centro do polígono como padrão.
     */

    public Obstaculo(Poligono poligono, boolean dinamico, int angulo, Ponto p){

        if(poligono == null){

            throw new IllegalArgumentException("O poligono tem de ser válido");
        }

        this.poligono = poligono;
        this.dinamico = dinamico;
        this.anguloRotacao = angulo;
        this.pontoRotacao = p;

    }

    public void setPoligono(Poligono poligono) {
        this.poligono = poligono;
    }

    public void setDinamico(boolean dinamico) {
        this.dinamico = dinamico;
    }

    public void setAnguloRotacao(int anguloRotacao) {
        this.anguloRotacao = anguloRotacao;
    }

    public void setPontoRotacao(Ponto pontoRotacao) {
        this.pontoRotacao = pontoRotacao;
    }

    public boolean isDinamico() {
        return dinamico;
    }

    public int getAnguloRotacao() {
        return anguloRotacao;
    }

    public Ponto getPontoRotacao() {
        return pontoRotacao;
    }

    public Poligono getPoligono() {
        return poligono;
    }

    /** Ajusta o ângulo de rotação para um intervalo de 0 a 359 graus.
     *
     * @param angulo O ângulo original que pode ser qualquer valor inteiro.
     * @return O ângulo ajustado para estar dentro do intervalo permitido.
     */

    public int validarEConverterAngulo(int angulo) {
        angulo = angulo % 360;

        if (angulo < 0) {
            angulo += 360;
        }

        return angulo;
    }

    /**
     * Executa a rotação do obstáculo se este for dinâmico.
     *
     * @param anguloRotacao Ângulo de rotação em graus.
     * @param centro Ponto em torno do qual o obstáculo deve ser rotacionado.
     */
    
    public void rotacao(int anguloRotacao, Ponto centro) {

        if(dinamico){
            Ponto pontoDeRotacaoEfetivo = pontoRotacao != null ? pontoRotacao : poligono.calcularCentro();
            int anguloCorrigido = validarEConverterAngulo(anguloRotacao);
            poligono = poligono.rotacao(anguloCorrigido, pontoDeRotacaoEfetivo);
        }
    }

    @Override
    public String toString() {
        return "Obstaculo{" +
                "forma:" + poligono +
                ",é dinâmico:" + dinamico +
                ",ângulo rotação:" + anguloRotacao +
                ",ponto rotação:" + pontoRotacao +
                '}';
    }
}
