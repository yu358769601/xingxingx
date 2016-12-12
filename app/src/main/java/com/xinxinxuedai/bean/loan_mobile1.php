<?php
define('IN_ECS', true);

require(dirname(__FILE__) . '/includes/init.php'); 
require(ROOT_PATH . '/includes/Aes.php');
require_once(ROOT_PATH . '/includes/Response.php');
/* $BeginDate = date('Y-m-d H:i:s','1482048000');
$a = strtotime("$BeginDate -4 hours");
print_r($a);
exit; */
$obj_aes = new Aes();
//解密
$para = json_decode(urldecode($obj_aes->siyuan_aes_decode($_POST['para'])),TRUE);

//注册验证码
if($para['action'] == "registSms"){
    
    if($para['loan_mobile'] == ''){
        Response::json(0,'手机号码为空',"");
    }
    
    require(ROOT_PATH . '/includes/lib_sms.php');
    //注册验证码
    $res = app_send_verification($para['loan_mobile'],1);
    
    $arr = array(
        "code"=>$res['code']
    ); 
    
    Response::json($res['type'],$res['message'],$arr);
}
//新用户注册
else if($para['action'] == "userRegist"){
    if(empty($para['loan_mobile'])){
        Response::json(0,"用户手机号为空","");
    }
    
    $para['loan_pwd'] = md5($para['loan_pwd']);
    
    $sql_count = "SELECT COUNT(loan_id) FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_mobile = " . $para['loan_mobile'];
    $count = $GLOBALS['db']->getOne($sql_count);
    
    if($count > 0){
        Response::json(0,"用户已经注册过","");
    }
    //创建用户
    $sql = "INSERT INTO " . $GLOBALS['ecs']->table('loan_users') . " (loan_mobile,loan_pwd,loan_from,loan_add_time,tuijianma) VALUES('" . $para['loan_mobile'] . "' , '" . $para['loan_pwd'] . "' , '" . $para['loan_from'] . "' , '" . time() . "', '" . $para['tuijianma'] . "')";
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户注册失败","");
    }else{
        $user_id = mysql_insert_id();
        
        $arr = array(
            "user_id"=>$user_id
        );
        
        Response::json(1,"用户注册成功",$arr);
    }
}

//新用户登录
else if($para['action'] == "userLogin"){
    if(empty($para['loan_mobile'])){
        Response::json(0,"用户手机号为空","");
    }
    
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_mobile = " . $para['loan_mobile'] ;    
    $user = $GLOBALS['db']->getRow($sql);
    
    if(empty($user)){
        Response::json(0,"该手机号没有注册过","");
    }
    
    $sql_loan = "SELECT loan_status FROM " . $GLOBALS['ecs']->table('user_loan') . " WHERE loan_userid = " . $user['loan_id'] . "  ORDER BY id DESC LIMIT 1";
    $user['loan_status']  = $GLOBALS['db']->getOne($sql_loan);
    
    if($user['loan_pwd'] != md5($para['loan_pwd'])){
        Response::json(0,"用户密码不正确","");
    }else{
        Response::json(1,"登录成功",$user);
    }
}

//修改密码发送验证码
else if($para['action'] == "findSms"){
    if(empty($para['loan_mobile'])){
        Response::json(0,"用户手机号为空","");
    }
    
    require(ROOT_PATH . '/includes/lib_sms.php');
    
    //修改密码发送验证码
    $res = app_send_verification($para['loan_mobile'],2);
    
    if($res['type'] != 1){
        Response::json(0,$res['message'],"");
    }else{
        $arr = array(
            "code"=>$res['code']
        );
        
        Response::json(1,$res['message'],$arr);
    }
}

//修改用户密码
else if($para['action'] == "findLoginPassWord"){
    if(empty($para['loan_mobile'])){
        Response::json(0,"用户手机号为空","");
    }
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_pwd = '" . md5($para['loan_pwd']) . "' WHERE loan_mobile = " . $para['loan_mobile'];
    
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户修改密码失败","");
    }else{
        Response::json(1,"用户修改密码成功","");
    }
}

