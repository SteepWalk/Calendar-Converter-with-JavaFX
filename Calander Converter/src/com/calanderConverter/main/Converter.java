package com.calanderConverter.main;

public class Converter {
    private static final int[] plusMonths = {1,3,5,7,8,10,12};
    private final int[] baseDateEC = {23, 4, 0};
    private final int[] baseDateGC = {11, 9, 0};

    public boolean checkValidity(int[] date, boolean ECDate) {
        if (ECDate) {
            if (date[0] > 0 && date[0] <= 30 && date[1] < 13 && date[1] > 0) {
                return true;
            } else if (date[1] == 13) {
                if ((date[2] + 1)%4 == 0 && date[0] == 6) {
                    return true;
                }
                if ( date[0] <= 5) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        if (date[0] > 0 && date[1] <= 12 && date[1] > 0) {

            boolean plusMonth = checkForPlusMonth(date[1]);
            System.out.println("It is printing at least. " + plusMonth);

            if (date[1] == 2 & date[0] >= 29) {
                if (date[0] == 29 && date[2]%4 != 0) {
                    return false;
                }
                return false;
            }
            if (plusMonth) {
                return date[0] <= 31;
            } else {
                return date[0] <= 30;
            }
        }
        return false;
    }
    public boolean isException (int [] date, boolean toEC) {
        int adjustedDate = date[2] + 1;
        if (toEC) {
            if (!((adjustedDate % 4 == 0) || (date[2] % 4 == 0))) {
                return false;
            }
            if (date[1] >= 9 && adjustedDate % 4 == 0  || (date[1] <= 2 && date[2] % 4 == 0)) {
                if ((date[1] == 9 && date[0] < 11)) {
                    return false;
                }
                return true;
            }
        }
        if (!(( adjustedDate % 4 == 0) || (date[2] % 4 == 0))) {

            return false;
        }

        if (date[1] == 13 && adjustedDate % 4 == 0 && date[0] == 6 || (date[1] <= 6 && date[2] % 4 == 0)) {
            return date[1] != 6 || date[0] <= 21;
        }
        return false;
    }

    public int fixer (int[] date) {
        int change = 7;
        for (int i = plusMonths.length - 1; i >= 0 ; i--) {
            if (date[1] > plusMonths[i]) {
                if ((date[1] >= 3)){
                    change -= 2;
                }
                System.out.println("Fixer for " + date[0] + ", " + date[1] + " is " + change);
                return change;
            }
            change--;
        }
        System.out.println("Fixer for " + date[0] + ", " + date[1] + " is " + change);
        return change;
    }

    public int[] findGap (int[] date) {
        System.out.println("At the start of find gap, Date is " + date[0] + ", " + date[1] + ", " + date[2]);
        int[] gap = {0,0,0};
        if (date[0] == 1 && date[1] == 1) {
            return gap;
        } else if (date[0] == 1 ) {
            gap[0] = 30;
            gap[1] = date[1] - 2;
            return gap;
        }
        gap[0] = date[0] - 1;
        gap[1] = date[1] - 1;
        return gap;
    }

    public int[] addGap(int[] gap, int[] date, boolean toEC) {
        if (toEC) {
            int fx = fixer(date);
            gap[0] += fx;
            gap = getDate(gap, true);
            date[0] = baseDateEC[0] + gap[0];
            date[1] = baseDateEC[1] + gap[1];
            System.out.println("Before getDate Date is " + date[0] + ", " + date[1] + ", " + date[2]);
            return getDate(date, true);
        }
        int[] tempGCdate = baseDateGC;
        tempGCdate[0] = baseDateGC[0] + gap[0];
        tempGCdate[1] = baseDateGC[1] + gap[1];
        tempGCdate[2] = date[2];

        return fixTempGCDate(tempGCdate);

    }

    private int[] fixTempGCDate(int[] date) {
        while(date[0] > 30) {
            date[0] -= 30;
            date[1] += 1;
        }
        while (date[1] > 12) {
            date[1] -= 12;
            date[2] += 1;
        }
        return date;
    }

    public boolean checkForPlusMonth (int month) {
        boolean plusMonth = false;
        boolean postPlusMonth = false;
        for (int x: plusMonths) {
            if (month == x) {
                plusMonth = true;
            }
        }
        for (int x: plusMonths) {
            if (month-1 == x || month == 1) {
                postPlusMonth = true;
            }
        }
        return plusMonth;
    }

    // method to fix overflow issues after addition of dates
    private int[] getDate(int[] date, boolean ECDate) {
        if (ECDate) {
            while(date[0] > 30) {
                date[0] -= 30;
                date[1] += 1;
            }
            while(date[0] <= 0) {
                if (date[1] == 1) {
                    date[0]+= 5;
                    date[1] = 12;
                    date[0]--;
                } else {
                    date[0] += 30;
                    date[1]--;
                }
            }
            while (date[1] >= 13 && date[0] > 5) {
                date[0] -= 5;
                date[1] -= 12;
                date[2] += 1;
            }
            return date;
        }
        boolean plusMonth = false;
        boolean postPlusMonth = false;
        for (int x: plusMonths) {
            if (date[1] == x) {
                plusMonth = true;
            }
        }
        for (int x: plusMonths) {
            if (date[1]-1 == x || date[1] == 1) {
                postPlusMonth = true;
            }
        }
        if (postPlusMonth) {
            if (date[0] <= 0 && date[1] == 1) {
                date[0] += 31;
                date[1] = 12;
                date[2]--;
            } else if (date[0] <= 0) {
                date[0] += 31;
                date[1]--;
            }
            if (date[0] > 30) {
                date[0] -= 30;
                date[1] += 1;
            }
            if (date[1] > 12) {
                date[1] -= 12;
                date[2] += 1;
            }
            return date;
        } else {
            if (date[0] <= 0 && date[1] == 3) {
                date[0] += 28;
                date[1]--;
            }
            if (date[0] <= 0) {
                date[0] += 30;
                date[1]--;
            }
        }
        if (plusMonth) {

            if (date[0] > 31) {
                date[0] -= 31;
                date[1] += 1;
            }
            if (date[1] > 12) {
                date[1] -= 12;
                date[3] += 1;
            }
            return date;
        } else {
            if (date[0] > 30) {
                date[0] -= 30;
                date[1] += 1;
            }
            if (date[1] > 12) {
                date[1] -= 12;
                date[3] += 1;
            }
        }
        if(date[1] == 2) {
            if (date[0] > 28) {
                date[0] -= 28;
                date[1] += 1;
            }
            return date;
        }

        return date;
    }
    public int[] unnamed (int[] date, int[] dateO) {
        if (date[2] >  dateO[2]) {
            int fx = fixer(date) + 2;
            date[0] -= fx;
        } else if (date[1] > 10) {
            date[0]--;
        }
        return getDate(date, false);
    }
    public int[] findFixedDate (int[] date, boolean exceptionDate, boolean toEC) {
        if (toEC) {
            date[2] -= 8;
        }else {
            date[2] += 7;
        }
        if (exceptionDate && toEC) {
            if (date[0] == 1 && date[1] == 1) {
                date[0] = 6;
                date[1] = 13;
                date[2]--;
                return date;
            } else {
                date[0]--;
            }
        } else if (exceptionDate){
            System.out.println("This should print");
            date[0]++;
        }
        System.out.println("The date is after fixed date " + date[0] + ", " + date[1] + ", " + date[2] + ", " + toEC);
        return getDate(date, toEC);
    }
    public int[] convert(int[] date, boolean toEC) {
        if (toEC) {
            boolean exceptionDate = isException(date, true);
            System.out.println("Please print this so that I can know sthat is at least working properly " + exceptionDate);
            int[] gap = findGap(date);
            int[] unfixedDate = addGap(gap, date, true);

            return findFixedDate(unfixedDate, exceptionDate, true);
        }
        boolean exceptionDate = isException(date, false);
        System.out.println("Please print this so that I can know sthat is at least working properly " + exceptionDate);
        int[] gap = findGap(date);
        System.out.println("Gap: " + gap[0] + ", " + gap[1] + ", " + gap[2]);
        int[] afterGap = addGap(gap, date, false);
        System.out.println("After Gap: " + afterGap[0] + ", " + afterGap[1] + ", " + afterGap[2]);
        int[] next = unnamed(afterGap, date);
        System.out.println("After fix: " + next[0] + ", " + next[1] + ", " + next[2]);
        return findFixedDate(next, exceptionDate, false);
    }

    class date {
        private int day = 1;
        private int month = 1;
        private int year = 2000;
    }
}
