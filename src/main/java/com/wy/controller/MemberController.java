package com.wy.controller;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.controller.viewobject.MemberVO;
import com.wy.service.MemberService;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.manager.QiNiu;
import com.wy.service.model.MemberModel;
import com.wy.utils.EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 描述：会员controller层
 * @author wangyu
 * @date 2019/8/4
 */

@Controller
@RequestMapping("/member")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class MemberController extends BaseController{

    @Autowired
    private MemberService memberService;


    /**
     * 会员数量
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getMemberCount() {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberCount(memberService.selectAllMemberCount());
        return CommonReturnType.create(memberVO);
    }



    /**
     * 移除会员数量获取
     */
    @RequestMapping(value = "/count/remove",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getRemoveMemberCount() {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberRemoveCount(memberService.selectAllRemoveMemberCount());
        return CommonReturnType.create(memberVO);
    }

    /**
     * 会员列表获取
     */
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnPageInfo getMemberList(Integer draw, //显示当前页数，默认为1
                                              Integer start,//分页开始值
                                              Integer length, //每页显示数量
                                              String searchKey,  //索引
                                              String minDate, String maxDate, //开始或结束的日期
                                              @RequestParam("search[value]") String search, //任意查找条件
                                              @RequestParam("order[0][column]") Integer orderCol, //单列排序号
                                              @RequestParam("order[0][dir]") String orderDir) //排序方式
                                            throws BusinessException {
        if (draw == null || start == null ||length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //单列序号和字段对应
        String[] map = new String[]{"checkbox","id", "username","sex", "phone", "email", "address", "created", "updated", "state"};
        String sortByColumn = "";
        if(orderCol != null) {
            sortByColumn = map[orderCol];
        }else {
            //默认Id排序
            sortByColumn = "id";
        }

        if(searchKey != null && searchKey.isEmpty()){
            searchKey = search;
        }
        CommonReturnPageInfo commonReturnPageInfo = null;
        //获取分页信息
        commonReturnPageInfo = memberService.getMemberListAndPageInfo(start,length,searchKey,sortByColumn,orderDir,minDate,maxDate);

        return commonReturnPageInfo;
    }

    /**
     * 已经移除会员列表获取
     */
    @RequestMapping(value = "/list/remove" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnPageInfo getMemberRemoveList(Integer draw, //显示当前页数，默认为1
                                              Integer start,//分页开始值
                                              Integer length, //每页显示数量
                                              String searchKey,  //索引
                                              String minDate, String maxDate, //开始或结束的日期
                                              @RequestParam("search[value]") String search, //任意查找条件
                                              @RequestParam("order[0][column]") Integer orderCol, //单列排序号
                                              @RequestParam("order[0][dir]") String orderDir) //排序方式
            throws BusinessException {
        if (draw == null || start == null ||length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //单列序号和字段对应
        String[] map = new String[]{"checkbox","id", "username","sex", "phone", "email", "address", "created", "updated", "state"};
        String sortByColumn = "";
        if(orderCol != null) {
            sortByColumn = map[orderCol];
        }else {
            //默认Id排序
            sortByColumn = "id";
        }

        if(searchKey != null && searchKey.isEmpty()){
            searchKey = search;
        }
        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        //获取分页信息
        commonReturnPageInfo = memberService.getRemoveMemberListAndPageInfo(start,length,searchKey,sortByColumn,orderDir,minDate,maxDate);

        return commonReturnPageInfo;
    }


    /**
     * 添加用户-验证用户各项信息是否重复,重复返回false
     */
    @RequestMapping(value = "/validate" ,method = RequestMethod.GET)
    @ResponseBody
    public Boolean validateMemberInfo(String info) throws BusinessException {
        if (StringUtils.isEmpty(info)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(memberService.validateMemberInfoIsExist(info)) {
            //用户信息存在返回false
            return false;
        }
        return true;
    }

    /**
     * 添加会员信息
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType addMemberInfo(@RequestParam(value="memberfile",
            required = false) MultipartFile part , @ModelAttribute MemberModel memberModel) throws IOException, BusinessException {

            if (memberModel == null) {
                return CommonReturnType.create("核实信息是否输入","fail");
            }
            // 文件转发到QINIU
            if (part != null){
                String image = QiNiu.upload(part);
            }

            //地址信息拼接
            String address = memberModel.getProvince() + memberModel.getCity() + memberModel.getDistrict();
            memberModel.setAddress(address);

            //密码加密
            String password = EncryptionUtil.encrypt(memberModel.getPassword());
            memberModel.setPassword(password);

            if (memberService.addMemberInfo(memberModel).getReturnResult() == "success"){
                return CommonReturnType.create("添加成功");
            }
            return CommonReturnType.create("添加失败，服务器加载不动啦！","fail");
    }

    /**
     * 启动用户
     */
    @RequestMapping(value = "/start/{id}" ,method = RequestMethod.PUT)
    @ResponseBody
    public CommonReturnType startMemberState(@PathVariable("id") Long id) throws BusinessException {

        if (memberService.setMemberState(id,1).getReturnResult() == "success"){
            return CommonReturnType.create("更新成功");
        }
        return CommonReturnType.create("启动失败,服务器加载不动啦！","fail");
    }

    /**
     * 停止用户
     */
    @RequestMapping(value = "/stop/{id}" ,method = RequestMethod.PUT)
    @ResponseBody
    public CommonReturnType stopMemberState(@PathVariable("id") Long id) throws BusinessException {

        if (id < 0 || id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (memberService.setMemberState(id,0).getReturnResult() == "success"){
            return CommonReturnType.create(null);
        }
        return CommonReturnType.create("修改失败,系统正在维护中。。。","fail");
    }


    /**
     * 修改会员信息
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType updateMemberInfo(@RequestParam(value="memberfile",
            required = false) MultipartFile part , @PathVariable("id") Long id, @ModelAttribute MemberModel memberModel) throws IOException, BusinessException {

        if (memberModel == null || id == null) {
            return CommonReturnType.create("核实信息是否输入", "fail");
        }

        // 文件转发到图片服务器
        if (part != null) {
            String image = QiNiu.upload(part);
            memberModel.setFile(image);
        }

        if (memberService.updateMemberInfo(memberModel).getReturnResult() == "success") {
            return CommonReturnType.create("修改成功");
        }

        return CommonReturnType.create("修改失败，服务器加载不动啦！","fail");
    }

    /**
     * 验证除去指定的Id-用户各项信息是否重复,重复返回false
     */
    @RequestMapping(value = "/validate/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public Boolean validateMemberInfoById(@PathVariable("id") Long id ,String info) throws BusinessException {

        if (StringUtils.isEmpty(info) || id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if(memberService.validateMemberInfoIsExistById(id,info)) {
            //用户信息存在返回false
            return false;
        }
        return true;
    }


    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.PUT)
    @ResponseBody
    public CommonReturnType delMemberInfo(@PathVariable("idList") Long[] idList) throws BusinessException {
        if (idList == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        return CommonReturnType.create("删除失败","fail");
    }

    /**
     * 移入回收站
     */
    @RequestMapping(value = "/remove/{ids}" ,method = RequestMethod.PUT)
    @ResponseBody
    public CommonReturnType setMemberStateToRemove(@PathVariable("ids") Long[] ids) throws BusinessException {
        if (ids == null || ids.length == 0) {
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        MemberModel memberModel = new MemberModel();

        for (Long id : ids){
            //设置状态码2为回收站状态
           memberModel = memberService.setMemberState(id,2);
        }
        if (memberModel.getReturnResult() == "success") {
            return CommonReturnType.create("删除成功！");
        }
        return CommonReturnType.create("删除失败！","fail");
    }

    /**
     * 批量还原
     */
    @RequestMapping(value = "/list/start/{ids}" ,method = RequestMethod.PUT)
    @ResponseBody
    public CommonReturnType startMemberList(@PathVariable("ids") Long[] ids) throws BusinessException {
        if (ids == null || ids.length == 0) {
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        MemberModel memberModel = new MemberModel();

        for (Long id : ids){
            //设置状态码1为还原状态
            memberModel = memberService.setMemberState(id,1);
            if (memberModel.getReturnResult() != "success") {
                return CommonReturnType.create("ID为 "+ id +"的"+ memberModel.getReturnResult(),"fail");
            }
        }
            return CommonReturnType.create("还原成功!");

    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/set/password/{id}" ,method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType setMemberPassword(@PathVariable("id")Long id,String password) throws BusinessException {

        if (id == null || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (memberService.setMemberPassword(id,password).getReturnResult() == "success") {
            return CommonReturnType.create("修改成功");
        }
        return CommonReturnType.create("修改失败","fail");
    }

    /**
     * 删除单个数据
     */
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public CommonReturnType delMemberInfo(@PathVariable("id") Long id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        MemberModel memberModel = memberService.delMemberInfoById(id);
        if (memberModel.getReturnResult() == "success") {
            return CommonReturnType.create("已经彻底删除该用户");
        }

        return CommonReturnType.create(memberModel.getReturnResult(),"faile");
    }

    /**
     * 删除多条数据
     */
    @RequestMapping(value = "/list/del/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public CommonReturnType delMemberList(@PathVariable("ids") Long[] ids) throws BusinessException {
        if (ids == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        MemberModel memberModel = new MemberModel();
        for (Long id:ids) {
            memberModel = memberService.delMemberInfoById(id);
            if (memberModel.getReturnResult() != "success") {
                return CommonReturnType.create("ID为"+id+"的"+memberModel.getReturnResult(),"fail");
            }
        }
        return CommonReturnType.create("删除成功！");
    }


}
