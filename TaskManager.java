import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        TaskFileManager taskManager = new TaskFileManager("tarefas.json"); // Criação do arquivo e carregamento o
                                                                           // TaskFileManager

        ArrayList<Task> taskList = new ArrayList<>();
        if (taskManager.loadTasks() != null) {

            taskList = taskManager.loadTasks(); // Resolver o problema das listas estarem
            // sendo carregadas e
        } // subi pois estava depois da onde eu estava testando o se a lista era vazia,
          // acarretando ela sempre vazia.
        int lastId;
        if (!taskList.isEmpty()) {
            lastId = calculateLastId(taskManager); // Calcula o último ID a partir das tarefas carregadas
        } else { // se vazio o arquivo adiciona
            lastId = 1;
        }

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Marcar tarefa como concluida");
            System.out.println("4 - Editar tarefa");
            System.out.println("5 - Deletar tarefa");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após o nextInt()
            System.out.println("\n");
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da tarefa: ");
                    String name = sc.nextLine();
                    System.out.println("Digite a descrição da tarefa: ");
                    String description = sc.nextLine();
                    taskList.add(new Task(lastId, name, description, false));// cria a lista de tarefas

                    taskManager.saveTasks(taskList); // Salvar as tarefas no arquivo
                    lastId++;
                    break;
                case 2:
                    // criar um sub menu para escolher qual a forma de listagem
                    System.out.println("A seguir todas as tarefas registradas: \n ");
                    ArrayList<Task> loadedTasks = taskManager.loadTasks();
                    if (loadedTasks != null) {
                        for (Task task : loadedTasks) {
                            System.out.println("ID: " + task.getId());
                            System.out.println("Nome: " + task.getName());
                            System.out.println("Descrição: " + task.getDescription());
                            System.out.println("Status: " + (task.getStatus() ? "Concluída" : "Pendente"));
                            System.out.println();
                        }
                    } else {
                        System.out.println("Nenhuma tarefa encontrada.\n");
                    }

                    break;
                case 3:
                    System.out.println("Digite o ID da tarefa que deseja marcar como concluida.\n");
                    int idMark = sc.nextInt();
                    sc.nextLine(); // Consumir a quebra de linha após o nextInt()
                    System.out.println("\n");
                    for (Task task : taskList) {
                        if (task.getId() == idMark) {
                            if (task.getStatus() == false) { // verificando se a tarefa ja estava marcada
                                task.setStatus(true);
                                taskManager.saveTasks(taskList); // Salvar as tarefas no arquivo
                                System.out.println("A Tarefa \"" + task.getName() + "\" marcada como concluida.\n");
                                idMark = 0; // saber que tarefa foi encontrada
                            } else {
                                System.out.println(
                                        "A Tarefa \"" + task.getName() + "\" ja estava marcada como concluida.\n");
                            }
                        }
                    }
                    if (idMark != 0) { // se tarefa não foi encontrada informa ao usuario.
                        System.out.println("Nenhuma tarefa encontrada.\n");
                    }

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Até logo!\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
        sc.close(); // Fechando o Scanner
    }

    public static int calculateLastId(TaskFileManager taskManager) {
        ArrayList<Task> tasks = taskManager.loadTasks(); // Carrega as tarefas do arquivo
        int maxId = 0;
        if (tasks != null) {

            for (Task task : tasks) {
                if (task.getId() > maxId) {
                    maxId = task.getId();
                }
            }
        }
        return maxId + 1;
    }

}
