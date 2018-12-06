package com.qatix.base.spring.controller;

//import com.kika.terminal.common.utils.CdnUtil;
//import com.kika.terminal.common.utils.R;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.RandomUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
///**
// * @Author: Logan.Tang
// * @Date: 2018/11/16 4:38 PM
// */
//@Slf4j
//@RequestMapping("base/upload")
//@RestController
//public class UploadController {
//
//    /**
//     * 上传文件到CDN，file是上传的文件，key为指定的文件前缀
//     *
//     * @param file
//     * @param key
//     * @return
//     */
//    @PostMapping("/cdn")
//    public R uploadToCdn(@RequestParam("file") MultipartFile file, @RequestParam("key") String key) {
//        File localFile = convert(file);
//        if (localFile != null) {
//            String extension = FilenameUtils.getExtension(localFile.getPath());
//            //用当前时间戳+10000以内的随机数作为文件后缀，避免重复
//            String fileKey = String.format("%s/%d-%04d.%s", key, System.currentTimeMillis(), RandomUtils.nextInt(0, 10000), extension);
//            String url = CdnUtil.uploadFileToCdn(fileKey, localFile);
//            localFile.delete();
//            if (url != null) {
//                return R.ok().put("url", url);
//            }
//        }
//        return R.error();
//    }
//
//    /**
//     * 保存上传的文件到本地
//     *
//     * @param file
//     * @return
//     */
//    private File convert(MultipartFile file) {
//        File convFile = new File(file.getOriginalFilename());
//        try {
//            convFile.createNewFile();
//            FileOutputStream fos = new FileOutputStream(convFile);
//            fos.write(file.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            log.error("Upload err:{}", e.getMessage());
//        }
//        return convFile;
//    }
//}
