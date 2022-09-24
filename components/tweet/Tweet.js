import React from 'react'
import Image from 'next/image'
import { ChatIcon, RefreshIcon, HeartIcon, UploadIcon} from '@heroicons/react/outline';
import { dotsHorizontalIcon } from '@heroicons/react/solid';
import { timeSince } from '../../utils/dateconverter';
import LikeButton from './buttons/LikeButton';
import Link from 'next/link'


const Tweet = React.forwardRef (({tweet}, ref) => {
    const convertedDate = timeSince(new Date(tweet.publicationDate))
  return (
    <Link href={`/${encodeURIComponent(tweet.user.twitterHandle)}/${encodeURIComponent(tweet.id)}`}>
    <div onClick={() => console.log('click')} key={tweet.id} className="p-2 pl-4 border-gray-700 border-b-2 hover:cursor-pointer">
        <div className="flex items-align-center justify-between mt-2">
            <div className="flex">
                <Image 
                src={tweet.user.profileImage}
                width="50" height="50"
                alt="Picture of the author"
                    className="h-10 w-10 rounded-full cursor-pointer "
                    />
                <div >    
                    <h2 className="font-bold pl-3">
                        <Link href={`/${encodeURIComponent(tweet.user.twitterHandle)}`}>
                        <a className="hover:underline" >{`${tweet.user.firstName} ${tweet.user.lastName} `}</a>
                        </Link>
                    </h2>
                    <a className="font-normal text-gray-500 ml-2" >@{tweet.user.twitterHandle}  · </a>
                    <a className="text-xs text-center content-align justify-center text-gray-500">{convertedDate} ago</a>
                </div>
            </div>
        </div>
        {tweet.parentTweet && 
        <p className="text-s text-gray-500 ml-2 hover:underline">replying to @{tweet.parentTweet.user.twitterHandle}</p>
        }
        <div className=" flex h-12 text-sm  pt-2">
            <p>{tweet.message}</p>
        </div>
        <div className="flex">
        {tweet.hashtags.map((hashtag) => <p key={hashtag} className="text-sm "> #{hashtag.message}</p>)}
        </div>
        <div className="flex justify-between w-full pt-2 pl-6 pr-6">
            <div className="">
                <button className="flex text-sky-700 hover:text-sky-500">
                    <ChatIcon className=" h-5"/>
                    <div className="text-sm pl-2 ">{tweet.replyCount}</div>
                </button>
            </div>
            <div>
                <button className="flex text-sky-700 hover:text-sky-500">
                    <RefreshIcon className="text-sky-700 h-5"/>
                </button>
            </div>
            <div>
                <LikeButton tweet={tweet} />
            </div>
            <div>
                <button>
                    <UploadIcon className="text-sky-700 h-5"/>
                </button>
            </div>
            {/** reference callback component for infite scroll */}
            { ref ? <p ref={ref}></p> : <p></p> }
        </div>
    </div>
    </Link>

  )
})

export default Tweet
