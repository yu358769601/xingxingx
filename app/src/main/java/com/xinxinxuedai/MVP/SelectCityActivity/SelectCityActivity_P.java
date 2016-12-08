package com.xinxinxuedai.MVP.SelectCityActivity;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsMeasure;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:45 . 2016年12月08日
 * 描述:选择城市_P
 * <p>
 * <p>
 * 备注:
 */

public class SelectCityActivity_P extends BaseMvp<SelectCityActivity_C> implements SelectCityActivity_M{

    //34
    final String[] sheng = {"黑龙江","北京","上海","天津","重庆","河北", "山西","内蒙古","辽宁","吉林","江苏","浙江",
            "安徽","福建","江西","山东","河南","湖北", "湖南","广东","广西","海南","四川","贵州",
            "云南","西藏","陕西","甘肃","青海","宁夏", "新疆","台湾","香港","澳门"};

    final String[][] city = {
            {"哈尔滨","齐齐哈尔","鸡西","鹤岗","双鸭山","大庆", "伊春","佳木斯","七台河","牡丹江","黑河","绥化","大兴安岭"},
            {"北京"},
            {"上海"},
            {"天津"},
            {"重庆"},
            {"石家庄","唐山","秦皇岛","邯郸","邢台","保定","张家口","承德","沧州","廊坊","衡水"},
            {"太原","大同","阳泉","长治","晋城","朔州", "晋中","运城","忻州","临汾","吕梁"},
            {"呼和浩特","包头","乌海","赤峰","通辽","鄂尔多斯", "呼伦贝尔","巴彦淖尔","乌兰察布","兴安","锡林郭勒","阿拉善"},
            {"沈阳","大连","鞍山","抚顺","本溪","丹东","锦州","营口","阜新","辽阳","盘锦","铁岭","朝阳","葫芦岛"},
            {"吉林","长春","四平","辽源","通化","白山","松原","白城","延边"},
            {"南京","无锡","徐州","常州","苏州","南通","连云港","淮安","盐城","扬州","镇江","泰州","宿迁"},
            {"杭州","宁波","温州","嘉兴","湖州","绍兴","金华","衢州","舟山","台州","丽水"},
            {"合肥","芜湖","蚌埠","淮南","马鞍山","淮北","铜陵","安庆","黄山","滁州","阜阳","宿州","巢湖","六安","亳州","池州","宣城"},
            {"福州","厦门","莆田","三明","泉州","漳州","南平","龙岩","宁德"},
            {"南昌","景德镇","萍乡","九江","新余","鹰潭","赣州","吉安","宜春","抚州","上饶"},
            {"济南","青岛","淄博","枣庄","东营","烟台","潍坊","威海","济宁","泰安","日照","莱芜","临沂","德州","聊城","滨州","菏泽"},
            {"郑州","开封","洛阳","平顶山","焦作","鹤壁","新乡","安阳","濮阳","许昌","漯河","三门峡","南阳","商丘","信阳","周口","驻马店"},
            {"武汉","黄石","襄樊","十堰","荆州","宜昌","荆门","鄂州","孝感","黄冈","咸宁","随州","恩施"},
            {"长沙","株洲","湘潭","衡阳","邵阳","岳阳","常德","张家界","益阳","郴州","永州","怀化","娄底","湘西"},
            {"广州","深圳","珠海","汕头","韶关","佛山","江门","湛江","茂名","肇庆","惠州","梅州","汕尾","河源","阳江","清远","东莞","中山","潮州","揭阳","云浮"},
            {"南宁","柳州","桂林","梧州","北海","防城港","钦州","贵港","玉林","百色","贺州","河池","来宾","崇左"},
            {"海口","三亚"},
            {"成都","自贡","攀枝花","泸州","德阳","绵阳","广元","遂宁","内江","乐山","南充","宜宾","广安","达州","眉山","雅安","巴中","资阳","阿坝","甘孜","凉山"},
            {"贵阳","六盘水","遵义","安顺","铜仁","毕节","黔西南","黔东南","黔南"},
            {"昆明","曲靖","玉溪","保山","昭通","丽江","普洱","临沧","文山","红河","西双版纳","楚雄","德宏","怒江","迪庆"},
            {"拉萨","昌都","山南","日喀则","那曲","阿里","林芝"},
            {"西安","铜川","宝鸡","咸阳","渭南","延安","汉中","榆林","安康","商洛"},
            {"兰州","嘉峪关","金昌","白银","天水","武威","张掖","平凉","酒泉","庆阳","定西","陇南","临夏","甘南"},
            {"西宁","海东","海北","黄南","海南","果洛","玉树","海西"},
            {"银川","石嘴山","吴忠","固原","中卫"},
            {"乌鲁木齐","克拉玛依","吐鲁番","哈密","和田","阿克苏","喀什","克孜勒苏柯尔克孜","巴音郭楞蒙古","博尔塔拉蒙古","塔城","阿勒泰"},
            {"台北","高雄","基隆","台中","台南","新竹","嘉义"},
            {"香港"},
            {"澳门"},
    };



