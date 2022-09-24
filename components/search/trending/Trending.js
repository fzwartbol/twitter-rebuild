import React, { useEffect, useState } from 'react'
import { getTrendingHashtags } from '../../../api/axios';

const Trending = ({setSearchQuery}) => {
    const [hashtags, setHashtags] = useState();

    const returnSearchQuery = (data) => {
        setSearchQuery(data)
    }

    useEffect(() => {
        getTrendingHashtags()
        .then(data =>  {
            setHashtags(data) })
        },[]);

    const hashtagDiv = hashtags && hashtags.map(hashtag =>  { 
        return (<div className="p-2 hover:cursor-pointer ">
        <p onClick = { e => returnSearchQuery(e.currentTarget.textContent)} className="font-bold p-1 hover:underline hover:text-gray-200">#{hashtag.hashtagMessage}</p>
        <p className="text-xs text-gray-500 pl-1">{hashtag.hashtagCount} Tweets</p>
        </div>
        )})

    return (
        <div className="border-gray-700 border-b border-t h-92 pt-4 pb-4">
            <h className="font-bold text-xl p-2">Trending</h>
            {hashtagDiv}
        </div>
    )
}

export default Trending;
