<%@ page contentType="application/vnd.ms-excel;charset=GBK"%>
<% response.setContentType("application/octet-stream");      response.addHeader("Content-Disposition","attachment; filename=export.xls"); %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<head>
<title>Test</title>
</head>
<style>
.number {
	mso-number-format: 0\.00;
}

.text {
	mso-number-format: \@
}

.date {
	mso-number-format: "yy\/mm\/dd"
}

.percent {
	mso-number-format: Percent
}
</style>

<body>
	<TABLE cellSpacing=0 cellPadding=2 width=1200 align=center border=1>
		<tr>
			<td align="center" colspan="4">商户秘钥</td>

		</tr>
		<TR>
			<td align="center">序号</td>
			<td align="center">商户私钥</td>
			<td align="center">平台公钥</td>

		</tr>
		<tr>
			<td align="center" class="text"><s:property
					value="merchMap.MEMBERID" /></td>

			<td align="center" class="text"><s:property
					value="merchMap.MEMBERPRIKEY" /></td>
			<td align="center" class="text"><s:property
					value="merchMap.LOCALPUBKEY" /></td>

		</tr>

	</TABLE>
</body>
</HTML>
