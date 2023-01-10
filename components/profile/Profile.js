import React, {useEffect} from 'react'
import { useState } from 'react';
import {getTwitterHandle,getTweetsUserPaginated, getTweetsUserWithMedia, getTweetsAndRepliesUserPaginated, getTweetsLikedByUserPaginated, postFollowUser, postUnfollowUser} from "../../api/axios";
import Image from 'next/image'
import Tweet from "../tweet/Tweet"
import ProfileInformation from './ProfileInformation';
import ButtonFeed from './ButtonFeed';
import {SparklesIcon} from "@heroicons/react/24/outline";


const Profile = ({ userProfile }) => {

const buttons = [
        {name: 'Tweets', active: true, link:"", 
        api: getTweetsUserPaginated},
        {name: 'Tweets & Replies', active: false, link:"/with_replies", 
        api: getTweetsAndRepliesUserPaginated},
        {name: 'Media', active: false, link:"",
        api:  getTweetsUserWithMedia},
        {name: 'Likes', active: false, link:"",
        api: getTweetsLikedByUserPaginated
        },
    ]

const { id: userId,
        profileImage, 
        firstName, 
        lastName, 
        twitterHandle,
        following: initialFollowing,
        followers,
        profileInformation,  tweets:initialTweets } = userProfile

    const [tweets, setTweets] = useState(initialTweets)
    const [following, setFollowing] = useState(initialFollowing)
    const [selected, setSelected] = useState(buttons[0])
    const [buttonList, setButtonList] = useState(buttons);

    useEffect(() => {
        buttons.map(( button => button.active=false)) 
        buttons.filter(button => button.name == selected.name).map(button => button.active=true)
        setButtonList(buttons);
        selected.api(userId).then(data => {
            setTweets(data.content) 
        }
            )
        },[selected])

    const unfollowUser = () => {
        postUnfollowUser(userProfile.userId,getLoggedInUserId())
            .then((result) => setFollowing(false))
            .catch((err) => { console.log(err) });
    }

    const followUser = () => {
        postFollowUser(userProfile.userId,getLoggedInUserId())
            .then((result) => setFollowing(true))
            .catch((err) => { console.log(err) });
    }

    const getLoggedInUserId = () => {
        return 1;
    }

    const tweetList = tweets.map((tweet, i) => {
                    if (tweets.length === i+1) return <Tweet key={tweet.id} tweet={tweet}/>
                        return <Tweet  key={tweet.id} tweet={tweet} />
                    })

    const resetButtons = () => {
        buttons.forEach(( button => button.selected=false))   
    }

    const activeButton = (selected) => {
        buttons.filter(button => button.name == selected).map(button => button.selected=true)
        setButtonList(buttons);
    }

    const feedButtons =  buttonList.map(button => {
        return <ButtonFeed button= {button} setSelected={setSelected}/>
    })
    
    return (
        <div className="w-full">
            {userProfile &&
                <div>
                    <div className="text-[#d9d9d9] flex items-center
            sm:justify-between py-2 px-3 sticky top-0 z-50 bg-black border-b border-gray-700">
                        <h2 className="text-lg sm:text-xl font-bold">Profile</h2>
                        <div className="hoverAnimation w-9 h-9 flex items-center justify-center xl:px-0 ml-auto">
                            <SparklesIcon className="h-5 text-white" />
                        </div>
                    </div>
                    <div className="border-b border-gray-700 ">
                        <div className="h-80 flex flex-col relative"  style={{ backgroundImage: `url(${profileInformation.profileBackground ? profileInformation.profileBackground : "https://upload.wikimedia.org/wikipedia/commons/4/49/A_black_image.jpg"})`, backgroundSize: 'cover', backgroundPosition: 'center'}}>
                            <div className="flex   w-full justify-between bottom-0 absolute">
                                <div className="flex ml-5 mb-5">
                                    <Image
                                        src={profileImage}
                                        width="125" height="125"
                                        alt="Picture of the author"
                                        className="ml-2 mt-2 rounded-full border-gray-700 border hover:cursor-pointer hover:"
                                    />
                                </div>
                                <div className="flex justify-center items-center aright-0">
                                    <div className="mr-5 mt-1 ">
                                        {userId != getLoggedInUserId() ?
                                            <button onClick={() => following ? unfollowUser() : followUser()}
                                                    className="bg-white text-black
                                        rounded-full w-24 h-[32px] text-sm font-bold
                                        hover:bg-gray-100
                                        ">{following ? "Following" : "Follow"}</button>
                                        : <button
                                                  className="bg-white text-black
                                        rounded-full w-24 h-[32px] text-sm font-bold
                                        hover:bg-gray-100
                                        ">Edit Profile</button>
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="flex mb-1 justify-bet ">
                            <h2 className="font-bold text-lg pl-3">
                                <a className="hover:underline hover:cursor-pointer" >{firstName + ' ' + lastName + ' '}</a>
                            </h2>
                            <a className="text-sm text-gray-500 ml-2" >@{twitterHandle}</a>
                        </div> 
                        <ProfileInformation profileInformation={profileInformation}/>
                        <div className="flex pt-1 text-xs">
                            <div className="flex ml-5 pb-2 hover:cursor-pointer ">
                                <p className="font-bold">{following.length}</p>
                                <p className="text-gray-500 ml-1 hover:text-white hover:underline">Following</p>
                            </div>
                            <div className="flex ml-7 hover:cursor-pointer hover:text-white">
                                <p className="font-bold">{followers.length}</p>
                                <div className="text-gray-500 ml-1 hover:text-white hover:underline ">Followers</div>
                            </div>
                        </div>
                        <div className="flex w-full justify-between">
                            {buttonList.map(button =>  <ButtonFeed button={button} setSelected={setSelected}/> )}
                        </div>
                    </div>
                </div>
            }
            {tweetList}
        </div>

    )
}

export default Profile



