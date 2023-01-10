
// import { signIn, signOut, useSession } from "next-auth/react"

import Image from "next/image";
import SidebarLink from "./SidebarLink";
import {
    BellIcon,
    BookmarkIcon,
    ClipboardIcon,
    HashtagIcon,
    HomeIcon,
    InboxIcon,
    UserIcon
} from "@heroicons/react/24/outline";
import ellipsisHorizontalCircleIcon from "@heroicons/react/20/solid/esm/EllipsisHorizontalCircleIcon";

export default function Sidebar() {
    const { data: session, status } = useSession()
    const loading = status === "loading"

    return (
        <>
        <nav className="hidden sm:flex flex-col
        xl:min-w-[240px] xl:max-w-[360px] p-2 h-full
        ">
            <div className="flex items-center justify-center w-14 h-14
             hoverAnimation p-0">
                <Image src="https://rb.gy/ogau5a" width={30} height={30}/>
            </div>
            <div className="flex-row items-center w-full mt-4 mb-2.5">
                <SidebarLink text="Home" Icon={HomeIcon} active href="/"/>
                <SidebarLink text="Explore" Icon={HashtagIcon} href="/explore"/>
                <SidebarLink text="Notifications" Icon={BellIcon} href="/"/>
                <SidebarLink text="Messages" Icon={InboxIcon} href="/"/>
                <SidebarLink text="Bookmarks" Icon={BookmarkIcon} href="/"/>
                <SidebarLink text="Lists" Icon={ClipboardIcon} href="/"/>
                <SidebarLink text="Profile" Icon={UserIcon} href="/" />
                <SidebarLink text="More" Icon={ellipsisHorizontalCircleIcon} href="/"/>
            </div>
            {/*<button className="hidden xl:inline ml-auto bg-[#1d9bf0] text-[#d9d9d9] */}
            {/*rounded-full w-56 h-[52px] text-lg font-bold shadow-md*/}
            {/*hover:bg-[#1a8cd8]*/}
            {/*">Tweet</button>*/}
            <div className="text-[#d9d9d9] flex items-center
            justify-center hoverAnimation xl:ml-auto xl:mr-32 mt-auto">
              {/*  {!session && (*/}
              {/*      <>*/}
              {/*<span className={""}>*/}
              {/*  You are not signed in*/}
              {/*</span>*/}
              {/*          <a*/}
              {/*              href={`/api/auth/signin`}*/}
              {/*              className={""}*/}
              {/*              onClick={(e) => {*/}
              {/*                  e.preventDefault()*/}
              {/*                  signIn()*/}
              {/*              }}*/}
              {/*          >*/}
              {/*              Sign in*/}
              {/*          </a>*/}
              {/*      </>*/}
              {/*  )}*/}
                {session ?.user && (
                    <>
                    <img src="https://lh3.googleusercontent.com/ogw/ADea4I4WRquKJSLU1R99irO0QpgEKcoOkFcCtdGCo3td=s64-c-mo"
                    className="h-10 w-10 rounded-full xl:mr-2.5"
                    ></img>
                        <div className="hidden xl:inline leading-5">
                            <h4 className="font-bold">FrederikZwartbol</h4>
                            <p className="text-[#6e767d]">@MasterOfTheUniverse</p>
                        </div>
                    <DotsHorizontalIcon className="h-5 hidden xl:inline ml-10"/>
                        </>
                    )}
            </div>
        </nav>
        </>
)}




