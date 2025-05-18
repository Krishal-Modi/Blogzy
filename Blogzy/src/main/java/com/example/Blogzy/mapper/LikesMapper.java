package com.example.Blogzy.mapper;

import com.example.Blogzy.entity.Likes;
import com.example.Blogzy.model.LikesOnFeedModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LikesMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    LikesOnFeedModel feedLikeToFeedLikeModel(Likes Likes);

    Likes feedLikeModelToFeedLike(LikesOnFeedModel likesOnFeedModel);
}
