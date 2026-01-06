package com.K955.Blog_App.Service;

import com.K955.Blog_App.Dto.Blog.BlogRequest;
import com.K955.Blog_App.Dto.Blog.BlogResponse;
import com.K955.Blog_App.Dto.Blog.BlogSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BlogService {
    BlogResponse createBlog(BlogRequest request, Long userId);

    BlogResponse getBlogById(Long id, Long userId);

    List<BlogSummaryResponse> getUserBlogs(Long userId);

    BlogResponse updateBlog(Long id, BlogRequest request, Long userId);

    void deleteBlog(Long id, Long userId);
}
