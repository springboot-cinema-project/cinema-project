package com.movie.mapper;

import com.movie.domain.Coupons;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CouponMapper {

    @Insert("insert into coupons (coupon_title, coupon_type, coupon_price) values (#{couponTitle}, #{couponType}, #{couponPrice})")
    long insertCoupon(Coupons coupons);

    @Select("select * from coupons order by id desc")
    List<Coupons> couponList();

    @Select("select * from coupons where id = #{id}")
    Coupons selectCoupon(long id);

    @Update("update set coupons coupon_title = #{couponTitle}, coupon_type = #{couponType}, coupon_price = #{couponPrice} where id = #{id}")
    long updateCoupon(Coupons coupons);

    @Delete("delete from coupons where id = #{id}")
    long deleteCoupon(long id);
}
