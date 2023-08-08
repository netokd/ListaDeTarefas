//CLasse que define uma Tarefa, onde temos o nome, descrição(Tarefa de fato descriminada) e o 
//status(pendente ou concluida)

public class Task {

    private int id;
    private String name;
    private String description;
    private boolean status;

    public Task(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}