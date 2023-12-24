package com.omnia.salusbackend.service.analyser;

import com.omnia.salusbackend.service.analyser.CSV.CSVDTO;

public class AnalyserExample{


    public Integer calculateRatingHealth(CSVDTO data) {
         Integer recycling = data.getRecycling();
         Integer sportsevents = data.getSportsevents();
         Integer hourssportsevents = data.getHourssportsevents();
         Integer workinghours = data.getWorkinghours();
         Integer numberofhoursworked = data.getNumberofhoursworked();
         Integer medicalhours = data.getMedicalhours();

        if (workinghours - medicalhours == 0) {
            // Обработка случая деления на ноль
            // Измените ее в соответствии с требованиями вашей логики
            // return null; // или выбросьте исключение или присвойте другое значение
            throw new IllegalArgumentException("Divisor cannot be zero");
        }

        return (numberofhoursworked + hourssportsevents - recycling - medicalhours) / (workinghours - medicalhours);



        //return (numberofhoursworked + hourssportsevents - recycling - medicalhours)/(workinghours -medicalhours);
    }
    public Integer calculateRatingMental(CSVDTO data) {
        Integer fairleadership = data.getFairleadership();
        Integer satisfiedposition = data.getSatisfiedposition();
        Integer satisfiedteam = data.getSatisfiedteam();
        Integer satisfiedsalary = data.getSatisfiedsalary();
        Integer salarylevel = data.getSalarylevel();
        Integer satisfiedcompany = data.getSatisfiedcompany();


        return (fairleadership + satisfiedposition + satisfiedteam + satisfiedsalary + salarylevel + satisfiedcompany)/12*100;
    }

}
