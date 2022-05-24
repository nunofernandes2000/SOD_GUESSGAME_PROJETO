package folha2.ex2;

public class Circulo {

    private int raio;
    private String cor;

    public Circulo () {

    }

    public Circulo (int raio) {
        this.raio = raio;
    }

    public Circulo (int raio, String cor) {
        this.raio = raio;
        this.cor = cor;
    }

    public double areaCirculo() {
        return Math.PI * this.raio * this.raio;
    }

    public double getRaio() {
        return raio;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }
}