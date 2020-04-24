package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private ArrayList<Participation> participations = new ArrayList<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        float count = 0;
        float sum = 0;

        for (Participation participation: participations)
        {
            count += 1;
            sum += participation.getPoints();
        }

        if (count == 0)
            return count;
        return sum / count;
    }

    public String getCourses() {
        StringBuilder courses = new StringBuilder();
        for (Participation participation: participations)
        {
            Course course = participation.getCourse();
            courses.append(course.getTitle()).append(",");
        }
        if (courses.toString().equals(""))
            return "";

        String result = courses.toString();
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public void addParticipation(Participation participation)
    {
        this.participations.add(participation);
    }

    public void removeParticipation(Participation participation)
    {
        this.participations.remove(participation);
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
