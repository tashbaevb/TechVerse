package com.example.makersprojectbackend.controller.users;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.dto.course.FreeCourseDto;
import com.example.makersprojectbackend.dto.course.PaidCourseDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.mappers.course.CourseMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.service.FavoriteService;
import com.example.makersprojectbackend.service.UserService;
import com.example.makersprojectbackend.service.course.CourseService;
import com.example.makersprojectbackend.service.impl.FavoriteServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "get personal info, make feedback/enroll(sub), get courses")
public class UserController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserService userService;
//    private final FavoriteService favoriteService;
    private final FavoriteServiceImpl favoriteService;

    // ЛИЧНЫЙ КАБИНЕТ
    @GetMapping("/personal-info")
    public @ResponseBody UserDto showPersonalInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        return userMapper.convertToDtoOpt(user);
    }

    @PutMapping("/user/update")
    public UserDto update(@RequestBody UserDto dto, Authentication authentication) {
        User user = userMapper.convertToEntity(dto);
        return userMapper.convertToDto(userService.update(user, authentication));
    }


    // ЗАЯВКИ И ЗАПИСЬ
    @PutMapping("/subscribe/{courseId}") //записаться на курс
    public void subscribe(@PathVariable Long courseId, @RequestBody Enroll enroll) throws Exception {
        userService.enrollPaidCourse(courseId, enroll);
    }

    @PutMapping("/feedback") //связаться
    public ResponseEntity<String> feedback(@RequestBody Feedback feedback) {
        return userService.makeFeedback(feedback);
    }

    // COURSE
    @GetMapping("/course/free/get/{id}")
    public FreeCourseDto getFreeCourseById(@PathVariable Long id) {
        return courseMapper.convertToFreeCourseDto(courseService.getById(id));
    }


    @GetMapping("/course/free/get/all")
    public List<FreeCourseDto> getAllFreeCourses() {
        return courseMapper.convertToFreeCourseDtoList(courseService.getAll());
    }

    @GetMapping("/course/paid/get/{id}")
    public PaidCourseDto getPaidCourseById(@PathVariable Long id) {
        return courseMapper.convertToPaidCourseDto(courseService.getById(id));
    }


    @GetMapping("/course/paid/get/all")
    public List<PaidCourseDto> getAllPaidCourses() {
        return courseMapper.convertToPaidCourseDtoList(courseService.getAll());
    }


    // FAVORITE
    @PostMapping("/favorite/add/{courseId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Long courseId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        try {
            favoriteService.addToFavorites(user.getId(), courseId);
            return ResponseEntity.ok("Course added to favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/favorite/remove/{courseId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long courseId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        try {
            favoriteService.removeFromFavorites(user.getId(), courseId);
            return ResponseEntity.ok("Course removed from favorites.");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/favorite/get")
    public ResponseEntity<List<Course>> getUserFavorites(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        List<Course> userFavorites = favoriteService.getUserFavorites(currentUser.getId());
        return ResponseEntity.ok(userFavorites);
    }


    // FILTER
    @GetMapping("/course/filter")
    public List<Course> filterCourses(
            @RequestParam(required = false) String courseDirection,
            @RequestParam(required = false) BigDecimal minPrice, BigDecimal maxPrice,
            @RequestParam(required = false) Integer maxDuration) {

        return courseService.filterCourses(courseDirection, minPrice, maxPrice, maxDuration);
    }
}