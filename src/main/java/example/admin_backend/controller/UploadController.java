package example.admin_backend.controller;

import com.aliyuncs.exceptions.ClientException;
import example.admin_backend.utils.AliyunOss;
import example.admin_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    /**
     * 注入阿里云oss的bean
     */
    @Autowired
    private AliyunOss aliyunOss;

    /**
     * 上传用户头像
     * @param file 用户头像
     * @return 返回一个阿里云oss中文件的url地址
     * @throws IOException
     * @throws ClientException
     */
    @PostMapping(value = "/avatar")
    public Result<String> uploadAvatar(MultipartFile file) throws IOException, ClientException {
        String avatarUrl = aliyunOss.uploadAvatar(file);
        return Result.success(avatarUrl);
    }

    /**
     * 上传文章封面图
     * @param file 封面图
     * @return 返回一个阿里云oss中文件的url地址
     * @throws IOException
     * @throws ClientException
     */
    @PostMapping(value = "/coverImg")
    public Result<String> uploadCoverImg(MultipartFile file) throws IOException, ClientException{
        String coverImgUrl = aliyunOss.uploadCoverImg(file);
        return Result.success(coverImgUrl);
    }
}
