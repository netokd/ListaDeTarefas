# ListaDeTarefas
Desenvolvendo uma lista de tarefas simples para praticar com java, onde para armazenar as tarefas estou usando arquivo de texto, para não perder as informações todas as vezes que executo o programa


*Na pasta "lib" está a dependencia do google para converter json, gson. utilizei de forma local pois não estava conseguindo implantar direto
pelo link online, pois não usei maven nem algo similar.

* Task.java Classe Task, onde define a tarefe e tem os get e setters necesserios para trabalhar com objetos task. Inicialmente não continha o atributo id, onde decidir por para facilitar exclusão e localização de uma tarefa especifica.

* TaskFileManager.java arquivo onde tem a manipulação do arquivo .json nesse arquivo foi utilizado do chat gpt para me auxliar, ja que não sabia como trabalhar manipulando arquivos, atraves do gpt descobri a dependencia gson comentei o que cada etapa do codigo faz como forma de aprendizagem.

* TaskManager.java, onde de fato criamos a lista de Array, e o arquivo .json, onde temos o menu para selecionar o que iremos fazer no programa, e a logica para executar cada etapa do menu. Uma dificuldade encontrada foi a questão de cada fez que executava o programa ele colova a lista de array nova gerada, que não estava composta pela lista pre-existentes de outras execuções no arquivo de texto, ressolvi carregando sempre numa nova execução a lista no arquivo para a lista criada para execução do programa.

* Tarefas.json arquivo de texto onde está sendo armazenado as listas de tarefas. 
