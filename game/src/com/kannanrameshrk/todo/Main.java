package com.kannanrameshrk.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.todo.dto.Task;

public class Main {
	private static Scanner input = new Scanner(System.in);
	private static TaskManager taskManager=new TaskManager();
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("\n Task Manager");
			System.out.println("1. View Tasks");
			System.out.println("2. Add Task");
			System.out.println("3. Delete Task");
			System.out.println("4. Search Task");
			System.out.println("5. Update Task Status");
			System.out.println("6. Exit");
			
			System.out.println("Enter Your Option:");
			int choice=input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:{
				viewTasks();
				break;
			}
			case 2:{
				addTask();
				break;
			}
			case 3:{
				deleteTask();
				break;
			}
			case 4:{
				searchTask();
				break;
			}
			case 5:{
				updateTaskStatus();
				break;
			}
			case 6:{
				System.out.println("Exixting..");
				return;
				
			}
			default:{
				System.out.println("Invalid choice,try again..");
				break;
			}
			}
		}
	}

	private static void updateTaskStatus() {
		System.out.println("Enter title of the Task is Your Complete:");
		String title=input.nextLine();
		System.out.print("Is the task complete? (true/false): ");
	    boolean isComplete = input.nextBoolean();
	    taskManager.updateTaskStatus(title,isComplete);
	}

	private static void searchTask() {
		System.out.println("Enter search Title:");
		String title=input.nextLine();
		List<Task> tasks=taskManager.searchTasks(title);
		
		if(tasks.isEmpty()) {
			System.out.println("Matched Task Not Found..");
		}else {
			for(Task t:tasks) {
				System.out.println(t);
			}
		}
		
	}

	private static void deleteTask() {
		System.out.println("Enter the Title of the task to delete:");
		String title=input.nextLine();
		taskManager.deleteTask(title);
	}

	private static void addTask() {
		try {
			System.out.println("Enter Title: ");
			String title=input.nextLine();
			System.out.println("Enter Category: ");
			String category=input.nextLine();
			System.out.println("Enter data: ");
			String data=input.nextLine();
			System.out.println("Enter date(yyyy-mm-dd): ");
			String dateStr=input.nextLine();
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			taskManager.addTask(new Task(title,category,data,date,false));
		}catch(ParseException e) {
			System.out.println("Please check your date format...");
		}
	}

	private static void viewTasks() {
		List<Task> tasks=taskManager.viewTasks();
		
		if(tasks.isEmpty()) {
			System.out.println("No Task found...");
		}else {
			for(Task t:tasks) {
				System.out.println(t);
			}
		}
	}

}
