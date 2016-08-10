package com.yufeng.controller;

import com.yufeng.algorithm.MD5Util;
import com.yufeng.authorization.annotation.Authorization;
import com.yufeng.authorization.annotation.CurrentUser;
import com.yufeng.config.ResultStatus;
import com.yufeng.entity.RegisterAccount;
import com.yufeng.entity.TokenModel;
import com.yufeng.service.RegisterAccountService;
import com.yufeng.service.TokenModelService;
import com.yufeng.util.ResultMap;
import com.yufeng.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created by kingstones on 16/7/16.
 */
@RestController
public class RegisterAccountController {

    @Autowired
    private RegisterAccountService registerAccountService;

    @Autowired
    private TokenModelService tokenModelService;

    @RequestMapping(value = "/isExistedRegisterAccount",method = RequestMethod.POST)
    public ResponseEntity<String> isExistedRegisterAccount(@RequestParam("accoutName") String accoutName) {

        boolean result = registerAccountService.isExistedRegisterAccount(accoutName);
        if(result) {
            return new ResponseEntity<String>(accoutName,HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<String>(accoutName,HttpStatus.OK);
        }
    }

    @RequestMapping("isExistedRegisterAccountByPhoneNumber")
    public ResponseEntity<String> isExistedRegisterAccountByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {

        boolean result = registerAccountService.isExistedRegisterAccountByPhoneNumber(phoneNumber);
        if(result) {
            return new ResponseEntity<String>(phoneNumber,HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<String>(phoneNumber,HttpStatus.OK);
        }
    }



    @RequestMapping("insertRegisterAccount")
    public ResponseEntity<RegisterAccount> insertRegisterAccount(@RequestBody RegisterAccount registerAccount){

        registerAccount.setPassword(MD5Util.string2MD5(registerAccount.getPassword()));
        RegisterAccount registerAccount1 = registerAccountService.insertRegisterAccount(registerAccount);
        if (registerAccount1!=null) {
            return new ResponseEntity<RegisterAccount>(registerAccount1,HttpStatus.OK);
        }else {
            return new ResponseEntity<RegisterAccount>(HttpStatus.OK);
        }
    }

    @RequestMapping("updateRegisterAccountPassword")
    public Map<Object,Object> updateRegisterAccount(@RequestParam("name") String name,String password){
        return null;
    }



    @RequestMapping(value="loginByAccoutName",method = RequestMethod.POST)
    public ResponseEntity<ResultModel> loginByAccoutName(@RequestParam String accountName, @RequestParam String password) {
        Assert.notNull(accountName, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        RegisterAccount registerAccount=registerAccountService.getRegisterAccountByName(accountName);
        if (registerAccount == null ||  //未注册
                !MD5Util.convertMD5(registerAccount.getPassword()).equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenModelService.createToken(registerAccount.getRegisterAccountId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(value="/loginByPhoneNumber",method = RequestMethod.POST)
    public ResponseEntity<ResultModel> loginByPhoneNumber(@RequestParam String phoneNumber, @RequestParam String password) {
        Assert.notNull(phoneNumber, "phoneNumber can not be empty");
        Assert.notNull(password, "password can not be empty");

        RegisterAccount registerAccount=registerAccountService.getRegisterAccountByPhoneNumber(phoneNumber);
        if (registerAccount == null ||  //未注册
                !MD5Util.convertMD5(registerAccount.getPassword()).equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenModelService.createToken(registerAccount.getRegisterAccountId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(value = "logout",method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser RegisterAccount registerAccount) {
        tokenModelService.deleteToken(registerAccount.getRegisterAccountId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(), HttpStatus.OK);
    }


}
