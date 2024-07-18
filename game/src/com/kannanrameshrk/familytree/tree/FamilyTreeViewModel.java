package com.kannanrameshrk.familytree.tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kannanrameshrk.familytree.dto.Person;

class FamilyTreeViewModel {
	private static final String FILE="data.dat";
	private FamilyTreeView familyTreeView;
	
	Map<String,Person> data=new HashMap<>();
	
	public FamilyTreeViewModel(FamilyTreeView familyTreeView) {
		this.familyTreeView=familyTreeView;
	}

	public boolean regPerson(Person person) {
		if(data.containsKey(person.getName())) {
			return false;
		}else {
			data.put(person.getName(), person);
			saveDoc(person);
		}
		return true;
	}

	private void saveDoc(Person person) {
		ObjectOutputStream oos=null;
		FileOutputStream fos=null;
		
		try {
			fos= new FileOutputStream(FILE);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Person> getData() {
		loadDoc();
		return new ArrayList<>(data.values());
	}

	@SuppressWarnings("unchecked")
	private void loadDoc() {
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try {
			fis=new FileInputStream(FILE);
			ois=new ObjectInputStream(fis);
			data= (Map<String, Person>) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("data not found..");
		}
	}

	public List<Person> findPair(String personName) {
		List<Person> eligibleMatches=new ArrayList<>();
		
		Person person=data.get(personName);
		
		if(person==null) {
			return eligibleMatches;
		}
		
		if(person.getGender().equals("Male")) {
			//Fathers sister s daughters
			
			if(person.getFatherName()!=null) {
				Person father=data.get(person.getFatherName());
				
				if(father !=null) {
					for(Person sibling:getSisters(father)) {
						for(Person cousin:getChilldren(sibling)) {
							if(cousin.getGender().equals("Female")) {
								eligibleMatches.add(cousin);
							}
						}
					}
				}
			}
			
			//mother s brother daughters
			 if (person.getMotherName() != null) {
	                Person mother = data.get(person.getMotherName());
	                if (mother != null) {
	                    for (Person sibling : getBrothers(mother)) {
	                        for (Person cousin : getChilldren(sibling)) {
	                            if (cousin.getGender().equals("Female")) {
	                                eligibleMatches.add(cousin);
	                            }
	                        }
	                    }
	                }
	            }
			
		}else if(person.getGender().equals("Female")) {
			//Fathers sister s daughters
			
			if(person.getFatherName()!=null) {
				Person father=data.get(person.getFatherName());
				
				if(father !=null) {
					for(Person sibling:getSisters(father)) {
						for(Person cousin:getChilldren(sibling)) {
							if(cousin.getGender().equals("male")) {
								eligibleMatches.add(cousin);
							}
						}
					}
				}
			}
			
			//mother s brother daughters
			 if (person.getMotherName() != null) {
	                Person mother = data.get(person.getMotherName());
	                if (mother != null) {
	                    for (Person sibling : getBrothers(mother)) {
	                        for (Person cousin : getChilldren(sibling)) {
	                            if (cousin.getGender().equals("male")) {
	                                eligibleMatches.add(cousin);
	                            }
	                        }
	                    }
	                }
	            }
		}
		return eligibleMatches;
	}

	private List<Person> getBrothers(Person person) {
		List<Person> brother=new ArrayList<>();
		
		for(Person p:data.values()) {
			if(p.getMotherName()!=null && p.getMotherName().equals(person.getMotherName()) && p.getGender().equals("Male") && !p.getName().equals(person.getName())) {
				brother.add(p);
			}
		}
		return brother;
	}

	private List<Person> getChilldren(Person person) {
		 List<Person> children = new ArrayList<>();
	        for (Person p : data.values()) {
	            if (p.getFatherName() != null && p.getFatherName().equals(person.getName()) || p.getMotherName() != null && p.getMotherName().equals(person.getName())) {
	                children.add(p);
	            }
	        }
	        return children;
	}

	private List<Person> getSisters(Person person) {
		List<Person> sisters=new ArrayList<>();
		
		for(Person p:data.values()) {
			if(p.getFatherName()!=null && p.getFatherName().equals(person.getFatherName()) && p.getGender().equals("Female") && !p.getName().equals(person.getName())) {
				sisters.add(p);
			}
		}
		return sisters;
	}
}