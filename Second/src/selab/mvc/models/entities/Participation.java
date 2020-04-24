package selab.mvc.models.entities;

import selab.mvc.models.Model;

public class Participation implements Model
{
    private Student student;
    private Course course;
    private int points;

    @Override
    public String getPrimaryKey()
    {
        String studentNo = this.student.getStudentNo();
        String courseNo = this.course.getCourseNo();
        return "St" + studentNo + "Co" + courseNo;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}
