//package com.yfzm.recommendation.dao.impl;
//
//import com.kiwi.backend.DAO.PictureRepository;
//import com.kiwi.backend.util.file.FileUtil;
//import com.kiwi.backend.util.tool.MyBase64;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//
//@Repository
//public class PictureRepositoryImpl implements PictureRepository {
//
//    private String serverPath;
//
//    public PictureRepositoryImpl() {
//    }
//
//    @Override
//    public String getBase64Image(String imgUrl) {
//        try {
//
//            byte[] bytes = FileUtil.toByteArray(imgUrl);
//            String Base64Image = MyBase64.encode(bytes);
//            return Base64Image;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }
//
//    @Override
//    public String saveBase64Image(String imgString, String filename) {
//        byte[] bytes = MyBase64.decode(imgString);
//
//        FileUtil.saveFile(bytes, serverPath, filename);
//
//        return serverPath + filename;
//
//    }
//}