//添加個人信息1
else if($para['action'] == "setInfo1"){
    $sql = "SELECT COUNT(loan_id) FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_card_id = '" . $para['loan_card_id']  . "' AND loan_id != " . $para['loan_id'];
    $count = $GLOBALS['db']->getOne($sql);
    
    if($count > 0){
        Response::json(0,"用户身份证信息重复","");
    }
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_sex = '" . $para['loan_sex'] . "' , loan_realname = '" . $para['loan_realname'] . "',"
     . " loan_card_id = '"  . $para['loan_card_id'] . "', loan_weixin = '" . $para['loan_weixin'] . "' ,loan_address = '" . $para['loan_address'] . "' , "
      ." loan_parent = '" . $para['loan_parent'] . "', loan_parent_mobile = '" . $para['loan_parent_mobile'] . "' WHERE loan_id = " . $para['loan_id'];
    
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户信息添加失败","");
    }else{
        Response::json(1,"用户信息添加成功","");
    }
}

//添加借款人学校信息
else if($para['action'] == "setInfo2"){
    //查询省份所归属的大区
    $area_sql = "SELECT area_id FROM " . $GLOBALS['ecs']->table('region') . " WHERE region_name = '" . $para['loan_province'] . "'";
    $area = $GLOBALS['db']->getOne($area_sql);
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_school_name = '" . $para['loan_school_name'] . "' , loan_admission_school = '" . $para['loan_admission_school'] . "',"
        . " loan_area = '"  . $area . "', loan_present_address = '" . $para['loan_present_address'] . "' ,loan_tutor = '" . $para['loan_tutor'] . "' , "
            . " loan_city = '"  . $para['loan_city'] . "', loan_province = '" . $para['loan_province'] . "' , "
                ." loan_tutor_mobile = '" . $para['loan_tutor_mobile'] . "'  WHERE loan_id = " . $para['loan_id'];

    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户信息添加失败","");
    }else{
        Response::json(1,"用户信息添加成功","");
    }
}

//添加借款人银行卡信息
else if($para['action'] == "setInfo3"){

    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_bank_card = '" . $para['loan_bank_card'] . "' , loan_bank_name = '" . $para['loan_bank_name'] . "',"
        . " loan_bank_open = '"  . $para['loan_bank_open'] . "'  WHERE loan_id = " . $para['loan_id'];

    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户信息添加失败","");
    }else{
        Response::json(1,"用户信息添加成功","");
    }
}

//获取借款人信息
else if($para['action'] == "getInfo"){
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_users') . "  WHERE loan_id = " . $para['loan_id'];
    $user = $GLOBALS['db']->getRow($sql);
    
    if(empty($user)){
        Response::json(0,"用户不存在","");
    }else{
        Response::json(1,"查询成功",$user);
    }
}

//判断借款人信息是否为空
else if($para['action'] == "getInfoShow"){
    
    $sql1 = "SELECT loan_realname FROM " . $GLOBALS['ecs']->table('loan_users') . "  WHERE loan_id = " . $para['loan_id'];
    $loan_realname = $GLOBALS['db']->getOne($sql1);
    
    if(empty($loan_realname)){
        $info1 = 0;
    }else{
        $info1 = 1;
    }
    
    $sql2 = "SELECT loan_school_name FROM " . $GLOBALS['ecs']->table('loan_users') . "  WHERE loan_id = " . $para['loan_id'];
    $loan_school_name = $GLOBALS['db']->getOne($sql2);

    if(empty($loan_school_name)){
        $info2 = 0;
    }else{
        $info2 = 1;
    }
    
    $sql3 = "SELECT loan_bank_name FROM " . $GLOBALS['ecs']->table('loan_users') . "  WHERE loan_id = " . $para['loan_id'];
    $loan_bank_name = $GLOBALS['db']->getOne($sql3);
    
    if(empty($loan_bank_name)){
        $info3 = 0;
    }else{
        $info3 = 1;
    }
    
    $arr = array("info1"=>$info1,"info2"=>$info2,"info3"=>$info3);
    
    Response::json(1,"查询成功",$arr);
}

//添加用户借款信息
else if($para['action'] == "setLoanInfo"){
    
    $sql = "SELECT id FROM " . $GLOBALS['ecs']->table('user_loan') . " WHERE loan_userid = " . $para['loan_userid'] . " AND loan_status = 0";
    //借款人申请记录
    $user_loan_id = $GLOBALS['db']->getOne($sql);
    
    if(empty($user_loan_id)){
        $sql = "INSERT INTO " . $GLOBALS['ecs']->table('user_loan') . " (loan_userid, add_time, money, loan_term, loan_category, loan_describe, loan_small_describe, loan_status, loan_plan) "
            . " VALUES('" . $para['loan_userid'] . "' , '" . time() . "' , '" . $para['money'] . "' , '" . $para['loan_term'] . "' , '" . $para['loan_category'] . "' , '" . $para['loan_describe'] . "', '" . $para['loan_small_describe'] . "', '0', '" . $para['loan_plan'] . "')";
    }else{
        $sql = "UPDATE " . $GLOBALS['ecs']->table('user_loan') . " SET add_time = '" . time() . "' , money = '" . $para['money'] . "',"
            . " loan_term = '"  . $para['loan_term'] . "',loan_category = '" . $para['loan_category'] . "',loan_describe = '" . $para['loan_describe'] . "',"
                . " loan_small_describe = '" . $para['loan_small_describe'] . "',loan_status = 0 , loan_plan = '" . $para['loan_plan'] . "' WHERE id = " . $user_loan_id;
        
    }
    
   
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"借款申请提交失败","");
    }else{
    
        Response::json(1,"借款申请提交成功","");
    }
}

