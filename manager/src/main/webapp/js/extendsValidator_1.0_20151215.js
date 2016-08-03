$.extend($.fn.validatebox.defaults.rules, {
	bankcard: { // 验证银行卡卡号
		validator: function(value) {
			return /^\d{10,30}$/.test(value);
		},
		message: '银行账号必须为10至30位数字'
	},
	number: { // 验证数字
		validator: function(value) {
			return /^\d+$/.test(value);
		},
		message: '请输入数字'
	},
	amount: { // 验证金额
		validator: function(value) {
			return /(^(0|([1-9][0-9]*))((.[0-9]{1,2}){0,1}$))/.test(value);

		},
		message: '请输入正确的金额格式,最多精确到小数点后2位'
	},
	busicode: { // 业务代码
		validator: function(value) {
			return /^\d{4}$/.test(value);

		},
		message: '业务代码必须为4位数字'
	},
	rate: { //  万分比
		validator: function(value) {
			return /^\d{1,4}$/.test(value);

		},
		message: '0~9999之间的数字'
	},
	percent: { //  百分比
		validator: function(value) {
			return /^(100|[0-9]{1,2})([.]\d{1,2})?$/.test(value);
			// /(^\d{1,2}$)|(^(0|([1-9][0-9])(.[0-9]{1,2})$))/.test(value);
		},
		message: '0~100之间,最多精确到小数点后2位'
	},
	miramount: { // 验证金额，单位分
		validator: function(value) {
			return /^([0-9]{1,12})$/.test(value);
		},
		message: '请输入不以0开头的数字,最大长度为12'
	},
	mcc: { //MCC
		validator: function(value) {
			return /^\d{4}$/.test(value);

		},
		message: 'MCC必须为4位数字'
	},
	merchno: { //商户号
		validator: function(value) {
			return /^\w{13,15}$/.test(value);

		},
		message: '请输入正确的商户号'
	},
	packcode: { // 版本号
		validator: function(value) {
			return /^\w{8}$/.test(value);

		},
		message: '8位字母,数字或下划线组成'
	},

	licencenoMerLength: { //营业执照号
		validator: function(value, param) {
			return value.length >= param[0];
		},
		message: '请输入15位的营业执照号号'

	},
	merLength: { //税务登记号
		validator: function(value, param) {
			return value.length >= param[0];
		},
		message: '请输入15-20位的税务登记号'

	},
	orgLength: {//组织机构代码
		validator: function(value, param) {
			return value.length >= param[0];
		},
		message: '请输入8-10位的组织机构代码号'

	},
	MerchLength: { //商户名称
		validator: function(value, param) {
			var i;
			var num = 0;
			var arr = value.replace(/\s+/g, '');
			if (arr != value) {
				return false;
			}
			for (i = 0; i < value.length; i++) {
				num++;
				if (value.charCodeAt(i) > 255) num++;
			}

			return num <= param[0];
		},
		message: '请输入不大于30位长度的商户名称，不可有空格'
	},
	postcode: { //邮政编码
		validator: function(value, param) {
			var re = /^\d+$/;
			if (!re.test(value)) {
				return false;
			}
			return value.length >= param[0];
		},
		message: '请输入正确的邮政编码'
	},

	accName: { //账户名
		validator: function(value, param) {
			var arr = value.replace(/\s+/g, '');
			if (arr != value) {
				return false;
			}

			return true;
		},
		message: '请输入正确的开户名，不可有空格'
	},
	settleAccount: { //结算账号
		validator: function(value, param) {
			var arr = value.replace(/\s+/g, '');
			if (arr != value) {
				return false;
			}
			return /^\d+$/.test(value);
		},
		message: '请输入正确的结算账号，不可有空格，必须为数字'
	},
	cardNo: { //身份证号
		validator: function(value) {
			return /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/.test(value);
		},
		message: '身份证输入不合法'
	},
	email: { //电子邮箱
		validator: function(value) {
			return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value);
		},
		message: '邮箱输入不合法'
	}, 
	chinesetest: { // 验证是否含中文,含有则返回false
		validator: function(value) {
			if (/[\u4E00-\u9FA5]/i.test(value)) {
				return false;
			} else {
				return true;
			}
		},
		message: '请输入正确的格式'
	},
	cellphonenum: { // 手机号
		validator: function(value) {
			if (/^1[3|4|5|8|7][0-9]\d{4,9}$/.test(value)) {
				return true;
			} else {
				return false;
			}
		},
		message: '请输入正确的手机号'
	} ,
	url: { // 网站地址
		validator: function(value) {
			if (/((https|http|ftp|rtsp|mms):\/\/)?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)/g.test(value)) {
				return true;
			} else {
				return false;
			}
		},
		message: '请输入正确的url'
	}    
});