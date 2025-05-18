package com.example.Blogzy.mapper;

import com.example.Blogzy.entity.Feed;
import com.example.Blogzy.model.FeedModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    Feed feedModelToFeed(FeedModel feedModel);

    FeedModel feedToFeedModel(Feed feed);
}
