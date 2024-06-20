package main.game;

public class Jogador {
    private String nome;
    private int vidaMaxima = Configuracoes.vidaMaximaPadrao;
    private int vidaAtual = vidaMaxima;
    private int quantidadeOuro = 0;
    private int quantidadeExperiencia = 0;
    public int multiplicadorDeDano = 1;
    
    public Jogador(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        status.append("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
        status.append("   " + this.nome.toUpperCase() + "\n");
        status.append("\nVida: " + this.vidaAtual + "/" + this.vidaMaxima);
        status.append("\nExperiência: " + this.quantidadeExperiencia + "/10");
        status.append("\nOuro: " + this.quantidadeOuro + "/10" + "\n\n");
        status.append("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
        String statusString = status.toString();
        return statusString;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaAtual() {
        if (vidaAtual > vidaMaxima) vidaAtual = vidaMaxima;
        return this.vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public int getQuantidadeOuro() {
        return this.quantidadeOuro;
    }

    public void setQuantidadeOuro(int quantidadeOuro) {
        this.quantidadeOuro = quantidadeOuro;
    }

    public int getQuantidadeExperiencia() {
        return this.quantidadeExperiencia;
    }

    public void setQuantidadeExperiencia(int quantidadeExperiencia) {
        this.quantidadeExperiencia = quantidadeExperiencia;
    }

}
