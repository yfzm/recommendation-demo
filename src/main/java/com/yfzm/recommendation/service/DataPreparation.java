package com.yfzm.recommendation.service;

import com.yfzm.recommendation.dao.*;
import com.yfzm.recommendation.entity.*;
import com.yfzm.recommendation.util.Constant;
import com.yfzm.recommendation.util.GeneralTool;
import com.yfzm.recommendation.util.MyGridFsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Service
public class DataPreparation {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final MongoClothDao clothDao;
    private final CollocationDao collocationDao;
    private final CommentDao commentDao;
    private final TweetDao tweetDao;

    private RoleEntity roleEntityNormal;
    private RoleEntity roleEntityCorporation;

    private final ProfileDao profileDao;
    private final MyGridFsUtil myGridFsUtil;

    private static final String COLLOCATION_ATTR_FILE_PATH = "src/main/resources/trans_data.txt";
    private static final String TWEET_FILE_PATH = "src/main/resources/tweets.txt";
    private static final String DEFAULT_PROFILE_PHOTO_NAME = "default_photo.png";

    @Autowired
    public DataPreparation(UserDao userDao, RoleDao roleDao, MongoClothDao clothDao, CollocationDao collocationDao, CommentDao commentDao, TweetDao tweetDao, MyGridFsUtil myGridFsUtil, ProfileDao profileDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.clothDao = clothDao;
        this.collocationDao = collocationDao;
        this.commentDao = commentDao;
        this.tweetDao = tweetDao;
        this.myGridFsUtil = myGridFsUtil;
        this.profileDao = profileDao;
    }

    public void initializeDatabase() throws IOException {
        System.out.println("Initialize Database BEGIN");
        initRoles();
        initUsers(500, 80);
        initCollocations(COLLOCATION_ATTR_FILE_PATH);
        initTweets(TWEET_FILE_PATH);
        System.out.println("Initialize Database END");
    }

    private void initRoles() {
        clothDao.deleteAll();
        roleEntityNormal = new RoleEntity();
        roleEntityNormal.setRoleId(Constant.NORMAL_USER);
        roleEntityNormal.setRoleDescription("普通");
        roleDao.save(roleEntityNormal);
        roleEntityCorporation = new RoleEntity();
        roleEntityCorporation.setRoleId(Constant.CORPORATION_USER);
        roleEntityCorporation.setRoleDescription("企业");
        roleDao.save(roleEntityCorporation);
    }

    private void initUsers(int normNum, int corpNum) {
        for (int i = 0; i < normNum; i++) {
            insertUser(Constant.NORMAL_USER);
        }
        for (int i = 0; i < corpNum; i++) {
            insertUser(Constant.CORPORATION_USER);
        }
    }

