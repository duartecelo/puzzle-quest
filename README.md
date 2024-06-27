
<hr><br>
<h1 style="text-align: center; font-size: 5.5em; font-family: Garamond, sans-serif; color: white;">Puzzle Quest<br><p style="font-size: 0.2193em; font-family: Arial;">Trabalho de final de semestre da faculdade - <span style="color: red; background-color: white; padding: 4px; border-radius: 4px">UniRitter</span></p></h1>

<p style="font-size: 2.2em; margin: 0px; font-family: Trebuchet MS; font-weight: 1000">Introdução</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   O trabalho final do primeiro semestre dado para nós, alunos de Ciência da Computação na <span style="color: white; background-color: red; padding: 3px; border-radius: 4px; font-size: 0.8em; font-weight: 700">UniRitter</span>, consiste em uma releitura do jogo <a href="https://pt.wikipedia.org/wiki/Puzzle_Quest:_Challenge_of_the_Warlords">Puzzle Quest</a>, mantendo a maior parte da lógica por trás dele.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   A principal mudança é a diferença na funcionalidade de cada tipo de peça/esfera quando formadas e a ausência de itens, personagens, personalização de personagens e demais coisas desse tipo.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   <small><a href="/assets/proposta.pdf">Clique aqui</a> para acessar o arquivo da proposta do trabalho em PDF</small>
</p>

<hr>

<p style="font-size: 2.2em; margin: 0px; font-family: Trebuchet MS; font-weight: 1000">Como Jogar</p>

<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Objetivo
<p style="text-indent: 1em; font-size: 1.3em;">
   Seu principal objetivo é não ter sua vida zerada, pois assim você garante que você não irá perder, mas, melhor que isso, é ganhar, e para isso você precisa zerar a vida do seu adversário através das esferas dos tipos "caveira" e "experiência", as quais você aprenderá abaixo.
</p>

<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Esferas
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Há os seguintes tipos de esferas:
   <ul style="font-size:1.3em">
      <li><code><span style="color: #ffffff; font-weight: 700">Caveira</span></code>: A cada <span style="color: #ffffff; font-weight: 500">caveira</span> formada, um (1) de dano é dado ao inimigo;
      <li><code><span style="color: #9d00d1; font-weight: 500">Experiência</span></code>: A cada <span style="color:#9d00d1">experiência</span> formada, um (1) de <span style="color:#9d00d1">experiência</span> (XP) é creditado ao jogador que a formou. Chegando em 10 <span style="color:#9d00d1">experiências</span>, o valor é resetado e o inimigo perde 10 pontos de vida <u>máxima</u>;
      <li><code><span style="color: #ff2222; font-weight: 500">Fogo</span></code>: A cada <span style="color:#ff2222">fogo</span> formado, o jogador que o formou recebe um (1) de vida atual a mais (caso o valor atual seja menor que o valor máximo);
      <li><code><span style="color: #5555ff; font-weight: 500">Gelo</span></code>: Um jogo de <span style="color:#5555ff">gelos</span> transforma todos os <span style="color:#ff2222">fogos</span> em <span style="color: #ffffff; font-weight: 500">caveiras</span>;
      <li><code><span style="color: #11bc11; font-weight: 500">Natureza</span></code>: Um jogo de <span style="color:#11bc11">naturezas</span> transforma todas as <span style="color: #ffffff; font-weight: 500">caveiras</span> em <span style="color:#ff2222">fogos</span>;
      <li><code><span style="color: #ffff00; font-weight: 500"><u>Ouro</u></span></code>: A cada <span style="color:#ffff00"><u>ouro</u></span> formado, um (1) de <span style="color:#ffff00"><u>ouro</u></span> é creditado ao jogador que o formou. Chegando em 10 <span style="color:#ffff00"><u>ouros</u></span>, o valor é resetado e a próxima jogada desse jogador causa o dobro de dano (caso ocorra esse ataque);
      <li><code><span style="color: #fedf00; font-weight: 500">Raio</span></code>: Um jogo de <span style="color:#fedf00">raios</span> "zera" o <span style="color:#ffff00"><u>ouro</u></span> do inimigo.
   </ul>
</p>
<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Comandos
</p>
<p style="font-size: 1.3em;">
   Mover-se para cima no tabuleiro <span style="position:relative; float: right; margin-right: 100px; font-family: monospace">W + Enter</span><br>
   Mover-se para esquerda no tabuleiro <span style="position:relative; float: right; margin-right: 100px; font-family: monospace">A + Enter</span><br>
   Mover-se para direita no tabuleiro <span style="position:relative; float: right; margin-right: 100px; font-family: monospace">S + Enter</span><br>
   Mover-se para baixo no tabuleiro <span style="position:relative; float: right; margin-right: 100px; font-family: monospace">D + Enter</span><br>
   Selecionar esfera / Confirmar input<span style="position:relative; float: right; margin-right: 100px; font-family: monospace">Enter</span><br>
   
