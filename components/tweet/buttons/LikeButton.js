import React from 'react'
import { HeartIcon } from '@heroicons/react/outline';
import { useState, useEffect } from 'react';

const LikeButton = (props) => {
    const [postLiked, setPostLiked] = useState(false)
    const [postLikeCount, setPostLikeCount] = useState(null)

    useEffect(() => {
    setPostLikeCount(props.tweet.likeCount)
    }, []);

    const handleClick = (e) => {
        e.stopPropagation();
        const {id: tweetId} = props.tweet;
        const userId = 1;
        const payload = {
            tweetId: tweetId,
            userId: 1
        }

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        };

        const prepareUrl = (tweetId) => {
            return 'http://localhost:8080/tweet/'+1+'/like';
        }


        fetch(prepareUrl(tweetId), requestOptions)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();
    
                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }
                setPostLiked(data.postLiked)
                setPostLikeCount(data.likeCount)
            })
            .catch(error => {
                console.error('There was an error!', error);
            });

    }     // if post is liked info over post

  return (
    <div>
        <button onClick={(e) => handleClick(e)} className={postLiked === false ? "text-sky-700  hover:text-sky-500 flex" : "text-red-500 flex"}>
             <HeartIcon className="h-5"/>
                <div  className="text-sm">{postLikeCount}</div>
        </button>
    </div>
  )
}

export default LikeButton  