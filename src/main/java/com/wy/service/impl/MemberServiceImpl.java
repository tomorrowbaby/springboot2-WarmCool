package com.wy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.dao.MemberDOMapper;
import com.wy.dao.PasswordDOMapper;
import com.wy.dataobject.MemberDO;
import com.wy.dataobject.PasswordDO;
import com.wy.service.MemberService;
import com.wy.service.model.MemberModel;
import com.wy.utils.EncryptionUtil;
import com.wy.validator.ValidationResult;
import com.wy.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private PasswordDOMapper passwordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public Long selectAllMemberCount() {
        return  memberDOMapper.selectAllMemberCount();
    }

    @Override
    public Long selectAllRemoveMemberCount() {
        return  memberDOMapper.selectAllRemoveMemberCount();
    }

    @Override
    public CommonReturnPageInfo getMemberListAndPageInfo(Integer start, Integer length, String searchKey, String orderCol, String orderDir, String minDate, String maxDate) throws BusinessException {
        if (start == null || length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (!StringUtils.equals(orderDir,"desc")) {
            orderDir = "asc";
        }

        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%"+searchKey+"%";
        }

        //开始分页
        PageHelper.startPage(start/length+1,length);
        Page<MemberDO> memberDOPage = new Page<>();
        try {
            memberDOPage = memberDOMapper.selectPageListByMember(searchKey,minDate,maxDate,orderCol,orderDir);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        //分页结果获取
        List<MemberDO> memberDOList = memberDOPage.getResult();

        CommonReturnPageInfo pageInfoModel = new CommonReturnPageInfo();
        pageInfoModel.setData(memberDOList);
        pageInfoModel.setRecordsTotal(Long.bitCount(this.selectAllMemberCount()));
        pageInfoModel.setRecordsFiltered((int)(memberDOPage.getTotal()));

        return pageInfoModel;
    }

    @Override
    public Boolean validateMemberInfoIsExist(String memberInfo) throws BusinessException {
        if (StringUtils.isEmpty(memberInfo)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        try {
            //根据用户信息进行分类
            if (StringUtils.contains(memberInfo,"@")&& StringUtils.contains(memberInfo,".com")) {
                //邮箱重复判断
                if (memberDOMapper.selectByEmail(memberInfo) > 0) {
                     return true;
                }
            } else if (StringUtils.length(memberInfo) == 11 && StringUtils.isNumeric(memberInfo)) {
                //手机号码判断重复
                if (memberDOMapper.selectByPhone(memberInfo) > 0){
                    return  true;
                }
            }else {
                //如果用户名已经存在返回true
                if (memberDOMapper.selectByUsername(memberInfo) > 0) {
                    return true;
                }
            }
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        return false;
    }

    @Override
    @Transactional
    public MemberModel addMemberInfo(MemberModel memberModel) throws BusinessException {
        if (memberModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult validationResult = validator.validate(memberModel);
        if (validationResult.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }

        memberModel.setState(0);
        memberModel.setCreate(new DateTime());
        memberModel.setUpdate(new DateTime());
        MemberDO memberDO = convertFromMemberModel(memberModel);
        PasswordDO passwordDO = convertFromMemberModelToPassword(memberModel);
        //插入
        int row = 0;
        try {
            memberDOMapper.insertSelective(memberDO);
            passwordDO.setMemberId(memberDO.getId());
            row = passwordDOMapper.insertSelective(passwordDO);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row > 0) {
            memberModel.setReturnResult("success");
        }
        return memberModel;
    }




    @Override
    @Transactional
    public MemberModel updateMemberInfo(MemberModel memberModel) throws BusinessException {
        if (memberModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        memberModel.setUpdate(new DateTime());
        MemberDO memberDO = this.convertFromMemberModel(memberModel);

        int row = 0;
        try{
            row = memberDOMapper.updateByPrimaryKeySelective(memberDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row > 0){
            memberModel.setReturnResult("success");
        }
        return memberModel;
    }

    @Override
    public Boolean validateMemberInfoIsExistById(Long id, String memberInfo) throws BusinessException {
        try {
            if (StringUtils.isEmpty(memberInfo) || id == null){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
            }
            //根据用户信息进行分类
            if (StringUtils.contains(memberInfo,"@")&& StringUtils.contains(memberInfo,".com")) {
                //邮箱重复判断
                if (memberDOMapper.selectByEmailAndId(id,memberInfo) > 0) {
                    return true;
                }
            }else if (StringUtils.length(memberInfo) == 11 && StringUtils.isNumeric(memberInfo)) {
                //手机号码判断重复
                if (memberDOMapper.selectByPhoneAndId(id,memberInfo) > 0){
                    return  true;
                }
            }else {
                //如果用户名已经存在返回true
                if (memberDOMapper.selectByUsernameAndId(id,memberInfo) > 0) {
                    return true;
                }
            }
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        return false;
    }

    @Override
    @Transactional
    public MemberModel delMemberInfoById(Long id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        int memberRow = 0;
        int passwordRow = 0;
        try {
            memberRow = memberDOMapper.deleteByPrimaryKey(id);
            passwordRow = passwordDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        MemberModel memberModel = new MemberModel();
        if (memberRow > 0 && passwordRow > 0){
            memberModel.setReturnResult("success");
        }else {
            memberModel.setReturnResult("要删除的用户信息不存在");
        }
        return memberModel;
    }

    @Override
    @Transactional
    public MemberModel setMemberState(Long id, Integer state) throws BusinessException {

        if (id == null || state == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        MemberModel memberModel = new MemberModel();

        memberModel.setId(id);
        memberModel.setState(state);
        long row = 0;
        try {
            row = memberDOMapper.setMemberStateById(id,state);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row > 0){
            memberModel.setReturnResult("success");
        }else {
            memberModel.setReturnResult("用户不存在！");
        }
        return memberModel;
    }

    @Override
    public CommonReturnPageInfo getRemoveMemberListAndPageInfo(Integer start, Integer length, String searchKey, String orderCol, String orderDir, String minDate, String maxDate) throws BusinessException {
        if (start == null || length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (!StringUtils.equals(orderDir,"desc")) {
            orderDir = "asc";
        }

        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%"+searchKey+"%";
        }

        //开始分页
        PageHelper.startPage(start/length+1,length);
        Page<MemberDO> memberDOPage = new Page<>();
        try {
            memberDOPage = memberDOMapper.selectRemovePageListByMember(searchKey,minDate,maxDate,orderCol,orderDir);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        //分页结果获取
        List<MemberDO> memberDOList = memberDOPage.getResult();

        CommonReturnPageInfo pageInfoModel = new CommonReturnPageInfo();
        pageInfoModel.setData(memberDOList);
        pageInfoModel.setRecordsTotal(Long.bitCount(this.selectAllMemberCount()));
        pageInfoModel.setRecordsFiltered((int)(memberDOPage.getTotal()));

        return pageInfoModel;
    }

    @Override
    @Transactional
    public MemberModel setMemberPassword(Long id, String password) throws BusinessException {
        if (id == null || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        String encrptPassword = EncryptionUtil.encrypt(password);
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setMemberId(id);
        passwordDO.setEncrptPassword(encrptPassword);

        int row = 0;
        try{
            row = passwordDOMapper.updateByPrimaryKeySelective(passwordDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        MemberModel memberModel = new MemberModel();
        if (row > 0) {
            memberModel.setReturnResult("success");
        }
        return memberModel;
    }

    private MemberModel convertFromPasswordDO(PasswordDO passwordDO){
        if (passwordDO == null) {
            return  null;
        }
        MemberModel memberModel = new MemberModel();
        memberModel.setPassword(passwordDO.getEncrptPassword());
        memberModel.setId(passwordDO.getMemberId());
        return memberModel;
    }

    private PasswordDO convertFromMemberModelToPassword(MemberModel memberModel){
        if (memberModel == null) {
            return  null;
        }
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setMemberId(memberModel.getId());
        passwordDO.setEncrptPassword(memberModel.getPassword());
        return  passwordDO;
    }

    private MemberModel convertFromMemberDO(MemberDO memberDO) {
        if (memberDO == null) {
            return  null;
        }
        MemberModel memberModel= new MemberModel();
        BeanUtils.copyProperties(memberDO,memberModel);
        memberModel.setCreate(new DateTime(memberDO.getCreated()));
        memberModel.setUpdate(new DateTime(memberDO.getUpdated()));
        return memberModel;
    }


    private MemberDO convertFromMemberModel(MemberModel memberModel) {
        if (memberModel == null) {
            return  null;
        }
        MemberDO memberDO = new MemberDO();
        BeanUtils.copyProperties(memberModel,memberDO);
        if (memberModel.getCreate() != null) {
            memberDO.setCreated(memberModel.getCreate().toDate());
        }
        if (memberModel.getUpdate() != null) {
            memberDO.setUpdated(memberModel.getUpdate().toDate());
        }

        return memberDO;
    }

    private MemberModel convertFromMemberDOAndMemberPassword(MemberDO memberDO,PasswordDO passwordDO){
        if (passwordDO == null && memberDO == null) {
            return  null;
        }
        MemberModel memberModel  = new MemberModel();
        BeanUtils.copyProperties(memberDO,memberModel);
        memberModel.setId(passwordDO.getMemberId());
        memberModel.setPassword(passwordDO.getEncrptPassword());
        return  memberModel;
    }
}
