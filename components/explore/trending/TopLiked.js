import React, {useState, useEffect} from 'react'
import { getTopLiked } from '../../../api/axios';
import Tweet from '../../tweet/Tweet'

const TopLiked = () => {
    const [tweets, setTweets] = useState();

    useEffect(() => {
       getTopLiked().then(data => {
        setTweets(data)})
        },[]);

    const topLikedtweets = tweets && tweets.map(tweet =>
        <Tweet  key={tweet.id} tweet={tweet} />)

      return (
        <div className="border-gray-700 border-b border-t h-92 pt-4 pb-4">
            <h className="font-bold text-xl p-2 mb-4 ">Top Liked Posts</h>
            {topLikedtweets}
        </div>
      )
    }

export default TopLiked