@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public Progress save(@RequestBody Progress progress) {
        return progressService.saveProgress(progress);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> byUser(@PathVariable Long userId) {
        return progressService.getProgressByUser(userId);
    }

    // ✅ REQUIRED ENDPOINT
    @GetMapping("/lesson/{lessonId}")
    public List<Progress> byLesson(@PathVariable Long lessonId) {
        return progressService.getProgressByLesson(lessonId);
    }
}
