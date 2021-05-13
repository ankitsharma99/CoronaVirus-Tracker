package com.example.coronavirustracker;



public class CountryModel {
    private String flagURL, country, cases, todayCases, todayDeath, recovered, activeCases, criticalCases, testsDone, totalDeaths;

    public CountryModel() {
    }

    public CountryModel(String flagURL, String country, String cases, String todayCases, String todayDeath, String recovered, String activeCases, String criticalCases, String testsDone, String totalDeaths) {
        this.flagURL = flagURL;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.activeCases = activeCases;
        this.criticalCases = criticalCases;
        this.testsDone = testsDone;
        this.totalDeaths = totalDeaths;
    }



    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(String criticalCases) {
        this.criticalCases = criticalCases;
    }

    public String getTestsDone() {
        return testsDone;
    }

    public void setTestsDone(String testsDone) {
        this.testsDone = testsDone;
    }


}
