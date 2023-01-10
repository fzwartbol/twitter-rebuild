import Link from "next/link";
import Image from "next/image";
import React from "react";

export default function PersonSearchItem({userId, profileImage, firstName, lastName, profileInformation, twitterHandle, following, followers}:
    {userId: number, profileImage: string, firstName: string, lastName: string, profileInformation: any, twitterHandle: string, following: number, followers: number} ) {
    const {profileBio } : {profileBio: string}= profileInformation;

    return (
      <Link href={`/${encodeURIComponent(twitterHandle)}`}>
          <div key={userId} className="p-2 pl-4 border-gray-700 border-b-2 hover:cursor-pointer">
              <div className="flex items-align-center justify-between mt-2">
                  <div className="flex">
                      <div>
                          <Image
                              src={profileImage}
                              width="50" height="50"
                              alt="Picture of the author"
                              className="h-10 w-10 rounded-full cursor-pointer "
                          />
                      </div>
                      <div>
                          <h2 className="font-bold text-sm pl-3">
                              <Link href={`/${encodeURIComponent(twitterHandle)}`}>
                                  <a className="hover:underline" >{`${firstName} ${lastName}`}</a>
                              </Link>
                          </h2>
                          <a className="text-sm text-gray-500 ml-2" >@{twitterHandle}</a>
                          <div className=" flex h-12 text-sm pl-3 pt-2">
                              <p>{profileBio}</p>
                          </div>
                      </div>
                  </div>
                  <div className="flex justify-center items-center aright-0">
                      <div className="mr-5 mt-1 ">
                          {userId != 1 ?
                              <button
                                  // onClick={() => following ?
                                  // unfollowUser() :
                                  // followUser()}
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
      </Link>
  )
}

