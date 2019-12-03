package com.chainanalytica.nic.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import com.chainanalytica.nic.http.client.HttpPostClient;
import com.chainanalytica.nic.reports.UserReport;
import com.chainanalytica.nic.reports.VehicleReport;
import com.chainanalytica.nic.repository.CitizenRepository;
import com.chainanalytica.nic.repository.VehicleRepository;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired 
	CitizenRepository citizenRepository;
	@Autowired
	VehicleRepository vehicleRepository;
	
	public String GenarateAllUsersReport() {
		UserReport report = new UserReport();
		JRDataSource dataSource = new JRBeanCollectionDataSource(citizenRepository.findAll());
		String reportName = UUID.randomUUID().toString()+".pdf";
		//reportName = "test.pdf";
		try {
			report.generateReport(dataSource);
			JasperExportManager.exportReportToPdfFile(report.getJasperPrint(), "src/main/resources/public/"+reportName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("could not generate the report");	
		}
		return reportName;
	}
	
	public String GenerateAllVehiclesReport() throws IOException {
//		declare output stream to capture pdfstream from export function in jasper
		OutputStream outputStream = null;
//		OutputStream pdfOutStream = new OutputStream();
		VehicleReport vehicleReport = new VehicleReport();
		HttpPostClient httpPostClient = new HttpPostClient();
		JRDataSource dataSourceNew = new JRBeanCollectionDataSource(vehicleRepository.findAll());
//		String reportName = UUID.randomUUID().toString()+".pdf";
		//reportName = "test.pdf";
		String reportNamePrefix = UUID.randomUUID().toString();
		String reportName = reportNamePrefix+".pdf";
		
		File pdfReport = File.createTempFile(reportNamePrefix, ".pdf");
		
		try {
			vehicleReport.generateReport(dataSourceNew);
//			JasperExportManager.exportReportToPdfFile(vehicleReport.getJasperPrint(), "src/main/resources/public/"+reportName);
			try {
				JasperExportManager.exportReportToPdfStream(vehicleReport.getJasperPrint(), new FileOutputStream(pdfReport));
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
			System.out.println(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("could not generate the report");	
		}
		try {
			httpPostClient.uploadToAlfECM(pdfReport.toString(), reportName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		pdfReport.delete();
		System.out.println(pdfReport);
		return reportName;
	}
	
}
