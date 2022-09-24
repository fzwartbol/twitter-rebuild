
import { signIn, signOut, useSession } from "next-auth/react"

import Image from "next/image";
import SidebarLink from "./SidebarLink";
import { HomeIcon } from "@heroicons/react/solid";
import {
  HashtagIcon,
  BellIcon,
  InboxIcon,
  BookmarkIcon,
  ClipboardListIcon,
  UserIcon,
  DotsCircleHorizontalIcon,
  DotsHorizontalIcon,
} from "@heroicons/react/outline";

export default function Sidebar() {
    const { data: session, status } = useSession()
    const loading = status === "loading"

    return (
        <>
        {/*{session ?.user && (*/}
        <nav className="hidden sm:flex flex-col items-center xl:items-start
        xl:w-[340px] p-2 fixed h-full
        ">
            <div className="flex items-center justify-center w-14 h-14
             hoverAnimation p-0 xl:ml-24">
                <Image src="https://rb.gy/ogau5a" width={30} height={30}/>
            </div>
            <div className="space-y-2.5 mt-4 mb-2.5 xl:ml-24">
            <SidebarLink text="Home" Icon={HomeIcon} active href="/"/>
            <SidebarLink text="Explore" Icon={HashtagIcon} href="/explore"/>
            <SidebarLink text="Notifications" Icon={BellIcon} href="/"/>
            <SidebarLink text="Messages" Icon={InboxIcon} href="/"/>
            <SidebarLink text="Bookmarks" Icon={BookmarkIcon} href="/"/>
            <SidebarLink text="Lists" Icon={ClipboardListIcon} href="/"/>
            <SidebarLink text="Profile" Icon={UserIcon} href="/" />
            <SidebarLink text="More" Icon={DotsCircleHorizontalIcon} href="/"/>
            </div>
            <button className="hidden xl:inline ml-auto bg-[#1d9bf0] text-[#d9d9d9] 
            rounded-full w-56 h-[52px] text-lg font-bold shadow-md
            hover:bg-[#1a8cd8]
            ">Tweet</button>
            <div className="text-[#d9d9d9] flex items-center 
            justify-center hoverAnimation xl:ml-auto xl:-mr-5 mt-auto">
                <img src="https://lh3.googleusercontent.com/ogw/ADea4I4WRquKJSLU1R99irO0QpgEKcoOkFcCtdGCo3td=s64-c-mo"
                className="h-10 w-10 rounded-full xl:mr-2.5"
                ></img>
                <div className="hidden xl:inline leading-5">
                    <h4 className="font-bold">FrederikZwartbol</h4>
                    <p className="text-[#6e767d]">@MasterOfTheUniverse</p>
                </div>
                <DotsHorizontalIcon className="h-5 hidden xl:inline ml-10"/>
            </div>
        </nav>
    {/*)}*/}
        </>
)}