//确认提交申请
else if($para['action'] == "setLoanStatus"){
    $sql = "UPDATE " . $GLOBALS['ecs']->table('user_loan') . " SET loan_from = '" . $para['loan_from'] . "' , loan_status = 1  WHERE loan_userid = " . $para['loan_id'] . " ORDER BY id DESC LIMIT 1 ";
    
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"确认提交借款申请失败","");
    }else{
    
        Response::json(1,"确认提交借款申请成功","");
    }
}

//获取借款详情
else if($para['action'] == "getLoanDetail"){
    
    $sql = "SELECT *,FROM_UNIXTIME(add_time,'%Y-%m-%d   %H:%i:%S') AS add_time FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE loan_userid = " . $para['loan_id'] . " ORDER BY id DESC LIMIT 1";
    $loan_detail = $GLOBALS['db']->getRow($sql);
    
    
    
    if(empty($loan_detail)){
        Response::json(0,"查询失败","");
    }else{

        Response::json(1,"查询成功",$loan_detail);
    }
}

//查询用户还款详情
else if($para['action'] == "getLoanPlanList"){

//     $sql = "SELECT loan_status,id FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE  loan_userid = " . $para['loan_id'] . " ORDER BY id DESC LIMIT 1 ";
//     $loan_status = $GLOBALS['db']->getRow($sql);
    
//     if($loan_status['loan_status'] == 4){
//         $sql = "SELECT id,loan_plan,money,loan_term FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE loan_status = 4 AND loan_userid = " . $para['loan_id'];
//         $loan = $GLOBALS['db']->getRow($sql);
        
//         $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $loan['id'] . " AND repay_status = 0";
//         $loan_list = $GLOBALS['db']->getAll($sql);
        
//         foreach($loan_list AS $key => $list){
//             if($list['plan_date'] < time()){
//                 $weiyue_money = $loan['money']*0.03;
//                 if($loan['loan_plan'] == 0){
//                     if($loan['loan_term']/7 == $list['current_flag'] ){
//                         $weiyue_money +=  $loan['money']*0.1;
//                     }
//                 }else{
//                     if($loan['loan_term']/7%4 == 0 ){
//                         //等额本息还款方式 应还本金的1% 加上 违约金 (总金额的3%)
//                         $weiyue_money +=  $list['money']*0.1;
//                     }
//                 }
        
        
//                 $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_plan') . " SET repay_status = '2' , weiyue_money = '" . $weiyue_money . "'  WHERE id = " . $list['id'];
//                 $GLOBALS['db']->query($sql);
                 
//             }
//         }
        
//         $sql = "SELECT *,FROM_UNIXTIME(plan_date,'%Y-%m-%d   %H:%i:%S') AS plan_date FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $loan['id'];
//         $loan_ALL = $GLOBALS['db']->getAll($sql);
        
//         if(empty($loan_ALL)){
//             Response::json(0,"查询失败","");
//         }else{
        
//             Response::json(1,"查询成功",$loan_ALL);
//         }
//     }elseif($loan_status['loan_status'] == 6 || $loan_status['loan_status'] == 7){
//         $sql = "SELECT *,FROM_UNIXTIME(plan_date,'%Y-%m-%d   %H:%i:%S') AS plan_date FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $loan_status['id'];
//         $loan_ALL = $GLOBALS['db']->getAll($sql);
        
//         Response::json(1,"查询成功",$loan_ALL);
//     }
       //查询还款记录   上方功能未加备用金操作
       $sql = "SELECT loan_status,id FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE  loan_userid = " . $para['loan_id'] . " ORDER BY id DESC LIMIT 1 ";
       $loan_status = $GLOBALS['db']->getRow($sql);
       
       $sql = "SELECT *,FROM_UNIXTIME(plan_date,'%Y-%m-%d   %H:%i:%S') AS plan_date FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $loan_status['id'];
       $loan_ALL = $GLOBALS['db']->getAll($sql);
       
       Response::json(1,"查询成功",$loan_ALL);
    
}

