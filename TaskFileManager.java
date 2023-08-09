//Arquivo que gerencia nosso armazenamento, no caso optei por escolher um arquivo de texto, para nao perder os dados 
//em uma proxima execução do programa, vale ressaltar que esse codigo utilizei o gpt para me auxiliar, pois foi
//meu primeiro contato manipulando arquivos em java.

import java.io.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaskFileManager {
    private String fileName; // Nome do arquivo que será usado para salvar e carregar as tarefas

    public TaskFileManager(String fileName) {
        this.fileName = fileName; // Inicializa o nome do arquivo ao criar um novo gerenciador
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (Writer writer = new FileWriter(fileName)) { // Abre um fluxo de saída para escrever no arquivo
            Gson gson = new Gson(); // Cria uma instância do Gson, uma biblioteca para serialização/desserialização
                                    // JSON
            gson.toJson(tasks, writer); // Converte a lista de tarefas para JSON e escreve no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Em caso de erro, imprime o stack trace para depuração
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>(); // Cria uma nova lista de tarefas vazia
        try (Reader reader = new FileReader(fileName)) { // Abre um fluxo de entrada para ler o arquivo
            Gson gson = new Gson(); // Cria uma instância do Gson
            // Desserializa o JSON do arquivo de acordo com o tipo de ArrayList<Task>
            tasks = gson.fromJson(reader, new TypeToken<ArrayList<Task>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace(); // Em caso de erro, imprime o stack trace para depuração
        }
        return tasks; // Retorna a lista de tarefas, que pode ser vazia ou conter as tarefas lidas do
                      // arquivo
    }

    public void removeTask(int taskIndex) {
        ArrayList<Task> tasks = loadTasks(); // Carrega as tarefas do arquivo
        tasks.remove(taskIndex); // Remove a tarefa pelo Index
        saveTasks(tasks); // Salva as tarefas no arquivo
    }
}