package resources;

public class BombaResource {
    private String tipoCombustivel;
    private double precoLitro;
    private double velocidadeAbastecimento;

    public BombaResource(String tipoCombustivel, double precoLitro, double velocidadeAbastecimento) {
        this.tipoCombustivel = tipoCombustivel;
        this.precoLitro = precoLitro;
        this.velocidadeAbastecimento = velocidadeAbastecimento;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public double getPrecoLitro() {
        return precoLitro;
    }

    public double getVelocidadeAbastecimento() {
        return velocidadeAbastecimento;
    }
}
