package com.kannanrameshrk.todo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kannanrameshrk.todo.dto.Task;

public class TaskManager {
	private List<Task> tasks;
	private static final String FILE_NAME="tasks.dat";
	
	public TaskManager() {
		tasks=new ArrayList<>();
		loadTasks();
	}

	private void loadTasks() {
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try {
			fis=new FileInputStream(FILE_NAME);
			ois=new ObjectInputStream(fis);
			tasks=(List<Task>) ois.readObject();
		}catch(FileNotFoundException e) {
			System.out.println("No saved tasks found. Starting with an empty list.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Task> viewTasks() {
		return tasks;
	}

	public void addTask(Task task) {
		tasks.add(task);
		saveTasks();
	}

	private void saveTasks() {
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		try {
			fos=new FileOutputStream(FILE_NAME);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(tasks);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null) {
					oos.close();
				}
				if(fos !=null) {
					fos.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteTask(String title) {
		tasks=tasks.stream().filter(task-> !task.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
		saveTasks();
	}

	public List<Task> searchTasks(String title) {
		return tasks.stream().filter(task -> task.getTitle().contains(title) || task.getCategory().contains(title)).collect(Collectors.toList());
	}

	public void updateTaskStatus(String title, boolean isComplete) {
		for(Task t:tasks) {
			if(t.getTitle().equalsIgnoreCase(title)) {
				t.setComplete(isComplete);
			}
		}
		saveTasks();
	}
}
