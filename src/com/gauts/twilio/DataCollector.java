package com.gauts.twilio;


import java.util.ArrayList;
import java.util.List;

public class DataCollector {
    public static void main(String[] args) {
        DataCollector app = new DataCollector();
        System.out.println(app.generateAllDatesInclusive("7-December-2000", "7-January-2001"));
    }

    public String monthIntToString(int month){
        switch(month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
        }
        return "";
    }

    public int monthStringToInt(String month){
        switch(month){
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
        }
        return -1;
    }

    public List<String> generateAllDatesInclusive(String fromDate, String toDate){
        String[] splitFromDate = fromDate.split("-");
        String[] splitToDate = toDate.split("-");

        String fromDay = splitFromDate[0];
        String fromMonth = splitFromDate[1];
        String fromYear = splitFromDate[2];

        String toDay = splitToDate[0];
        String toMonth = splitToDate[1];
        String toYear = splitToDate[2];

        int fromDayInt = Integer.valueOf(fromDay);
        int fromMonthInt = monthStringToInt(fromMonth);
        int fromYearInt = Integer.valueOf(fromYear);

        int toDayInt = Integer.valueOf(toDay);
        int toMonthInt = monthStringToInt(toMonth);
        int toYearInt = Integer.valueOf(toYear);

        List<String> rangeDates = new ArrayList<>();

        int fromDayMonth = fromDayInt;
        int fromMonthYear = fromMonthInt;

        int toDayMonth = 31;
        int toMonthYear = 12;

        for(int i=fromYearInt; i<=toYearInt; i++){
            if(i>fromYearInt){
                fromMonthYear=1;
                toMonthYear=12;
            }
            if(i == toYearInt){
                toMonthYear=toMonthInt;
            }
            for (int j = fromMonthYear; j <= toMonthYear; j++) {
                String month = monthIntToString(j);
                if(i>fromYearInt || j>fromMonthYear){
                    fromDayMonth = 1;
                }
                if(i == toYearInt && j == toMonthYear){
                    toDayMonth = toDayInt;
                }
                for (int k = fromDayMonth; k <= toDayMonth; k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(k).append("-").append(month).append("-").append(i);
                    rangeDates.add(sb.toString());
                }
            }
        }
        return rangeDates;
    }

    public String buildUrlString(String date,int page){
        String baseUrl = "";
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?").append("Date=").append(date).append("&").append("page=").append(page);
        return sb.toString();
    }
}
