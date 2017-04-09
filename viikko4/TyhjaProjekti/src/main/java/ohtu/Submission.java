package ohtu;

import java.util.ArrayList;

public class Submission {

    public String student_number;
    public int week;
    public int hours;
    public int id;
    public String comments;

    public boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,
            a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;

    public Integer[] tehdytTehtävät() {
        boolean[] exercises = {
            a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12,
            a13, a14, a15, a16, a17, a18, a19, a20, a21
        };

        ArrayList<Integer> done = new ArrayList<>();
        for (int i = 0; i < exercises.length; i++) {
            if (exercises[i]) {
                done.add(i + 1);
            }
        }

        return (Integer[]) done.toArray(new Integer[done.size()]);
    }

}
