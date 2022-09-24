import Link from 'next/link'
import React from 'react'


const ProfileInformation = ({profileInformation}) => {
    const { 
        profileHeader, 
        profileBio, 
        location,
        linkUrl } = profileInformation
        
  return (
    <div className="text-xs"> 
        {profileHeader && <div className="pl-3 text-sm text-gray-300">{profileHeader}</div>}   
        {profileBio && <div className="pl-3 text-sm text-gray-300">{profileBio}</div>}
        {location && <div className="pl-3 text-sm text-gray-300">{location}</div>} 
        {linkUrl &&  <div className="pl-3 text-sm text-gray-300">link: 
        <Link href={linkUrl} target="_blank" className="text-xs text-blue-500 text-underline">{linkUrl}</Link></div>}                        
    </div>
  )
}

export default ProfileInformation