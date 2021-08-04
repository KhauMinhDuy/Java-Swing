package com.project1;

public class Template {

	
	public static String getTemplate(String diachi, String soCT, String ngayCT, String nhanvien
			, boolean daGiam,
			boolean phieuMH,
			boolean istienThe) {
		
		String result = ""
				+ "<head>\r\n"
				+ "<title>Document</title>\r\n"
				+ "<style type=\"text/css\">\r\n"
				+ "* {\r\n"
				+ "	margin: 0;\r\n"
				+ "	padding: 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "h1, h2, h3, h4 {\r\n"
				+ "	text-align: center;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".div-pmh {\r\n"
				+ "	margin: 10px 0 10px 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".info-pmh {\r\n"
				+ "	width: 75%;\r\n"
				+ "	margin: 10px auto;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".info-sp {\r\n"
				+ "	width: 75%;\r\n"
				+ "	margin: 10px auto;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".info-tt {\r\n"
				+ "	width: 75%;\r\n"
				+ "	margin: 10px auto;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "hr {\r\n"
				+ "	margin: 0 auto;\r\n"
				+ "	width: 90%;\r\n"
				+ "}\r\n"
				+ "</style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<div style=\"width: 70%; margin: 0 auto;\">\r\n"
				+ "		<div>\r\n"
				+ "			<h2>BÁCH HÓA XANH</h2>\r\n"
				+ "			<h3 style=\"font-weight: normal;\">www.bachhoaxanh.com</h3>\r\n"
				+ "			<!-- <h3 style=\"font-weight: normal;\">193-195 Dương Văn Dương, P.Tân Quý, Q.Tân Bình, Tp.HCM</h3> -->\r\n"
				+ "			<h3 style=\"font-weight: normal;\">"+ diachi +"</h3>\r\n"
				+ "		</div>\r\n"
				+ "		<hr>\r\n"
				+ "		<div class=\"div-pmh\">\r\n"
				+ "			<h2>PHIẾU THANH TOÁN</h2>\r\n"
				+ "			<table class=\"info-pmh\">\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"40%\">Số CT:</td>\r\n"
				+ "					<td>"+ soCT +"</td>\r\n"
				+ "				</tr>\r\n"
				+ "				<tr>\r\n"
				+ "					<td>Ngày CT:</td>\r\n"
				+ "					<td>" + ngayCT + "</td>\r\n"
				+ "				</tr>\r\n"
				+ "				<tr>\r\n"
				+ "					<td>Nhân Viên:</td>\r\n"
				+ "					<td>" + nhanvien +"</td>\r\n"
				+ "				</tr>\r\n"
				+ "			</table>\r\n"
				+ "			<hr>\r\n"
				+ "			<table class=\"info-sp\">\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"100\" style=\"text-align: center; font-weight: bold;\">SL</td>\r\n"
				+ "					<td width=\"100\" style=\"text-align: right; font-weight: bold;\">Giá\r\n"
				+ "						Bán</td>\r\n"
				+ "					<td width=\"150\" style=\"text-align: right; font-weight: bold;\">T.Tiền</td>\r\n"
				+ "				</tr>\r\n"
				+ "				\r\n"
				+ "				<tr>\r\n"
				+ "					<td colspan=\"3\">Dua Leo</td>\r\n"
				+ "				</tr>\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"100\" style=\"text-align: center;\">10</td>\r\n"
				+ "					<td width=\"100\" style=\"text-align: right;\">19,216</td>\r\n"
				+ "					<td width=\"150\" style=\"text-align: right;\">192,157</td>\r\n"
				+ "				</tr>\r\n"
				+ "				\r\n"
				+ "				<tr>\r\n"
				+ "					<td colspan=\"3\">BIA 333 LON 330ML THUNG 24</td>\r\n"
				+ "				</tr>\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"100\" style=\"text-align: center;\">10</td>\r\n"
				+ "					<td width=\"100\" style=\"text-align: right;\">225,784</td>\r\n"
				+ "					<td width=\"150\" style=\"text-align: right;\">2,257,843</td>\r\n"
				+ "				</tr>\r\n"
				+ "				\r\n"
				+ "				<tr>\r\n"
				+ "					<td colspan=\"3\">MÌ HẢO HẢO TÔM CHUA CAY 75G</td>\r\n"
				+ "				</tr>\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"100\" style=\"text-align: center;\">1</td>\r\n"
				+ "					<td width=\"100\" style=\"text-align: right;\">3,900</td>\r\n"
				+ "					<td width=\"150\" style=\"text-align: right;\">3,900</td>\r\n"
				+ "				</tr>\r\n"
				+ "			</table>\r\n"
				+ "			<hr>\r\n"
				+ "			<table class=\"info-tt\" style=\"text-align: right;\">\r\n"
				+ "				<tr>\r\n"
				+ "					<td width=\"50%\" style=\"text-align: right;\">Tổng Tiền:</td>\r\n"
				+ "					<td>2,453,900</td>\r\n"
				+ "				</tr>\r\n";
		
		String footer = "" 
				+ "				<tr>\r\n"
				+ "					<td style=\"font-weight: bold; font-size: 20px\">Tổng Tiền:</td>\r\n"
				+ "					<td style=\"font-weight: bold; font-size: 20px\">1,303,500</td>\r\n"
				+ "				</tr>\r\n"
				+ "			</table>\r\n"
				+ "			<hr>\r\n"
				+ "			<p style=\"text-align: center; margin: 10px 0 10px 0;\">(Giá trên\r\n"
				+ "				đã bao gồm thuế GTGT)</p>\r\n"
				+ "			<hr>\r\n"
				+ "			<p style=\"margin: 10px 0 10px 0; text-align: center;\">\r\n"
				+ "				<img style=\"height: 50px; width: 80%;\"\r\n"
				+ "					 alt=\"\"\r\n"
				+ "					src=\"src\\main\\resources\\barcode.png\">\r\n"
				+ "			</p>\r\n"
				+ "			<hr>\r\n"
				+ "			<div style=\"margin: 0px auto; text-align: center;\">\r\n"
				+ "             <p style=\"margin-bottom: 10px; \">Mã đơn hàng web</p>"
				+ "				<img style=\"height: 15%; width: 15%;\"\r\n"
				+ "					 alt=\"\"\r\n"
				+ "					src=\"src\\main\\resources\\qr_code.jpg\">\r\n"
				+ "			</div>"
				+ "			<hr>\r\n"
				+ "			<p style=\"text-align: center; width: 90%; margin: 0 auto;\">\r\n"
				+ "				Tổng đài góp ý/khiếu nại:1800 1067\r\n"
				+ "			</p>"
				+ "			<p style=\"text-align: center; width: 90%; margin: 10px auto 0;\">Lưu\r\n"
				+ "				ý: Bách Hóa Xanh chỉ xuất hóa đơn trong ngày, Quý khách vui lòng\r\n"
				+ "				liên hệ thu ngân để được hỗ trợ. Quý khách có thể in bản sao hóa đơn\r\n"
				+ "				VAT tại trang web https://hddt.bachhoaxanh.com</p>\r\n"
				+ "			<br>\r\n"
				+ "			<p style=\"text-align: center; width: 90%; margin: 0 auto;\">Quý\r\n"
				+ "				khách vui lòng xem chi tiết Chính Sách đổi-trả hàng được niêm yết\r\n"
				+ "				tại cửa hàng BHX. Xin cảm ơn quý khách. Hẹn gặp lại.</p>\r\n"
				+ "		</div>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		String daGiamstr = 
				  "<tr>\r\n"
				+ "<td style=\"text-align: right;\">Đã Giảm:</td>\r\n"
				+ "<td>100,000</td>\r\n"
				+ "</tr>\r\n";
		String phieuMHstr = 
				  "<tr>\r\n"
				+ "<td style=\"text-align: right;\">Phiếu Mua Hàng:</td>\r\n"
				+ "<td>150,000</td>\r\n"
				+ "</tr>\r\n";
		
		String tienTheStr = 
				  "<tr>\r\n"
				+ "<td style=\"text-align: right;\">Tiền Cà Thẻ:</td>\r\n"
				+ "<td>1,000,000</td>\r\n"
				+ "</tr>\r\n";
		
		if(daGiam) {
			result += daGiamstr;
		}
		if(phieuMH) {
			result += phieuMHstr;
		}
		if(istienThe) {
			result += tienTheStr;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		result += footer;
		
		return result;
		
	}
}
