import Link from 'next/link'
import React from 'react'
import {CalendarDaysIcon, LinkIcon, MapPinIcon} from "@heroicons/react/24/outline";

const ProfileInformation = ({profileInformation}) => {
    const { 
        profileHeader,
        // registerDate,
        profileBio, 
        location,
        linkUrl } = profileInformation

    const registerDate = new Date();
  return (
    <div className="text-sm text-gray-300 ml-3">
        {profileHeader && <div>{profileHeader}</div>}
        {profileBio && <div>{profileBio}</div>}
        <div className={"flex pl-1 p-2 text-gray-500 text-sm "}>
            {location &&
                <div className={"flex "}>
                    <MapPinIcon className={"w-5 h-5"}/>
                    <div>{location}</div>
                </div>
            }
            {linkUrl &&
                <div className={"flex pl-2 hover:underline  hover:text-b"}>
                    <LinkIcon className={"w-5 h-5 mr-1"}/>
                    <Link href={linkUrl}>{linkUrl.replace(/(^\w+:|^)\/\//, '')}</Link>
                </div>
            }
            {registerDate &&
                <div className={"pl-2 flex "}>
                    <CalendarDaysIcon className={"w-5 h-5 mr-2"}/>
                    <div>Joined on {registerDate.toLocaleDateString('en-GB',{month: 'long' ,year: 'numeric'})}</div>
                </div>
            }
        </div>
    </div>
  )
}

export default ProfileInformation