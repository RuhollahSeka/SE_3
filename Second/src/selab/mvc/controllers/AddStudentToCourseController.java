package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Participation;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class AddStudentToCourseController extends Controller {

    private DataSet<Participation> participations;
    private DataSet<Student> students;
    private DataSet<Course> courses;

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
        this.participations = dataContext.getParticipations();
        this.students = dataContext.getStudents();
        this.courses = dataContext.getCourses();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");

        HashMap<String, String> result = new HashMap<>();
        String participationNo = "St" + studentNo + "Co" + courseNo;
        Participation participation = this.participations.get(participationNo);
        if (participation != null)
        {
            result.put("success", "false");
            return new JsonView(new JSONObject(result));
        }

        Student student = this.students.get(studentNo);
        Course course = this.courses.get(courseNo);

        participation = new Participation();
        participation.setStudent(student);
        participation.setCourse(course);
        participation.setPoints(Integer.parseInt(points));

        student.addParticipation(participation);
        course.addParticipation(participation);

        this.participations.add(participation);

        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }
}
