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

    public int validarEConverterAngulo(int angulo) {
        angulo = angulo % 360;

        if (angulo < 0) {
            angulo += 360;
        }

        return angulo;
    }

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