//提前还款
else if($para['action'] == "loanAdvanceMoney"){
    $sql = "SELECT id,loan_plan,money,loan_term FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE loan_status = 4 AND loan_userid = " . $para['loan_id'];
    $loan = $GLOBALS['db']->getRow($sql);
    
    if(empty($loan)){
        Response::json(0,"欠款已结清",0);
    }
    
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $loan['id'] . " AND repay_status = 0 ";
    $loan_list = $GLOBALS['db']->getAll($sql);
    
    $fuwufei = 0;
    foreach($loan_list AS $key => $list){
        $fuwufei += $list['service_fee'];
    }
    
    if($loan['loan_plan'] == 0){
        $advanceMoney = $loan['money']+$fuwufei+$loan['money']*0.0275;
    }else{
        foreach($loan_list AS $k => $val){
            $benjin += $val['money'];
        }
        
        $advanceMoney = $benjin+$fuwufei+$loan['money']*0.1275;
    }
    
    
    Response::json(1,"查询成功",$advanceMoney);
    
}

//借款人充值
else if($para['action'] == "fuYouChongZhi"){
    
    //生成四位随机数
    $r = rand(1000,9999);
    
    if($para['money'] <= 800){
        $fee_money = 2;
    }else{
        $fee_money = $para['money']*0.0025;
    }
    
    $real_money = $para['money'] - $fee_money;
    
    $order_id = time() .$r. $para['loan_id'];
    
    $sql = "INSERT INTO " . $GLOBALS['ecs']->table('platform_record') . " 
                (loan_user_id ,money ,real_money ,fee_money ,add_time ,flag ,from_app ,channel ,bank_card ,real_name ,id_card ,mobile,order_id) VALUES 
                    ('" . $para['loan_id'] . "','" . $para['money'] . "','" . $real_money . "','" . $fee_money . "','" . time() . "',
                        '1','1','1', '" . $para['bank_card'] . "','" . $para['real_name'] . "','" . $para['id_card'] . "','" . $para['mobile'] . "'," .$order_id. " ) ";
    
    /* if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"插入用户充值失败","");
    } */
    
   /*  $sql = "SELECT loan_money FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_id = " . $para['loan_id'];
    $loan_money = $GLOBALS['db']->getOne($sql);
    
    $money = $loan_money+$para['money'];
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_money = '" . $money . "'  WHERE loan_id = " . $para['loan_id']; */
    
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户充值失败","");
    }else{
        
        Response::json(1,"用户充值成功",$order_id);
    }
}


//富友充值回执
else if($para['action'] == "fuYouChongZhiHuiZhi"){

    //生成四位随机数
    $r = rand(1000,9999);
    
    if($para['money'] <= 800){
        $fee_money = 2;
    }else{
        $fee_money = $para['money']*0.0025;
    }

    $real_money = $para['money'] - $fee_money;

    $order_id = time() .$r. $para['loan_id'];

    $sql = "INSERT INTO " . $GLOBALS['ecs']->table('platform_record') . "
                (loan_user_id ,money ,real_money ,fee_money ,add_time ,flag ,from_app ,channel ,bank_card ,real_name ,id_card ,mobile,order_id) VALUES
                    ('" . $para['loan_id'] . "','" . $para['money'] . "','" . $real_money . "','" . $fee_money . "','" . time() . "',
                        '1','1','1', '" . $para['bank_card'] . "','" . $para['real_name'] . "','" . $para['id_card'] . "','" . $para['mobile'] . "'," .$order_id. " ) ";

    /* if(!$GLOBALS['db']->query($sql)){
     Response::json(0,"插入用户充值失败","");
     } */

    /*  $sql = "SELECT loan_money FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_id = " . $para['loan_id'];
     $loan_money = $GLOBALS['db']->getOne($sql);

     $money = $loan_money+$para['money'];

     $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_money = '" . $money . "'  WHERE loan_id = " . $para['loan_id']; */

    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户充值失败","");
    }else{

        Response::json(1,"用户充值成功",$order_id);
    }
}

