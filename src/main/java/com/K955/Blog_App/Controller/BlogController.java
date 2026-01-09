package com.K955.Blog_App.Controller;

import com.K955.Blog_App.Dto.Blog.BlogRequest;
import com.K955.Blog_App.Dto.Blog.BlogResponse;
import com.K955.Blog_App.Dto.Blog.BlogSummaryResponse;
import com.K955.Blog_App.Security.AuthUtil;
import com.K955.Blog_App.Service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BlogController {

    BlogService blogService;

    AuthUtil authUtil;

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@RequestBody BlogRequest request) {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(blogService.createBlog(request, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id) {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(blogService.getBlogById(id, userId));
    }

    @GetMapping
    public ResponseEntity<List<BlogSummaryResponse>> getMyBlogs() {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(blogService.getUserBlogs(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long id, @RequestBody BlogRequest request) {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(blogService.updateBlog(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        Long userId = authUtil.getCurrentUserId();
        blogService.deleteBlog(id, userId);
        return ResponseEntity.noContent().build();
    }

}
