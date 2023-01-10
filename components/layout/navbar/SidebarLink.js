import React from 'react'
import Link from 'next/link';

export default function SidebarLink({text, Icon, active, href}) {
    return (
        <Link href={href}>
            <div className={`text-[#d9d9d9] flex items-center justify-center
            xl:justify-start space-x-2 hoverAnimation ${active && "font-bold"}`}>
                <Icon className="h-7 text-white"/>
                <span className="hidden text-lg xl:inline">{text}</span>
            </div>
        </Link>
    )
}


