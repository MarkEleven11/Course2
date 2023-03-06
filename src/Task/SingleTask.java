package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task{
    public SingleTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
       return localDate.equals(this.getDateTime().toLocalDate());
    }

    @Override
    public Repeatability getRepeatability() {
        return Repeatability.SINGLE;
    }
}
