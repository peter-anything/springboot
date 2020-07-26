package com.galaxy.venus.entity;

public class NBAPlayer implements Cloneable {
    public NBAPlayer clone() throws CloneNotSupportedException {
        NBAPlayer proto = (NBAPlayer) super.clone();
        return proto;
    }
    private Integer id;

    private String code;

    private String country;

    private String countryEn;

    private String displayAffiliation;

    private String displayName;

    private String dob;

    private String draftYear;

    private String experience;

    private String firstInitial;

    private String firstName;

    private String firstNameEn;

    private String height;

    private String jerseyNo;

    private String lastName;

    private String lastNameEn;

    private String leagueId;

    private String playerId;

    private String position;

    private String schoolType;

    private String weight;

    private String teamAbbr;

    private String teamCity;

    private String teamCityEn;

    private String teamCode;

    private String teamConference;

    private String teamDisplayAbbr;

    private String teamDisplayConference;

    private String teamDivision;

    private String teamId;

    private Short teamIsAllStarTeam;

    private Short teamIsLeagueTeam;

    private String teamLeagueId;

    private String teamName;

    private String teamNameEn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn == null ? null : countryEn.trim();
    }

    public String getDisplayAffiliation() {
        return displayAffiliation;
    }

    public void setDisplayAffiliation(String displayAffiliation) {
        this.displayAffiliation = displayAffiliation == null ? null : displayAffiliation.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob == null ? null : dob.trim();
    }

    public String getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(String draftYear) {
        this.draftYear = draftYear == null ? null : draftYear.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getFirstInitial() {
        return firstInitial;
    }

    public void setFirstInitial(String firstInitial) {
        this.firstInitial = firstInitial == null ? null : firstInitial.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn == null ? null : firstNameEn.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(String jerseyNo) {
        this.jerseyNo = jerseyNo == null ? null : jerseyNo.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn == null ? null : lastNameEn.trim();
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId == null ? null : leagueId.trim();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr == null ? null : teamAbbr.trim();
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity == null ? null : teamCity.trim();
    }

    public String getTeamCityEn() {
        return teamCityEn;
    }

    public void setTeamCityEn(String teamCityEn) {
        this.teamCityEn = teamCityEn == null ? null : teamCityEn.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getTeamConference() {
        return teamConference;
    }

    public void setTeamConference(String teamConference) {
        this.teamConference = teamConference == null ? null : teamConference.trim();
    }

    public String getTeamDisplayAbbr() {
        return teamDisplayAbbr;
    }

    public void setTeamDisplayAbbr(String teamDisplayAbbr) {
        this.teamDisplayAbbr = teamDisplayAbbr == null ? null : teamDisplayAbbr.trim();
    }

    public String getTeamDisplayConference() {
        return teamDisplayConference;
    }

    public void setTeamDisplayConference(String teamDisplayConference) {
        this.teamDisplayConference = teamDisplayConference == null ? null : teamDisplayConference.trim();
    }

    public String getTeamDivision() {
        return teamDivision;
    }

    public void setTeamDivision(String teamDivision) {
        this.teamDivision = teamDivision == null ? null : teamDivision.trim();
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public Short getTeamIsAllStarTeam() {
        return teamIsAllStarTeam;
    }

    public void setTeamIsAllStarTeam(Short teamIsAllStarTeam) {
        this.teamIsAllStarTeam = teamIsAllStarTeam;
    }

    public Short getTeamIsLeagueTeam() {
        return teamIsLeagueTeam;
    }

    public void setTeamIsLeagueTeam(Short teamIsLeagueTeam) {
        this.teamIsLeagueTeam = teamIsLeagueTeam;
    }

    public String getTeamLeagueId() {
        return teamLeagueId;
    }

    public void setTeamLeagueId(String teamLeagueId) {
        this.teamLeagueId = teamLeagueId == null ? null : teamLeagueId.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamNameEn() {
        return teamNameEn;
    }

    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn == null ? null : teamNameEn.trim();
    }
}