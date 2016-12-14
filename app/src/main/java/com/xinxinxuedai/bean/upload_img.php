<?php
define('IN_ECS', true);
require(dirname(__FILE__) . '/includes/init.php');
require_once(ROOT_PATH . '/includes/Response.php');


$loan_id = $_GET['loan_id'];
$loan_area = $_GET['loan_area'];

if($loan_area == 1){
    $loan_area = "Northeast_area";
}else if($loan_area == 2){
    $loan_area = "Middle_area";
}else if($loan_area == 3){
    $loan_area = "Southwest_area";
}else if($loan_area == 4){
    $loan_area = "Southeast_area";
}else{
    $loan_area = "No_open_area";
}

if($_GET['app'] == "xingxingdai"){
    
    if($_FILES['file']['error'] == 0){
    
        if(!is_uploaded_file($_FILES['file']['tmp_name'])){
            Response::json_noASE(0,'上传图片模式出错',"");
        }
    
        $upload_path = ROOT_PATH . "upload_file/" . $_GET['app'] . "/" . $loan_area . "/" . $loan_id . "/"; //上传文件的存放路径
        
        $tab_path = "/" . $_GET['app'] . "/" . $loan_area . "/" . $loan_id . "/";
    
        // 如果目标目录不存在，则创建它
        if (!file_exists($upload_path))
        {
            if (!make_dir($upload_path))
            {
                Response::json_noASE(0,'创建图片上传目录失败',"");
            }
        }
        
        if($_FILES['file']['name'] == 'xingxingdai_loan_id_card_photo1.jpg'){
            $aa = 'photo1_'.time().'.jpg';
            $where = "loan_id_card_photo1 = '" . $tab_path.$aa . "'"; 
            
        }else if($_FILES['file']['name'] == 'xingxingdai_loan_id_card_photo2.jpg'){
            $aa = 'photo2_'.time().'.jpg';
            $where = "loan_id_card_photo2 = '" . $tab_path.$aa . "'";
            
        }else if($_FILES['file']['name'] == 'xingxingdai_loan_id_card_photo3.jpg'){
            $aa = 'photo3_'.time().'.jpg';
            $where = "loan_id_card_photo3 = '" . $tab_path.$aa . "'";
            
        }else if($_FILES['file']['name'] == 'xingxingdai_loan_video1.mp4'){
            $aa = 'video1_'.time().'.mp4';
            $where = "loan_video1 = '" . $tab_path.$aa . "'";
            
        }else{
            $aa = 'video2_'.time().'.mp4';
            $where = "loan_video2 = '" . $tab_path.$aa . "'";
            
        }
        
        $sql = "UPDATE " . $GLOBALS['ecs']->table('loan_users') . " SET " . $where . "  WHERE loan_id = " . $loan_id;
    
        $GLOBALS['db']->query($sql);
        
        //开始移动文件到相应的文件夹
        if(move_uploaded_file($_FILES['file']['tmp_name'],$upload_path.$aa)){
            Response::json_noASE(1,'图片上传成功',"");
        }else{
            Response::json_noASE(0,'图片上传失败',"");
        }
    
    
    
    }else{
        Response::json_noASE(0,'上传图片出错',"");
    }
}



/**
 *  生成指定目录不重名的文件名
 *
 * @access  public
 * @param   string      $dir        要检查是否有同名文件的目录
 *
 * @return  string      文件名
 */
/* function unique_name($dir)
{
    $filename = '';
    while (empty($filename))
    {
        $filename = random_filename();
        if (file_exists($dir . $filename . '.jpg') || file_exists($dir . $filename . '.gif') || file_exists($dir . $filename . '.png'))
        {
            $filename = '';
        }
    }

    return $filename;
} */

/**
 * 生成随机的数字串
 *
 * @return string
 */
/* function random_filename(){
    $str = '';
    for($i = 0; $i < 9; $i++)
    {
        $str .= mt_rand(0, 9);
    }

    return gmtime() . $str;
} */