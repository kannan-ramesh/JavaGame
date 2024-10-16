package com.kannanrameshrk.taskmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kannanrameshrk.taskmanager.dto.Task;

public class TaskManager {
	List<Task> list;
	public static final String file="todo.data";
	
	public TaskManager() {
		list=new ArrayList<>();
		loadTasks();
	}

	@SuppressWarnings("unchecked")
	private void loadTasks() {
		FileInputStream fis=null;
		ObjectInputStream dis=null;
		
		try {
			fis=new FileInputStream(file);
			dis=new ObjectInputStream(fis);
			list=(List<Task>) dis.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("No task found,,file not genrate..");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Task> getTask() {
		return list.size()==0?null:list;
	}

	public void addTask(Task task) {
		list.add(task);
		saveTasks();
	}

	private void saveTasks() {
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		try {
			fos=new FileOutputStream(file);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Task> findData(String title) {
		return list.stream().filter(p-> p.getTitle().equalsIgnoreCase(title)).toList();
	}

	public void changeStatus(String title, boolean status) {
		for(Task i:list) {
			if(i.getTitle().equals(title)) {
				i.setComplete(status);
				System.out.println("Succesfully Status Changed..");
				saveTasks();
			}
		}
	}

	public boolean deleteTask(String title) {
		list=list.stream().filter(p->!p.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
		saveTasks();
		return true;
	}
}
