package com.lcl.galaxy.cs.mapper;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MybatisCustomerStaffMapper {

    @Select("select customer_staff where id = #{staffId}")
    CustomerStaff findCustomerStaffById(Long staffId);


    @Insert("insert into customer_staff(group_id, nickname, account_id, staff_name, gender, status) " +
            "values (#{groupId}, #{nickname}, #{accountId}, #{staffName}, #{gender}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void creatCustomerStaff(CustomerStaff customerStaff);
}
