package pl1111w.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/29 15:19
 */
@RestController
public class FileUploadController {

    private static final String PATHFILE = "E:/";

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("head_img") MultipartFile[] file,
                             @RequestParam("name") String name) {

        //获取文件名
        String fileName = file[0].getOriginalFilename();

        //打印文件类型
        System.out.println(file[0].getContentType());

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        for (int i = 0; i < file.length; i++) {
            //生成唯一文件名
            fileName = UUID.randomUUID() + suffixName;
            File uploadFile = new File(PATHFILE + name + fileName);
            try {
                file[i].transferTo(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "success";

    }
}
