package domain;

import java.time.LocalDate;

public class Course extends Entity {
    private String name;
    private int hours;
    private String topic;
    private Instructor instructor;
    private LocalDate beginDate;
    private LocalDate finishDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        return instructor != null ? instructor.equals(course.instructor) : course.instructor == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (instructor != null ? instructor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", topic='" + topic + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
