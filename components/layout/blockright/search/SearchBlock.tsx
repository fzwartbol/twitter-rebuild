import React from "react";
import {MagnifyingGlassIcon} from "@heroicons/react/20/solid";

export default function SearchBlock() {

  return (
                <div className="bg-[#1d1f23] align-top p-3 flex rounded-full ">
                    <label htmlFor="header-search">
                        <span className="hidden">Search Tweet</span>
                    </label>
                    <MagnifyingGlassIcon className="h-5 w-5 text-gray-500 ml-3 mr-4 " aria-hidden="true" />
                    <input
                        type="text"
                        // value={searchQuery}
                        // onInput={e => returnSearchQuery(e.target.value)}
                        id="header-search"
                        placeholder="Search on Twitter"
                        className="bg-[#1d1f23] border-none outline-none align-text-top "
                    />
                </div>

  )
}