//星星学贷还款
else if($para['action'] == "repayment"){
    /* $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE id = " . $para['id'];
    $plan = $GLOBALS['db']->getRow($sql);
    
    $yinghuan_money = $plan['money']+$plan['interest_money']+$plan['service_fee'];
    
    if($money < $yinghuan_money){
        $status = 0;
    }else{
        $status = 1;
    } */
    //查询还款记录
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE id = " . $para['id'];
    $loan_plan = $GLOBALS['db']->getRow($sql);
    
    //当前时间
    $time_tody = time();
    //计算还款时间与计划还款时间中间相隔多少秒
    $xiangge_time = $loan_plan['plan_date'] - $time_tody;
    
    $benjin_edu = 0;
    $fuwufei_edu = 0;
    
    //比较还款时间提前一天 当天12点之前当天16点之前
    if($xiangge_time >= 0){
        //正常还款 吧时间秒转换成小时
        $jiange_hours = $xiangge_time/60/60;
        
        if($jiange_hours > 24){
            //提前一天还款
            //判断还本金还是服务费
            if($loan_plan['money'] > 0){
                $benjin_edu = 50;
            }
            if($loan_plan['interest_money'] > 0){
                $fuwufei_edu = 25;
            }
        }elseif($jiange_hours > 12 && $jiange_hours < 24){
            //当天12点前还款
            if($loan_plan['money'] > 0){
                $benjin_edu = 30;
            }
            if($loan_plan['interest_money'] > 0){
                $fuwufei_edu = 15;
            }
        }else{
            //16点前还款
            if($loan_plan['money'] > 0){
                $benjin_edu = 10;
            }
            if($loan_plan['interest_money'] > 0){
                $fuwufei_edu = 5;
            }
        }
    }
    
    //应还多少钱
    $yinghuan = $loan_plan['money']+$loan_plan['interest_money']+$loan_plan['service_fee']+$loan_plan['weiyue_money']-$loan_plan['real_money'];
    
    $yinghuan_zonge = $loan_plan['money']+$loan_plan['interest_money']+$loan_plan['service_fee']+$loan_plan['weiyue_money'];
    
    //用户账户金额
    $sql = "SELECT loan_money,xinyong_money FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_id = " . $para['user_id'];
    $loan_money = $GLOBALS['db']->getRow($sql);
    
    //账户余额
    $money = $loan_money['loan_money'] - $yinghuan;
    
    //账户余额
    if($money < 0){
        Response::json(0,"资金不足","");
    }
    
    //计算自己池金额
    $xinyong_money = $loan_money['xinyong_money'] + $benjin_edu + $fuwufei_edu;
    
    //更新账户余额
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_money = '" . $money . "',xinyong_money = '" . $xinyong_money . "'  WHERE loan_id = " . $para['user_id'];
    
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户表更新失败","");
    }
    
    if($loan_plan['repay_status'] == 2){
        $beiyongjin_money = $loan_plan['money']+$loan_plan['interest_money']+$loan_plan['service_fee']-$loan_plan['real_money'];
        
        //增加备用金账户记录信息
        $sql = "INSERT INTO " . $GLOBALS['ecs']->table('beiyongjin_action') . " (loan_user_id, loan_plan_id, money, add_time,flag) VALUES ('" . $para['user_id'] . "', '" . $loan_plan['id'] . "','" . $beiyongjin_money . "', '" . time() . "',1)";
        if(!$GLOBALS['db']->query($sql)){
            Response::json(0,"用户还款失败","");
        }
        
        //扣除备用金账户金额
        $sql = "SELECT money FROM " . $GLOBALS['ecs']->table('beiyongjin') . " WHERE id = 1 ";
        $beiyongjin = $GLOBALS['db']->getOne($sql);
        
        $byj = $beiyongjin + $beiyongjin_money;
        
        //更新备用金账户金额
        $sql = "UPDATE " . $GLOBALS['ecs']->table('beiyongjin') . " SET money = '" . $byj . "'  WHERE id = 1";
        if(!$GLOBALS['db']->query($sql)){
            Response::json(0,"用户还款失败","");
        }
    }
    
    //更新还款记录状态
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_plan') . " SET  repay_status = '1', real_data = '" . time() . "',real_money = '" . $yinghuan_zonge . "'  WHERE id = " . $para['id'];
    if(!$GLOBALS['db']->query($sql)){
        Response::json(0,"用户还款失败","");
    }
    
    $sql = "SELECT loan_term,loan_status FROM " . $GLOBALS['ecs']->table('user_loan') . " WHERE id = " . $para['loan_id'];
    $loan_term = $GLOBALS['db']->getRow($sql);
    
    if($loan_term['loan_status'] == 7){
        Response::json(0,"用户已全部结清","");
    }
    
    $sql = "SELECT count(id) FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $para['loan_id'] . " AND repay_status = 1 ";
    $count = $GLOBALS['db']->getOne($sql);
    
    if($count == $loan_term['loan_term']/7){
        $sql = "UPDATE " . $GLOBALS['ecs']->table('user_loan') . " SET loan_status = 6 WHERE id = " . $para['loan_id'];
        
        if(!$GLOBALS['db']->query($sql)){
            Response::json(0,"用户还款失败","");
        }else{
            Response::json(1,"用户已全部结清",$money);
        }
    }
     
    Response::json(1,"用户还款成功",$money);
}


