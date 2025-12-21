@Autowired
private UserService userService;

// Create new user
@PostMapping("/register")
public User createUser(@RequestBody User user) {
    return userService.saveUser(user);
}

// Get all users
@GetMapping("/all")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}

// Get user by ID
@GetMapping("/{id}")
public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
}

// Delete user by ID
@DeleteMapping("/{id}")
public String deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return "User deleted with ID: " + id;
}
