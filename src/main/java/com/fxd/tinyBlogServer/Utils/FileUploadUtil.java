package com.fxd.tinyBlogServer.Utils;

import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {

    // 接收上传文件并保存至path处
    public static Map<String, Object> saveTo(String path, MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        String realPath = req.getServletContext().getRealPath(path);
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
        res.put("path", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/" + newName);
        return res;
    }
}
