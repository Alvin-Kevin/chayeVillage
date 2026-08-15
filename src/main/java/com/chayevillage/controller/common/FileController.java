package com.chayevillage.controller.common;

import com.chayevillage.common.Result;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/common")
public class FileController {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.access-key}")
    private String accessKey;

    @Value("${oss.secret-key}")
    private String secretKey;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${file.upload.path}")
    private String uploadPath;

    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    @PostMapping("/upload")
    public Result<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(400, "文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String dateDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String newFilename = UUID.randomUUID().toString().replace("-", "") + ext;

        // 未配置 OSS 密钥时，回退到本地磁盘存储（便于本地开发）
        if (!StringUtils.hasText(accessKey) || !StringUtils.hasText(secretKey)) {
            return uploadToLocal(file, dateDir, newFilename);
        }
        return uploadToOss(file, dateDir, newFilename);
    }

    private Result<Map<String, String>> uploadToOss(MultipartFile file, String dateDir, String newFilename) {
        try {
            String contentType = file.getContentType() != null
                    ? file.getContentType()
                    : "application/octet-stream";
            String objectKey = dateDir + "/" + newFilename;

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectKey)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(contentType)
                    .build());

            String url = endpoint + "/" + bucket + "/" + objectKey;
            log.info("文件上传成功(OSS): {}", url);

            Map<String, String> data = new HashMap<>();
            data.put("url", url);
            return Result.success(data);
        } catch (io.minio.errors.ErrorResponseException e) {
            io.minio.messages.ErrorResponse er = e.errorResponse();
            log.error("MinIO上传失败: code={}, message={}, bucket={}, object={}, resource={}, requestId={}, hostId={}",
                    er.code(), er.message(), er.bucketName(), er.objectName(), er.resource(),
                    er.requestId(), er.hostId());
            return Result.error(500, "文件上传失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error(500, "文件上传失败: " + e.getMessage());
        }
    }

    private Result<Map<String, String>> uploadToLocal(MultipartFile file, String dateDir, String newFilename) {
        try {
            File dir = new File(uploadPath, dateDir.replace("/", File.separator));
            if (!dir.exists() && !dir.mkdirs()) {
                log.error("创建上传目录失败: {}", dir.getAbsolutePath());
                return Result.error(500, "创建上传目录失败");
            }
            File target = new File(dir, newFilename);
            file.transferTo(target);

            String url = "/uploads/" + dateDir + "/" + newFilename;
            log.info("文件上传成功(本地): {}", target.getAbsolutePath());

            Map<String, String> data = new HashMap<>();
            data.put("url", url);
            return Result.success(data);
        } catch (Exception e) {
            log.error("本地文件上传失败", e);
            return Result.error(500, "文件上传失败: " + e.getMessage());
        }
    }
}