</p>

<hr>

<p style="font-size: 2.2em; margin: 0px; font-family: Trebuchet MS; font-weight: 1000">Funcionamento</p>

<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Tabuleiro
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   O tabuleiro é gerado de forma aleatória com o uso da classe <code>Random</code>, própria do Java. Usando estruturas condicionais, estruturas de repetição e métodos de classe também foi possível fazer com que um tabuleiro <strong>nunca</strong> comece com uma combinação de 3 ou mais peças/esferas de mesmo tipo consecutivas, vertical ou horizontalmente.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Diversos métodos da classe responsável pelo tabuleiro conseguem transformá-lo, permitindo a troca entre 2 peças apenas em casos em que essa troca resulta em um trio, quarteto ou quinteto de esferas formadas consecutivamente, por exemplo.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Sempre que uma jogada é realizada, as peças que foram formadas/feitas somem e todas acima "caem", para substituir todos os espaços vazios, exceto os mais superiores do tabuleiro, que são substituídos por novas peças completamente aleatórias, podendo <u>ou não</u> formar um trio, quarteto ou quinteto automaticamente na queda
</p>
<p style="text-align: center; margin-bottom: 0px">Exemplo de tabuleiro</p>
<img src="assets/exemplo-tabuleiro.png" alt="Exemplo de tabuleiro" style="margin-left:50%; transform: translate(-50%)">

<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Partida
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Ao criar uma nova partida, são inicializadas todas as instâncias (objetos) necessárias, sendo elas dos tipos <code>Tabuleiro</code>, <code>Jogador</code> e <code>ArrayList<></code>. Elas servem para organizar e facilitar toda a estrutura e funcionamento do programa, seguindo o conceito de POO (<strong>P</strong>rogramação <strong>O</strong>rientada a <strong>O</strong>bjeto).
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   A cada turno da partida, a "vez" passa para o jogador que não estava jogando, a não ser que, em uma única jogada/turno, ele consiga "fazer" quatro (4) ou mais esferas do mesmo tipo, mesmo que em regiões diferentes.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   O jogo apenas irá finalizar quando um dos jogadores estiver com a vida zerada ou negativa, indicando que perdeu, assim mostrando na tela o vencedor, quanto de vida ainda tinha e em quantas rodadas conseguiu ganhar.
</p>
<p style="text-align: center; margin-bottom: 0px">Exemplo de partida</p>
<img src="assets/exemplo-partida.png" alt="Exemplo de partida" style="margin-left:50%; transform: translate(-50%)">

<p style="text-indent: 0.5em; font-size: 1.7em; margin: 0px; font-weight: 700">
   Salvamento e Carregamento
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Quando uma partida é criada, ela instantaneamente é alocada em um <code>ArrayList<></code> contendo partidas daquela específica execução do programa e, como o salvamento é feito por referência, não é necessário ficar atualizando a lista nenhuma vez, visto que essas mudanças individuais de cada partida ocorrerão automaticamente.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   No encerramento do programa, todas as partidas da lista (<code>ArrayList<></code>) local são salvas dentro de um banco de dados através de diversas colunas, com o objetivo de guardar toda a situação da partida, do tabuleiro e dos jogadores e, graças a essa mecânica do banco de dados - implementada na classe <code>Salvamento</code> - é possível, em futuras execuções do programa, carregar partidas passadas, mesmo que já finalizadas, através da classe <code>Carregamento</code>.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Para o correto funcionamento do salvamento e do carregamento das partidas, é necessário ter, na sua máquina, o sistema de gerenciamento de banco de dados MySQL, além de um banco de dados de nome "puzzle_quest" com uma tabela de nome "partidas_salvas" dentro dele, com todas as seguintes colunas/atributos: id (Primary key & auto_increment), nome, timestamp (opcional)(current_timestamp), situacaoTabuleiro, vidaAtualJogador1, vidaAtualJogador2, vidaMaximaJogador1, vidaMaximaJogador2, ouroAtualJogador1, ouroAtualJogador2, xpAtualJogador1, xpAtualJogador2, multiplicadorJogador1 e multiplicadorJogador2. OBS.: A conexão que conterá o banco de dados deve ser local.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   Também é necessário modificar as constantes "PASSWORD", presentes nas linhas 20 e 24 das classes <code>Salvamento</code> e <code>Carregamento</code>, respectivamente, para a senha de acesso da sua conexão local do MySQL, a não ser que a senha seja <i>12345678</i>.
</p>
<p style="text-indent: 1em; font-size: 1.3em;">
   <a href="assets/criacao-banco-de-dados.sql"><strong>Clique aqui</strong></a> para abrir o script (lista de comandos) SQL da criação do banco de dados, da tabela e das colunas necessárias.
</p>