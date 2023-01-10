import Tweet from "../tweet/Tweet";
import PersonSearchItem from "./searchItems/PersonSearchItem";
import React from "react";

interface queryData {
    fieldType: string,
    page: number,
    query: string,
    queryTweets: any,
    queryUsers: any,
}
const SearchItems = ({queryData}:{queryData:queryData}) => {
    const users = queryData?.queryUsers?.content;
    const tweets = queryData?.queryTweets?.content;

    if (queryData?.fieldType === "user") {
        return (
            <>
            {users && users.map((user: { userid: number; firstName: string; lastName: string; profileImage: string; profileInformation: any; twitterHandle: string; following: number; followers: number; }, i: any) =>  <PersonSearchItem
                userId={user.userid} firstName={user.firstName} lastName={user.lastName}
                profileImage={user.profileImage}
                profileInformation={user.profileInformation} twitterHandle={user.twitterHandle}
                following={user.following} followers={user.followers}/>
                )}
            </>
    )}

    if (queryData?.fieldType === "user") {
        return (
            <>
                {tweets && tweets.map((tweet: { id: React.Key | null | undefined; }, i: number) => {
                    if (tweets.length === i + 1) return <Tweet key={tweet.id} tweet={tweet}/>
                        return <Tweet key={tweet.id} tweet={tweet}/>
                })}
            </>
        )
    }

    return (
        <></>
    )
}

export default SearchItems