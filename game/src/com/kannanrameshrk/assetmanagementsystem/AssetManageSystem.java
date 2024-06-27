package com.kannanrameshrk.assetmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.assetmanagementsystem.dto.Device;
import com.kannanrameshrk.assetmanagementsystem.dto.Employee;
import com.kannanrameshrk.assetmanagementsystem.dto.Installation;
import com.kannanrameshrk.assetmanagementsystem.dto.Software;
import com.kannanrameshrk.assetmanagementsystem.dto.Vendor;

public class AssetManageSystem {
	static Scanner input=new Scanner(System.in);
	private List<Software> softwares;
	private List<Device> devices;
	private List<Employee> employees;
	private List<Vendor> vendors;
	
	public AssetManageSystem() {
		this.softwares=new ArrayList<>();
		this.devices=new ArrayList<>();
		this.employees=new ArrayList<>();
		this.vendors=new ArrayList<>();
	}
	
	public void run() {
		while(true) {
			System.out.println("\n Asset Management System Menu:");
			System.out.println("1. Add Vendor");
			System.out.println("2. Add Software");
			System.out.println("3. Add Employee");
			System.out.println("4. Add Device");
			System.out.println("5. Install Software on Device");
			System.out.println("6. Genrate Report");
			System.out.println("7. Exit");
			
			System.out.println("Enter your Option:");
			int option=input.nextInt();
			input.nextLine();
			
			switch(option) {
			case 1:{
				AddVendor();
				break;
			}
			case 2:{
				addSoftware();
				break;
			}
			case 3:{
				addEmployee();
				break;
			}
			case 4:{
				addDevice();
				break;
			}
			case 5:{
				installSoftware();
				break;
			}
			case 6:{
				genrateReport();
				break;
			}
			case 7:{
				System.out.println("Exiting ....");
				return;
			}
			
			default:{
				System.out.println("Invalid option try again..");
				break;
			}
			}
		}
		
	}
	private void genrateReport() {
		System.out.println("\n Report Menu:");
		System.out.println("1. Number of installations of a particular software");
		System.out.println("2. Number of Software installed on a device");
		System.out.println("3. Number of software installed for an employee");
		System.out.println("4. Amount spent on a software");
		System.out.println("5. Amount spent for an employee");
		System.out.println("6. Number of installations from a vendor");
		System.out.println("7. Devices with expired software");
		System.out.println("Enter your Choice:");
		int choice=input.nextInt();
		input.nextLine();
		
		switch(choice) {
		case 1:{
			System.out.println("Enter software Name:");
			String software=input.nextLine();
			System.out.println("Number of installations of"+software+":"+numberOfInstallations(software) );
			break;
		}
		case 2:{
			System.out.println("Enter Your DeviceID:");
			String deviceID=input.nextLine();
			System.out.println("Number of installations of"+deviceID+"software:"+numberOfInstallationsDevice(deviceID) );
			break;
		}
		case 3:{
			System.out.println("Enter Employee ID:");
			String employeeID=input.nextLine();
			System.out.println("Number of installations software of"+employeeID+":"+numberOfInstallationsEmployee(employeeID) );
			break;
		}
		case 4:{
			System.out.println("Enter Software Name:");
			String software=input.nextLine();
			System.out.println("Amount spent of"+ software+":"+findSoftwareAmount(software) );
			break;
		}
		case 5:{
			System.out.println("Enter Employee ID:");
			String employeeID=input.nextLine();
			System.out.println("Amount spent of "+employeeID+"in software :"+findSpentEmployeeAmount(employeeID) );
			break;
		}
		case 6:{
			System.out.println("Enter Vendor ID:");
			String vendorID=input.nextLine();
			System.out.println("Number of installations software of"+vendorID+":"+numberOfInstallationsVendor(vendorID) );
			break;
		}
		case 7:{
			System.out.println("Enter Current Date(yyy-mm-dd): ");
			String currentDate=input.nextLine();
			Date date=parseDate(currentDate);
			
			if(date==null) {
				System.out.println("Invalid Date..");
				return;
			}
			List<Device> expiredDevices =deviceWithExpiredSoftware(date);
			System.out.println("Devices with expired software:");
			
			for(Device device:expiredDevices) {
				System.out.println(device.getDeviceID());
			}
			break;
		}
		default:{
			System.out.println("Invalid choice..");
			break;
		}
		}
		
	}

	private List<Device> deviceWithExpiredSoftware(Date date) {
		List<Device> expiredDevices = new ArrayList<>();
		
		for(Device device:devices) {
			for(Installation inst:device.getInstallations()) {
				if(inst.getSoftware().getExpiryDate().before(date)) {
					expiredDevices.add(device);
					break;
				}
			}
		}
		return expiredDevices;
	}

	private int numberOfInstallationsVendor(String vendorID) {
		int count=0;
		
		for(Device d:devices) {
			for(Installation inst:d.getInstallations()) {
				if(inst.getSoftware().getVendor().getVendorId().equals(vendorID)) {
					count++;
				}
			}
		}
		
		return count;
	}

