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
        int nextId;
        if (!taskList.isEmpty()) {
            nextId = calculateLastId(taskManager); // Calcula o último ID a partir das tarefas carregadas
        } else { // se vazio o arquivo adiciona
            nextId = 1;
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

            while (!sc.hasNextInt()) {
                System.out.println("Opção inválida. Escolha uma opção válida: ");
                sc.next(); // Limpar a entrada inválida
            }
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após o nextInt()
            System.out.println("\n");

            switch (opcao) {
                case 1:
                    // Adicionar uma nova Tarefa
                    System.out.println("Digite o nome da tarefa: ");
                    String name = sc.nextLine();
                    System.out.println("Digite a descrição da tarefa: ");
                    String description = sc.nextLine();
                    taskList.add(new Task(nextId, name, description, false));// cria a lista de tarefas

                    taskManager.saveTasks(taskList); // Salvar as tarefas no arquivo
                    nextId++;
                    break;
                case 2:
                    // implementação futura: criar um sub menu para escolher qual a forma de
                    // listagem

                    ArrayList<Task> loadedTasks = taskManager.loadTasks();
                    if (loadedTasks != null && loadedTasks.size() > 0) {
                        System.out.println("A seguir todas as tarefas registradas: \n ");
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
                    // Marcar Tarefa como Concluida
                    System.out.println("Digite o ID da tarefa que deseja marcar como concluida.\n");
                    while (!sc.hasNextInt()) {
                        System.out.println("Opção inválida. Escolha uma opção válida: ");
                        sc.next(); // Limpar a entrada inválida
                    }
                    int taskIdToMark = sc.nextInt();
                    sc.nextLine(); // Consumir a quebra de linha após o nextInt()
                    System.out.println("\n");
                    for (Task task : taskList) {
                        if (task.getId() == taskIdToMark) {
                            if (task.getStatus() == false) { // verificando se a tarefa ja estava marcada
                                task.setStatus(true);
                                taskManager.saveTasks(taskList); // Salvar as tarefas no arquivo
                                System.out.println("A Tarefa \"" + task.getName() + "\" marcada como concluida.\n");
                                taskIdToMark = 0; // saber que tarefa foi encontrada
                            } else {
                                System.out.println(
                                        "A Tarefa \"" + task.getName() + "\" ja estava marcada como concluida.\n");
                            }
                        }
                    }
                    if (taskIdToMark != 0) { // se tarefa não foi encontrada informa ao usuario.
                        System.out.println("Nenhuma tarefa encontrada.\n");
                    }

                    break;
                case 4:
                    // editar as tarefas
                    System.out.println("Digite o ID da tarefa que deseja marcar como concluida.\n");
                    while (!sc.hasNextInt()) {
                        System.out.println("Opção inválida. Escolha uma opção válida: ");
                        sc.next(); // Limpar a entrada inválida
                    }
                    int taskIdToEdit = sc.nextInt(); // recebe do usuario qual Tarefa deseja editar
                    sc.nextLine(); // Consumir a quebra de linha após o nextInt()
                    System.out.println("\n");
                    for (Task task : taskList) {
                        if (task.getId() == taskIdToEdit) {
                            int editOptions;
                            int edited = 0;
                            do {
                                System.out.println("Menu de Edição de Tarefas: ");
                                System.out.println("1 - Editar nome");
                                System.out.println("2 - Editar descrição");
                                System.out.println("3 - Editar status");
                                System.out.println("0 - Voltar");
                                System.out.println("Escolha uma opção: ");
                                while (!sc.hasNextInt()) {
                                    System.out.println("Opção inválida. Escolha uma opção válida: ");
                                    sc.next(); // Limpar a entrada inválida
                                }
                                editOptions = sc.nextInt();
                                sc.nextLine(); // Consumir a quebra de linha após o nextInt()
                                System.out.println("\n");
                                switch (editOptions) {
                                    case 1:
                                        System.out.println("Digite o novo nome da tarefa: ");
                                        task.setName(sc.nextLine());
                                        edited++;
                                        break;
                                    case 2:
                                        System.out.println("Digite a nova descrição da tarefa: ");
                                        task.setDescription(sc.nextLine());
                                        edited++;
                                        break;
                                    case 3:
                                        task.setStatus(!task.getStatus());
                                        edited++;
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        break;
                                }

                            } while (editOptions != 0);
                            if (edited != 0) {
                                taskManager.saveTasks(taskList); // Salvar as tarefas no arquivo
                            } else {
                                System.out.println("Nenhuma tarefa editada.\n");
                            }

                        }
                    }
                    break;
                case 5:
                    // Marcar Tarefa como Concluida
                    System.out.println("Digite o ID da tarefa que deseja excluir.\n");
                    while (!sc.hasNextInt()) {
                        System.out.println("Opção inválida. Escolha uma opção válida: ");
                        sc.next(); // Limpar a entrada inválida
                    }
                    int taskIdToRemove = sc.nextInt();
                    sc.nextLine(); // Consumir a quebra de linha após o nextInt()
                    System.out.println("\n");
                    int indexTask = findTaskIndexById(taskList, taskIdToRemove); // localiza o index, correspondente ao
                                                                                 // id passado pelo
                    if (indexTask != -1) {
                        taskList.remove(indexTask); // remove da lista a tarefa. (ressolvendo o bug encontrado antes)
                        taskManager.removeTask(indexTask); // remove do arquivo a tarefa
                        System.out.println("Tarefa removida com sucesso.\n");
                    } else {
                        System.out.println("Nenhuma tarefa encontrada para o id informado.\n");
                    }
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

    public static int findTaskIndexById(ArrayList<Task> tasks, int taskId) { // vai procurar o index correspondente ao
                                                                             // id
        // que
        // passarmos para a função
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                return i; // Retorna o índice da tarefa com o ID correspondente
            }
        }
        return -1; // Retorna -1 se o ID não foi encontrado na lista
    }

}
