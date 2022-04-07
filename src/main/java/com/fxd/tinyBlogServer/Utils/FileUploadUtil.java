package com.fxd.tinyBlogServer.Utils;

import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {

    static private final String winFileSavePath = "E:\\upload";

    static private final String unixFileSavePath = "/opt/upload/";

    // 接收上传文件并保存至path处
    public static Map<String, Object> saveTo(String path, MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        String realPath = getRealPath(path);
        File saveDir = new File(realPath);
        if (!saveDir.isDirectory()) {
            saveDir.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.indexOf("."));
        uploadFile.transferTo(new File(saveDir, newName));

        log.info("接收到上传文件：{}；保存至：{}", oldName, realPath + "/" + newName);

        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(200, "上传成功！"));
        res.put("path", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + "/images" + path + "/" + newName);
        log.info("access url:{}",
                req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + "/images" + path + "/" + newName );
        return res;
    }

    // 根据操作系统获取上传路径
    private static String getRealPath(String path) {
        String os = System.getProperty("os.name");
        String realPath;
        // win系统
        if (os.toLowerCase().startsWith("win")) {
            realPath = winFileSavePath + path;
        } else {
            // unix系统
            realPath = unixFileSavePath + path;
        }
        log.info(realPath);
        return realPath;
    }
}
