package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Blog.BlogRequest;
import com.K955.Blog_App.Dto.Blog.BlogResponse;
import com.K955.Blog_App.Dto.Blog.BlogSummaryResponse;
import com.K955.Blog_App.Entity.Blog;
import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Error.ResourceNotFoundException;
import com.K955.Blog_App.Mapper.BlogMapper;
import com.K955.Blog_App.Repository.BlogRepository;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Service.BlogService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BlogServiceImpl implements BlogService {

    BlogRepository blogRepository;

    UserRepository userRepository;

    BlogMapper blogMapper;

    @Override
    public BlogResponse createBlog(BlogRequest request, Long userId) {

        User owner = userRepository.findById(userId)
                .orElseThrow( () -> new ResourceNotFoundException(userId.toString(), "User"));

        Blog blog = Blog.builder()
                .owner(owner)
                .title(request.title())
                .content(request.content())
                .isPublic(request.isPublic())
                .build();

        blogRepository.save(blog);

        return blogMapper.toBlogResponseFromBlog(blog);
    }

    @Override
    public BlogResponse getBlogById(Long id, Long userId) {
        //replace with accessible projects by user

        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "Blog"));

        return blogMapper.toBlogResponseFromBlog(blog);
    }

    @Override
    public List<BlogSummaryResponse> getUserBlogs(Long userId) {

        List<Blog> blogs = blogRepository.findAllAccessibleByUser(userId);

        return blogMapper.toListOfBlogSummaryResponse(blogs);
    }

    @Override
    public BlogResponse updateBlog(Long id, BlogRequest request, Long userId) {
        //replace with accessible projects by user

        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "Blog"));

        blog.setTitle(request.title());
        blog.setContent(request.content());
        blog.setIsPublic(request.isPublic());

        blogRepository.save(blog);

        return blogMapper.toBlogResponseFromBlog(blog);
    }

    @Override
    public void deleteBlog(Long id, Long userId) {

        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "Blog"));

        blog.setDeletedAt(Instant.now());

        blogRepository.save(blog);
    }
}
