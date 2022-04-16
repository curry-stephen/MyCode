package com.yy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vip {
    private int id;
    private String name;
    private String sex;
    private long card;
    private long phone;
    private String v_type;
    private String startTime;
    private String endTime;
}
