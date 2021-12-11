package com.fields;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FieldDemo {

	public static void main(String[] args) {
		
		// Creating a list of Prime Numbers
        List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11,13);
          
        // Creating a list of Odd Numbers
        List<Integer> OddNumbers = Arrays.asList(1, 3, 5);
          
        // Creating a list of Even Numbers
        List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);
  
        List<List<Integer>> listOfListofInts =
                Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);
  
        System.out.println("The Structure before flattening is : " +
                                                  listOfListofInts);
          
        // Using flatMap for transformating and flattening.
        List<Integer> listofInts  = listOfListofInts.stream()
                                    .flatMap(list -> list.stream())
                                    .collect(Collectors.toList());
  
        System.out.println("The Structure after flattening is : " +
                                                         listofInts);
		
		
//		
//		String[] objArray = {""};
//		
//		List<Field> fieldList = new ArrayList<>();
//		
//		
//		for(Field field:fieldList) {
//			System.out.println(field.getName());
//		}
//		
//		System.out.println(fieldList.contains("name"));
//		
//		String[] select = new String[5];
//		
//		
//		for(int i=0; i<select.length; i++) {
//			select[i] = "" + i;			
//		}
//		
//		System.out.println(select[4]);

		Student st1 = new Student(1L, "raj", "Science", StudentType.JUNIOR);
		Student st2 = new Student(1L, "qwe", "Science", StudentType.JUNIOR);
		Student st3 = new Student(1L, "wer", "Science", StudentType.JUNIOR);
		Student st4 = new Student(1L, "rert", "Science", StudentType.SENIOR);
		Student st5 = new Student(1L, "rrty", "Science", StudentType.JUNIOR);
		Student st6 = new Student(1L, "rtyui", "Science", StudentType.JUNIOR);

		System.out.println("super class name: " + st1.getClass().getSuperclass());
		System.out.println("generic super name: " + st1.getClass().getGenericSuperclass());
		System.out.println("cannonical name: " + st1.getClass().getCanonicalName());
		System.out.println("class name: " + st1.getClass().getSimpleName());
		System.out.println("class name: " + st1.getClass().getName());
		System.out.println("type name: " + st1.getClass().getTypeName());

//		List<?super Object> list = new LinkedList<>();

		List<Student> list = new LinkedList<>();

		list.add(st1);
		list.add(st2);
		list.add(st3);
		list.add(st4);
		list.add(st5);
		list.add(st6);

//		Map<Long, Long> countMap = groupRoleUserCounts.stream()
//				.collect(Collectors.toMap(GroupRoleUserCount::getRoleId, GroupRoleUserCount::getAssignedToUsers));
		
//		Map<Long, String> studentIdNameMap = list.stream().collect(Collectors.toMap(Student::getStudentId, Student::getName));

		Optional<Student> seniorStudent = list.stream().filter(stud -> stud.getType().equals(StudentType.SENIOR))
				.findFirst();
		if (seniorStudent.isPresent()) {
			System.out.println("Student name got in optional: "+seniorStudent.get().getName());
		}
		
		// OR
		
		list.stream().filter(stud -> stud.getType().equals(StudentType.SENIOR))
				.findFirst().ifPresent(s->System.out.println("Student Name got in streams : " + s.getName()));
		
		// OR use flatMap if value is present as it return sequential stream or null
		

	}

}

class serviceIml{
	public void printList() {
		
		Student st1 = new Student(1L, "raj", "Science", StudentType.JUNIOR);
		Student st2 = new Student(1L, "qwe", "Science", StudentType.JUNIOR);
		Student st3 = new Student(1L, "wer", "Science", StudentType.JUNIOR);
		Student st4 = new Student(1L, "rert", "Science", StudentType.SENIOR);
		Student st5 = new Student(1L, "rrty", "Science", StudentType.JUNIOR);
		Student st6 = new Student(1L, "rtyui", "Science", StudentType.JUNIOR);
		
		List<Student> list = new LinkedList<>();

		list.add(st1);
		list.add(st2);
		list.add(st3);
		list.add(st4);
		list.add(st5);
		list.add(st6);
		

	}
}


class Student implements Comparable<Student> {
	public Long studentId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public StudentType getType() {
		return type;
	}

	public void setType(StudentType type) {
		this.type = type;
	}

	public String name;
	public String course;
	public StudentType type;

	public Student(Long studentId, String name, String course, StudentType type) {
		this.studentId = studentId;
		this.name = name;
		this.course = course;
		this.type = type;
	}

	@Override
	public int compareTo(Student stud) {
		int value = name.compareToIgnoreCase(stud.name);

		return (value > 0) ? 1 : (value < 0) ? -1 : 0;
	}

}

class courseComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.course.compareTo(o1.course);
	}

}

enum StudentType {
	SENIOR, JUNIOR
}
