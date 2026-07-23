package com.chayevillage.controller.common;

import com.chayevillage.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/common")
public class FileController {

    @Value("${file.upload.path:uploads}")
    private String uploadPath;

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

        Path targetDir = Paths.get(uploadPath, dateDir);
        try {
            Files.createDirectories(targetDir);
            File dest = targetDir.resolve(newFilename).toFile();
            file.transferTo(dest);

            String url = "/uploads/" + dateDir + "/" + newFilename;
            log.info("文件上传成功: {}", url);

            Map<String, String> data = new HashMap<>();
            data.put("url", url);
            return Result.success(data);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.error(500, "文件上传失败: " + e.getMessage());
        }
    }
}
