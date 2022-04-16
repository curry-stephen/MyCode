package com.yy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.PreparedStatement;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Home {
    private int id;
    private int num;
    private String h_type;
    private String price;
    private String state;
    private String img;
    private String text;
    private MultipartFile file;
    public MultipartFile getFile()  {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
