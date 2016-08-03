var Nums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); 
var Digits = new Array("", "拾", "佰", "仟"); 
var Units = new Array("", "万", "亿", "万亿","仟兆"); 
 
/**
* 检查字符串是否为合法的金额
* @param {String} 字符串
* @return {bool} 是否为合法金额
*/
function isMoney(s) 
{
    var isMoney = RegExp(/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/);
    return ( isMoney.test(s) );
}
 
/**
* 判断输入变量是否是浮点数
* @param {String} 要检查的变量值
* @return {bool} 是否为浮点数
*/
function isFloat( s )
{
    var isFloat = RegExp(/^([0-9]+(\.+))[0-9]+$/);
    return ( isFloat.test(s) );
}
 
/**
* 检查指定的input中输入的金额是否合法，如果合法返回金额，否则返回null
* @param {String} 要检查的input的id
*/
function getMoney(inputName)
{

    var money = document.getElementById(inputName).value;
    if ( money !=null && money.length>0 )
    {
        return removeComma( money );
    }else{
        return null;
    }
}
 
/**
* 检查指定的input中输入的金额是否合法，并进行显示转换
* @param {String} 要检查的input的id
*/
function checkMoney(inputName)
{
    var input = document.getElementById(inputName);
    if ( isEmpty( input.value ) )
    {
        return false;
    }
    var testMoney = removeComma( input.value );
    if ( isMoney( testMoney ) )
    {
        input.value = toCashWithCommaAndDot( testMoney );
        return true;
    }else{
        input.value = "";
        input.focus();
        return false;
    }
} 
 
/**
* 转换为中文金额表示
* @param {String} 要转换的金额字符串
* @return {String} 转换后的金额字符串
*/
function toChineseCash( cash )
{
    var noCommaCash = removeComma(cash);
    if( !isFloat( noCommaCash ) )
        return convertIntegerToChineseCash(noCommaCash);   
             
    var dotIndex = noCommaCash.indexOf('.');
    var integerCash = noCommaCash.substring( 0, dotIndex );
    var decimalCash = noCommaCash.substring( dotIndex + 1 );
     
    return convertIntegerToChineseCash(integerCash)+convertDecimalToChineseCash(decimalCash);
}
 
/**
* 为金额添加,分割符
* @param {String} 要转换的金额字符串
* @return {String} 转换后的金额字符串
*/
function toCashWithComma( cash )
{
    while( cash.charAt(0) == '0' )
    {
        cash = cash.substr(1);
    }
    if( !isFloat( cash ) )
        return addComma(cash);   
             
    var dotIndex = cash.indexOf('.');
    var integerCash = cash.substring( 0, dotIndex );
    var decimalCash = cash.substring( dotIndex );
     
    return addComma(integerCash)+decimalCash;
}
 
/**
* 为金额添加,分割符和.分割符
* @param {String} 要转换的金额字符串
* @return {String} 转换后的金额字符串
*/
function toCashWithCommaAndDot( cash )
{
    var temp = toCashWithComma( cash );
    if ( temp.length == 0 )
    {
        return "0.00";
    }
    var dotPos = temp.indexOf(".");
    if ( dotPos < 0 )
    {
        return temp+'.00';
    }
    if ( dotPos == 0 )
    {
        temp = '0' + temp;
        dotPos = temp.indexOf(".");
    }
    if ( dotPos == temp.length-2)
    {
        return temp + '0';
    }
    if ( dotPos == temp.length-1)
    {
        return temp + '00';
    }
    return temp;
}
 
function convertIntegerToChineseCash(cash)
{
    if ( cash == "0" )
        return "";
    var S = ""; //返回值 
    var p = 0; //字符位置指针 
    var m = cash.length % 4; //取模 
 
    // 四位一组得到组数 
    var k = (m > 0 ? Math.floor(cash.length / 4) + 1 : Math.floor(cash.length / 4)); 
    // 外层循环在所有组中循环 
    // 从左到右 高位到低位 四位一组 逐组处理 
    // 每组最后加上一个单位: "[万亿]","[亿]","[万]" 
    for (var i = k; i > 0; i--) 
    {
        var L = 4; 
        if (i == k && m != 0)
        {
            L = m;
        }
        // 得到一组四位数 最高位组有可能不足四位 
        var s = cash.substring(p, p + L);
        var l = s.length;
 
        // 内层循环在该组中的每一位数上循环 从左到右 高位到低位 
        for (var j = 0;j < l;j++)
        {
            var n = parseInt(s.substring(j, j+1));
            if (n == 0)
            {
                if ((j < l - 1) && (parseInt(s.substring(j + 1, j + 1+ 1)) > 0) //后一位(右低) 
                    && S.substring(S.length-1,S.length) != Nums[n])
                {
                    S += Nums[n];
                }
            }
            else 
            {
                //处理 1013 一千零"十三", 1113 一千一百"一十三" 
                if (!(n == 1 && (S.substring(S.length-1,S.length) == Nums[0] | S.length == 0) && j == l - 2)) 
                {
                    S += Nums[n];
                }
                S +=  Digits[l - j - 1];
            }
        }
        p += L;
        // 每组最后加上一个单位: [万],[亿] 等 
        if (i < k)//不是最高位的一组 
        {
            if (parseInt(s)!= 0)
            {
                //如果所有 4 位不全是 0 则加上单位 [万],[亿] 等 
                S += Units[i - 1];
            }
        }
        else
        {
            //处理最高位的一组,最后必须加上单位 
            S += Units[i - 1];
        }
    }
    //alert(S);
    return S+"圆";
}
 
function convertDecimalToChineseCash( cash )
{
    var returnCash = "";
     
    if ( cash == "00" )
        returnCash = "整";
    else
    {
        for( var i = 0;i < cash.length; i++ )
        {
            if( i >= 2 )
                break;
            var intValue = parseInt(cash.charAt(i));
         
            switch( i )
            {
                case 0:
                    if ( intValue != 0 )
                        returnCash += Nums[intValue]+"角";
                    break;
                case 1:
                    returnCash += Nums[intValue]+"分";
                    break;
                default:
                    break;
            }
        }
         
    }
    return returnCash;   
}
 
function addComma(str) {
    if (str.length > 3) 
    {
        var mod = str.length % 3;
        var output = (mod > 0 ? (str.substring(0,mod)) : '');
        for (i=0 ; i < Math.floor(str.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += str.substring(mod+ 3 * i, mod + 3 * i + 3);
            else
                output += ',' + str.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
    else return str;
}
 
function removeComma(str)
{
    return str.replace(new RegExp('\,',["g"]),'');
}