package com.galaxy.mecury.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Auther: peter
 * @Date: 2020/6/9 01:18
 * @Description:
 */
@TableName("players")
public class NBAPlayer {
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
    private Boolean teamIsAllStarTeam;
    private Boolean teamIsLeagueTeam;
    private String teamLeagueId;
    private String teamName;
    private String teamNameEn;
}
