@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    // ✅ CORRECT METHOD NAME
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.findCourse(id);
    }

    // ✅ REQUIRED ENDPOINT
    @GetMapping("/instructor/{instructorId}")
    public List<Course> getByInstructor(@PathVariable Long instructorId) {
        return courseService.findCoursesByInstructor(instructorId);
    }
}
