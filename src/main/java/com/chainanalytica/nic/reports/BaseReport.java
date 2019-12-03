package com.chainanalytica.nic.reports;
import java.util.HashMap;
import java.util.Map;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public abstract class BaseReport {
	protected JasperPrint jasperPrint;
	protected JasperReport jasperReport;
	protected Map<String, Object> params = new HashMap<String, Object>();
	protected DynamicReport dynamicReport;
	public abstract DynamicReport buildReport() throws Exception;
	public void generateReport(JRDataSource dataSourcep) throws Exception {
		dynamicReport = buildReport();
		JRDataSource dataSource = dataSourcep;
		jasperReport = DynamicJasperHelper.generateJasperReport(dynamicReport, getLayoutManager(), params);
		if (dataSource != null) {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params);
		}
	}
	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}
	public JasperReport getJasperReport() {
		return jasperReport;
	}
	public Map<String, Object> getParams() {
		return params;
	}
}
