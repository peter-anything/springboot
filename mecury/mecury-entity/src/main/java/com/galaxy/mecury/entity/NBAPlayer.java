package com.galaxy.mecury.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.Length;

/**
 * @Auther: peter
 * @Date: 2020/6/10 23:42
 * @Description:
 */

@TableName("players")
public class NBAPlayer {
    private Integer id;
    private String code;
    private String country;
    @Length(min = 6)
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
    private Boolean teamIsAllStarTeam;
    private Boolean teamIsLeagueTeam;
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
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getDisplayAffiliation() {
        return displayAffiliation;
    }

    public void setDisplayAffiliation(String displayAffiliation) {
        this.displayAffiliation = displayAffiliation;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(String draftYear) {
        this.draftYear = draftYear;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFirstInitial() {
        return firstInitial;
    }

    public void setFirstInitial(String firstInitial) {
        this.firstInitial = firstInitial;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(String jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamCityEn() {
        return teamCityEn;
    }

    public void setTeamCityEn(String teamCityEn) {
        this.teamCityEn = teamCityEn;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamConference() {
        return teamConference;
    }

    public void setTeamConference(String teamConference) {
        this.teamConference = teamConference;
    }

    public String getTeamDisplayAbbr() {
        return teamDisplayAbbr;
    }

    public void setTeamDisplayAbbr(String teamDisplayAbbr) {
        this.teamDisplayAbbr = teamDisplayAbbr;
    }

    public String getTeamDisplayConference() {
        return teamDisplayConference;
    }

    public void setTeamDisplayConference(String teamDisplayConference) {
        this.teamDisplayConference = teamDisplayConference;
    }

    public String getTeamDivision() {
        return teamDivision;
    }

    public void setTeamDivision(String teamDivision) {
        this.teamDivision = teamDivision;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Boolean getTeamIsAllStarTeam() {
        return teamIsAllStarTeam;
    }

    public void setTeamIsAllStarTeam(Boolean teamIsAllStarTeam) {
        this.teamIsAllStarTeam = teamIsAllStarTeam;
    }

    public Boolean getTeamIsLeagueTeam() {
        return teamIsLeagueTeam;
    }

    public void setTeamIsLeagueTeam(Boolean teamIsLeagueTeam) {
        this.teamIsLeagueTeam = teamIsLeagueTeam;
    }

    public String getTeamLeagueId() {
        return teamLeagueId;
    }

    public void setTeamLeagueId(String teamLeagueId) {
        this.teamLeagueId = teamLeagueId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNameEn() {
        return teamNameEn;
    }

    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn;
    }
}
