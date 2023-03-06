package Task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 0;
    private int id;
    private String title;
    private LocalDateTime dateTime;
    private String description;
    private Type type;

    ///Concstructor
    public Task(String title,  String description, LocalDateTime dateTime, Type type) {
        this.id = idGenerator++;
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
        this.type = type;
    }

    ///Getters
    public String getTitle(){
        return title;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    ///Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //Methods

    public abstract boolean appearsIn (LocalDate localDate);

    public abstract Repeatability getRepeatability();


    ///Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idGenerator == task.idGenerator && id == task.id && title.equals(task.title) && dateTime.equals(task.dateTime) && description.equals(task.description) && type == task.type;
    }
    ///HashCode
    @Override
    public int hashCode() {
        return Objects.hash(idGenerator, id, title, dateTime, description, type);
    }
}
