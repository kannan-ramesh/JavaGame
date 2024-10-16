package com.kannanrameshrk.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransactionalKeyValueStore {
    private Map<String, String> store = new HashMap<>();
    private Stack<Map<String, String>> transactionStack = new Stack<>();
    
    
	public void set(String key, String value) {
		if(!transactionStack.isEmpty()) {
			transactionStack.peek().put(key, value);
		}
		store.put(key, value);
	}


	public String get(String key) {
		return store.getOrDefault(key,null);
	}


	public void unSet(String key) {
		if(!transactionStack.isEmpty()) {
			transactionStack.peek().put(key, store.get(key));
		}
		store.remove(key);
	}


	public void begin() {
		transactionStack.push(new HashMap<>());
	}


	public int count(String value) {
		int count=0;
		
		for(String val:store.values()) {
			if(val.equals(value)) {
				count++;
			}
		}
		return count;
	}


	public void commit() {
		if(transactionStack.isEmpty()) {
			System.out.println("No Transaction..");
			return;
		}
		transactionStack.clear();
	}


	public void rollBack() {
		 if (transactionStack.isEmpty()) {
	            System.out.println("NO TRANSACTION");
	            return;
	        }
	        // Get the most recent transaction map
	        Map<String, String> lastTransaction = transactionStack.pop();
	        // Restore the previous values
	        for (Map.Entry<String, String> entry : lastTransaction.entrySet()) {
	            if (entry.getValue() == null) {
	                store.remove(entry.getKey()); // If previous value was null, remove it
	            } else {
	                store.put(entry.getKey(), entry.getValue()); // Restore the previous value
	            }
	        }
	}
    
}
