import React, {useEffect, useState} from 'react';
import Tweet from './Tweet'
import { useRouter } from "next/router";
import InputReply from '../InputReply'
import {getTweet} from "../../api/axios";
import {SparklesIcon} from "@heroicons/react/24/outline";

const TweetDetailPage = () => {
    const [replies, setReplies] = useState(null)
    const [tweetData, setTweetData ] = useState(null)
    const router = useRouter();
    const [childData, setChildData] = useState("");

    useEffect(() => {  
      if(!router.isReady) return;

      getTweet(router.query.tweet)
            .then((result) => {
                setTweetData(result)
                setReplies(result.replies)
              })
            .catch((err) => { console.log(err)});
        }, [router.isReady]);

  return (
      <div className={"w-full"}>
            <div className="text-[#d9d9d9] flex items-center
            sm:justify-between py-2 px-3 sticky top-0 z-50 bg-black border-b border-gray-700">
                <h2 className="text-lg sm:text-xl font-bold">Tweet</h2>
                <div className="hoverAnimation w-9 h-9 flex items-center justify-center xl:px-0 ml-auto">
                    <SparklesIcon className="h-5 text-white" />
                </div>
            </div>
            {tweetData && <Tweet tweet={tweetData}/>}
            <div>
              <InputReply passChildData={setChildData} />
           {childData && <Tweet tweet = {childData} />}
            </div>
            <div>
            {replies && replies.map((reply) => <Tweet key= {reply.id} tweet={reply}/>
            )}
            </div>
      </div>
  )
}

export default TweetDetailPage