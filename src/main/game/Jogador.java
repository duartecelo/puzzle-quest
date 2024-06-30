package main.game;
    
/**
    * Representa um jogador do jogo com atributos como nome, vida, ouro e experiência.
    */
 public class Jogador {
    private String nome;
    private int vidaMaxima = Configuracoes.vidaMaximaPadrao;
    private int vidaAtual = vidaMaxima;
    private int quantidadeOuro = 0;
    private int quantidadeExperiencia = 0;
    public int multiplicadorDeDano = 1;    
    
    /**
     * Construtor da classe Jogador que inicializa o jogador com um nome específico.
     * @param nome O nome do jogador.
     */
    public Jogador(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna uma representação em string dos status do jogador.
     * @return Uma string formatada com o nome, vida atual e máxima, experiência e ouro do jogador.
     */
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
    
    /**
     * Obtém o nome do jogador.
     * @return O nome do jogador.
     */

    public String getNome() {
        return this.nome;
    }
    
    /**
     * Define o nome do jogador.
     * @param nome O novo nome do jogador.
     */

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Obtém a quantidade máxima de vida do jogador.
     * @return A quantidade máxima de vida do jogador.
     */

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }
    
    /**
     * Define a quantidade máxima de vida do jogador.
     * @param vidaMaxima A nova quantidade máxima de vida do jogador.
     */

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
    
    /**
     * Obtém a quantidade atual de vida do jogador.
     * Garante que a vida atual não ultrapasse a vida máxima do jogador.
     * @return A quantidade atual de vida do jogador.
     */

    public int getVidaAtual() {
        if (vidaAtual > vidaMaxima) vidaAtual = vidaMaxima;
        return this.vidaAtual;
    }
    
    /**
     * Define a quantidade atual de vida do jogador.
     * @param vidaAtual A nova quantidade atual de vida do jogador.
     */

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    /**
     * Obtém a quantidade de ouro do jogador.
     * @return A quantidade de ouro do jogador.
     */

    public int getQuantidadeOuro() {
        return this.quantidadeOuro;
    }

    /**
     * Define a quantidade de ouro do jogador.
     * @param quantidadeOuro A nova quantidade de ouro do jogador.
     */

    public void setQuantidadeOuro(int quantidadeOuro) {
        this.quantidadeOuro = quantidadeOuro;
    }

    /**
     * Obtém a quantidade de experiência do jogador.
     * @return A quantidade de experiência do jogador.
     */

    public int getQuantidadeExperiencia() {
        return this.quantidadeExperiencia;
    }

    /**
     * Define a quantidade de experiência do jogador.
     * @param quantidadeExperiencia A nova quantidade de experiência do jogador.
     */

    public void setQuantidadeExperiencia(int quantidadeExperiencia) {
        this.quantidadeExperiencia = quantidadeExperiencia;
    }

}