    private void insertUser(int type) {
        UserEntity user = new UserEntity();
        user.setUsername(generateString(8));
        user.setPhone(generateNum(11));
        if (type == Constant.NORMAL_USER) {
            user.setRoleEntity(roleEntityNormal);
        } else {
            user.setRoleEntity(roleEntityCorporation);
        }
        user.setPassword(generateString(16));
        userDao.save(user);

        MongoProfileEntity profileEntity = new MongoProfileEntity();
        profileEntity.setUserId(user.getUserId());
        profileEntity.setBirthTime(generateTime());
        profileEntity.setEmail(generateString(11));
        profileEntity.setGender(Constant.GENDER_FEMALE);
        profileEntity.setPersonas(GeneralTool.generateUserPreference());
        try {
            InputStream is = new FileInputStream("src/main/resources/static/" + DEFAULT_PROFILE_PHOTO_NAME);
            String imageId = myGridFsUtil.saveImage(is, DEFAULT_PROFILE_PHOTO_NAME);
//            metaData.put("type", "image");
//            String imageId = gridFsOperations.store(is, DEFAULT_PROFILE_PHOTO_NAME, "image/png", metaData).toString();
            profileEntity.setPhotoId(imageId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        profileDao.save(profileEntity);
    }

    private void initCollocations(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        for (int i = 0; i < 2000; ++i) {
            String name = GeneralTool.generateImageName();
            List<Double> upperAttr = GeneralTool.generateUserPreference();
            List<Double> lowerAttr = GeneralTool.generateUserPreference();
            insertCollocation(name, upperAttr, lowerAttr);
        }
    }

    private void insertCollocation(String picUrl, List<Double> upperAttr, List<Double> lowerAttr) {
        Random random = new Random();
        CollocationEntity collocationEntity = new CollocationEntity();
        collocationEntity.setPicUrl(picUrl);
        MongoClothEntity upperCloth = new MongoClothEntity();
        MongoClothEntity lowerCloth = new MongoClothEntity();
        upperCloth.setType(Constant.UPPER_CLOTH);
        lowerCloth.setType(Constant.LOWER_CLOTH);
        ClothColor upperClothColor = new ClothColor(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        ClothColor lowerClothColor = new ClothColor(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        upperCloth.setColor(upperClothColor);
        lowerCloth.setColor(lowerClothColor);
        upperCloth.setAttr(upperAttr);
        lowerCloth.setAttr(lowerAttr);
        upperCloth.setTags(GeneralTool.generateTags());
        lowerCloth.setTags(GeneralTool.generateTags());
        clothDao.save(upperCloth);
        clothDao.save(lowerCloth);

        collocationEntity.setUpperId(upperCloth.getId().toString());
        collocationEntity.setLowerId(lowerCloth.getId().toString());
        collocationDao.save(collocationEntity);
    }

    private class Tweet {
        int tweetId;
        String title;
        String description;
        String detail;
        int likeCount;
        int commentCount;
        int userId;
        List<Integer> collocationIds;
        List<Integer> userIds;
        List<CommentItem> comments;


    }

    private class CommentItem {
        int userId;
        String content;
    }

    private void initTweets(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            Tweet tweet = new Tweet();
            reader.readLine();  // ignore tweet id

            tweet.userId = Integer.valueOf(reader.readLine());
            tweet.title = reader.readLine();
            tweet.description = reader.readLine();
            tweet.detail = reader.readLine();
            tweet.collocationIds = GeneralTool.convertStringToIntegerList(reader.readLine());
            tweet.likeCount = Integer.valueOf(reader.readLine());
            tweet.userIds = GeneralTool.convertStringToIntegerList(reader.readLine());
            tweet.commentCount = Integer.valueOf(reader.readLine());

            List<CommentItem> comments = new ArrayList<>();
            for (int i = 0; i < tweet.commentCount; i++) {
                CommentItem item = new CommentItem();
                item.userId = Integer.valueOf(reader.readLine());
                item.content = reader.readLine();
                comments.add(item);
            }
            tweet.comments = comments;

            insertTweet(tweet);
        }
    }

    private void insertTweet(Tweet tweet) {
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setTitle(tweet.title);
        tweetEntity.setDescription(tweet.description);
        tweetEntity.setDetail(tweet.detail);
        tweetEntity.setLikeCount(tweet.likeCount);
        tweetEntity.setCommentCount(tweet.commentCount);
        tweetEntity.setUser(userDao.findByUserId(tweet.userId));
        tweetEntity.setCreateTime(generateTime());

        Set<CollocationEntity> collocations = new HashSet<>();
        for (Integer collocationId : tweet.collocationIds) {
            CollocationEntity entity = collocationDao.findByCollocationId(collocationId);
            Assert.notNull(entity, "Can't find collocation!");
            collocations.add(entity);
        }
        tweetEntity.setCollocations(collocations);

        Set<UserEntity> users = new HashSet<>();
        for (Integer userId : tweet.userIds) {
            UserEntity entity = userDao.findByUserId(userId);
            Assert.notNull(entity, "Can't find user!");
            users.add(entity);
        }
        tweetEntity.setStarUsers(users);
        tweetDao.save(tweetEntity);

//        Set<CommentEntity> comments = new HashSet<>();
        for (CommentItem item : tweet.comments) {
            CommentEntity commentEntity = new CommentEntity();

            UserEntity commentUser = userDao.findByUserId(item.userId);
            Assert.notNull(commentUser, "Can't find commentUser");
            commentEntity.setUser(commentUser);

            commentEntity.setContent(item.content);
            commentEntity.setCreateTime(generateTime());
            commentEntity.setTweet(tweetEntity);

            commentDao.save(commentEntity);
//            comments.add(commentEntity);
        }
//        tweetEntity.setComments(comments);

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

    private static Timestamp generateTime() {
        long now = new Date().getTime();
        long ONE_YEAR = (long) 365 * 24 * 60 * 60 * 1000;
        long date = now - (long) (Math.random() * ONE_YEAR);
        return new Timestamp(date);
    }

}
