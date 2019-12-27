package com.wy.service;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.model.MemberModel;


/**
 * 描述：会员服务接口
 * @author wangyu
 * @date 2019/8/4
 */
public interface MemberService {


    /**
     * 查询所有注册成员数量
     * @return
     * @date 2019/8/4
     */
    Long selectAllMemberCount();

    /**
     * 查询回收站会员数量
     * @return
     */
    Long selectAllRemoveMemberCount();

    /**
     * 分页查询用户列表
     * @param start 起始页数
     * @param length  每页显示长度
     * @param searchKey 索引
     * @param orderCol 按列排序
     * @param orderDir  排序方式
     * @param minDate 注册日期小区间
     * @param maxDate 注册日期大区间
     * @return
     * @date 2019/8/4
     */
     CommonReturnPageInfo getMemberListAndPageInfo(Integer start, Integer length,
                                                   String searchKey, String orderCol,
                                                   String orderDir, String minDate, String maxDate) throws BusinessException;


    /**
     * 判断用户信息是否存在，如果存在返回true，不存在返回false
     * @param memberInfo
     * @return
     * @throws BusinessException
     * @date 2019/8/6
     */
     Boolean validateMemberInfoIsExist(String memberInfo) throws BusinessException;


    /**
     * 插入会员信息
     * @param memberModel
     * @return
     */
     MemberModel addMemberInfo(MemberModel memberModel) throws BusinessException;


    /**
     * 修改用户信息
     * @param memberModel
     * @return
     */
    MemberModel updateMemberInfo(MemberModel memberModel) throws BusinessException;


    /**
     * 判断除了此id外用户信息是否存在，如果存在返回true，不存在返回false
     * @param id
     * @param memberInfo
     * @return
     * @throws BusinessException
     * @date 2019/8/8
     */

    Boolean validateMemberInfoIsExistById(Long id ,String memberInfo) throws BusinessException;

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    MemberModel delMemberInfoById(Long id) throws BusinessException;


    /**
     * 设置会员状态
     * @return
     */
    MemberModel setMemberState(Long id,Integer state) throws BusinessException;


    /**
     * 移除会员列表获取
     * @param start
     * @param length
     * @param searchKey
     * @param orderCol
     * @param orderDir
     * @param minDate
     * @param maxDate
     * @return
     * @throws BusinessException
     */
    CommonReturnPageInfo getRemoveMemberListAndPageInfo(Integer start, Integer length,
                                                  String searchKey, String orderCol,
                                                  String orderDir, String minDate, String maxDate) throws BusinessException;


    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    MemberModel setMemberPassword(Long id,String password) throws BusinessException;


    /**
     * 通过手机号码查询用户信息
     * @param phone
     * @return
     */
    MemberModel selectMemberInfoByPhone(String phone) throws BusinessException;
}


