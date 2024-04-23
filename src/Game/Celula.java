package Game;

public class Celula {
 private EstadoCelula estado;
 private int linha;
 private int coluna;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.estado = EstadoCelula.EMPTY;

    }

    public EstadoCelula getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelula estado) {
        this.estado = estado;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {

        return "Celula (linha: " + linha + ",coluna: " + coluna + ",estado: " + estado +")";
    }
}
