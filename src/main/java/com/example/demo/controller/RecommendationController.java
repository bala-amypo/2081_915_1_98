@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // ✅ REQUIRED POST /generate
    @PostMapping("/generate")
    public Recommendation generate(@RequestParam Long userId) {
        return recommendationService.generate(userId);
    }

    // ✅ REQUIRED GET /latest
    @GetMapping("/latest")
    public List<Long> latest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendationIds(userId);
    }
}
