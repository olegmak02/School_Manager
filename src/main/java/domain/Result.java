package domain;


public class Result extends Entity {
    private Assignment assignment;
    private int grade;
    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (assignment != null ? assignment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + getId() +
                ", assignment=" + assignment +
                ", grade=" + grade +
                '}';
    }
}