    static  SelectCityActivity_P sSelectCityActivity_p;
    Context context;
    public SelectCityActivity_P(Context context) {
        this.context = context;
    }

    public static SelectCityActivity_P getSelectCityActivity_p(Context context){
        if (sSelectCityActivity_p == null){
            return sSelectCityActivity_p = new SelectCityActivity_P(context);
        }
        return sSelectCityActivity_p;
    }

    SelectCityActivity_C selectCityActivity_c;
    @Override
    public void setCallBack(SelectCityActivity_C selectCityActivity_c) {
        this.selectCityActivity_c = selectCityActivity_c;
    }
    String shengtitle ="";
    String shititle ="";
    /**
     * 初始化 radioGroup 的数据
     * @param selectcity_rg1
     * @param selectcity_rg2
     */
    @Override
    public void setRadioGroup(RadioGroup selectcity_rg1, final RadioGroup selectcity_rg2) {
        selectcity_rg1.removeAllViews();

        // LogUtils.i("tag数组内容是"+sheng);
        final RadioGroup myRadioGroup = selectcity_rg1;
        for (int i = 0; i < sheng.length; i++) {
            RadioButton radioButton = new RadioButton(context);

            radioButton.setText(sheng[i]);
           // LogUtils.i("tag数组内容是"+city[i]);
            radioButton.setTag(city[i]);
            radioButton.setPadding(0,10,0,10);
            myRadioGroup.addView(radioButton);

        }


        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectcity_rg2.removeAllViews();

                for (int i = 0; i < myRadioGroup.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) group.getChildAt(i);
                    if (childAt.getId() ==checkedId){
                        int[] measure = UtilsMeasure.measure(childAt);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams)selectcity_rg2.getLayoutParams();
                        layoutParams.setMargins(0, measure[1]*(i),0,0);
                        selectcity_rg2.setLayoutParams(layoutParams);

                        shengtitle = childAt.getText().toString();
                        String[] childAtTag = (String[]) childAt.getTag();

                        //给右面的添加数据
                        for (int y = 0; y < childAtTag.length; y++) {
                            RadioButton radioButton = new RadioButton(context);

                            radioButton.setText(childAtTag[y]);
                            // LogUtils.i("tag数组内容是"+city[i]);
                            radioButton.setTag(y);
                            radioButton.setPadding(0,10,0,10);
                            selectcity_rg2.addView(radioButton);

                        }
                        selectcity_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                for (int i = 0; i < group.getChildCount(); i++) {
                                    RadioButton childAt = (RadioButton) group.getChildAt(i);
                                    if (childAt.getId()==checkedId){
                                        shititle = childAt.getText().toString();
                                        LogUtils.i("省"+shengtitle+"\t"+"市"+shititle);
                                        selectCityActivity_c.getData(shengtitle, shititle);
                                    }
                                }
                            }
                        });
                    }
                }

                LogUtils.i("我点了"+(checkedId)+"号城市");
                //设置右面的出现高度
                //先获取左面点按钮的高度 在设置右面 selectcity_rg2 margin + 左面按钮的当前高度
               // ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();




            }
        });

    }
}
