package com.yfzm.recommendation.service;

import com.yfzm.recommendation.dao.CollocationDao;
import com.yfzm.recommendation.dao.MongoClothDao;
import com.yfzm.recommendation.dao.RoleDao;
import com.yfzm.recommendation.dao.UserDao;
import com.yfzm.recommendation.entity.*;
import com.yfzm.recommendation.util.Constant;
import com.yfzm.recommendation.util.GeneralTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Random;
import java.util.UUID;

@Service
public class DataPreparation {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final MongoClothDao clothDao;
    private final CollocationDao collocationDao;

    private RoleEntity roleEntityNormal;

    private static final String COLLOCATION_ATTR_FILE_PATH = "src/main/resources/trans_data.txt";

    @Autowired
    public DataPreparation(UserDao userDao, RoleDao roleDao, MongoClothDao clothDao, CollocationDao collocationDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.clothDao = clothDao;
        this.collocationDao = collocationDao;
    }

    public void initializeDatabase() throws IOException {
        System.out.println("Initialize Database BEGIN");
        init();
        insertUser();
        insertUser();
        initCollocations(COLLOCATION_ATTR_FILE_PATH);
        System.out.println("Initialize Database END");
    }

    private void init() {
        clothDao.deleteAll();
        roleEntityNormal = new RoleEntity();
        roleEntityNormal.setRoleId(1);
        roleEntityNormal.setRoleDescription("normal");
        roleDao.save(roleEntityNormal);
    }

    private void insertUser() {
        UserEntity user = new UserEntity();
        user.setUsername(generateString(8));
        user.setPhone(generateNum(11));
        user.setRoleEntity(roleEntityNormal);
        user.setPassword(generateString(16));
        userDao.save(user);
    }

    private void initCollocations(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String name = reader.readLine();
            Integer num = Integer.valueOf(reader.readLine());
            if (num == 1) {
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
                continue;
            }
            if (num != 2) {
                throw new RuntimeException("the number of tags is not correct!");
            }
            ModelClothItem upperAttr = new ModelClothItem();
            ModelClothItem lowerAttr = new ModelClothItem();
            upperAttr.setType(Constant.UPPER_CLOTH);
            upperAttr.setColor(new ClothColor(0, 0, 0));
            upperAttr.setTexture(GeneralTool.convertStringToDoubleList(reader.readLine()));
            upperAttr.setFabric(GeneralTool.convertStringToDoubleList(reader.readLine()));
            upperAttr.setShape(GeneralTool.convertStringToDoubleList(reader.readLine()));
            upperAttr.setPart(GeneralTool.convertStringToDoubleList(reader.readLine()));
            upperAttr.setStyle(GeneralTool.convertStringToDoubleList(reader.readLine()));
            lowerAttr.setType(Constant.LOWER_CLOTH);
            lowerAttr.setColor(new ClothColor(255, 255, 255));
            lowerAttr.setTexture(GeneralTool.convertStringToDoubleList(reader.readLine()));
            lowerAttr.setFabric(GeneralTool.convertStringToDoubleList(reader.readLine()));
            lowerAttr.setShape(GeneralTool.convertStringToDoubleList(reader.readLine()));
            lowerAttr.setPart(GeneralTool.convertStringToDoubleList(reader.readLine()));
            lowerAttr.setStyle(GeneralTool.convertStringToDoubleList(reader.readLine()));

            if (upperAttr.getTexture().size() + upperAttr.getFabric().size() + upperAttr.getShape().size() + upperAttr.getPart().size() + upperAttr.getStyle().size() != 1000
                    || lowerAttr.getTexture().size() + lowerAttr.getFabric().size() + lowerAttr.getShape().size() + lowerAttr.getPart().size() + lowerAttr.getStyle().size() != 1000) {
                throw new RuntimeException("the dimension of attr vector is not correct!");
            }
            insertCollocation(name, upperAttr, lowerAttr);
        }
    }

    private void insertCollocation(String picUrl, ModelClothItem upperAttr, ModelClothItem lowerAttr) {
        CollocationEntity collocationEntity = new CollocationEntity();
        collocationEntity.setPicUrl(picUrl);
        MongoClothEntity upperCloth = new MongoClothEntity();
        MongoClothEntity lowerCloth = new MongoClothEntity();
        upperCloth.setAttr(upperAttr);
        lowerCloth.setAttr(lowerAttr);
        clothDao.save(upperCloth);
        clothDao.save(lowerCloth);

        collocationEntity.setUpperId(upperCloth.getId().toString());
        collocationEntity.setLowerId(lowerCloth.getId().toString());
        collocationDao.save(collocationEntity);
    }

    public static void main(String[] args) {
        System.out.println(generateString(100));
        System.out.println(generateString(100).length());
        String num = generateNum(11);
        System.out.println(num + ": " + num.length());
    }

    private static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String generateString(int length) {
        String baseString = generateString();
        if (length <= baseString.length()) {
            return baseString.substring(0, length);
        }
        return baseString + generateString(length - baseString.length());
    }

    private static String generateNum(int length) {
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(String.valueOf(random.nextInt(10)));
        }
        return str.toString();
    }


}
