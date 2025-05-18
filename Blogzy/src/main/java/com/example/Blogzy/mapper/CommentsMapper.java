package com.example.Blogzy.mapper;

import com.example.Blogzy.entity.Comments;
import com.example.Blogzy.entity.Likes;
import com.example.Blogzy.model.CommentsFeedModel;
import com.example.Blogzy.model.LikesOnFeedModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    CommentsFeedModel commentsToCommentsFeedModel(Comments comments);

    Comments commentsFeedModelToFeedComments(CommentsFeedModel commentsFeedModel);
}
