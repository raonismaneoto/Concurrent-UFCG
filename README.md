# Concurrent-UFCG

#Respostas

8: 
(a)	
	1.
(b) 
	13.
(c) 
	i) Se ambos começarem com 1 as duas regiões poderão ser executadas de forma paralela ou simultânea apesar de
	estarem sujeitas a um atraso ou outro. Ou seja, ambos conseguem executar suas regiões críticas assim que ganham a cpu e precisam apenas esperar que o outro processo faça um post para poder rodar a região crítica novamente.

	ii) Os processos executarão suas respectivas regiões críticas de forma alternada, primeiro o semáforo S sofre um wait e sua variável de controle vai para 0, a() é executado. Logo em seguida o semáforo Q que tinha 0 na variável de controle sofre um post tornando-a 1. O processo dois é desbloqueado, o que decrementa a variável de controle de Q, executa b() e faz um post em S. Agora é a vez do processo 1 executar novamente e repetir todo o fluxo.

	iii) Deadlock - nenhum processo consegue executar a região crítica uma vez que ambos farão wait em um semáforo zerado e que nunca vai sofrer um post. Ou seja, bloquearão para sempre.
(d)
	Pode haver deadlock facilmente. Suponha que o processo 1 faça wait(Q), perca a cpu e o processo 2 faça wait(S). O processo dois não conseguirá executar entrar na região critíca pois o 1 já fez wait em Q, ou seja, ele vai bloquear. O processo 1 também não conseguirá entrar na região crítica pois o S já sofreu um wait por 2, o que acarreta um deadlock pois ambos não sairão dessa situação.
	Com sorte, porém, talvez não haja deadlock. O processo 1 pode conseguir pegar as duas travas seguidamente e destravá-las no final, permitindo o processo dois de executar, mas nada garante isso.