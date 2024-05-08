package Game;

import java.util.ArrayList;

public class Obstaculo{

    protected Poligono poligono;
    protected boolean dinamico;
    protected int anguloRotacao;
    protected Ponto pontoRotacao;


    public Obstaculo(Poligono poligono, boolean dinamico, int angulo, Ponto p){

        if(poligono == null){

            throw new IllegalArgumentException("O poligono tem de ser válido");
        }
        if (angulo == 0 && dinamico) {
            throw new IllegalArgumentException("Obstáculo não pode ser dinâmico com um ângulo de rotação 0.");
        }

        this.poligono = poligono;
        this.dinamico = dinamico;
        this.anguloRotacao = angulo;
        this.pontoRotacao = p;

    }


    public void rotacao(int i) {

        if(dinamico){
            Ponto pontoDeRotacaoEfetivo = pontoRotacao != null ? pontoRotacao : poligono.calcularCentro();
            poligono = poligono.rotacao(anguloRotacao, pontoDeRotacaoEfetivo);
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


}
