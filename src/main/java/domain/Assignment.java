package domain;


public class Assignment extends Entity {
    private Student student;
    private Course course;
    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        if (!super.equals(o)) return false;

        Assignment that = (Assignment) o;

        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        return course != null ? course.equals(that.course) : that.course == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + getId() +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
