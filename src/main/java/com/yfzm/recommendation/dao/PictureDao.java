package com.yfzm.recommendation.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface PictureDao {

    String getBase64Image(String imgUrl);

    String saveBase64Image(String imgString, String filename);

}
