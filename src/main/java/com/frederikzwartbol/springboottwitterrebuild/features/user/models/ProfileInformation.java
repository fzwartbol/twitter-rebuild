package com.frederikzwartbol.springboottwitterrebuild.features.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "`profile`")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileInformation {
    @Id
    @Column(name = "user_id")
    @JsonIgnore
    private Long id;
    private String profileHeader;
    private String profileBio;
    private String profileBackground;
    private String location;
    private String linkUrl;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_Id")
    @JsonIgnore
    @ToString.Exclude
    private User user;

    public ProfileInformation(String profileHeader, String profileBio, String profileBackground, String location, String linkUrl) {
        this.profileHeader = profileHeader;
        this.profileBio = profileBio;
        this.profileBackground = profileBackground;
        this.location = location;
        this.linkUrl = linkUrl;
    }
}
