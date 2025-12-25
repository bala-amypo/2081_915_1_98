@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    // ✅ REQUIRED constructor (matches tests)
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    // other methods...
}
