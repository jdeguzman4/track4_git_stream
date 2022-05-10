package org.ssglobal.training.codes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Score {

	public static class ScoreInfo {
		String firstName;
		String lastName;
		Integer score;

		ScoreInfo(String lName, String fName, int s) {
			firstName = fName;
			lastName = lName;
			score = s;
		}
	}

	private static final double count = 15;
	
	public static ScoreInfo[] scoreData = new ScoreInfo[] { 
			new ScoreInfo("Smith", "John", 70),
			new ScoreInfo("Doe", "Mary", 85), 
			new ScoreInfo("Page", "Alice", 82), 
			new ScoreInfo("Cooper", "Jill", 97),
			new ScoreInfo("Flintstone", "Fred", 66), 
			new ScoreInfo("Rubble", "Barney", 80),
			new ScoreInfo("Smith", "Judy", 48), 
			new ScoreInfo("Dean", "James", 90), 
			new ScoreInfo("Russ", "Joe", 55),
			new ScoreInfo("Wolfe", "Bill", 73), 
			new ScoreInfo("Dart", "Mary", 54), 
			new ScoreInfo("Rogers", "Chris", 78),
			new ScoreInfo("Toole", "Pat", 51), 
			new ScoreInfo("Khan", "Omar", 93), 
			new ScoreInfo("Smith", "Ann", 95) 
	};
	
	public int getNumScores() {
		int count = (int) Arrays.stream(scoreData).count();
		System.out.format("Number of Students: %d \n", count);
		return count;
	}

	public double getAverage() {
		int total = Arrays.stream(scoreData).mapToInt(s -> s.score).sum();
		System.out.format("Average Grade: %.2f \n", (double) total / count);
		return (double) total / count;
	}

	public int getNumberAListers() {
		int countA = (int) Arrays.stream(scoreData).filter(s -> s.score >= 90).count();
		System.out.format("Number of Students who got an A: %d \n", countA);
		System.out.println();
		return countA;
	}
	
	Function<ScoreInfo, String> mapStudentName = (s) -> {
		return String.join(" ", s.firstName, s.lastName);
	};

	public List<String> getFailingStudentList() {
		List<String> failing = 
				Arrays.stream(scoreData).filter(s -> (s.score < 70))
				      .map(mapStudentName).collect(Collectors.toList());
		System.out.println("Failing Students: ");
		failing.forEach(System.out::println);
		System.out.println();
		return failing;
	}
	
	public List<String> printPassingStudents() {
		List<String> passing = Arrays.stream(scoreData).filter(s -> (s.score >= 70))
				.map(mapStudentName).collect(Collectors.toList());
		System.out.println("Passing Students: ");
		passing.forEach(System.out::println);
		System.out.println();
		return passing;
	}
	
	Comparator<ScoreInfo> sortedStudentLastName = (s1, s2) -> {
		if (s1.lastName.compareTo(s2.lastName) == 0) {
			return 0;
		} else if (s1.lastName.compareTo(s2.lastName) > 0) {
			return 2;
		} else {
			return -2;
		}
	};

	public void displayAllStudents() {
		System.out.println("All Students by Last Name :");
		Arrays.stream(scoreData).sorted(sortedStudentLastName)
			  .forEach(s -> System.out.format("%s, %s: %d \n", s.lastName, s.firstName, s.score));
		System.out.println();
	}
	
	Comparator<ScoreInfo> sortedStudentScore = (s1, s2) -> {
		if (s1.score.compareTo(s2.score) == 0) {
			return 0;
		} else if (s1.score.compareTo(s2.score) > 0) {
			return -2;
		} else {
			return 2;
		}
	};

	public void getStudentRecords() {
		System.out.println("All Students by Score:");
		Arrays.stream(scoreData).sorted(sortedStudentScore)
			  .forEach(s -> System.out.format("%s, %s: %d \n", s.lastName, s.firstName, s.score));
	}

}
