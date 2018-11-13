package com.qatix.base.guava.cache;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/5 7:30 PM
 */
//@Service
//public class UserCache {
//    public LoadingCache<String, User> localCache;
//    @Autowired
//    private UserDao userDao;
//
//    @PostConstruct
//    public void init() {
//        localCache = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build(
//                        new CacheLoader<String, User>() {
//                            public User load(String key) throws Exception {
//                                return userDao.findUserByMobile(key);
//                            }
//                        });
//    }
//
//    public User getUserByMobile(String mobile){
//        return localCache.get(mobile);
//    }
//}