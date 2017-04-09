package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        Course course = getCourse(1);
        Submission[] submissions = getSubmissions(studentNr);

        printOutput(course, submissions);

    }

    private static Course getCourse(int courseID) throws IOException {
        String url = "https://ohtustats2017.herokuapp.com/courses/" + courseID + ".json";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Course.class);
    }

    private static Submission[] getSubmissions(String studentNro) throws IOException {
        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNro + "/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Submission[].class);
    }

    private static void printOutput(Course course, Submission[] submissions) {
        System.out.println("Kurssi: " + course.name + ", " + course.term + "\n");
        System.out.println("opiskelijanumero: " + submissions[0].student_number + "\n");

        int totalDone = 0;
        int totalHours = 0;
        for (Submission s : submissions) {
            System.out.print(String.format(
                    "viikko %d: tehtyja tehtavia yhteensa: %d (maksimi %d) "
                    + "aikaa kului %d tuntia, tehdyt tehtavat: ",
                    s.week, s.tehdytTehtävät().length, course.exercises(s.week),
                    s.hours));

            for (Integer done : s.tehdytTehtävät()) {
                System.out.print(done + " ");
            }

            totalDone += s.tehdytTehtävät().length;
            totalHours += s.hours;
            System.out.println("");
        }

        System.out.println("\n");
        System.out.println("yhteensa: " + totalDone + " tehtavaa " + totalHours + " tuntia");
    }

    public static class Course {

        public String term;
        public String name;
        public int week1, week2, week3, week4, week5, week6;

        public int exercises(int week) {
            if (week < 0 || week > 6) {
                return 0;
            }
            int[] weeks = {week1, week2, week3, week4, week5, week6};
            return weeks[week - 1];
        }

    }
}
