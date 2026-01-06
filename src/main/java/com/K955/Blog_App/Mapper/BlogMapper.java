package com.K955.Blog_App.Mapper;

import com.K955.Blog_App.Dto.Blog.BlogResponse;
import com.K955.Blog_App.Dto.Blog.BlogSummaryResponse;
import com.K955.Blog_App.Entity.Blog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    BlogResponse toBlogResponseFromBlog(Blog blog);

    List<BlogSummaryResponse> toListOfBlogSummaryResponse(List<Blog> blogs);

}
