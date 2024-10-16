package com.kannanrameshrk.taskmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.taskmanager.dto.Task;

public class Main {

	public static void main(String[] args) {
		System.out.println("\t\t Task Manager");
		System.out.println("\t\t*****************");
		Scanner input=new Scanner(System.in);
		
		while(true) {
			System.out.println(" 1.View Task\n 2.Add Task\n 3.Delete Task\n 4.Search Task\n 5.Update Task Status\n 6.Exit\n");
			
			System.out.println("Enter Your Choice:");
			int choice=input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:{
				ViewTask();
				break;
			}
			case 2:{
				addTask(input);
				break;
			}
			case 3:{
				deleteTask(input);
				break;
			}
			case 4:{
				searchTask(input);
				break;
			}
			case 5:{
				updateTask(input);
				break;
			}
			case 6:{
				System.out.println("Exit TODO Application...");
				return;
			}
			default:{
				System.out.println("Invalid Choice...");
				break;
			}
			}
		}

	}

	private static void updateTask(Scanner input) {
		System.out.println("Enter task title:");
		String title=input.nextLine();
		System.out.println("Status (True/False):");
		boolean status=input.nextBoolean();
		TaskManager tm=new TaskManager();
		tm.changeStatus(title,status);
	}

	private static void searchTask(Scanner input) {
		System.out.println("Enter task title:");
		String title=input.nextLine();
		TaskManager tm=new TaskManager();
		List<Task> data=tm.findData(title);
		
		if(data.size()==0) {
			System.out.println("No task ...");
		}else {
			for(Task i:data) {
				System.out.println(i.toString());
			}
		}
	}

	private static void deleteTask(Scanner input) {
		System.out.println("Enter task title:");
		String title=input.nextLine();
		TaskManager tm=new TaskManager();
		if(tm.deleteTask(title)) {
			System.out.println("Successfully Deleted...");
		}else {
			System.out.println("Task Not found...");
		}
	}

	private static void addTask(Scanner input) {
		try {
			System.out.println("ENter Title:");
			String title=input.nextLine();
			System.out.println("Enter Category:");
			String category=input.nextLine();
			System.out.println("Enter Data:");
			String data=input.nextLine();
			System.out.println("Enter task date(yyyy-mm-dd):");
			String dat=input.nextLine();
			Date date=new SimpleDateFormat("yyyy-mm-dd").parse(dat);
			Task task=new Task(title,category,data,date,false);
			
			TaskManager tm=new TaskManager();
			tm.addTask(task);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void ViewTask() {
		TaskManager tm=new TaskManager();
		List<Task> data=tm.getTask();
		if(data==null) {
			System.out.println("No task ...");
		}else {
			for(Task i:data) {
				System.out.println(i.toString());
			}
		}
	}

}
