@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // REQUIRED BY TESTS
    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // Keep existing
    @Override
    public Course createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

    // REQUIRED BY TESTS
    public Course createCourse(Course course, long instructorId) {
        if (courseRepository.existsByTitleAndInstructorId(
                course.getTitle(), instructorId)) {
            throw new RuntimeException("Course already exists");
        }

        User instructor = userRepository.findById(instructorId)
                .orElseThrow();

        course.setInstructor(instructor);
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepository.findById(id).orElseThrow();
        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        return courseRepository.save(existing);
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }
}
