package com.K955.Blog_App.Repository;

import com.K955.Blog_App.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("""
            SELECT b FROM Blog b
            WHERE b.deletedAt IS NULL
            AND b.owner.id = :userId
            ORDER BY b.updatedAt DESC
            """
    )
    List<Blog> findAllAccessibleByUser(@Param("userId") Long userId);

}
