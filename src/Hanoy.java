public class Hanoy {
    static final int maxDisk = 2;
    static int move = 0;
    //out stems storage
    static Stem[] stems = new Stem[3];

    public static void main(String[] args) {
        //initialize 3 stems
        for (int i = 0; i < 3; i++) {
            stems[i] = new Stem();
        }

        //add some disks to the first(left) stem
        for (int i = maxDisk ; i > 0; i--) {
            stems[0].addDisk(i);
        }
        printStatus();

        //main cycle
        int currMax = maxDisk;
        int tries = 0;
        while(currMax > 0) {
            tries++; if (tries > 10) break;
            if (currMax == stems[0].getHighestDisk() || currMax == stems[1].getHighestDisk()) {
                if (move(findByHighestDisk(currMax), stems[2])) {
                    printStatus();
                    currMax--;
                } else {
                    //TODO
                }
            } else {
                moveAllDisksExceptCurrentMaxToStem1or2(currMax, currMax % 2 == 0 ? stems[1] : stems[0]);
            }
        }
    }

    private static void moveAllDisksExceptCurrentMaxToStem1or2(int currMax, Stem neededStem) {
        if (findByHighestDisk(currMax-1) != null) {
            if (move(findByHighestDisk(currMax-1), neededStem)) {
                printStatus();
            } else {
                //TODO
            }
        } else {
            //
        }
    }

    public static Stem findByHighestDisk(int disk) {
        for (Stem stt : stems) {
            if (stt.getHighestDisk() == disk) {
                return stt;
            }
        }
        return null;
    }

    public static Stem findByAnyDisk(int disk) {
        for (Stem stt : stems) {
            if (stt.getAllDisks().contains(disk)) return stt;
        }
        return null;
    }

    private static int findBiggestAvail() {
        int temp = Math.max(stems[0].getHighestDisk(), stems[1].getHighestDisk());
        if (temp > stems[3].getHighestDisk()) {
            return temp;
        } else return stems[3].getHighestDisk();
    }

    private static int findSmallestAvail() {
        int res = 0;
        int temp = Math.min(stems[0].getHighestDisk(), stems[1].getHighestDisk());
        if (temp < stems[2].getHighestDisk()) {
            res = temp;
        } else res = stems[2].getHighestDisk();
        if (res == 0) {
            return findBiggestAvail();
        }
        return res;
    }

    static boolean move(Stem start, Stem finish) {
        if ((start.getHighestDisk() < finish.getHighestDisk() || finish.getHighestDisk() == 0) && start.getHighestDisk() != 0) {
            int disk = start.removeDisk();
            return finish.addDisk(disk);
        }
        else return false;
    }

    private static void printStatus() {
        System.out.print("move " + move + ": ");
        System.out.print(stems[0].toString());
        System.out.print(stems[1].toString());
        System.out.println(stems[2].toString());
        move++;
    }


}
