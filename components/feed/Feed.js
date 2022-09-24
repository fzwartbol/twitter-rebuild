import React from 'react'
import { SparklesIcon } from "@heroicons/react/outline";
import Input from "../Input";
import Tweet from "../tweet/Tweet";
import {useState, useRef, useCallback} from 'react';
import useFeed from "../hooks/useFeed"
import Spinner from "../spinner/Spinner"
import useAuth from '../hooks/useAuth'

const Feed = () => {
    const [tweetCallbackData, setTweetCallbackData] = useState("");
    const [pageNum, setPageNum] = useState(0)
    const {isLoading,
        isError,
        error,
        results,
        hasNextPage
    } = useFeed(pageNum)

    
    const { auth } = useAuth();
    console.log(auth)
    console.log(auth?.accessToken)

    const intObserver = useRef()
    const lastPostRef = useCallback( tweet => {
        if(isLoading) return
        if(intObserver.current) intObserver.current.disconnect()
        intObserver.current = new IntersectionObserver( tweets => {
            if( tweets[0].isIntersecting && hasNextPage) {
                setPageNum(prev => prev +1)
            }
        })
        if(tweet) intObserver.current.observe(tweet)

    },[isLoading, hasNextPage])

    if (isError) return <p className="center">Error: {error.message}</p>

    const tweets = results
        .map((tweet, i) => {
            if (results.length === i+1) return <Tweet ref={lastPostRef} key={tweet.id} tweet={tweet}/>
                return <Tweet  key={tweet.id} tweet={tweet} />
            })  
                  
    return (
        <div className="text-white flex-grow border-l border-r border-gray-700 max-w-2xl sm:ml-[73px] xl:ml-[370px]">
           <div className="text-[#d9d9d9] flex items-center
           sm:justify-between py-2 px-3 sticky top-0 z-50 bg-black border-b border-gray-700">
               <h2 className="text-lg sm:text-xl font-bold">Home</h2>
               <div className="hoverAnimation w-9 h-9 flex items-center justify-center xl:px-0 ml-auto">
                   <SparklesIcon className="h-5 text-white" />
               </div>
           </div>
           <Input passChildData={setTweetCallbackData} />
           {tweetCallbackData && <Tweet tweet = {tweetCallbackData} />}
           {tweets}
           {isLoading && <Spinner />}
        </div>
    )
}

export default Feed

// export async function getServerSideProps(context) {
//     // const { twitterHandle} = context.query;
//     // const userProfile =  await getTwitterHandle(twitterHandle)
  
//     return {
//       props: {userProfile: userProfile}, 
//     }
//   }
