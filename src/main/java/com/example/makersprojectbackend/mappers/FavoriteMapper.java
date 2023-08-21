//package com.example.makersprojectbackend.mappers;
//
//import com.example.makersprojectbackend.dto.FavoriteDto;
//import com.example.makersprojectbackend.entity.Favorite;
//import com.example.makersprojectbackend.enums.CourseType;
//import lombok.NoArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//@NoArgsConstructor
//public class FavoriteMapper {
//
//    private ModelMapper mapper;
//    private CourseMapper courseMapper;
//
//
//    public FavoriteDto convertToFavoriteDto(Favorite favorite) {
//        FavoriteDto favoriteDto = mapper.map(favorite, FavoriteDto.class);
//        favoriteDto.setCourse_id(favorite.getCourse().getId());
//        if (favorite.getCourse().getCourseType() == CourseType.PAID){
//            favoriteDto.setPaidCourseDto(courseMapper.convertToPaidCourseDto(favorite.getCourse()));
//            return favoriteDto;
//        } else if (favorite.getCourse().getCourseType() == CourseType.FREE) {
//            favoriteDto.setFreeCourseDto(courseMapper.convertToFreeCourseDto(favorite.getCourse()));
//            return favoriteDto;
//        }
//        return favoriteDto;
//    }
//}
