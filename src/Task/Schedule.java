package Task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Schedule {

    public final Map <Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task){
        this.tasks.put(task.getId(), task);
    }

    public Collection <Task> getAllTask() {
        return this.tasks.values();
    }

    public Collection<Task> getTaskForDay (LocalDate date) {
        TreeSet<Task> taskForDay = new TreeSet<>();
        for (Task task : tasks.values()) {
            if (task.appearsIn(date)) {
                taskForDay.add(task);
            }
        }
        return taskForDay;
    }

    public void removeTask(int id) throws IncorrectArgEx {
        if(this.tasks.containsKey(id)) {
            this.tasks.remove(id);
        }
        else {
            throw new IncorrectArgEx();
        }
    }
}
