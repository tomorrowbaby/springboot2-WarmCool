package com.wy.dao;

import com.github.pagehelper.Page;
import com.wy.dataobject.MemberDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface MemberDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberDO record);

    Long insertSelective(MemberDO record);

    MemberDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberDO record);

    int updateByPrimaryKey(MemberDO record);

    //查询所有会员数量
    Long selectAllMemberCount();

    //查询所有回收站会员数量
    Long selectAllRemoveMemberCount();

    //通过手机号获取会员信息
    MemberDO selectMemberInfoByPhone(String phone);




    /**
     * 分页查询
     * @param searchKey
     * @param minDate
     * @param maxDate
     * @param orderCol
     * @param orderDir
     * @return
     */
    Page<MemberDO> selectPageListByMember(String searchKey,  String minDate, String maxDate,String orderCol,  String orderDir);


    //判断用户名是否存在
    int selectByUsername(String username);

    //判断手机号码是否存在
    int selectByPhone(String phone);

    //判断邮箱是否存在
    int selectByEmail(String email);


    //判断除了此id的用户名是否存在
    int selectByUsernameAndId(@Param("id")Long id,@Param("username") String username);

    //判断除了此id的手机号码是否存在
    int selectByPhoneAndId(@Param("id")Long id ,@Param("phone") String phone);

    //判断除了此id的邮箱是否存在
    int selectByEmailAndId(@Param("id")Long id ,@Param("email") String email);


    int setMemberStateById(@Param("id")Long id,@Param("state") Integer state);

    Page<MemberDO> selectRemovePageListByMember(String searchKey,  String minDate, String maxDate,String orderCol,  String orderDir);



}