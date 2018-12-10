package com.research.ageac.gamified;

import java.util.Date;

public class AppUsage {
    private Date start;

    public Date getDateTimeActivityStart() {
        return start;
    }

    public void setDateTimeActivityStart(Date dateTimeStart) {
        this.start = dateTimeStart;
    }

    public long getTimeDifference(Date startDate, Date end)
    {
        return (end.getTime()-startDate.getTime())/1000;
    }
}
