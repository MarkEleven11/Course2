package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DaylyTask extends  Task {
    public DaylyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate);
    }

    @Override
    public Repeatability getRepeatability() {
        return Repeatability.DAILY;
    }


}
