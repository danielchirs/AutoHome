package com.reggie.au.autohome.model;

import com.tandong.sa.sql.Model;
import com.tandong.sa.sql.annotation.Column;
import com.tandong.sa.sql.annotation.Table;

/**
 * Created by daniel on 2015/3/22.
 * 房屋信息数据表
 */
@Table(name="SmtechHouse")
public class SmtechHouse extends Model {
    @Column(name = "name")
    public String name;
    @Column(name = "address")
    public String address;
    @Column(name = "code")
    public String code;

    public SmtechHouse(){
        super();
    }


    public SmtechHouse(String name, String address, String code){
        super();
        this.name = name;
        this.address = address;
        this.code=code;
    }

}
