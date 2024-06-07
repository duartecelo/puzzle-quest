<h1>Puzzle Quest</h1>
Realização do projeto de software solicitado para o trabalho final do 1° semestre de 2024 do curso de ciência da computação da universidade UniRitter, o qual é, em resumo, uma releitura do jogo Puzzle Quest.
<h2>Itens que devem ser implementados</h2>
<ul>
  <li>
    Carregamento e salvamento: A implementação apresentada por seu grupo deve conter um
    menu inicial, onde o usuário poderá escolher entre iniciar uma nova partida ou restaurar o
    progresso de uma partida anterior. Nesse caso, uma lista de jogos salvos deverá ser
    mostrada. Ainda, deverá existir uma opção para apagar jogos salvos. Os jogos deverão ser
    salvos em arquivo ou, caso o grupo queria implementar o extra, em banco de dados.
  </li>
  <li>
    Turnos: O grupo deverá implementar a mecânica de turnos. Em outras palavras, o jogo
    deverá ser "jogável" por dois jogadores. Assim que um jogador executar sua jogada, o
    próximo jogador deverá receber o controle do tabuleiro. A partida acaba quando os pontos
    de vida de um dos jogadores chega a zero. Quando o jogador consegue encaixar 4 esferas
    de uma mesma cor, ele recebe um turno extra.
  </li>
  <li>
    Turnos: O grupo deverá implementar a mecânica de turnos. Em outras palavras, o jogo
    deverá ser "jogável" por dois jogadores. Assim que um jogador executar sua jogada, o
    próximo jogador deverá receber o controle do tabuleiro. A partida acaba quando os pontos
    de vida de um dos jogadores chega a zero. Quando o jogador consegue encaixar 4 esferas
    de uma mesma cor, ele recebe um turno extra.
  </li>
  <li>
    Jogada: A cada turno, cada jogador deverá escolher uma célula do tabuleiro e uma direção.
    Se a direção e a célula escolhida forem válidas, o conteúdo da célula escolhida e da célula
    adjacente na direção escolhida deverão trocar de lugar. Se uma das duas posições
    escolhidas estiver fora do tabuleiro, o jogo deverá notificar o erro e permitir que o jogador
    faça uma nova escolha.
  </li>
  <li>
    Pontuação: PRESTE ATENÇÃO AQUI! Seu tabuleiro deverá conter esferas de 7 cores
    distintas. No jogo original, as esferas são (i) caveiras, (ii) elementos coloridos - fogo, gelo,
    raio ou natureza, (iii) ouro, ou (iv) experiência. Aqui, você deverá implementar as seguintes
    esferas:
  </li>
  <ul>
    <li>Caveiras: possuem o mesmo efeito que no jogo original. Cada caveira reduz a vida
    do adversário em 1 ponto.</li>
    <li>Esfera vermelha: Aumenta os pontos de vida do jogador, 1 ponto por esfera.</li>
    <li>Esfera azuis: Um jogo de esferas azuis transforma todas as esferas vermelhas em
    caveiras.</li>
    <li>Esfera verde: Transforma todas as caveiras em esferas vermelhas.</li>
    <li>Esfera amarela: Esvazia o ouro do inimigo.</li>
    <li>Ouro: Adiciona 1x ouro. Quando atingir 10x de ouro, você causará dano dobrado no
    seu próximo turno e seu ouro se tornará zero.</li>
    <li>Experiência (roxa): A cada 10x de experiência, a vida máxima do seu inimigo é
    permanentemente reduzida em 10 pontos (e sua experiência é zerada).</li>
  </ul>
</ul>
