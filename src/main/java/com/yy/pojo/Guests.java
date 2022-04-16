package com.yy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guests {
    private int id;
    private String name;
    private String sex;
    private long card;
    private long phone;
    private String enterTime;
    private String exitTime;
    private String h_type;
    private int num;
    
}
