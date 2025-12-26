public interface CourseService {

    Course createCourse(Course course);

    Course updateCourse(Long courseId, Course course);

    Course getCourse(Long courseId);

    List<Course> getCoursesByInstructor(Long instructorId);
}