	private double findSpentEmployeeAmount(String employeeID) {
		double total=0;
		
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeID)) {
				for(Device d:emp.getDevices()) {
					for(Installation inst:d.getInstallations()) {
						total+=inst.getSoftware().getCostPerDevice();
					}
				}
			}
		}
		return total;
	}

	private double findSoftwareAmount(String software) {
		double total=0;
		
		for(Device d:devices) {
			for(Installation inst:d.getInstallations()) {
				if(inst.getSoftware().getName().equals(software)) {
					total+=inst.getSoftware().getCostPerDevice();
				}
			}
		}
		return total;
	}

	private int numberOfInstallationsEmployee(String employeeID) {
		int count=0;
		
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeID)) {
				for(Device d:emp.getDevices()) {
					count+=d.getInstallations().size();
				}
			}
		}
		return count;
	}

	private int numberOfInstallationsDevice(String deviceID) {
		for(Device d:devices) {
			if(d.getInstallations().equals(deviceID)) {
				return d.getInstallations().size();
			}
		}
		return 0;
	}

	private int numberOfInstallations(String software) {
		int count=0;
		for(Device device:devices) {
			for(Installation i:device.getInstallations()) {
				if(i.getSoftware().getName().equals(software)) {
					count++;
				}
			}
		}
		
		return count;
	}

	private void installSoftware() {
		System.out.println("Enter Device ID:");
		String deviceID=input.nextLine();
		
		Device device=findDeviceId(deviceID);
		if(device==null) {
			System.out.println("Device not found.Please add the device first.");
			return;
		}
		System.out.println("Enter Software Name:");
		String softWareName=input.nextLine();
		Software soft=findSoftwareName(softWareName);
		
		if(soft==null) {
			System.out.println("Software not found.Please add the software first.");
			return;
		}
		System.out.println("Enter Installation Date(yyyy-mm-dd):");
		String installDate=input.nextLine();
		Date installationDate=parseDate(installDate);
		
		if(installationDate==null) {
			System.out.println("Invalid date Format..");
			return;
		}
		installSoftwareDevice(device,soft,installationDate);
	}

	private void installSoftwareDevice(Device device, Software soft, Date installationDate) {
		Installation installation=new Installation(soft,installationDate);
		device.addInstallation(installation);
	}

	private Software findSoftwareName(String softWareName) {
		for(Software s:softwares) {
			if(s.getName().equals(softWareName)) {
				return s;
			}
		}
		return null;
	}

	private Device findDeviceId(String deviceID) {
		for(Device d:devices ) {
			if(d.getDeviceID().equals(deviceID)) {
				return d;
			}
		}
		return null;
	}

	private void addDevice() {
		System.out.println("Enter Device ID:");
		String deviceID=input.nextLine();
		System.out.println("Enter Employee ID: ");
		String employeeID = input.nextLine();
		Employee employee=findEmployeeId(employeeID);
		
		if(employee == null) {
			System.out.println("Employee not found.Please add the employee first.");
			return;
		}
		
		Device device = new Device(deviceID,employee);
		employee.addDevice(device);
		addDevice(device);
		System.out.println("Add Device Successfully...");
	}

	private void addDevice(Device device) {
		devices.add(device);
	}

	private Employee findEmployeeId(String employeeID) {
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeID)) {
				return emp;
			}
		}
		return null;
	}

	private void addEmployee() {
		System.out.println("Enter EmployeeID:");
		String employeeId=input.nextLine();
		System.out.println("Enter Employee Name:");
		String name=input.nextLine();
		
		Employee employee=new Employee(employeeId,name);
		addEmployee(employee);
		System.out.println("Employee added Successfully..");
	}

	private void addEmployee(Employee employee) {
		employees.add(employee);
	}

	private void addSoftware() {
		System.out.println("Enter Software Name:");
		String name=input.nextLine();
		System.out.println("Enter Vendor ID:");
		String vendorID=input.nextLine();
		
		Vendor vendor=findVendorID(vendorID);
		
		if(vendor==null) {
			System.out.println("Vendor Not found.Please add the vendor first..");
			return;
		}
		
		System.out.println("Enter Cost Per Device:");
		double costPerDevice = input.nextDouble();
		System.out.println("Enter Expiry Date(yyyy-mm-dd):");
		String date=input.nextLine();
		Date expiryDate=parseDate(date);
		
		if(expiryDate == null) {
			System.out.println("Invalid Date Format.");
			return;
		}
		
		Software software=new Software(name,vendor,costPerDevice,expiryDate);
		addSoftware(software);
		System.out.println("Software Added Successfully..");
	}
	private void addSoftware(Software software) {
		softwares.add(software);
	}

	private Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	private Vendor findVendorID(String vendorID) {
		for(Vendor vendor:vendors) {
			if(vendor.getVendorId().equals(vendorID)) {
				return vendor;
			}
		}
		return null;
	}

	private void AddVendor() {
		System.out.println("Enter Vendor ID:");
		String vendorID = input.nextLine();
		System.out.println("Enter Vendor Name:");
		String name=input.nextLine();
		Vendor vendor=new Vendor(vendorID,name);
		AddVendor(vendor);
		System.out.println("vendor Added Successfully..");
	}

	private void AddVendor(Vendor vendor) {
		vendors.add(vendor);
		
	}

}
