    public String getShiftTimeDiff(SSDate startDate, SSDate endDate) {
        if (minutesStep == 15) {
            boolean reversed = false;
            if (endDate.getTime() < startDate.getTime() - 1000) {
                reversed = true;
            }
            int shiftTotalHours = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60)));
            int shiftTotalMin = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60)) % 60);
            if (shiftTotalMin == 59) {
                shiftTotalMin = 0;
                shiftTotalHours++;
            } else if (shiftTotalMin % 15 == 14) {
                shiftTotalMin++;
            }
            int totalMinAndHours = (shiftTotalHours * 60) + shiftTotalMin;
    
            if (totalMinAndHours >= 1441 || totalMinAndHours <= 1439) {
                if (endDate.getTime() - startDate.getTime() > 86399000) {
                    shiftTotalHours = 24;
                    totalMinAndHours = 1440;
                    shiftTotalMin = 0;
                }
            }
    
            //check if date difference are a varient of 0.25
            if (((15 - (shiftTotalMin % 15) < 1)) || (totalMinAndHours > 1440) || reversed) {
                return "( -err )";
            }
    
            if (shiftTotalMin == 0) {
                return "( " + shiftTotalHours + "h )";
            }
            String displayedMinutes = String.valueOf((int)((Math.ceil(shiftTotalMin) / 60) * 100)).replaceAll("0", "");
            return "( " + shiftTotalHours + "." + displayedMinutes + "h )";
        } else {
            /**
             * Due to the nature of the events being thrown in this component, the
             * getShiftTimeDiff ignores the values passed in to this method(unless the minutes 
             * step is 15) and instead retrieves it from the components itself. This way, it is 
             * guaranteed to always have the latest values, and avoid being thrown off by a dalayed
             * event containing old date values.
             */
            // Get start date object
            startDate = startDatePicker.getValue();
            // Set the time to the start time picker
            startDate.setDirectTime(
                startTimePicker.getDateTime().getHours(),
                startTimePicker.getDateTime().getMinutes(),
                startTimePicker.getDateTime().getSeconds()
            );
            // Get end date object
            endDate = endDatePicker.getValue();
            // Set the time to the end time picker
            endDate.setDirectTime(
                endTimePicker.getDateTime().getHours(),
                endTimePicker.getDateTime().getMinutes(),
                endTimePicker.getDateTime().getSeconds()
            );
            //getTime is in milliseconds, so divide by 100 for seconds and then 60 for minutes
            float shiftTotalMinutes = (endDate.getTime() - startDate.getTime()) / 1000 / 60;
            
            //divide the total minutes by 60 and cast to int to get amount of hours e.g 8.3h will be 8h
            int hours = (int) (shiftTotalMinutes / 60);
            //subtract the amount of minutes taken up by whole hours above to get remaining minutes.
            int min = (int) (shiftTotalMinutes - (hours * 60));
            
            String totalTimeString = TOTAL_TIME_FORMAT_START + hours + TOTAL_TIME_FORMAT_HOURS;
            if (min < 10) {
                totalTimeString += " " + 0;
            } else {
                totalTimeString += " ";
            }
            totalTimeString += min + TOTAL_TIME_FORMAT_MINUTES + TOTAL_TIME_FORMAT_END;
            
            return totalTimeString;
        }
    }
