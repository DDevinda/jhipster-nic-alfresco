package com.chainanalytica.nic.reports;


import java.util.Date;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;


public class UserReport extends BaseReport {

	@Override
	public DynamicReport buildReport() throws Exception {
		
		FastReportBuilder builder = new FastReportBuilder();
		builder.setMargins(20, 40, 30, 30);
		builder.setDetailHeight(10);
		builder.setTitle("User List");
		builder.setSubtitle("Nic All users: " + new Date());
		builder.addColumn("First name", "firstName", String.class.getName(), 33);
		builder.addColumn("Last name", "lastName", String.class.getName(), 33);
		builder.addColumn("Address", "address", String.class.getName(), 33);
		builder.addColumn("Telephone no.", "telephone", String.class.getName(), 33);
//		builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT);
		builder.setPrintBackgroundOnOddRows(true);
		builder.setUseFullPageWidth(true);
//		GroupBuilder gb1 = new GroupBuilder();
//		DJGroup group1 = gb1.setCriteriaColumn((PropertyColumn) builder.getColumn(0))
//				.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
//				.build();
//		builder.addGroup(group1);
		getParams().put("test1", "test2");
		builder.setTemplateFile("template-report.jrxml");
		return builder.build();
	}

	
	





	
	

}