// 提前还款
else if($para['action'] == "userLoanAdvanceMoney"){
    $sql = "SELECT id,loan_plan,money,loan_term FROM " . $GLOBALS['ecs']->table('user_loan') . "  WHERE loan_status = 4 AND id = " . $para['loan_id'];
    $loan = $GLOBALS['db']->getRow($sql);
    
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $para['loan_id'] . " AND repay_status = 0 ";
    $loan_list = $GLOBALS['db']->getAll($sql);
    
    //利息
    $lixi = 0;
    foreach($loan_list AS $key => $list){
        $lixi += $list['service_fee'];
    }
    
    if($loan['loan_plan'] == 0){
        $advanceMoney = $loan['money']+$lixi+$loan['money']*0.0275;
        $weiyue_tiqian = 0;
    }else{
        foreach($loan_list AS $k => $val){
            $benjin += $val['money'];
        }
        $weiyue_tiqian = $loan['money']*0.1;
        $advanceMoney = $benjin+$lixi+$loan['money']*0.1275;
    }
    
    $sql = "SELECT loan_money FROM " . $GLOBALS['ecs']->table('loan_users') . " WHERE loan_id =  " . $para['user_id'];
    
    $loan_money = $GLOBALS['db']->getOne($sql);
    
    $user_money = $loan_money - $advanceMoney;
    
    if($user_money < 0){
        Response::json(0,"账户资金不足","");
    }
    
    $sql = "SELECT id FROM "  . $GLOBALS['ecs']->table('loan_plan') . "  WHERE user_loan_id = " . $para['loan_id'] . " AND repay_status = 0 ORDER BY current_flag ASC LIMIT 1";
    
    $plan_id = $GLOBALS['db']->getOne($sql);
    
    $GLOBALS['db']->query("BEGIN");
    
    $sql_update = "UPDATE  "  . $GLOBALS['ecs']->table('loan_plan') . " SET real_money = " . $advanceMoney . ",repay_status = 1,weiyue_money = '".$weiyue_tiqian."',real_data = '".time()."' WHERE id = " .$plan_id;
    
    if(!$GLOBALS['db']->query($sql_update)){
        $GLOBALS['db']->query("ROLLBACK");
        Response::json(0,"更新还款计划失败","");
    }
    
    $sql = "SELECT * FROM " . $GLOBALS['ecs']->table('loan_plan') . " WHERE user_loan_id = " . $para['loan_id'] . " AND repay_status = 0 ";
    
    $plan = $GLOBALS['db']->getAll($sql);
    
    foreach($plan AS $key=> $val){
        //TODO 提前还款状态 钱数清零
        $sql = "UPDATE  "  . $GLOBALS['ecs']->table('loan_plan') . " SET repay_status = 3,real_data = " . time() . " WHERE id = " . $val['id'];
        
        if(!$GLOBALS['db']->query($sql)){
            $GLOBALS['db']->query("ROLLBACK");
            Response::json(0,"更新还款计划失败","");
        }
    }
    
    
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET loan_money = " . $user_money . "  WHERE loan_id = " . $para['user_id'] ;
    
    if(!$GLOBALS['db']->query($sql)){
        $GLOBALS['db']->query("ROLLBACK");
        Response::json(0,"更新借款信息失败","");
    }
    
    $sql = "UPDATE " . $GLOBALS['ecs']->table('user_loan') . " SET loan_status = 7 WHERE id = " . $para['loan_id'] ;
    
    if(!$GLOBALS['db']->query($sql)){
        $GLOBALS['db']->query("ROLLBACK");
        Response::json(0,"更新还款计划失败","");
    }else{
        $GLOBALS['db']->query("COMMIT");
        Response::json(1,"用户还款成功","");
    }
    
}

Response::json(0,'参数为空',"");


