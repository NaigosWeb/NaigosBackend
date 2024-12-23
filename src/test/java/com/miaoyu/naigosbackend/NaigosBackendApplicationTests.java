package com.miaoyu.naigosbackend;

import com.miaoyu.naigos.NaigosBackendApplication;
import com.miaoyu.naigos.utils.minio.MinioBuckets;
import com.miaoyu.naigos.utils.minio.MinioObjects;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = NaigosBackendApplication.class)
class NaigosBackendApplicationTests {

    @Resource
    private MinioClient minioClient;
    @Autowired
    private MinioObjects minioObjects;
    @Autowired
    private MinioBuckets minioBuckets;


    @Test
    void test01() throws Exception {
        boolean isBucket = minioClient.bucketExists(BucketExistsArgs.builder().bucket("first0test").build());
        System.out.println(isBucket);
    }

    @Test
    void test03() throws Exception {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("javatest").build());
    }

    @Test
    void test02() throws Exception {
        List<Bucket> buckets = minioClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
        }
    }
    @Test
    void test04() throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket("javatest").build());
    }
    @Test
    void test05() throws Exception {
        File file = new File("D:\\二次元图片\\碧蓝档案\\106294252_p0.png");
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket("first0test")
                .object("hina2.png")
                .stream(new FileInputStream(file), file.length(), -1)
                        .contentType("image/png")
                .build());
        System.out.println(objectWriteResponse);
    }
    @Test
    void test06() throws Exception {
        ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket("first0test")
                .object("123500725_p0.jpg")
                .filename("D:\\二次元图片\\碧蓝档案\\123500725_p0.jpg").build());
        System.out.println(objectWriteResponse);
    }
    @Test
    void test07() throws Exception {
        String objectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket("first0test")
                .object("123500725_p0.jpg")
                .method(Method.GET).build());
        System.out.println(objectUrl);
    }
    @Test
    void test08() throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket("first0test").build());
        results.forEach(itemResult -> {
            try {
                Item item = itemResult.get();
                System.out.println("https://file.naigos.cn:52011/first0test/" + item.objectName());
                System.out.println(item.size() < 1024?
                        item.size() + "Byte": 1024 <= item.size() && item.size() < (1024 * 1024)?
                        (double) (item.size()/1024) + "KB": (double) (item.size()/1024/1024) + "MB");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void test09(){
        List<Map<String, Object>> re = minioObjects.getObjectList("DC4D81A09FB2728A3C7D028B035652FA");
        if (re != null) {
            for (Map<String, Object> map : re) {
                System.out.println(map);
            }
        } else {
            System.out.println("none");
        }
    }
    @Test
    void test10(){
        Map<String, Object> reMap = minioBuckets.isBucketOrCreate("DC4D81A09FB2728A3C7D028B035652FA");
        if ((boolean)reMap.get("isBucket")){
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }
    @Test
    void test11(){
    }
}
