package com.project1;

import java.text.DecimalFormat;

import com.project1.model.Bill;
import com.project1.model.Product;

public class Template {
	
	private static StringBuffer stringBuffer = new StringBuffer();
	
	public static String getTemplate(Bill bill) {
		stringBuffer.setLength(0);
append("<style type=\"text/css\">");
append("    table {");
append("        font-family: Arial, \"Helvetica Neue\", Helvetica, sans-serif;");
append("        font-size: 16px;");
append("        width: 380px;");
append("		margin: 0 auto;");
append("    }");
append("        table td {");
append("            line-height: 1.2em;");
append("        }");
append("    .text-center {");
append("        text-align: center;");
append("    }");
append("    .text-left {");
append("        text-align: left;");
append("    }");
append("    .text-right {");
append("        text-align: right;");
append("    }");
append("</style>");
append("<table>");
append("    <tr>");
append("        <td style=\"padding:0;margin:0;font-size:20px\" class=\"text-center\" colspan=\"3\"><strong>");append(bill.getCompanyName());append("</strong></td>");
append("    </tr>");
append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\" style=\"padding:5px 0px\">"); append(bill.getWebsite()); append("</td>");
append("    </tr>");
append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\">"); append(bill.getStoreAddress()); append("</td>");
append("    </tr>");
append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;padding: 5px 0px\"></td>");
append("    </tr>");
append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\" style=\"font-size:20px;padding:10px 0px\"><strong>PHIẾU THANH TOÁN</strong></td>");
append("    </tr>");
append("    <tr>");
append("        <td>Số CT:</td>");
append("        <td colspan=\"2\">"); append(bill.getOutputVoucherID()); append("</td>");
append("    </tr>");
append("    <tr>");
append("        <td>Ngày CT:</td>");
append("        <td colspan=\"2\">"); append(bill.getOutputDATE()); append("</td>");
append("    </tr>");
append("    <tr>");
append("        <td>Nhân viên:</td>");
append("        <td colspan=\"2\">"); append(bill.getOutputUSER()); append("</td>");
append("    </tr>");
append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;padding: 5px 0px\"></td>");
append("    </tr>");
append("    <tr>");
append("        <td class=\"text-center\" style=\"padding:10px 0px;font-size:17px\"><strong>SL</strong></td>");
append("        <td class=\"text-center\" style=\"padding:10px 0px;font-size:17px\"><strong>Giá bán</strong></td>");
append("        <td class=\"text-right\" style=\"padding:10px 0px;font-size:17px\"><strong>T.Tiền</strong></td>");
append("    </tr>");
if(bill.getProducts() != null) {
	for(Product product : bill.getProducts()) {
		
append("		<tr>");
append("     		<td colspan=\"3\">"); append(product.getProductName()); append("</td>");
append("		</tr>");
append("        <tr>");
append("            <td class=\"text-center\" style=\"padding:5px 0px\">"); append(formatNumber(product.getQuantity()));  append("</td>");
append("            <td class=\"text-center\" style=\"padding:5px 0px\">"); append(formatNumber(product.getSalePriceVAT())); append("</td>");
append("            <td class=\"text-right\" style=\"padding:5px 0px\">"); append(formatNumber(product.getTotalAmountVAT())); append("</td>");
append("        </tr>");
	}			
} else {
append("<tr>");
append("     <td colspan=\"3\"></td>");
append("</tr>");
append("<tr>");
append("     <td class=\"text-center\" style=\"padding:5px 0px\"></td>");
append("     <td class=\"text-center\" style=\"padding:5px 0px\"></td>");
append("     <td class=\"text-right\" style=\"padding:5px 0px\"></td>");
append("</tr>");
}

append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;padding: 5px 0px\"></td>");
append("    </tr>");

append("    <tr>");
append("        <td class=\"text-right\" colspan=\"2\">Tổng tiền:</td>");
append("        <td class=\"text-right\">"); append(formatNumber(bill.getTotalAmount())); append("</td>");
append("    </tr>");

if(bill.getTotalDiscount() != 0) {
append("    <tr>");
append("            <td class=\"text-right\" colspan=\"2\">Đã giảm:</td>");
append("            <td class=\"text-right\">"); append(formatNumber(bill.getTotalDiscount())); append("</td>");
append("    </tr>");
}
if(bill.getTotalGiftVoucherAmount() != 0) {
append("    <tr>");
append("            <td class=\"text-right\" colspan=\"2\">Phiếu mua hàng:</td>");
append("            <td class=\"text-right\">"); append(formatNumber(bill.getTotalGiftVoucherAmount())); append("</td>");
append("    </tr>");
}
if(bill.getMoneyCard() != 0) {
append("    <tr>");
append("            <td class=\"text-right\" colspan=\"2\">Tiền cà thẻ:</td>");
append("            <td class=\"text-right\">"); append(formatNumber(bill.getMoneyCard())); append("</td>");
append("    </tr>");
}

append("    <tr>");
append("        <td class=\"text-right\" colspan=\"2\"><strong style=\"font-size:18px\">Thanh toán:</strong><br /><i>(Đã làm tròn)</i></td>");
append("        <td class=\"text-right\" style=\"font-size: 20px;\"><strong>");  append(formatNumber(bill.getTotalAmountRound())); append("</strong></td>");
append("    </tr>");

append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;padding: 5px 0px\"></td>");
append("    </tr>");

append("    <tr>");
append("        <td colspan=\"3\" class=\"text-center\"><i>(Giá trên đã bao gồm thuế GTGT)</i></td>");
append("    </tr>");

append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;padding: 5px 0px\"></td>");
append("    </tr>");

append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\"><img style=\"width:330px;height:40px;margin:5px;\" src=\""); append(bill.getBarcode()); append("\" />");    append("</td>"); 
append("    </tr>");

append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;\"></td>");
append("    </tr>");

append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\"><img style=\"width:15%;height:15%;margin:5px;\" src=\""); append(bill.getQrCode()); append("\" />");    append("</td>"); 
append("    </tr>");

append("    <tr>");
append("        <td colspan=\"3\" style=\"border-bottom:1px solid black;\"></td>");
append("    </tr>");

append("    <tr>");
append("        <td class=\"text-center\" colspan=\"3\" style=\"padding:10px; \">"); append(bill.getSpecialMessage()); append("</td>");
append("    </tr>");
append("</table>");
		
		return stringBuffer.toString();
	}
	
	private static void append(String text) {
		stringBuffer.append(text);
	}
	
	
	public static String formatNumber(int value) {
	    DecimalFormat df = new DecimalFormat("###,###,###");
	    return df.format(value);
	}
	
}
