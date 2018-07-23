package com.yfzm.recommendation.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class MyGridFsUtil {

    private final GridFsOperations gridFsOperations;
    private final MongoDbFactory mongoDbFactory;

    @Autowired
    public MyGridFsUtil(GridFsOperations gridFsOperations, MongoDbFactory mongoDbFactory) {
        this.gridFsOperations = gridFsOperations;
        this.mongoDbFactory = mongoDbFactory;
    }


    public String saveBase64Image(String pic, String fileName) {
        byte[] imageBytes = MyBase64.decode(pic);
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        String imgID = saveImage(inputStream, fileName);
        return imgID;
    }

    public String getFileBase64(String ImageId) throws IOException {
        GridFSFile image = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(ImageId)));

        InputStream inputStream = GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(image.getObjectId());

        byte[] bytes = IOUtils.toByteArray(inputStream);

        return MyBase64.encode(bytes);
    }


    public String saveImage(InputStream is, String fileName) {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "image");

        return gridFsOperations.store(is, fileName, "image/png", metaData).toString();

    }

    public void deleteImage(String ImageId) {

        gridFsOperations.delete(Query.query(Criteria.where("_id").is(ImageId)));
    }

}
