package com.landvibe.chinstagram.repositories;

import com.landvibe.chinstagram.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
